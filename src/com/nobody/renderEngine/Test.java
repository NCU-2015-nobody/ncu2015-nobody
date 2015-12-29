package com.nobody.renderEngine;

import java.io.IOException;
import javax.swing.JFrame;
import com.nobody.domStub.DomStub;

public class Test
{
	public Test()
	{
		
	}
	
	public static void main(String[] args)
	{
		SceneDataModule sdm = new SceneDataModule();
		try
		{
			sdm.loadMap("mapfile.txt");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		JFrame frame = new JFrame();
		frame.setSize(815, 535);
		
		
		GameCanvas canvas = new GameCanvas(33, 21);
		canvas.loadSceneImage(sdm.getAllKindOfType());
		DomStub domStub = new DomStub();
		
		SceneRender background = new SceneRender(sdm, domStub, canvas);
		background.renderScene();
		UIRender uiSystem = new UIRender(domStub, canvas);
		
		frame.add(canvas);
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		for(int i = 0; i < 100; i++)
		{
			try
			{
				Thread.sleep(50);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			background.renderScene();
			uiSystem.renderUI();
		}
	}
}
