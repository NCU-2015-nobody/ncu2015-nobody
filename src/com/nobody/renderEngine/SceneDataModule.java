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
		FileReader fr = new FileReader(mapfileName);
        BufferedReader br = new BufferedReader(fr);
        sceneType.clear();
        sceneType.add(BasicBlock.DEFAULT); // load default type
		
        String rawData = br.readLine();
        String[] datas = rawData.split(" ");
		int width = Integer.parseInt(datas[0]);
		int length = Integer.parseInt(datas[1]);
		System.out.println(width + " " + length);

		newMap(width, length);
		for(int y = 0; y < width; y++)
		{
			rawData = br.readLine();
			//System.out.println("rawData=" + rawData);
	        datas = rawData.split(" ");
			for(int x = 0; x < length; x++)
			{
				this.map[y][x] = Integer.parseInt(datas[x]);
				System.out.print(map[y][x] + " ");
				
				if(!sceneType.contains(map[y][x]))
				{
					sceneType.add(map[y][x]);
				}
			}
			System.out.println();
		}
		
		br.close();
		fr.close();
	}
	
	public int[][] getSceneType(int mapX, int mapY, int sceneWidth, int sceneLength)
	{
		int[][] sceneType = new int[sceneLength][sceneWidth];
		
		for(int i = 0; i < sceneLength; i++)
		{
			for(int j = 0; j < sceneWidth; j++)
			{
				if(mapY + i < 0 || mapY + i >= 20 || mapX + j < 0 || mapX + j >= 50)
				{
					sceneType[i][j] = BasicBlock.DEFAULT;
					continue;
				}
				sceneType[i][j] = map[mapY + i][mapX + j];
			}
		}
		
		return sceneType;
	}
	
	public ArrayList<Integer> getAllKindOfType()
	{
		return this.sceneType;
	}
	
	private void newMap(int width, int length)
	{
		map = new int[width][length];
	}

	public int[][] getMap()
	{
		return map;
	}
}
