package com.nobody.dom;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

public class d 
{
	//self number!!
	
	information info = new information();
	
	public void addVirtualCharacter(int clientNumber, int currentX, int currentY, int dir, int health)//��ǤJ�ѼơF�����C�Ө��⪺�̤j��q
	{
		// ��UDPUS�I�s�C
		// ��UDPUS����addVirtualCharacter�T���ɡA�I�s����k�A�Nserver�ݶǰe���H����T�x�s��DOM�C
		// �H�U�C����T���U�Τ@��arraylist�s�A�ê�l��
	
		// int clientNumber �G ����s�� 
		// Point before_xy �G �����s�e���y��
		// Point current_xy �G �����e�y��
		// int dir �G  ��V (��0�B�W1�B�k2�B�U3)
		// int health �G �����q
		
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

	public void addMonster(int objectID, int currentX, int currentY, int dir, int health) // ��ǤJ�Ѽ�
	{
		// ��UDPUS�I�s
		// ��UDPUS����add�T���ɡA�I�s����k�A�Nserver�ݶǰe���Ǫ���T�x�s��DOM�C
		// �H�U�C����T���U�Τ@��arraylist�s�A�ê�l��
	
	
		// int objectID �G �Ǫ��s�� 
		// Point before_xy_monster �G �Ǫ���s�e���y��
		// Point current_xy_monster �G �Ǫ���e�y��
		// int dir_monster �G  ��V (��0�B�W1�B�k2�B�U3)
		// int health_monster �G �Ǫ���q
		
//		information info = new information();
		
		Point current_xy = new Point();
		current_xy.setLocation(currentX, currentY);
		
		info.objectIDList.add(objectID);
		info.before_xy_monsterList.add(null);
		info.current_xy_monsterList.add(current_xy);
		info.dir_monsterList.add(dir);
		info.health_monsterList.add(health);
	}
	
	public void updateVirtualCharacter(int clientNumber, int currentX, int currentY, int dir, int health)
	{
		// ��UDPUS�I�s�C
		// ��UDPUS����update�T���ɡA�I�s����k�A�ˬdclientNumberList�̦��S���ǤJ��clientNumber
		// �p�G�����ܡA�Τ@��index�ӰO��update����m�F�Y�L�A�h���I�saddVirtualCharacter()�C
		// �A����s�C
	
		// ���F�P�_���A������or�R��A�b��s�e���x�s��s�e���y��
	
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
		
		info.before_xyList.set(index, info.current_xyList.get(index)); //�x�s��s�e���y�� 
		
		info.current_xyList.set(index, current_xy);
		info.dirList.set(index, dir);
		info.healthList.set(index, health);
	
	}


	public void updateMonsterStatus(int objectID, int currentX, int currentY, int dir, int health)
	{
		// ��UDPUC�I�s�C
		// ��UDPUS����update�T���ɡA�I�s����k�A�ˬdobjectIDList�̦��S���ǤJ��objectID�A�p�G�����ܡA�K��s�F�Y�L�A�h���I�saddMonster()�A��s�C
	
		// ���F�P�_���A������or�R��A�b��s�e���x�s��s�e���y��

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
		
		info.before_xy_monsterList.set(indexM, info.current_xy_monsterList.get(indexM)); //�x�s��s�e���y��
		//warn the situation of the index and the other are not equal 
		
		info.current_xy_monsterList.set(indexM, current_xy);
		info.dir_monsterList.set(indexM, dir);
		info.health_monsterList.set(indexM, health);
	
	}


	public boolean CDTimer() /////////not done
	{
		// ��TCPCM�I�s�C
		// ���a���U������A�������Ǫ��ATCPCM����UIM�ǨӪ��T���A�K�I�s����k�A�H�T�{���a���N�o�ɶ��A�P�_��O�_�i�o�X�����C
	
		// int CD �G �ۤv���⪺�N�o�ɶ�
	
		/*if (CD == 0)
			CD = 1000; // �ȩw�������j��1000�@��
			Thread t = new Thread(new Runnable()); // �}�l��CD
			return true;
		end if
		else
			return false;
		end else*/
		
//		final information info = new information();
		final int timeInterval = 1000; // ���j1000�@��
		
		if(info.CD == 0)
		{
			info.CD = 3;
			Thread t = new Thread(new Runnable(){ // �����|3��
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


	public Vector getAllDynamicObjects() /////////not done
	{
		// ��SPRITERE�I�s�C
		// �^�� clientNumberList , before_xyList , current_xyList , dirList , health
		// �^�� objectIDList , before_xy_monsterList , current_xy_monsterList , dir_monsterList , health_monster
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
	
	public boolean mainCharacterAttack()
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

	public Point getVirtualCharacterXY()
	{
		// ��SCENERE�I�s�C
		// �^�ǥثe�H�����Ҧb���С]��l�Ʈɻݳ]�w�ۤv��client Number�^�C
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
	
	public int[] getVirtualCharactersHP() // �ഫ%�A�令int[]
	{
		// ��UIRE�I�s�C
		// �^�ǦU�H�����⪺HP�ȡA�Hø�s��C���e�����C
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


	public boolean[] getVirtualCharactersCD() // ��boolean[]
	{
		// ��UIRE�I�s�C
		// �^�ǦU�H�����⪺CD�ȡA�Hø�s��C���e�����C
		
		boolean[] boolCD = {false, false, false, false};
		int self_no = TCPtest.character();
		
		if(info.CD > 0)
			boolCD[self_no] = false;
		else
			boolCD[self_no] = true;
		
		return boolCD;
	}
	
	public static class information //XstaticX �F 
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

