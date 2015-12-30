package com.nobody.renderEngine;

import com.nobody.dom.DOM;


public class UIRender
{
	private DOM dom;
	private UpdateUI canvas;
	
	public UIRender(DOM dom, UpdateUI canvas)
	{
		this.dom = dom;
		this.canvas = canvas;
	}
	
	public void renderUI()
	{
		int[] hp = dom.getVirtualCharactersHP(); // divide with maxHP
		boolean[] cdFlag = dom.getVirtualCharactersCD();
		
		canvas.updateUI(hp, cdFlag);
	}
}
