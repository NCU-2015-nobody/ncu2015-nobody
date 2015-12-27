package com.nobody.renderEngine;

import com.nobody.domStub.Point;
import com.nobody.domStub.SceneInformation;


public class SceneRender
{
	private SceneDataModule sdm;
	private SceneInformation dom;
	private UpdateScene canvas;
	private int[][] scene;
	
	public SceneRender(SceneDataModule sdm, SceneInformation dom, UpdateScene canvas)
	{
		this.sdm = sdm;
		this.dom = dom;
		this.canvas = canvas;
	}
	
	public void renderScene()
	{
		Point character = dom.getVirtualCharacterXY();
		int characterX = character.getX();
		int characterY = character.getY();
		System.out.println("characterX=" + characterX);
		System.out.println("characterY=" + characterY);
		
		int panelX = characterX - 400;
		int panelY = characterY - 300;
		int mapX = panelX / 25;
		int mapY = panelY / 25;
		
		int[][] scene = sdm.getSceneType(mapX, mapY, 9, 6);	
		
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

	public int[][] getScene()
	{
		return scene;
	}

	public void setScene(int[][] scene)
	{
		this.scene = scene;
	}
}
