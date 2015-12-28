package com.app.dom;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

public class d 
{
	//self number!!
	
	void addVirtualCharacter(int clientNumber)
	{
		// ��UDPUS�I�s�C
		// ��UDPUS����addVirtualCharacter�T���ɡA�I�s����k�A�Nserver�ݶǰe���H����T�x�s��DOM�C
		// �H�U�C����T���U�Τ@��arraylist�s�A�ê�l��
	
		// int clientNumber �G ����s�� 
		// Point before_xy �G �����s�e���y��
		// Point current_xy �G �����e�y��
		// int dir �G  ��V (��0�B�W1�B�k2�B�U3)
		// int health �G �����q
		
		information info = new information();
		
		info.clientNumberList.add(clientNumber);
		info.before_xyList.add(null);
		info.current_xyList.add(null);
		info.dirList.add(null);
		info.healthList.add(null);
	}

	void addMonster(int objectID)
	{
		// ��UDPUS�I�s
		// ��UDPUS����add�T���ɡA�I�s����k�A�Nserver�ݶǰe���Ǫ���T�x�s��DOM�C
		// �H�U�C����T���U�Τ@��arraylist�s�A�ê�l��
	
	
		// int objectID �G �Ǫ��s�� 
		// Point before_xy_monster �G �Ǫ���s�e���y��
		// Point current_xy_monster �G �Ǫ���e�y��
		// int dir_monster �G  ��V (��0�B�W1�B�k2�B�U3)
		// int health_monster �G �Ǫ���q
		
		information info = new information();
		
		info.objectIDList.add(objectID);
		info.before_xy_monsterList.add(null);
		info.current_xy_monsterList.add(null);
		info.dir_monsterList.add(null);
		info.health_monsterList.add(null);
	}
	
	void updateVirtualCharacter(int clientNumber, Point current_xy, int dir, int health)
	{
		// ��UDPUS�I�s�C
		// ��UDPUS����update�T���ɡA�I�s����k�A�ˬdclientNumberList�̦��S���ǤJ��clientNumber
		// �p�G�����ܡA�Τ@��index�ӰO��update����m�F�Y�L�A�h���I�saddVirtualCharacter()�C
		// �A����s�C
	
		// ���F�P�_���A������or�R��A�b��s�e���x�s��s�e���y��
	
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
		
		info.before_xyList.set(index, info.current_xyList.get(index)); //�x�s��s�e���y��
		//warn the situation of the index and the other are not equal 
		
		info.current_xyList.set(index, current_xy);
		info.dirList.set(index, dir);
		info.healthList.set(index, health);
	
	}


	void updateMonsterStatus(int objectID, Point current_xy, int dir, int health)
	{
		// ��UDPUC�I�s�C
		// ��UDPUS����update�T���ɡA�I�s����k�A�ˬdobjectIDList�̦��S���ǤJ��objectID�A�p�G�����ܡA�K��s�F�Y�L�A�h���I�saddMonster()�A��s�C
	
		// ���F�P�_���A������or�R��A�b��s�e���x�s��s�e���y��

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
		
		info.before_xy_monsterList.set(indexM, info.current_xy_monsterList.get(indexM)); //�x�s��s�e���y��
		//warn the situation of the index and the other are not equal 
		
		info.current_xy_monsterList.set(indexM, current_xy);
		info.dir_monsterList.set(indexM, dir);
		info.health_monsterList.set(indexM, health);
	
	}


	boolean CDTimer()
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
		
		final information info = new information();
		final int timeInterval = 1000; // ���j1000�@��
		
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
		// ��SPRITERE�I�s�C
		// �^�� clientNumberList , before_xyList , current_xyList , dirList , health
		// �^�� objectIDList , before_xy_monsterList , current_xy_monsterList , dir_monsterList , health_monster
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
		// ��SCENERE�I�s�C
		// �^�ǥثe�H�����Ҧb���С]��l�Ʈɻݳ]�w�ۤv��client Number�^�C
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
		// ��UIRE�I�s�C
		// �^�ǦU�H�����⪺HP�ȡA�Hø�s��C���e�����C
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
		// ��UIRE�I�s�C
		// �^�ǦU�H�����⪺CD�ȡA�Hø�s��C���e�����C
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

