package com.nobody.renderEngine;

import java.awt.Point;
import java.util.Vector;

import com.nobody.dom.DOM;
import com.nobody.renderEngine.DOMtest;

public class SRE 
{
	private DOM dom;
	private GameCanvas canvas;
	
	public SRE(DOM dom, GameCanvas canvas)
	{
		this.dom = dom;
		this.canvas = canvas;
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
		
		int count = 0;
		
		for(int i = 0 ; i < 3 ; i++)
		{
			v_picture.add(characterInfo[i]);
		}
		
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
			
			if (count < 4) // 4 character
			{
				if (health == 0) // dead
				{
					v_picture.add(no + "-2-4");
					v_picture.add(current);
				}

				if (before != current) // walking
				{
					v_picture.add(no + "-0-" + dir);
					v_picture.add(current);
				} 
				else // stand straight
				{
					v_picture.add(no + "-1-" + dir);
					v_picture.add(current);
				}
			} 
			else // monster
			{
				if (health == 0) // dead
				{
					continue;
				}

				if (before != current) // walking
				{
					v_picture.add("0-0-" + dir);
					v_picture.add(current);
				} 
				else // stand straight
				{
					v_picture.add("0-1-" + dir);
					v_picture.add(current);
				}
			}
				
			
			count++;
		}
		
		canvas.updateSprite(v_picture);
	}

}
