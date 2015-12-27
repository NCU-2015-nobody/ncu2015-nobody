package com.nobody.renderEngine;

import com.nobody.domStub.UIInformation;

public class UIRender
{

	private UIInformation dom;
	private UpdateUI canvas;
	
	public UIRender(UIInformation dom, UpdateUI canvas)
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
