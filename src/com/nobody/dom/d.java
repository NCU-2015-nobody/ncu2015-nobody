package com.app.dom;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

public class d 
{
	//self number!!
	
	void addVirtualCharacter(int clientNumber)
	{
		// 由UDPUS呼叫。
		// 當UDPUS收到addVirtualCharacter訊息時，呼叫此方法，將server端傳送的人物資訊儲存於DOM。
		// 以下每項資訊都各用一個arraylist存，並初始化
	
		// int clientNumber ： 角色編號 
		// Point before_xy ： 角色更新前的座標
		// Point current_xy ： 角色當前座標
		// int dir ：  方向 (左0、上1、右2、下3)
		// int health ： 角色血量
		
		information info = new information();
		
		info.clientNumberList.add(clientNumber);
		info.before_xyList.add(null);
		info.current_xyList.add(null);
		info.dirList.add(null);
		info.healthList.add(null);
	}

	void addMonster(int objectID)
	{
		// 由UDPUS呼叫
		// 當UDPUS收到add訊息時，呼叫此方法，將server端傳送的怪物資訊儲存於DOM。
		// 以下每項資訊都各用一個arraylist存，並初始化
	
	
		// int objectID ： 怪物編號 
		// Point before_xy_monster ： 怪物更新前的座標
		// Point current_xy_monster ： 怪物當前座標
		// int dir_monster ：  方向 (左0、上1、右2、下3)
		// int health_monster ： 怪物血量
		
		information info = new information();
		
		info.objectIDList.add(objectID);
		info.before_xy_monsterList.add(null);
		info.current_xy_monsterList.add(null);
		info.dir_monsterList.add(null);
		info.health_monsterList.add(null);
	}
	
	void updateVirtualCharacter(int clientNumber, Point current_xy, int dir, int health)
	{
		// 由UDPUS呼叫。
		// 當UDPUS收到update訊息時，呼叫此方法，檢查clientNumberList裡有沒有傳入的clientNumber
		// 如果有的話，用一個index來記錄update的位置；若無，則先呼叫addVirtualCharacter()。
		// 再做更新。
	
		// 為了判斷狀態為走路or靜止，在更新前先儲存更新前的座標
	
		information info = new information();
		int index = -1;
	
		for(int i = 0 ; i < info.clientNumberList.size() ; i++)
		{
			if(clientNumber == info.clientNumberList.get(i))
				index = i;
		}
	
		if(index == -1)
		{
			addVirtualCharacter(clientNumber);
			index = info.clientNumberList.size() - 1;
		}
		
		info.before_xyList.set(index, info.current_xyList.get(index)); //儲存更新前的座標
		//warn the situation of the index and the other are not equal 
		
		info.current_xyList.set(index, current_xy);
		info.dirList.set(index, dir);
		info.healthList.set(index, health);
	
	}


	void updateMonsterStatus(int objectID, Point current_xy, int dir, int health)
	{
		// 由UDPUC呼叫。
		// 當UDPUS收到update訊息時，呼叫此方法，檢查objectIDList裡有沒有傳入的objectID，如果有的話，便更新；若無，則先呼叫addMonster()再更新。
	
		// 為了判斷狀態為走路or靜止，在更新前先儲存更新前的座標

		information info = new information();
		int indexM = -1;
	
		for(int i = 0 ; i < info.objectIDList.size() ; i++)
		{
			if(objectID == info.objectIDList.get(i))
				indexM = i;
		}
	
		if(indexM == -1)
		{
			addMonster(objectID);
			indexM = info.objectIDList.size() - 1;
		}
		
		info.before_xy_monsterList.set(indexM, info.current_xy_monsterList.get(indexM)); //儲存更新前的座標
		//warn the situation of the index and the other are not equal 
		
		info.current_xy_monsterList.set(indexM, current_xy);
		info.dir_monsterList.set(indexM, dir);
		info.health_monsterList.set(indexM, health);
	
	}


