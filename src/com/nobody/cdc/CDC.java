import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class CDC {
    CDCSDM cdcsdm;
    private static ArrayList<Character> characterList;
    private static ArrayList<Monster> monsterList;
    private static int[][] skillTable;//0, 1戰士, 2牧師, 3法師, 4弓箭手, 5魔王, 6怪物

    public CDC(){
        characterList = new ArrayList<Character>(4);
        monsterList = new ArrayList<Monster>();
        skillTable = new int[][]{{0,0}, {1,20}, {3,-20}, {5, 20}, {8,20}, {5,20}, {1, }};//{攻擊範圍, 傷害值}
    }

    public void addVirtualCharacter(int clientNumber) throws ExceedMaxException {
        assert !clientNumberExist(clientNumber) : "clientNumber already exists";

        if (characterList.size() == 4){//at most 4 characters
            throw new ExceedMaxException();
        }

        Character character = new Character(clientNumber);
        characterList.add(character);
    }

    public void addMonster(Point position){
        assert !monsterPositionTaken(position) : "position taken by another monster";

        int curCount = monsterList.size();
        Monster monster = new Monster(curCount+1, position);
        monsterList.add(monster);
    }

    public void updateCharacterStatus(int clientNumber, int newDirection){
        assert clientNumberExist(clientNumber) : "clientNumber does not exist";
        assert (0<=newDirection && newDirection<4) : "invalid moveCode";

        Character character = getCertainCharacter(clientNumber);
        Point newPosition = getCertainPosition(character.position, newDirection, 1);
        boolean isObstacle = cdcsdm.checkObstacle(character.position, newDirection, 1).get(0);
        boolean isTrap = cdcsdm.checkTrap(newPosition);
        if (!isObstacle && !isTrap){//position ahead is not obstacle/trap
            character.position.setLocation(newPosition);
            character.state = true;
        }
        else if (!isObstacle && isTrap){//position ahead is not obstacle, is trap
            character.position.setLocation(newPosition);
            character.HP = character.HP - 10;//trap 扣血?????????
            character.state = true;
        }
    }

    public void updateMonsterThread() throws InterruptedException {
        UpdateThread updateThread = new UpdateThread(cdcsdm, characterList, monsterList);

        Thread thread = new Thread(updateThread);
        thread.start();
    }

    public void characterAttack(int clientNumber){
        assert clientNumberExist(clientNumber) : "clientNumber does not exist";

        int attackRange = skillTable[clientNumber][0];
        Character character = getCertainCharacter(clientNumber);
        Point characterPosition = character.position;

        if (clientNumber == 2){//牧師
            Iterator<Character> charIterator = characterList.iterator();
            while (charIterator.hasNext()) {
                Character checkCharacter = charIterator.next();
                Point checkCharPosition = checkCharacter.position;
                if (characterPosition.getX() - 25 <= checkCharPosition.getX() && checkCharPosition.getX() <= characterPosition.getX() + 25
                        && characterPosition.getY() - 25 <= checkCharPosition.getY() && checkCharPosition.getY() <= characterPosition.getY() + 25) {
                    checkCharacter.HP = checkCharacter.HP - skillTable[clientNumber][1];
                    checkCharacter.state = true;
                }
            }
        }

        ArrayList<Boolean> isObstacle = cdcsdm.checkObstacle(character.position, character.direction, attackRange);
        for (int i=1; i<=attackRange; i++){
            Point checkPosition = getCertainPosition(characterPosition, character.direction, i);
            Monster checkMonster = getPositionMonster(checkPosition);
            if (checkMonster != null){
                checkMonster.HP = checkMonster.HP - skillTable[clientNumber][1];
                checkMonster.state = true;
                break;
            }
            else if (isObstacle.get(i-1)){//attack failed
                break;
            }
        }
    }

    public Vector<Object> getUpdateInfo(){
        Vector<Object> updatedInfo = new Vector<Object>();

        for (Character character : characterList) {
            if (character.state) {
                updatedInfo.add(character);
                character.state = false;
                character.attackState = false;
            }
        }

        Iterator<Monster> monsterIterator = monsterList.iterator();
        while (monsterIterator.hasNext()){
            Monster monster = monsterIterator.next();
            if (monster.state){
                updatedInfo.add(monster);
                monster.state = false;
            }
        }

        return updatedInfo;
    }

    private boolean clientNumberExist(int clientNumber){
        Iterator<Character> charIterator = characterList.iterator();
        while (charIterator.hasNext()) {
            Character character = charIterator.next();
            if (character.clientNumber == clientNumber) {
                return true;
            }
        }
        return false;
    }

    private Character getCertainCharacter(int clientNumber){
        Iterator<Character> charIterator = characterList.iterator();
        while (charIterator.hasNext()) {
            Character character = charIterator.next();
            if (character.clientNumber == clientNumber) {
                return character;
            }
        }
        throw new AssertionError("com.nobody.Character with clientNumber cannot be found");
    }

    private boolean monsterPositionTaken(Point position) {
        Iterator<Monster> monsterIterator = monsterList.iterator();
        while (monsterIterator.hasNext()){
            Monster monster = monsterIterator.next();
            if (monster.position == position){
                return true;
            }
        }
        return false;
    }

    private Monster getPositionMonster(Point position){
        Iterator<Monster> monsterIterator = monsterList.iterator();
        while (monsterIterator.hasNext()){
            Monster monster = monsterIterator.next();
            if (monster.position == position){
                return monster;
            }
        }
        return null;//no monster at the position
    }

    private Point getCertainPosition(Point position, int direction, int distance){
        switch (direction){
            case 0://left
                position.setLocation(position.getX()-25*distance, position.getY());
                break;
            case 1://up
                position.setLocation(position.getX(), position.getY()-25*distance);
                break;
            case 2://right
                position.setLocation(position.getX()+25*distance, position.getY());
                break;
            case 3://down
                position.setLocation(position.getX(), position.getY()+25*distance);
                break;
        }
        return position;
    }
}
