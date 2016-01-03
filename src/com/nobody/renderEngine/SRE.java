package com.nobody.renderEngine;

import java.awt.Point;
import java.util.Vector;

import com.nobody.dom.DOM;
import com.nobody.renderEngine.DOMtest;

public class SRE 
{
	private DOM dom;
	
	public SRE(DOM dom)
	{
		this.dom = dom;
	}
	
	@SuppressWarnings("null")
	void renderSprites()
	{	
		Vector v = dom.getAllDynamicObjects(); 
		Vector v_picture = new Vector(); 
		int[] characterInfo = dom.mainCharacterInfo();
		Point mainXY = dom.getVirtualCharacterXY();
		
		int no = -1;
		Point before = null;
		Point current = null;
		int dir = -1;
		int health = -1;
		
		for(int i = 0 ; i < 3 ; i++)
		{
			v.add(characterInfo[i]);
		}
		
		v_picture.add(mainXY);
		
		while(!v.isEmpty()) //character for first 4, monster for the rest
		{			
			no = (int) v.get(0);
			v.remove(0);
			if(v.get(0) != null)
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
		}
	}

}
