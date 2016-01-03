package com.nobody.renderEngine;


public class RenderThread implements Runnable
{
	private SceneRender sceneRender;
	private SRE spriteRender;
	private UIRender uiRender;
	
	public RenderThread(SceneRender sceneRender, SRE spriteRender, UIRender uiRender)
	{
		this.sceneRender = sceneRender;
		this.spriteRender = spriteRender;
		this.uiRender = uiRender;
	}

	@Override
	public void run()
	{
		// sleep for cdc and dom initialization
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		while(true)
		{
			sceneRender.renderScene();
			spriteRender.renderSprites();
			uiRender.renderUI();
			
			
			// 1/20s == 50ms == 20fps
			try
			{
//				Thread.sleep(50);
				Thread.sleep(5000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
