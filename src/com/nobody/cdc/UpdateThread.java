package com.nobody.cdc;

import java.util.ArrayList;
import java.util.Vector;
import com.nobody.cdcsdm.cdcsdm;

public class UpdateThread implements Runnable{
    cdcsdm cdcsdm;
    ArrayList<Character> characterList;
    ArrayList<Monster> monsterList;

    public UpdateThread(cdcsdm cdcsdm, ArrayList<Character> characterList, ArrayList<Monster> monsterList){
        this.cdcsdm = cdcsdm;
        this.characterList = characterList;
        this.monsterList = monsterList;
    }

    public void run() {
        while(true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("updateThread here.");
			
            for(Monster monster: monsterList){
                Vector<Double> distance = new Vector <Double>() ;
                Vector<Character> characterDetect = new Vector <Character>() ;//�����Ǫ��b������������Ө����
               
                for(Character character : characterList){

                    double distanceX = Math.abs(character.position.getX() - monster.position.getX()) ; //�����
                    double distanceY = Math.abs(character.position.getY() - monster.position.getY()) ;
                    System.out.println("distanceX and distanceY:  "+ distanceX + "---" + distanceY);
                    if( distanceX<250 && distanceY<400 ){//if monster is in the window of the character
                        distance.add(distanceX*distanceX+distanceY*distanceY) ;
                        characterDetect.add(character) ;
                        
                         //Boss attack
                        if(monster.isBoss==true){
                        	character.HP = character.HP-50 ;
                        	character.state = true ;
                        	
                        }//Boss attack end
                        
                    }
                    
                    if((distanceX + distanceY)==25){//�P�w����b�Ǫ�����h�l��
                    	character.HP = character.HP-25 ;
                    	character.state = true ;
                    }       
                    
                }

                //find out the nearest character for that monster
                int min=0 ;     
                for(int n=0 ; n<characterDetect.size() ; n++){
                    if(distance.get(min)>distance.get(n))
                        min = n ;
                }

                //determine the relative position between character and monster
                Character character  ;
                if(characterDetect.size() != 0){
                	character  = characterDetect.get(min) ;
	                int nx, ny ;//�]���O�ǩ������V�e�i�A�ҥH�H�Ǫ�������I
	                /**
	                 *
	                 * 00  10  20
	                 * -----------
	                 * 01  11  21
	                 * -----------
	                 * 02  12  22
	                 *
	                 * **/
	                if(character.position.getX()<monster.position.getX())
	                    nx=0;
	                else if(character.position.getX()==monster.position.getX()) nx=1;
	                else nx=2;
	
	                if(character.position.getY()<monster.position.getY()) ny=0;
	                else if(character.position.getY()==monster.position.getY()) ny=1;
	                else ny=2;
	
	
	                ArrayList<Boolean> list = new ArrayList<Boolean>() ;
	                //it's a "move", so the attackRange is the same as a step
	                int forChangeState = -1;
	                switch(nx+ny){
	                    case 0:{
	                        //�H���b�Ǫ����W���A���Ǫ������V�W����
	                        list = cdcsdm.checkObstacle(monster.position, 1, 1) ;
	                        forChangeState = 1;
	                    }break;
	
	                    case 1:{
	                        if(nx==1){
	                            list = cdcsdm.checkObstacle(monster.position, 1, 1) ;
	                            forChangeState = 1;
	                        }else{
	                            list = cdcsdm.checkObstacle(monster.position, 0, 1) ;
	                            forChangeState = 0;
	                        }
	                    }break;
	
	                    case 2:{
	                        if(nx==2){
	                            list = cdcsdm.checkObstacle(monster.position, 2, 1) ;
	                            forChangeState = 2;
	                        }else{
	                            list = cdcsdm.checkObstacle(monster.position, 0, 1) ;
	                            forChangeState = 0 ;
	                        }
	                    }break;
	
	                    case 3:{
	                        if(nx==2){
	                            list = cdcsdm.checkObstacle(monster.position, 2, 1) ;
	                            forChangeState = 2;
	                        }else{
	                            list = cdcsdm.checkObstacle(monster.position, 3, 1) ;
	                            forChangeState = 3 ;
	                        }
	                    }break;
	
	                    case 4:{
	                        list = cdcsdm.checkObstacle(monster.position, 3, 1) ;
	                        forChangeState = 3;
	                    }break;
	
	                    default:
	                        System.out.println("something wrong in the cdcsdm.");
	
	                }
	
	                if( list.get(0).equals(true) ){
	
	                    switch(forChangeState){
	                        case 0:{
	                            monster.position.setLocation(monster.position.getX()+25,monster.position.getY()) ;
	                            monster.direction = 0 ;
	                            monster.state = true ;
	                        }
	                        case 1:{
	                            monster.position.setLocation(monster.position.getX(),monster.position.getY()+25) ;
	                            monster.direction = 1 ;
	                            monster.state = true ;
	                        }
	                        case 2:{
	                            monster.position.setLocation(monster.position.getX()-25,monster.position.getY()) ;
	                            monster.direction = 2;
	                            monster.state = true ;
	                        }
	                        case 3:{
	                            monster.position.setLocation(monster.position.getX(),monster.position.getY()-25) ;
	                            monster.direction = 3 ;
	                            monster.state = true ;
	                        }
	                    }
	                }

                }
                
            }
        }
    }
}