	boolean CDTimer()
	{
		// 由TCPCM呼叫。
		// 當玩家按下攻擊鍵，欲攻擊怪物，TCPCM收到UIM傳來的訊息，便呼叫此方法，以確認玩家的冷卻時間，判斷其是否可發出攻擊。
	
		// int CD ： 自己角色的冷卻時間
	
		/*if (CD == 0)
			CD = 1000; // 暫定攻擊間隔為1000毫秒
			Thread t = new Thread(new Runnable()); // 開始扣CD
			return true;
		end if
		else
			return false;
		end else*/
		
		final information info = new information();
		final int timeInterval = 1000; // 間隔1000毫秒
		
		if(info.CD[0] == 0)
		{
			info.CD[0] = 3;
			Thread t = new Thread(new Runnable(){
				public void run() {
					while (true) {
						if(info.CD[0]==0)
						{
							break;
						}
						else
						{
							info.CD[0]--;
						}
							
						
						try {
							Thread.sleep(timeInterval);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
			});
			t.start();
			return true;
		}
		else
		{
			return false;
		}
		
	}


	Vector getAllDynamicObjects()
	{
		// 由SPRITERE呼叫。
		// 回傳 clientNumberList , before_xyList , current_xyList , dirList , health
		// 回傳 objectIDList , before_xy_monsterList , current_xy_monsterList , dir_monsterList , health_monster
		information info = new information();
		Vector v1 = new Vector();
		
		for(int i = 0 ; i < info.clientNumberList.size() ; i++)
		{
			v1.add(info.clientNumberList.get(i));
			v1.add(info.before_xyList.get(i));
			v1.add(info.current_xyList.get(i));
			v1.add(info.dirList.get(i));
			v1.add(info.healthList.get(i));
		}
		
		for(int i = 0 ; i < info.objectIDList.size() ; i++)
		{
			v1.add(info.objectIDList.get(i));
			v1.add(info.before_xy_monsterList.get(i));
			v1.add(info.current_xy_monsterList.get(i));
			v1.add(info.dir_monsterList.get(i));
			v1.add(info.health_monsterList.get(i));
		}
		
		return v1;
	}

	Point getVirtualCharacterXY()
	{
		// 由SCENERE呼叫。
		// 回傳目前人物的所在坐標（初始化時需設定自己的client Number）。
		Point p = new Point();
		information info = new information();
		
		for(int i = 0 ; i < info.clientNumberList.size() ; i++)
		{
			if(info.selfNumber == info.clientNumberList.get(i))
			{
				//p = info.current_xyList.get(i);
				p.setLocation(info.current_xyList.get(i));
			}
		}
		
		return p;
	}
	
	Vector getVirtualCharactersHP()
	{
		// 由UIRE呼叫。
		// 回傳各人物角色的HP值，以繪製於遊戲畫面中。
		Vector v = new Vector();
		information info = new information();
		
		for(int i = 0 ; i < info.healthList.size() ; i++)
		{
			v.add(info.healthList.get(i));
		}
		
		return v;
	}


	int getVirtualCharactersCD()
	{
		// 由UIRE呼叫。
		// 回傳各人物角色的CD值，以繪製於遊戲畫面中。
		information info = new information();
		
		return info.CD[0];
	}
	
	public static class information
	{
		public static ArrayList<Integer> clientNumberList = new ArrayList<Integer>();
		public static ArrayList<Point> before_xyList = new ArrayList<Point>();
		public static ArrayList<Point> current_xyList = new ArrayList<Point>();
		public static ArrayList<Integer> dirList = new ArrayList<Integer>();
		public static ArrayList<Integer> healthList = new ArrayList<Integer>();
		
		public static ArrayList<Integer> objectIDList = new ArrayList<Integer>();
		public static ArrayList<Point> before_xy_monsterList = new ArrayList<Point>();
		public static ArrayList<Point> current_xy_monsterList = new ArrayList<Point>();
		public static ArrayList<Integer> dir_monsterList = new ArrayList<Integer>();
		public static ArrayList<Integer> health_monsterList = new ArrayList<Integer>();
		
		public static int selfNumber = -1;
		public static int[] CD = {0};
	}
}

