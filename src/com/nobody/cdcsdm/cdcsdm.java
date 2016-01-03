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
		try {
			loadMap("mapfileForCDCSDM.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String mapfile) throws IOException{
		
		FileReader fr = new FileReader("./map/" + mapfile);
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
		//1石頭   2草地   3樹木   4水   5木牆   6尖刺
		int XX =  (int)position.getX()/25 ;
		int YY  = (int)position.getY()/25 ;
		if(columnList.get(YY).get(XX)==6)
			return true ;
		else
			return false;
	}
	
	public ArrayList<Boolean> checkObstacle(Point position, int direction, int attackRange){
		
		ArrayList<Boolean> obstacleList = new ArrayList<Boolean>();
		
		int XX = (int) position.getX()/25 ;//將座標值轉換成可供list使用的index值
		int YY = (int) position.getY()/25 ;
		
//		System.out.println("XX=" + XX + ", YY=" + YY);
		
		//不可行走區域：1石頭      3樹木     4水     5木牆
		//可行走區域：  2草地      6尖刺
		//false is not obstacle
		switch(direction){ // left0 up1 right2 down3
			case 0:{
				for(int n=0 ; n<attackRange ; n++){
					if( columnList.get(YY).get(XX-1)==2 || columnList.get(YY).get(XX-1)==6 ){
						obstacleList.add(false) ;
					}
					else
						obstacleList.add(true) ;
					XX-- ;
				}
			}break;
			
			case 1:{
				for(int n=0 ; n<attackRange ; n++){
					if( columnList.get(YY-1).get(XX)==2 || columnList.get(YY-1).get(XX)==6 ){
						obstacleList.add(false) ;
					}
					else
						obstacleList.add(true) ;
					YY-- ;
				}	
			}break;
			
			case 2:{
				for(int n=0 ; n<attackRange ; n++){
					if( columnList.get(YY).get(XX+1)==2 || columnList.get(YY).get(XX+1)==6 ){
						obstacleList.add(false) ;
					}
					else
						obstacleList.add(true) ;
					XX++ ;
				}	
			}break;
			
			case 3:{
				for(int n=0 ; n<attackRange ; n++){
					if( columnList.get(YY+1).get(XX)==2 || columnList.get(YY+1).get(XX)==6 ){
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

