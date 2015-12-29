package com.nobody.renderEngineTest;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import com.nobody.domStub.DynamicObjectModule;
import com.nobody.domStub.Point;
import com.nobody.renderEngine.SceneDataModule;
import com.nobody.renderEngine.SceneRender;
import com.nobody.renderEngine.UpdateScene;

public class SceneRenderTest
{
	private SceneDataModule sdm;
	private DomStub domStub;
	private GameCanvasMock mockedCanvas;
	private SceneRender sceneRender;
	
	// this should be changed !!!
	private int characterX = 1000; // 0, 1000, 2000, 3000, 4000, 5000
	private int characterY = 500; // 0, 500, 1000, 1500, 2000
	private int moveTimes = 5;
	private int nowMoves = 0;
	
	@Before
	public void setUp()
	{
		sdm = new SceneDataModule();
		domStub = new DomStub();
		domStub.setPoint(characterX, characterY);
		mockedCanvas = new GameCanvasMock();
		
		sceneRender = new SceneRender(sdm, domStub, mockedCanvas);
		
		try
		{
			sdm.loadMap("mapfile.txt");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	

	@Test
	public final void testGetSceneTypeMap() throws IOException
	{
		sdm.loadMap("mapfile1");
		int[][] checkMap = {
				{3, 1, 2, 3, 1, 2}, 
				{1, 2, 1, 2, 4, 2}, 
				{2, 2, 1, 3, 2, 2}, 
				{3, 4, 4, 4, 4, 3}};
		int[][] sceneMap = sceneRender.getSceneType(0, 0, 6, 4);
		
		for(int i = 0; i < sceneMap.length; i++)
		{
			for(int j = 0; j < sceneMap[i].length; j++)
			{
				assertEquals(checkMap[i][j], sceneMap[i][j]);
			}
		}
		

		int[][] checkMap2 = {
				{0, 0, 0, 0, 0, 0, 0}, 
				{0, 0, 0, 0, 0, 0, 0}, 
				{0, 3, 1, 2, 3, 1, 2}, 
				{0, 1, 2, 1, 2, 4, 2}, 
				{0, 2, 2, 1, 3, 2, 2}};
		sceneMap = sceneRender.getSceneType(-1, -2, 7, 5);
		
		for(int i = 0; i < sceneMap.length; i++)
		{
			for(int j = 0; j < sceneMap[i].length; j++)
			{
				assertEquals(checkMap2[i][j], sceneMap[i][j]);
			}
		}
	}
	
	// test many pattern
	@Test
	public final void testRenderSceneLeft()
	{
		domStub.setPattern(0);
		
		mockedCanvas.setVerifyStrategy(new UpdateScene()
		{
			@Override
			public void updateScene(int[][] sceneType)
			{
				int newCharacterX = characterX + (domStub.DIRECTION_X[0] * 25) * nowMoves;
				int newCharacterY = characterY + (domStub.DIRECTION_Y[0] * 25) * nowMoves;
				int panelX = newCharacterX - 400;
				int panelY = newCharacterY - 250;
				int mapX = panelX / 25;
				int mapY = panelY / 25;
				int[][] sceneTypeExpect = sceneRender.getSceneType(mapX, mapY, 33, 21);
				
				for(int i = 0; i < sceneType.length; i++)
				{
					for(int j = 0; j < sceneType[i].length; j++)
					{
						assertEquals(sceneTypeExpect[i][j], sceneType[i][j]);
					}
				}
			}

			@Override
			public int getBlockWidth()
			{
				// 800 / 25 + 1
				return 33;
			}

			@Override
			public int getBlockHeight()
			{
				// 500 / 25 + 1
				return 21;
			}
		});
		
		move();
	}
	
	@Test
	public final void testRenderSceneUp()
	{
		domStub.setPattern(1);
		
		mockedCanvas.setVerifyStrategy(new UpdateScene()
		{
			@Override
			public void updateScene(int[][] sceneType)
			{
				int newCharacterX = characterX + (domStub.DIRECTION_X[1] * 25) * nowMoves;
				int newCharacterY = characterY + (domStub.DIRECTION_Y[1] * 25) * nowMoves;
				int panelX = newCharacterX - 400;
				int panelY = newCharacterY - 250;
				int mapX = panelX / 25;
				int mapY = panelY / 25;
				int[][] sceneTypeExpect = sceneRender.getSceneType(mapX, mapY, 33, 21);
				
				for(int i = 0; i < sceneType.length; i++)
				{
					for(int j = 0; j < sceneType[i].length; j++)
					{
						assertEquals(sceneTypeExpect[i][j], sceneType[i][j]);
					}
				}
			}

			@Override
			public int getBlockWidth()
			{
				// 800 / 25 + 1
				return 33;
			}

			@Override
			public int getBlockHeight()
			{
				// 500 / 25 + 1
				return 21;
			}
		});
		
		move();
	}

	@Test
	public final void testRenderSceneRight()
	{
		domStub.setPattern(2);
		
		mockedCanvas.setVerifyStrategy(new UpdateScene()
		{
			@Override
			public void updateScene(int[][] sceneType)
			{
				int newCharacterX = characterX + (domStub.DIRECTION_X[2] * 25) * nowMoves;
				int newCharacterY = characterY + (domStub.DIRECTION_Y[2] * 25) * nowMoves;
				int panelX = newCharacterX - 400;
				int panelY = newCharacterY - 250;
				int mapX = panelX / 25;
				int mapY = panelY / 25;
				int[][] sceneTypeExpect = sceneRender.getSceneType(mapX, mapY, 33, 21);
				
				for(int i = 0; i < sceneType.length; i++)
				{
					for(int j = 0; j < sceneType[i].length; j++)
					{
						assertEquals(sceneTypeExpect[i][j], sceneType[i][j]);
					}
				}
			}

			@Override
			public int getBlockWidth()
			{
				// 800 / 25 + 1
				return 33;
			}

			@Override
			public int getBlockHeight()
			{
				// 500 / 25 + 1
				return 21;
			}
		});
		
		move();
	}

	@Test
	public final void testRenderSceneDown()
	{
		domStub.setPattern(3);
		
		mockedCanvas.setVerifyStrategy(new UpdateScene()
		{
			@Override
			public void updateScene(int[][] sceneType)
			{
				int newCharacterX = characterX + (domStub.DIRECTION_X[3] * 25) * nowMoves;
				int newCharacterY = characterY + (domStub.DIRECTION_Y[3] * 25) * nowMoves;
				int panelX = newCharacterX - 400;
				int panelY = newCharacterY - 250;
				int mapX = panelX / 25;
				int mapY = panelY / 25;
				int[][] sceneTypeExpect = sceneRender.getSceneType(mapX, mapY, 33, 21);
				
				for(int i = 0; i < sceneType.length; i++)
				{
					for(int j = 0; j < sceneType[i].length; j++)
					{
						assertEquals(sceneTypeExpect[i][j], sceneType[i][j]);
					}
				}
			}

			@Override
			public int getBlockWidth()
			{
				// 800 / 25 + 1
				return 33;
			}

			@Override
			public int getBlockHeight()
			{
				// 500 / 25 + 1
				return 21;
			}
		});
		
		move();
	}
	
	public void move()
	{
		for(nowMoves = 1; nowMoves <= moveTimes; nowMoves++)
		{
			System.out.println("Yes! Rendering! moveTimes=" + moveTimes + "\tnowMoves=" + nowMoves);
			sceneRender.renderScene();
		}
	}
}

class DomStub implements DynamicObjectModule
{
	private int pattern;
	private Point character;
	//private Random random;
	
	public final int[] DIRECTION_X = {-1, 0, 1, 0};
	public final int[] DIRECTION_Y = {0, -1, 0, 1};
	
	public DomStub()
	{
		character = new Point(0, 0);
		//random = new Random();
	}
	
	public void setPoint(int x, int y)
	{
		character.setX(x);
		character.setY(y);
	}
	
	public Point getCharacter()
	{
		return this.character;
	}
	
	private void addPointDirection(int direction)
	{
		// 0:LEFT  1:UP  2:RIGHT 3:DOWN
		character.setX(character.getX() + DIRECTION_X[direction] * 25);
		character.setY(character.getY() + DIRECTION_Y[direction] * 25);
	}
	
	public void setPattern(int pattern)
	{
		this.pattern = pattern;
	}
	
	@Override
	public Point getVirtualCharacterXY()
	{
		addPointDirection(pattern);
		
		return getCharacter();
	}
	
	// do nothing
	@Override
	public int[] getVirtualCharactersHP()
	{
		return null;
	}
	@Override
	public boolean[] getVirtualCharactersCD()
	{
		return null;
	}
}


class GameCanvasMock implements UpdateScene
{
	private UpdateScene canvas;
	
	public void setVerifyStrategy(UpdateScene canvas)
	{
		this.canvas = canvas;
	}

	@Override
	public void updateScene(int[][] sceneType)
	{
		this.canvas.updateScene(sceneType);
	}

	@Override
	public int getBlockWidth()
	{
		return 33;
	}

	@Override
	public int getBlockHeight()
	{
		return 21;
	}
}
