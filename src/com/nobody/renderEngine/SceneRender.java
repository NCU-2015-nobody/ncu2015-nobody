package com.nobody.renderEngine;

import java.awt.Point;

import com.nobody.dom.DOM;


public class SceneRender
{
	private SceneDataModule sdm;
	private DOM dom;
	private UpdateScene canvas;
	private int[][] scene;
	
	private int widthToCentral;
	private int heightToCentral;
	
	public SceneRender(SceneDataModule sdm, DOM dom, UpdateScene canvas)
	{
		this.sdm = sdm;
		this.dom = dom;
		this.canvas = canvas;
		
		this.widthToCentral = (canvas.getBlockWidth() - 1) * 25 / 2;
		this.heightToCentral = (canvas.getBlockHeight() - 1) * 25 / 2;
	}
	
	public void renderScene()
	{
		Point character = dom.getVirtualCharacterXY();
		int characterX = (int)character.getX();
		int characterY = (int)character.getY();
		System.out.println("characterX=" + characterX);
		System.out.println("characterY=" + characterY);
		
		int panelX = characterX - widthToCentral;
		int panelY = characterY - heightToCentral;
		int mapX = panelX / 25;
		int mapY = panelY / 25;
		
		int[][] scene = getSceneType(mapX, mapY, canvas.getBlockWidth(), canvas.getBlockHeight());	
		for(int y = 0; y < scene.length; y++)
		{
			for(int x = 0; x < scene[y].length; x++)
			{
				System.out.print(scene[y][x] + " ");
			}
			System.out.println();
		}
		
		// call GameCanvas update
		canvas.updateScene(scene);
	}
	
	public int[][] getSceneType(int mapX, int mapY, int sceneWidth, int sceneHeight)
	{
		int[][] sceneType = new int[sceneHeight][sceneWidth];
		
		int[][] map = sdm.getMap();
		
		for(int i = 0; i < sceneHeight; i++)
		{
			for(int j = 0; j < sceneWidth; j++)
			{
				if(mapY + i < 0 || mapY + i >= map.length || mapX + j < 0 || mapX + j >= map[i].length)
				{
					sceneType[i][j] = BasicBlock.DEFAULT;
					continue;
				}
				sceneType[i][j] = map[mapY + i][mapX + j];
			}
		}
		
		return sceneType;
	}
	
	public int[][] getScene()
	{
		return scene;
	}

	public void setScene(int[][] scene)
	{
		this.scene = scene;
	}
}
