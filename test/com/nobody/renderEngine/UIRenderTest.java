package com.nobody.renderEngine;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.nobody.domStub.DynamicObjectModule;
import com.nobody.domStub.Point;

public class UIRenderTest
{
	private DomStub2 domStub;
	private GameCanvasMock2 mockedCanvas;
	private UIRender uiRender;

	private int moveTimes = 5;
	private int nowMoves = 0;
	
	@Before
	public void setUp()
	{
		int[] initialHP = {50, 50, 50, 50};
		boolean[] initialCD = {false, false, false, false};
		
		domStub = new DomStub2(initialHP, initialCD);
		mockedCanvas = new GameCanvasMock2();
		
		uiRender = new UIRender(domStub, mockedCanvas);
	}
	
	// test two patterns
	@Test
	public final void testRenderUIAddHP()
	{
		domStub.setHPPattern(true);
		
		mockedCanvas.setVerifyStrategy(new UpdateUI()
		{
			@Override
			public void updateUI(int[] hpPecentage, boolean[] cdFlag)
			{
				int expectedHP = 50 + nowMoves * 5;
				
				for(int i = 0; i < hpPecentage.length; i++)
				{
					assertEquals(expectedHP, hpPecentage[i]);
				}
			}
		});
		
		move();
	}
	

	@Test
	public final void testRenderUIMinusHP()
	{
		domStub.setHPPattern(false);
		
		mockedCanvas.setVerifyStrategy(new UpdateUI()
		{
			@Override
			public void updateUI(int[] hpPecentage, boolean[] cdFlag)
			{
				int expectedHP = 50 - nowMoves * 5;
				
				for(int i = 0; i < hpPecentage.length; i++)
				{
					assertEquals(expectedHP, hpPecentage[i]);
				}
			}
		});
		
		move();
	}
	
	public void move()
	{
		for(nowMoves = 1; nowMoves <= moveTimes; nowMoves++)
		{
			System.out.println("Yes! Rendering! moveTimes=" + moveTimes + "\tnowMoves=" + nowMoves);
			uiRender.renderUI();
		}
	}

	// test two patterns
	@Test
	public final void testRenderUIinCD()
	{
		domStub.setCDPattern(true);
		
		mockedCanvas.setVerifyStrategy(new UpdateUI()
		{
			@Override
			public void updateUI(int[] hpPecentage, boolean[] cdFlag)
			{
				assertTrue(cdFlag[0]);
			}
		});
		
		uiRender.renderUI();
	}
	
	@Test
	public final void testRenderUIoutCD()
	{
		domStub.setCDPattern(false);
		
		mockedCanvas.setVerifyStrategy(new UpdateUI()
		{
			@Override
			public void updateUI(int[] hpPecentage, boolean[] cdFlag)
			{
				assertFalse(cdFlag[0]);
			}
		});
		
		uiRender.renderUI();
	}
}

class DomStub2 implements DynamicObjectModule
{
	private boolean hpPattern;
	private boolean cdPattern;
	private int[] virtualCharactersHP;
	private boolean[] virtualCharactersCD;
	
	public DomStub2(int[] virtualCharactersHP, boolean[] virtualCharactersCD)
	{
		this.virtualCharactersHP = virtualCharactersHP;
		this.virtualCharactersCD = virtualCharactersCD;
	}
	
	public void setHPPattern(boolean hpPattern)
	{
		this.hpPattern = hpPattern;
	}
	
	public void setCDPattern(boolean cdPattern)
	{
		this.cdPattern = cdPattern;
	}
	
	// do nothing
	@Override
	public Point getVirtualCharacterXY()
	{
		return null;
	}
	
	@Override
	public int[] getVirtualCharactersHP()
	{
		if(hpPattern)
		{
			for(int i = 0; i < virtualCharactersHP.length; i++)
			{
				virtualCharactersHP[i] += 5;
			}
		}
		else
		{
			for(int i = 0; i < virtualCharactersHP.length; i++)
			{
				virtualCharactersHP[i] -= 5;
			}
		}
		return this.virtualCharactersHP;
	}
	
	@Override
	public boolean[] getVirtualCharactersCD()
	{
		this.virtualCharactersCD[0] = cdPattern;
		
		return this.virtualCharactersCD;
	}
}


class GameCanvasMock2 implements UpdateUI
{
	private UpdateUI canvas;
	
	public void setVerifyStrategy(UpdateUI canvas)
	{
		this.canvas = canvas;
	}

	@Override
	public void updateUI(int[] hpPecentage, boolean[] cdFlag)
	{
		this.canvas.updateUI(hpPecentage, cdFlag);
	}
}
