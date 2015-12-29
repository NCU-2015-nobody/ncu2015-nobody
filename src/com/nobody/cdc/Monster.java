package com.nobody.cdc;

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

        if (objectID < 9){//boss
            this.isBoss = true;
            this.HP = 5000;
        }
        else {//monster
            this.isBoss = false;
            this.HP = 200;
        }

        this.state = true;
    }
}
