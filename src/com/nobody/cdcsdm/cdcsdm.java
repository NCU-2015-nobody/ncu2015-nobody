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
	
	public ArrayList checkObstacle(Point position, int direction, int attackRange){
		
		ArrayList<Boolean> obstacleList = new ArrayList<Boolean>();
		
		int XX = (int) position.getX() ;//將座標值轉換成可供list使用的index值
		int YY = (int) position.getY() ;
		
		//草地1   石頭2   陷阱3  樹4 
		switch(direction){ // left0 up1 right2 down3
			case 0:{
				for(int n=0 ; n<attackRange ; n++){
					if( columnList.get(XX).get(YY)==2 || columnList.get(XX).get(YY)==4 ){
						obstacleList.add(false) ;
					}
					else
						obstacleList.add(true) ;
					XX-- ;
				}
			}break;
			
			case 1:{
				for(int n=0 ; n<attackRange ; n++){
					if( columnList.get(XX).get(YY)==2 || columnList.get(XX).get(YY)==4 ){
						obstacleList.add(false) ;
					}
					else
						obstacleList.add(true) ;
					YY-- ;
				}	
			}break;
			
			case 2:{
				for(int n=0 ; n<attackRange ; n++){
					if( columnList.get(XX).get(YY)==2 || columnList.get(XX).get(YY)==4 ){
						obstacleList.add(false) ;
					}
					else
						obstacleList.add(true) ;
					XX++ ;
				}	
			}break;
			
			case 3:{
				for(int n=0 ; n<attackRange ; n++){
					if( columnList.get(XX).get(YY)==2 || columnList.get(XX).get(YY)==4 ){
						obstacleList.add(false) ;
					}
					else
						obstacleList.add(true) ;
					YY++ ;
				}
			}break;
				
		}
		
		return obstacleList ;
		
	}
	
	
}

