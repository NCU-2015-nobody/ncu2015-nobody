package com.nobody.cdc;

import java.awt.*;

public class Character {
    public int clientID;
    public Point position;
    public int direction;
    public int HP;
    public boolean state;
    public boolean attackState;

    public Character(int clientID) {
        this.clientID = clientID;

        switch (clientID){
            case 1://戰士
                this.position.setLocation(0, 0);//initial????
                this.direction = 0;//initial?????
                this.HP = 500;
                break;

            case 2://牧師
                this.position.setLocation(0, 0);//initial????
                this.direction = 0;//initial?????
                this.HP = 300;
                break;

            case 3://法師
                this.position.setLocation(0, 0);//initial????
                this.direction = 0;//initial?????
                this.HP = 200;
                break;

            case 4://弓箭手
                this.position.setLocation(0, 0);//initial????
                this.direction = 0;//initial?????
                this.HP = 250;
                break;
        }

        this.state = true;
        this.attackState = false;
    }
}
