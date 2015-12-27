package com.nobody.renderEngine;

public class BasicBlock
{
	private int type;
	
	public static final int DEFAULT = 0;
	public static final int STONE = 1;
	public static final int GRASS = 2;
	public static final int TREE = 3;
	public static final int WATER = 4;
	public static final int MUD = 5;
	public static final int TRAP = 6;
	public static final String[] TYPE_NAME = {"default", "stone", "grass", "tree", "water", "mud", "trap"};
	
	public BasicBlock(int type)
	{
		setType(type);
	}
	
	public void setType(int type)
	{
		this.type = type;
	}
	
	public int getType()
	{
		return this.type;
	}
	
	public String getTypeName()
	{
		return TYPE_NAME[type];
	}
}
