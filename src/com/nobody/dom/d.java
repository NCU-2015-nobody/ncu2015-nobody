package com.app.dom;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

public class d 
{
	//self number!!
	
	information info = new information();
	
	void addVirtualCharacter(int clientNumber, int currentX, int currentY, int dir, int health)//改傳入參數；紀錄每個角色的最大血量
	{
		// 由UDPUS呼叫。
		// 當UDPUS收到addVirtualCharacter訊息時，呼叫此方法，將server端傳送的人物資訊儲存於DOM。
		// 以下每項資訊都各用一個arraylist存，並初始化
	
		// int clientNumber ： 角色編號 
		// Point before_xy ： 角色更新前的座標
		// Point current_xy ： 角色當前座標
		// int dir ：  方向 (左0、上1、右2、下3)
		// int health ： 角色血量
		
//		information info = new information();
		
		Point p = new Point();
		p.setLocation(currentX, currentY);
		
		info.clientNumberList.add(clientNumber);
		info.before_xyList.add(null);
		info.current_xyList.add(p);
		info.dirList.add(dir);
		info.healthList.add(health);
		
		switch(clientNumber)
		{
		case 1:
			info.maxHP[0] = health;
			break;
		case 2:
			info.maxHP[1] = health;
			break;
		case 3:
			info.maxHP[2] = health;
			break;
		case 4:
			info.maxHP[3] = health;
			break;
		}
	}

	void addMonster(int objectID, int currentX, int currentY, int dir, int health) // 改傳入參數
	{
		// 由UDPUS呼叫
		// 當UDPUS收到add訊息時，呼叫此方法，將server端傳送的怪物資訊儲存於DOM。
		// 以下每項資訊都各用一個arraylist存，並初始化
	
	
		// int objectID ： 怪物編號 
		// Point before_xy_monster ： 怪物更新前的座標
		// Point current_xy_monster ： 怪物當前座標
		// int dir_monster ：  方向 (左0、上1、右2、下3)
		// int health_monster ： 怪物血量
		
//		information info = new information();
		
		Point current_xy = new Point();
		current_xy.setLocation(currentX, currentY);
		
		info.objectIDList.add(objectID);
		info.before_xy_monsterList.add(null);
		info.current_xy_monsterList.add(current_xy);
		info.dir_monsterList.add(dir);
		info.health_monsterList.add(health);
	}
	
	void updateVirtualCharacter(int clientNumber, int currentX, int currentY, int dir, int health)
	{
		// 由UDPUS呼叫。
		// 當UDPUS收到update訊息時，呼叫此方法，檢查clientNumberList裡有沒有傳入的clientNumber
		// 如果有的話，用一個index來記錄update的位置；若無，則先呼叫addVirtualCharacter()。
		// 再做更新。
	
		// 為了判斷狀態為走路or靜止，在更新前先儲存更新前的座標
	
//		information info = new information();
		int index = -1;
		Point current_xy = new Point();
		current_xy.setLocation(currentX, currentY);
	
//		for(int i = 0 ; i < info.clientNumberList.size() ; i++) // indexof
//		{
//			if(clientNumber == info.clientNumberList.get(i))
				
//		}
		
		index = info.clientNumberList.indexOf(clientNumber);
	
		if(index == -1)
		{
			addVirtualCharacter(clientNumber, currentX, currentY, dir, health);
			index = info.clientNumberList.size() - 1;
		}
		
		info.before_xyList.set(index, info.current_xyList.get(index)); //儲存更新前的座標 
		
		info.current_xyList.set(index, current_xy);
		info.dirList.set(index, dir);
		info.healthList.set(index, health);
	
	}


