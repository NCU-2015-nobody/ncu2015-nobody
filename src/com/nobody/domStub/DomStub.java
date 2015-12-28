package com.nobody.domStub;

public class DomStub implements DynamicObjectModule
{

	public final int[] DIRECTION_X = {-1, 0, 1, 0};
	public final int[] DIRECTION_Y = {0, -1, 0, 1};
	
	private Point character;
	private int[] charactersHP;
	private boolean[] charactersCD;
	
	public DomStub()
	{
		character = new Point(2500, 2500);
		
		// initial charactersHP
		charactersHP = new int[4];
		for(int i = 0; i < charactersHP.length; i++)
		{
			this.charactersHP[i] = 50;
		}

		// initial charactersCD
		charactersCD = new boolean[4];
		for(int i = 0; i < charactersCD.length; i++)
		{
			this.charactersCD[i] = false;
		}
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
	
	public void addPointDirection(int direction)
	{
		// 0:LEFT  1:UP  2:RIGHT 3:DOWN
		character.setX(character.getX() + DIRECTION_X[direction] * 25);
		character.setY(character.getY() + DIRECTION_Y[direction] * 25);
	}
	
	@Override
	public Point getVirtualCharacterXY()
	{
		addPointDirection(2);
		
		System.out.println("before return x=" + this.character.getX() + "\ty=" + this.character.getY());
		return this.character;
	}

	@Override
	public int[] getVirtualCharactersHP()
	{
		return this.charactersHP;
	}

	@Override
	public boolean[] getVirtualCharactersCD()
	{
		return this.charactersCD;
	}
}
