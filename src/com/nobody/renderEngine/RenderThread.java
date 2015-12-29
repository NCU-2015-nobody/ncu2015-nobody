package com.nobody.renderEngine;


public class RenderThread implements Runnable
{
	private SceneRender sceneRender;
	//  private SpriteRender spriteRender;
	private UIRender uiRender;
	
	public RenderThread(SceneRender sceneRender/*, SpriteRender spriteRender*/, UIRender uiRender)
	{
		this.sceneRender = sceneRender;
		// this.spriteRender = spriteRender;
		this.uiRender = uiRender;
	}

	@Override
	public void run()
	{
		while(true)
		{
			sceneRender.renderScene();
			//spriteRender.renderSprites();
			uiRender.renderUI();
			
			
			// 1/20s == 50ms == 20fps
			try
			{
				Thread.sleep(50);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
