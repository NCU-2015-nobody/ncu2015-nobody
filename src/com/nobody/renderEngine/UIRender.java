package com.nobody.renderEngine;

import com.nobody.domStub.DynamicObjectModule;

public class UIRender
{

	private DynamicObjectModule dom;
	private UpdateUI canvas;
	
	public UIRender(DynamicObjectModule dom, UpdateUI canvas)
	{
		this.dom = dom;
		this.canvas = canvas;
	}
	
	public void renderUI()
	{
		int[] hp = dom.getVirtualCharactersHP();
		boolean[] cdFlag = dom.getVirtualCharactersCD();
		
		canvas.updateUI(hp, cdFlag);
	}
}
