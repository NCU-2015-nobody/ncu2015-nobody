package com.nobody.cdc;

import java.awt.*;

public class Character {
    public int clientNumber;
    public Point position;
    public int direction;
    public int HP;
    public boolean state;
    public boolean attackState;

    public Character(int clientNumber) {
        this.clientNumber = clientNumber;
        this.position.setLocation(0, 0);//initial????
        this.direction = 0;//initial?????
        this.HP = 100;//initial????
        this.state = true;
        this.attackState = false;
    }
}
