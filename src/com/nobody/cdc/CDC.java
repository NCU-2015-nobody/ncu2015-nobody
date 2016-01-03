package com.nobody.cdc;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import com.nobody.cdcsdm.cdcsdm;

public class CDC {
	cdcsdm cdcsdm;
	public ArrayList<Character> characterList;
	public ArrayList<Monster> monsterList;
	private int[][] skillTable;// 0, 1戰士, 2牧師, 3法師, 4弓箭手, 5魔王, 6怪物

	public CDC() {
		cdcsdm = new cdcsdm();
		characterList = new ArrayList<Character>(4);
		monsterList = new ArrayList<Monster>();
		skillTable = new int[][] { { 0, 0 }, { 1, 100 }, { 3, -50 }, { 5, 200 }, { 8, 150 }, { 0, 50 }, { 1, 100 } };// {攻擊範圍,
																														// 傷害值}

		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		try {
			list = loadMap("MonsterMap.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("loadMap is wrong. CDC");
			e.printStackTrace();
		}

		Monster monster;
		Point position = new Point();
		for (int n = 0; n < list.size(); n++) {
			position.setLocation(list.get(n).get(0), list.get(n).get(1));
			addMonster(position) ;
		}
	}

	public void addVirtualCharacter(int clientID) throws ExceedMaxException {
		assert !clientIDExist(clientID) : "clientID already exists";// 要改成if!!!!!
		System.out.println(clientID + ": add Character");
		if (characterList.size() == 4) {// at most 4 characters
			throw new ExceedMaxException();
		}

		Character character = new Character(clientID);
		characterList.add(character);
	}

	public void addMonster(Point position) {
		assert !monsterPositionTaken(position) : "position taken by another monster";

		int curCount = monsterList.size();
		Monster monster = new Monster(curCount + 1, position);
		monsterList.add(monster);
	}

	public void updateCharacterStatus(int clientID, int newDirection) {
		assert clientIDExist(clientID) : "clientID does not exist";
		assert (0 <= newDirection && newDirection < 4) : "invalid moveCode";

		Character character = getCertainCharacter(clientID);
		Point newPosition = getCertainPosition(character.position, newDirection, 1);
		System.out.println("clientID="+clientID+",character.position="+character.position+",newDirection"+newDirection);
		boolean isObstacle = !cdcsdm.checkObstacle(character.position, newDirection, 1).get(0);
		boolean isTrap = cdcsdm.checkTrap(newPosition);
		if (!isObstacle) {// position ahead is not obstacle
			character.position.setLocation(newPosition);
			character.direction = newDirection;

			if (isTrap) {// position ahead is trap
				character.HP = character.HP - 50;
			}
			System.out.println("Character change to true");
			character.state = true;
		}
	}

	public void updateMonsterThread() throws InterruptedException {
		UpdateThread updateThread = new UpdateThread(cdcsdm, characterList, monsterList);

		Thread thread = new Thread(updateThread);
		thread.start();
	}

	public void characterAttack(int clientID) {
		assert clientIDExist(clientID) : "clientID does not exist";

		int attackRange = skillTable[clientID][0];
		Character character = getCertainCharacter(clientID);
		Point characterPosition = character.position;

		if (clientID == 2) {// 牧師
			Iterator<Character> charIterator = characterList.iterator();
			while (charIterator.hasNext()) {
				Character checkCharacter = charIterator.next();
				Point checkCharPosition = checkCharacter.position;
				if (characterPosition.getX() - 25 <= checkCharPosition.getX()
						&& checkCharPosition.getX() <= characterPosition.getX() + 25
						&& characterPosition.getY() - 25 <= checkCharPosition.getY()
						&& checkCharPosition.getY() <= characterPosition.getY() + 25) {
					checkCharacter.HP = checkCharacter.HP - skillTable[clientID][1];
					checkCharacter.state = true;
				}
			}
		} else {
			ArrayList<Boolean> isObstacle = cdcsdm.checkObstacle(character.position, character.direction, attackRange);
			for (int i = 1; i <= attackRange; i++) {
				Point checkPosition = getCertainPosition(characterPosition, character.direction, i);
				Monster checkMonster = getPositionMonster(checkPosition);
				if (checkMonster != null) {
					checkMonster.HP = checkMonster.HP - skillTable[clientID][1];
					checkMonster.state = true;
					break;
				} else if (isObstacle.get(i - 1)) {// attack failed
					break;
				}
			}
		}
	}

	public Vector<Object> getUpdateInfo() {
		Vector<Object> updatedInfo = new Vector<Object>();
		for (Character character : characterList) {
			if (character.state) {
				System.out.println("cdc update character=" + character.position);
				updatedInfo.add(character);
				character.state = false;
				character.attackState = false;
			}
		}

		Iterator<Monster> monsterIterator = monsterList.iterator();
		while (monsterIterator.hasNext()) {
			Monster monster = monsterIterator.next();
			if (monster.state) {
				updatedInfo.add(monster);
				monster.state = false;
			}
		}

		return updatedInfo;
	}

	private boolean clientIDExist(int clientID) {
		Iterator<Character> charIterator = characterList.iterator();
		while (charIterator.hasNext()) {
			Character character = charIterator.next();
			if (character.clientID == clientID) {
				return true;
			}
		}
		return false;
	}

	private Character getCertainCharacter(int clientID) {
		Iterator<Character> charIterator = characterList.iterator();
		while (charIterator.hasNext()) {
			Character character = charIterator.next();
			if (character.clientID == clientID) {
				return character;
			}
		}
		throw new AssertionError("com.nobody.Character with clientID cannot be found");
	}

	private boolean monsterPositionTaken(Point position) {
		Iterator<Monster> monsterIterator = monsterList.iterator();
		while (monsterIterator.hasNext()) {
			Monster monster = monsterIterator.next();
			if (monster.position == position) {
				return true;
			}
		}
		return false;
	}

	private Monster getPositionMonster(Point position) {
		Iterator<Monster> monsterIterator = monsterList.iterator();
		while (monsterIterator.hasNext()) {
			Monster monster = monsterIterator.next();
			if (monster.position == position) {
				return monster;
			}
		}
		return null;// no monster at the position
	}

	private Point getCertainPosition(Point position, int direction, int distance) {
		switch (direction) {
		case 0:// left
			position.setLocation(position.getX() - 25 * distance, position.getY());
			break;
		case 1:// up
			position.setLocation(position.getX(), position.getY() - 25 * distance);
			break;
		case 2:// right
			position.setLocation(position.getX() + 25 * distance, position.getY());
			break;
		case 3:// down
			position.setLocation(position.getX(), position.getY() + 25 * distance);
			break;
		}
		return position;
	}

	private static ArrayList loadMap(String mapfile) throws IOException {
		ArrayList<ArrayList<Integer>> columnList = new ArrayList<ArrayList<Integer>>();

		FileReader fr = new FileReader("./map/" + mapfile);
		BufferedReader br = new BufferedReader(fr);
		while (br.ready()) {
			String line = br.readLine();
			ArrayList<Integer> rowList = new ArrayList<Integer>();
			String[] AfterSplit = line.split(" ");
			rowList.add(Integer.valueOf(AfterSplit[0]));
			rowList.add(Integer.valueOf(AfterSplit[1]));
			columnList.add(rowList);
		}

		fr.close();

		return columnList;

	}
}
