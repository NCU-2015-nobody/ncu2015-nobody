import java.awt.*;

public class Monster {
    public int objectID;
    public Point position;
    public int direction;
    public int HP;
    public boolean isBoss;
    public boolean state;

    public Monster(int objectID, Point position) {
        this.objectID = objectID;
        this.position.setLocation(position);
        this.direction = 0;//initial?????

        if (objectID < 10){//boss
            this.isBoss = true;
            this.HP = 100;//initial????
        }
        else {//monster
            this.isBoss = false;
            this.HP = 100;//initial????
        }

        this.state = true;
    }
}
