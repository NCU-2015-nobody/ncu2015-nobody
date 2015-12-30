package com.nobody.renderEngine;

import java.awt.Point;
import java.util.Vector;

import com.nobody.renderEngine.DOMtest;

public class SRE 
{
	@SuppressWarnings("null")
	void renderSprites()
	{
		// �� Render Thread �I�s�C
		// ø�s�����d�򤺪��ʺA����C
		// �I�sDOM��getAllDynamicObjects()��k�A���o�s���Ҧ��ʺA����Vecter
		//�çP�_�Bø�s������������C
	
		Vector v = DOMtest.getAllDynamicObjects(); 
		Vector v_picture = new Vector(); // �x�s�Ϥ����|
		boolean attack = DOMtest.mainCharacterAttack();
		Point mainXY = DOMtest.getVirtualCharacterXY();
		
		int no = -1;
		Point before = null;
		Point current = null;
		int dir = -1;
		int health = -1;
		
	
		// �NgetAllDynamicObjects()�Ǧ^�Ӫ�vector����[clientNumber + before_xy + current_xy + dir + health���@��] or [objectID + before_xy_monster + current_xy_monster + dir_monster + health_monster���@��]�A�@���P�_�@�ո�ơC
	
		// �P�_�ݭnø�s���i�Ϩæs��v_picture��
		// �C�ո�ƬҳB�z����A�A�I�scanvas��updateSprite(Vector v_picture)
		
		v_picture.add(attack);
		v_picture.add(mainXY);
		
		while(!v.isEmpty()) //character for first 4, monster for the rest
		{			
			no = (int) v.get(0);
			v.remove(0);
			before = (Point) v.get(0);
			v.remove(0);
			current = (Point) v.get(0);
			v.remove(0);
			dir = (int) v.get(0);
			v.remove(0);
			health = (int) v.get(0);
			v.remove(0);
			
			if(health == 0) //dead
			{
				v_picture.add(no + "-2-4");
				v_picture.add(current);
			}
			else
			{
				if(before != current) // walking
				{
					v_picture.add(no + "-0-" +dir);
					v_picture.add(current);
				}
				else //stand straight
				{
					v_picture.add(no + "-1-" +dir);
					v_picture.add(current);
				}
			}
			
			// �C�ո�ƬҳB�z����A�A�I�scanvas��updateSprite(Vector v_picture)
		}
	}

}
