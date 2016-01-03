package com.nobody.dom;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

import com.nobody.tcpcm.Client;

public class DOM {
	information info = new information();
	private Client client;
	
	
	public DOM(Client client)
	{
		this.client = client;
	}
	
	
	public void addVirtualCharacter(int clientNumber, int currentX, int currentY, int dir, int health) {
		System.out.println(
				"dom: add chara :" + clientNumber + ", " + currentX + ", " + currentY + ", " + dir + ", " + health);
				
		Point p = new Point();
		p.setLocation(currentX, currentY);
		System.out.println("p = " + p.getX() + "," + p.getY());

		info.clientNumberList.add(clientNumber);
		info.before_xyList.add(null);
		info.current_xyList.add(p);
		info.dirList.add(dir);
		info.healthList.add(health);

		switch (clientNumber) {
		case 1:
			info.maxHP[0] = health;
			break;
		case 2:
			info.maxHP[1] = health;
			break;
		case 3:
			info.maxHP[2] = health;
			break;
		case 4:
			info.maxHP[3] = health;
			break;
		}
	}

	public void addMonster(int objectID, int currentX, int currentY, int dir, int health) {
		System.out
				.println("dom: add mon :" + objectID + ", " + currentX + ", " + currentY + ", " + dir + ", " + health);
		Point current_xy = new Point();
		current_xy.setLocation(currentX, currentY);

		info.objectIDList.add(objectID);
		info.before_xy_monsterList.add(null);
		info.current_xy_monsterList.add(current_xy);
		info.dir_monsterList.add(dir);
		info.health_monsterList.add(health);
	}

	public void updateVirtualCharacter(int clientNumber, int currentX, int currentY, int dir, int health) {
		System.out.println(
				"dom: update chara :" + clientNumber + ", " + currentX + ", " + currentY + ", " + dir + ", " + health);
		int index = -1;
		Point current_xy = new Point();
		current_xy.setLocation(currentX, currentY);

		index = info.clientNumberList.indexOf(clientNumber);

		if (index == -1) {
			addVirtualCharacter(clientNumber, currentX, currentY, dir, health);
			index = info.clientNumberList.size() - 1;
		}

		info.before_xyList.set(index, info.current_xyList.get(index)); // �x�s��s�e���y��

		info.current_xyList.set(index, current_xy);
		info.dirList.set(index, dir);
		info.healthList.set(index, health);

	}

	public void updateMonsterStatus(int objectID, int currentX, int currentY, int dir, int health) {
		System.out.println(
				"dom: update mon :" + objectID + ", " + currentX + ", " + currentY + ", " + dir + ", " + health);
		int indexM = -1;
		Point current_xy = new Point();
		current_xy.setLocation(currentX, currentY);

		indexM = info.objectIDList.indexOf(objectID);

		if (indexM == -1) {
			addMonster(objectID, currentX, currentY, dir, health);
			indexM = info.objectIDList.size() - 1;
		}

		info.before_xy_monsterList.set(indexM, info.current_xy_monsterList.get(indexM)); // �x�s��s�e���y��

		info.current_xy_monsterList.set(indexM, current_xy);
		info.dir_monsterList.set(indexM, dir);
		info.health_monsterList.set(indexM, health);

	}

	public boolean CDTimer() ///////// not done
	{
		final int timeInterval = 1000;

		if (info.CD == 0) {
			info.CD = 3;
			Thread t = new Thread(new Runnable() {
				public void run() {

					try {
						Thread.sleep(timeInterval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					info.CD = 0;
				}
			});
			t.start();
			return true;
		} else {
			return false;
		}

	}

	public Vector getAllDynamicObjects() {
		Vector v1 = new Vector();

		for (int i = 0; i < info.clientNumberList.size(); i++) {
			v1.add(info.clientNumberList.get(i));
			v1.add(info.before_xyList.get(i));
			v1.add(info.current_xyList.get(i));
			v1.add(info.dirList.get(i));
			v1.add(info.healthList.get(i));
		}
		
		for (int i = 0; i < info.objectIDList.size(); i++) {
			v1.add(info.objectIDList.get(i));
			v1.add(info.before_xy_monsterList.get(i));
			v1.add(info.current_xy_monsterList.get(i));
			v1.add(info.dir_monsterList.get(i));
			v1.add(info.health_monsterList.get(i));
		}

		return v1;
	}

	public int[] mainCharacterInfo() {
		int[] character = new int[3];
		int self_no = client.character();
		int index = info.clientNumberList.indexOf(self_no);
		
		System.out.println("num: " + info.clientNumberList.size());
		System.out.println("index: " + index);
		System.out.println("size: " + info.dirList.size());
		
		if (info.CD > 0) {
			character[0] = 0; // 0 for no attack; 1 for attack
		} else {
			character[0] = 1;
		}

		character[1] = info.dirList.get(index);
		character[2] = self_no;

		return character;
	}

	public Point getVirtualCharacterXY() {
		Point p = new Point();

		int self_no = client.character();
		
		System.out.println("info.clientNumberList.size()=" + info.clientNumberList.size());
		
		for (int i = 0; i < info.clientNumberList.size(); i++) {
			if (self_no == info.clientNumberList.get(i)) {
				p.setLocation(info.current_xyList.get(i));
				System.out.println("get XY point=" + p.getX() + "," + p.getY());
			}
		}

		return p;
	}

	public int[] getVirtualCharactersHP() {
		int[] percentageHP = new int[4];

		for (int i = 0; i < info.healthList.size(); i++) {
			switch (info.clientNumberList.get(i)) {
			case 1:
				percentageHP[0] = (info.healthList.get(i) / info.maxHP[0]) * 100;
				break;
			case 2:
				percentageHP[1] = (info.healthList.get(i) / info.maxHP[1]) * 100;
				break;
			case 3:
				percentageHP[2] = (info.healthList.get(i) / info.maxHP[2]) * 100;
				break;
			case 4:
				percentageHP[3] = (info.healthList.get(i) / info.maxHP[3]) * 100;
				break;
			}
		}

		return percentageHP;
	}

	public boolean[] getVirtualCharactersCD() {
		boolean[] boolCD = { false, false, false, false };
		int self_no = client.character() - 1;

		if (info.CD > 0)
			boolCD[self_no] = false;
		else
			boolCD[self_no] = true;

		return boolCD;
	}

	public class information {
		public ArrayList<Integer> clientNumberList = new ArrayList<Integer>();
		public ArrayList<Point> before_xyList = new ArrayList<Point>();
		public ArrayList<Point> current_xyList = new ArrayList<Point>();
		public ArrayList<Integer> dirList = new ArrayList<Integer>();
		public ArrayList<Integer> healthList = new ArrayList<Integer>();

		public ArrayList<Integer> objectIDList = new ArrayList<Integer>();
		public ArrayList<Point> before_xy_monsterList = new ArrayList<Point>();
		public ArrayList<Point> current_xy_monsterList = new ArrayList<Point>();
		public ArrayList<Integer> dir_monsterList = new ArrayList<Integer>();
		public ArrayList<Integer> health_monsterList = new ArrayList<Integer>();

		public int CD;
		public int[] maxHP = new int[4];
	}
}
