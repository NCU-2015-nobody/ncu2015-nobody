package com.app.sre;

import java.awt.Point;
import java.util.Vector;

import com.app.sre.DOMtest;

public class SRE 
{
	@SuppressWarnings("null")
	void renderSprites()
	{
		// 由 Render Thread 呼叫。
		// 繪製視野範圍內的動態物件。
		// 呼叫DOM的getAllDynamicObjects()方法，取得存有所有動態物件的Vecter
		//並判斷、繪製視野內的物件。
	
		Vector v = DOMtest.getAllDynamicObjects(); 
		Vector v_picture = new Vector(); // 儲存圖片路徑
		boolean attack = DOMtest.mainCharacterAttack();
		Point mainXY = DOMtest.getVirtualCharacterXY();
		
		int no = -1;
		Point before = null;
		Point current = null;
		int dir = -1;
		int health = -1;
		
	
		// 將getAllDynamicObjects()傳回來的vector分組[clientNumber + before_xy + current_xy + dir + health為一組] or [objectID + before_xy_monster + current_xy_monster + dir_monster + health_monster為一組]，一次判斷一組資料。
	
		// 判斷需要繪製哪張圖並存至v_picture裡
		// 每組資料皆處理完後，再呼叫canvas的updateSprite(Vector v_picture)
		
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
			
			// 每組資料皆處理完後，再呼叫canvas的updateSprite(Vector v_picture)
		}
	}

}
