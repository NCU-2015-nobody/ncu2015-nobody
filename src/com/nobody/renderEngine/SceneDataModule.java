package com.nobody.renderEngine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SceneDataModule
{

	private int[][] map;
	private ArrayList<Integer> sceneType;
	
	public SceneDataModule()
	{
		map = new int[0][0];
		sceneType = new ArrayList<Integer>();
	}
	
	public void loadMap(String mapfileName) throws IOException
	{
		FileReader fr = new FileReader("./map/" + mapfileName);
        BufferedReader br = new BufferedReader(fr);
        sceneType.clear();
        sceneType.add(BasicBlock.DEFAULT); // load default type
		
        String rawData = br.readLine();
        String[] datas = rawData.split(" ");
		int width = Integer.parseInt(datas[0]);
		int height = Integer.parseInt(datas[1]);
		System.out.println(width + " " + height);

		newMap(width, height);
		for(int y = 0; y < width; y++)
		{
			rawData = br.readLine();
	        datas = rawData.split(" ");
			for(int x = 0; x < height; x++)
			{
				this.map[y][x] = Integer.parseInt(datas[x]);
				//System.out.print(map[y][x] + " ");
				
				if(!sceneType.contains(map[y][x]))
				{
					sceneType.add(map[y][x]);
				}
			}
			//System.out.println();
		}
		
		br.close();
		fr.close();
	}
	
	private void newMap(int width, int length)
	{
		map = new int[width][length];
	}
	
	public ArrayList<Integer> getAllKindOfType()
	{
		return this.sceneType;
	}

	public int[][] getMap()
	{
		return map;
	}
}