	void updateMonsterStatus(int objectID, int currentX, int currentY, int dir, int health)
	{
		// 由UDPUC呼叫。
		// 當UDPUS收到update訊息時，呼叫此方法，檢查objectIDList裡有沒有傳入的objectID，如果有的話，便更新；若無，則先呼叫addMonster()再更新。
	
		// 為了判斷狀態為走路or靜止，在更新前先儲存更新前的座標

//		information info = new information();
		int indexM = -1;
		Point current_xy = new Point();
		current_xy.setLocation(currentX, currentY);
	
//		for(int i = 0 ; i < info.objectIDList.size() ; i++)
//		{
//			if(objectID == info.objectIDList.get(i))
//				indexM = i;
//		}
		
		indexM = info.objectIDList.indexOf(objectID);
	
		if(indexM == -1)
		{
			addMonster(objectID, currentX, currentY, dir, health);
			indexM = info.objectIDList.size() - 1;
		}
		
		info.before_xy_monsterList.set(indexM, info.current_xy_monsterList.get(indexM)); //儲存更新前的座標
		//warn the situation of the index and the other are not equal 
		
		info.current_xy_monsterList.set(indexM, current_xy);
		info.dir_monsterList.set(indexM, dir);
		info.health_monsterList.set(indexM, health);
	
	}


	boolean CDTimer() /////////not done
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
		
//		final information info = new information();
		final int timeInterval = 1000; // 間隔1000毫秒
		
		if(info.CD == 0)
		{
			info.CD = 3;
			Thread t = new Thread(new Runnable(){ // 直接稅3秒
				public void run() {						
						
						try {
							Thread.sleep(timeInterval);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						info.CD = 0;
				}				
			});
			t.start();
//			mainCharacterAttack();
			return true;
		}
		else
		{
			return false;
		}
		
	}


	Vector getAllDynamicObjects() /////////not done
	{
		// 由SPRITERE呼叫。
		// 回傳 clientNumberList , before_xyList , current_xyList , dirList , health
		// 回傳 objectIDList , before_xy_monsterList , current_xy_monsterList , dir_monsterList , health_monster
//		information info = new information();
		Vector v1 = new Vector();
		
		for(int i = 0 ; i < info.clientNumberList.size() ; i++)
		{
			v1.add(info.clientNumberList.get(i));
			v1.add(info.before_xyList.get(i));
			v1.add(info.current_xyList.get(i));
			v1.add(info.dirList.get(i));
			v1.add(info.healthList.get(i));
//			v1.add(info.CD);
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
	
	boolean mainCharacterAttack()
	{
		if(info.CD > 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	Point getVirtualCharacterXY()
	{
		// 由SCENERE呼叫。
		// 回傳目前人物的所在坐標（初始化時需設定自己的client Number）。
		Point p = new Point();
		
		int self_no = TCPtest.character();
		
		for(int i = 0 ; i < info.clientNumberList.size() ; i++)
		{
			if(self_no == info.clientNumberList.get(i))
			{
				//p = info.current_xyList.get(i);
				p.setLocation(info.current_xyList.get(i));
			}
		}
		
		return p;
	}
	
	int[] getVirtualCharactersHP() // 轉換%，改成int[]
	{
		// 由UIRE呼叫。
		// 回傳各人物角色的HP值，以繪製於遊戲畫面中。
//		Vector v = new Vector();
//		information info = new information();
		
//		int percentageHP = 0;
		int[] percentageHP = new int[4];
		
		for(int i = 0 ; i < info.healthList.size() ; i++)
		{
			switch(info.clientNumberList.get(i))
			{
			case 1:
				percentageHP[0] = info.healthList.get(i) / info.maxHP[0];
				break;
			case 2:
				percentageHP[1] = info.healthList.get(i) / info.maxHP[1];
				break;
			case 3:
				percentageHP[2] = info.healthList.get(i) / info.maxHP[2];
				break;
			case 4:
				percentageHP[3] = info.healthList.get(i) / info.maxHP[3];
				break;
			}
//			v.add(info.healthList.get(i));
		}
		
		return percentageHP;
	}


	boolean[] getVirtualCharactersCD() // 改boolean[]
	{
		// 由UIRE呼叫。
		// 回傳各人物角色的CD值，以繪製於遊戲畫面中。
		
		boolean[] boolCD = {false, false, false, false};
		int self_no = TCPtest.character();
		
		if(info.CD > 0)
			boolCD[self_no] = false;
		else
			boolCD[self_no] = true;
		
		return boolCD;
	}
	
	public static class information //XstaticX ； 
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
		
//		public static int selfNumber = -1;
		public static int CD;
		public static int[] maxHP = new int[4];
	}
}

