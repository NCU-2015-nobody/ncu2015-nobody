package com.nobody.cdcsdm;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class cdcsdm {

	/**
	 * @param args
	 */
	static ArrayList<ArrayList<Integer>> columnList = new ArrayList<ArrayList<Integer>>();
	
	public cdcsdm(){
		
	}
	
	public void loadMap(String mapfile) throws IOException{
		
		FileReader fr = new FileReader(mapfile);
		BufferedReader br = new BufferedReader(fr);
		while (br.ready()) {
			String line = br.readLine() ;
			ArrayList<Integer> rowList = new ArrayList <Integer>() ;
			for(int n=0 ; n<line.length(); n++){
				rowList.add(Integer.valueOf((String)line.subSequence(n, n+1)));
			}
			columnList.add(rowList) ;
		}
		
		fr.close();
		
	}
	
	public Boolean checkTrap(Point position) {
		//1���Y   2��a   3���   4��   5����   6�y��
		
		int XX =  (int)position.getX()/25 ;
		int YY  = (int)position.getY()/25 ;
		if(columnList.get(XX).get(YY)==6)
			return true ;
		else
			return false;
	}
	
	public ArrayList checkObstacle(Point position, int direction, int attackRange){
		
		ArrayList<Boolean> obstacleList = new ArrayList<Boolean>();
		
		int XX = (int) position.getX()/25 ;//�N�y�Э��ഫ���i��list�ϥΪ�index��
		int YY = (int) position.getY()/25 ;
		
		//���i�樫�ϰ�G1���Y      3���     4��     5����
		//�i�樫�ϰ�G2��a      6�y��
		//false�����i�q�L�ϰ�
		switch(direction){ // left0 up1 right2 down3
			case 0:{
				for(int n=0 ; n<attackRange ; n++){
					if( columnList.get(XX).get(YY)==2 || columnList.get(XX).get(YY)==6 ){
						obstacleList.add(true) ;
					}
					else
						obstacleList.add(false) ;
					XX-- ;
				}
			}break;
			
			case 1:{
				for(int n=0 ; n<attackRange ; n++){
					if( columnList.get(XX).get(YY)==2 || columnList.get(XX).get(YY)==6 ){
						obstacleList.add(true) ;
					}
					else
						obstacleList.add(false) ;
					YY-- ;
				}	
			}break;
			
			case 2:{
				for(int n=0 ; n<attackRange ; n++){
					if( columnList.get(XX).get(YY)==2 || columnList.get(XX).get(YY)==6 ){
						obstacleList.add(true) ;
					}
					else
						obstacleList.add(false) ;
					XX++ ;
				}	
			}break;
			
			case 3:{
				for(int n=0 ; n<attackRange ; n++){
					if( columnList.get(XX).get(YY)==2 || columnList.get(XX).get(YY)==6 ){
						obstacleList.add(true) ;
					}
					else
						obstacleList.add(false) ;
					YY++ ;
				}
			}break;
				
		}
		
		return obstacleList ;
	}
	
	
}

