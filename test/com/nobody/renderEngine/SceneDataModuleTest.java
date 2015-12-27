package com.nobody.renderEngine;

import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class SceneDataModuleTest
{
	private SceneDataModule sdm;
	
	@Before
	public void setUp()
	{
		sdm = new SceneDataModule();
	}

	@Test
	public final void testLoadMapFile1() throws IOException
	{
		sdm.loadMap("mapfile1");
		int[][] actualMap = {
				{3, 1, 2, 3, 1, 2, 3, 4, 4, 3, 1, 1, 3, 4, 3, 3, 4, 2, 1, 3, 1, 3, 3, 4, 3, 4, 4, 4, 4, 2, 2, 2, 3, 1, 2, 4, 2, 4, 3, 2, 3, 3, 1, 1, 1, 3, 2, 1, 4, 3}, 
				{1, 2, 1, 2, 4, 2, 1, 4, 2, 4, 3, 1, 1, 3, 1, 3, 2, 1, 1, 2, 2, 3, 1, 1, 2, 3, 1, 1, 3, 3, 2, 3, 3, 1, 4, 3, 3, 2, 1, 4, 1, 1, 3, 3, 2, 4, 1, 2, 2, 4}, 
				{2, 2, 1, 3, 2, 2, 3, 1, 3, 3, 4, 1, 2, 2, 4, 2, 4, 3, 4, 3, 3, 4, 2, 3, 3, 4, 3, 1, 4, 4, 1, 4, 3, 4, 2, 2, 4, 4, 1, 3, 4, 4, 3, 1, 1, 1, 2, 2, 4, 3}, 
				{3, 4, 4, 4, 4, 3, 4, 4, 4, 3, 3, 3, 1, 4, 4, 2, 2, 2, 3, 1, 1, 2, 1, 3, 4, 2, 3, 2, 3, 4, 4, 2, 1, 4, 4, 1, 2, 4, 4, 4, 3, 4, 2, 1, 2, 2, 4, 1, 2, 4}, 
				{2, 2, 4, 2, 3, 4, 3, 2, 2, 4, 3, 3, 2, 2, 3, 1, 4, 2, 4, 1, 3, 3, 3, 1, 1, 1, 1, 2, 1, 4, 1, 4, 3, 4, 2, 3, 2, 1, 3, 3, 1, 2, 3, 2, 4, 4, 3, 2, 3, 2}, 
				{4, 1, 4, 1, 2, 2, 2, 3, 3, 2, 4, 1, 4, 3, 1, 4, 1, 3, 2, 1, 2, 3, 4, 1, 3, 1, 4, 4, 2, 3, 4, 1, 2, 3, 4, 1, 4, 1, 2, 4, 3, 1, 4, 1, 4, 2, 1, 2, 3, 1}, 
				{2, 2, 2, 1, 1, 3, 1, 1, 3, 4, 3, 2, 1, 3, 4, 3, 4, 2, 2, 1, 1, 4, 2, 4, 3, 2, 4, 2, 2, 3, 3, 1, 2, 4, 4, 1, 2, 2, 4, 1, 4, 2, 1, 4, 4, 3, 1, 2, 4, 4}, 
				{1, 1, 3, 4, 1, 1, 2, 2, 1, 2, 2, 2, 3, 2, 2, 1, 1, 2, 1, 1, 1, 3, 2, 3, 3, 2, 3, 1, 2, 3, 3, 3, 4, 3, 3, 3, 1, 4, 3, 3, 2, 2, 3, 4, 4, 1, 2, 4, 4, 3}, 
				{1, 2, 2, 2, 2, 2, 4, 4, 1, 3, 4, 1, 1, 3, 2, 2, 3, 2, 3, 2, 2, 4, 3, 4, 1, 2, 3, 3, 4, 4, 2, 4, 2, 3, 1, 2, 1, 3, 3, 4, 2, 1, 3, 4, 2, 4, 3, 3, 1, 3}, 
				{3, 4, 4, 1, 4, 1, 4, 2, 2, 2, 3, 2, 3, 4, 4, 2, 3, 2, 1, 1, 1, 4, 3, 3, 1, 3, 4, 1, 3, 3, 3, 3, 3, 2, 1, 1, 4, 4, 2, 3, 1, 3, 3, 1, 4, 4, 3, 4, 1, 1}, 
				{1, 3, 1, 2, 3, 4, 2, 4, 3, 1, 1, 2, 2, 1, 3, 4, 4, 2, 3, 2, 4, 2, 3, 4, 1, 3, 4, 1, 1, 2, 1, 4, 4, 2, 3, 3, 1, 3, 1, 4, 4, 2, 1, 2, 3, 2, 3, 3, 3, 4}, 
				{4, 4, 4, 3, 3, 1, 3, 3, 2, 2, 4, 4, 4, 4, 4, 1, 2, 3, 3, 4, 3, 4, 3, 3, 3, 3, 3, 3, 4, 4, 1, 1, 1, 3, 1, 3, 2, 3, 2, 1, 4, 1, 3, 2, 4, 1, 2, 3, 3, 1}, 
				{3, 2, 4, 2, 1, 3, 4, 3, 4, 3, 4, 1, 4, 3, 4, 4, 1, 4, 1, 2, 3, 2, 4, 2, 3, 4, 2, 3, 4, 4, 1, 1, 3, 4, 3, 4, 2, 2, 3, 4, 1, 1, 4, 3, 1, 1, 4, 4, 1, 4}, 
				{3, 1, 2, 1, 2, 1, 3, 4, 2, 3, 2, 4, 1, 1, 4, 3, 3, 4, 4, 1, 1, 2, 2, 4, 2, 2, 3, 2, 1, 1, 3, 3, 4, 1, 1, 3, 1, 3, 4, 2, 1, 1, 3, 2, 4, 2, 3, 3, 1, 1}, 
				{4, 1, 2, 4, 3, 3, 2, 1, 4, 2, 4, 4, 3, 2, 1, 2, 4, 1, 1, 4, 4, 2, 2, 1, 3, 1, 3, 2, 3, 2, 2, 2, 3, 4, 3, 4, 1, 4, 2, 1, 2, 2, 2, 3, 2, 4, 2, 2, 2, 2}, 
				{4, 1, 4, 4, 3, 3, 2, 3, 4, 1, 1, 4, 1, 1, 1, 4, 2, 1, 2, 1, 1, 4, 4, 4, 1, 1, 2, 3, 2, 2, 2, 2, 2, 4, 3, 1, 2, 4, 4, 3, 4, 2, 4, 3, 2, 1, 2, 3, 1, 1}, 
				{2, 3, 3, 1, 3, 4, 3, 4, 1, 3, 1, 4, 2, 3, 3, 3, 3, 4, 1, 3, 1, 4, 3, 4, 2, 4, 1, 4, 4, 4, 1, 2, 1, 3, 2, 4, 2, 4, 4, 4, 1, 4, 1, 1, 2, 4, 2, 4, 1, 2}, 
				{4, 4, 3, 2, 1, 3, 2, 4, 2, 1, 2, 2, 1, 2, 2, 1, 1, 3, 2, 1, 2, 4, 4, 4, 4, 3, 1, 1, 1, 4, 1, 1, 4, 2, 1, 3, 4, 1, 3, 2, 2, 4, 4, 1, 4, 2, 2, 4, 4, 2}, 
				{2, 1, 3, 2, 3, 3, 4, 3, 2, 1, 1, 3, 4, 2, 4, 3, 3, 4, 3, 1, 3, 4, 2, 4, 2, 3, 1, 4, 4, 1, 3, 1, 1, 3, 3, 4, 3, 3, 3, 2, 4, 3, 1, 2, 1, 1, 2, 3, 2, 2}, 
				{4, 2, 1, 3, 4, 2, 4, 1, 1, 2, 3, 2, 3, 3, 3, 1, 1, 2, 1, 3, 1, 1, 3, 2, 2, 4, 1, 1, 2, 4, 2, 4, 2, 2, 3, 1, 2, 2, 4, 2, 4, 2, 1, 1, 3, 4, 1, 4, 1, 2}};
		
		int[][] map = sdm.getMap();
		
		assertEquals(actualMap.length, map.length);
		assertEquals(actualMap[0].length, map[0].length);
		for(int i = 0; i < actualMap.length; i++)
		{
			for(int j = 0; j < actualMap[i].length; j++)
			{
				assertEquals(actualMap[i][j], map[i][j]);
			}
		}
	}
	
	// doesn't contain "4" in mapfile2
	@Test
	public final void testLoadMapFile2() throws IOException
	{
		int[][] actualMap = {
				{3, 2, 1, 1, 2, 1, 2, 1, 1, 1, 3, 3, 2, 3, 3, 2, 3, 3, 3, 3, 3, 3, 1, 3, 1, 1, 1, 2, 3, 3}, 
				{3, 2, 2, 1, 1, 3, 3, 3, 2, 2, 3, 1, 1, 2, 2, 2, 1, 3, 2, 2, 1, 1, 3, 3, 3, 3, 3, 2, 2, 2}, 
				{3, 3, 1, 1, 1, 3, 2, 3, 1, 1, 2, 2, 2, 1, 1, 3, 2, 1, 1, 1, 1, 2, 2, 2, 2, 3, 1, 1, 3, 3}, 
				{2, 3, 2, 2, 1, 1, 2, 3, 1, 3, 3, 3, 2, 1, 1, 3, 3, 3, 3, 1, 2, 2, 2, 1, 2, 2, 2, 3, 3, 2}, 
				{3, 2, 1, 1, 3, 2, 3, 1, 1, 1, 1, 3, 2, 3, 1, 2, 1, 1, 3, 2, 1, 3, 1, 2, 1, 2, 1, 3, 3, 1}, 
				{3, 3, 3, 3, 1, 1, 1, 3, 1, 3, 2, 1, 2, 2, 3, 1, 3, 3, 1, 1, 2, 2, 2, 3, 3, 1, 3, 1, 2, 3}, 
				{2, 1, 1, 3, 3, 3, 2, 1, 2, 2, 3, 3, 1, 3, 3, 2, 3, 2, 1, 2, 1, 3, 2, 2, 3, 3, 3, 3, 2, 1}, 
				{1, 2, 3, 2, 1, 3, 2, 1, 1, 1, 1, 2, 1, 1, 1, 2, 2, 3, 3, 3, 3, 2, 1, 2, 3, 2, 2, 3, 3, 3}, 
				{1, 2, 1, 3, 3, 2, 1, 1, 2, 1, 3, 2, 1, 2, 1, 1, 1, 2, 1, 2, 2, 1, 3, 2, 1, 1, 3, 3, 2, 2}, 
				{1, 3, 1, 3, 2, 2, 3, 3, 3, 2, 1, 3, 2, 3, 1, 1, 1, 3, 3, 2, 1, 2, 2, 1, 3, 2, 3, 1, 2, 1}, 
				{3, 3, 2, 1, 1, 3, 3, 1, 1, 1, 3, 2, 3, 3, 3, 1, 3, 3, 1, 2, 2, 1, 3, 2, 3, 2, 1, 1, 2, 3}, 
				{2, 2, 3, 2, 2, 2, 3, 1, 2, 2, 3, 2, 1, 3, 2, 1, 2, 2, 2, 2, 3, 1, 2, 3, 1, 1, 3, 2, 2, 3}, 
				{1, 1, 3, 2, 3, 2, 2, 3, 3, 1, 3, 1, 1, 3, 1, 1, 3, 3, 1, 2, 2, 1, 2, 1, 3, 2, 2, 3, 2, 1}, 
				{1, 2, 1, 3, 3, 3, 3, 1, 1, 3, 3, 2, 1, 1, 2, 1, 3, 2, 1, 2, 2, 3, 1, 3, 3, 1, 1, 3, 3, 3}, 
				{2, 3, 3, 2, 1, 3, 1, 3, 1, 3, 1, 3, 2, 2, 2, 1, 2, 3, 3, 2, 1, 1, 3, 1, 2, 2, 1, 2, 3, 1}, 
				{3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 3, 3, 1, 3, 2, 1, 1, 3, 1, 3, 3, 2, 1, 1, 3}, 
				{3, 3, 1, 2, 3, 1, 2, 2, 1, 3, 3, 1, 1, 2, 3, 1, 1, 2, 3, 2, 3, 3, 2, 1, 3, 1, 1, 2, 2, 1}, 
				{1, 3, 2, 2, 3, 1, 2, 2, 2, 2, 2, 2, 2, 3, 2, 2, 3, 2, 1, 3, 1, 1, 3, 2, 2, 1, 1, 3, 3, 2}, 
				{2, 3, 3, 1, 1, 2, 3, 3, 2, 3, 1, 1, 1, 1, 3, 1, 2, 3, 1, 1, 2, 3, 2, 2, 2, 2, 1, 2, 1, 3}, 
				{2, 1, 1, 2, 2, 2, 3, 3, 3, 2, 3, 1, 1, 1, 2, 3, 1, 3, 1, 2, 1, 3, 1, 2, 1, 1, 3, 2, 2, 1}, 
				{3, 3, 3, 2, 1, 3, 3, 3, 1, 2, 2, 2, 3, 3, 1, 2, 2, 1, 2, 1, 3, 3, 3, 2, 2, 3, 1, 2, 2, 3}, 
				{3, 2, 2, 1, 1, 2, 3, 1, 3, 1, 3, 2, 1, 1, 1, 1, 1, 1, 2, 1, 3, 3, 1, 2, 2, 2, 1, 3, 1, 2}, 
				{1, 3, 3, 1, 3, 2, 2, 3, 3, 2, 1, 3, 3, 1, 1, 1, 3, 1, 1, 3, 1, 3, 1, 1, 3, 1, 3, 3, 2, 1}, 
				{2, 2, 1, 3, 3, 3, 3, 1, 1, 3, 3, 2, 2, 1, 2, 3, 2, 1, 1, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1}, 
				{1, 1, 1, 1, 1, 1, 1, 1, 3, 2, 2, 1, 1, 2, 3, 2, 3, 1, 3, 3, 3, 1, 3, 2, 2, 2, 1, 1, 2, 2}, 
				{3, 1, 1, 3, 1, 3, 1, 2, 1, 2, 2, 2, 2, 1, 3, 1, 3, 3, 3, 1, 1, 1, 3, 1, 3, 2, 1, 1, 1, 3}, 
				{2, 2, 2, 1, 3, 1, 3, 2, 1, 3, 1, 2, 2, 3, 2, 2, 3, 1, 1, 1, 3, 1, 1, 2, 3, 3, 2, 1, 1, 1}, 
				{1, 3, 3, 1, 3, 3, 2, 2, 1, 1, 3, 1, 3, 2, 3, 3, 3, 3, 2, 3, 3, 3, 2, 1, 2, 3, 3, 3, 1, 1}, 
				{1, 2, 2, 3, 1, 2, 3, 2, 1, 2, 3, 2, 1, 3, 3, 3, 3, 3, 1, 3, 2, 2, 2, 3, 2, 3, 1, 2, 3, 3}, 
				{2, 2, 3, 1, 1, 1, 2, 3, 1, 3, 3, 2, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 1, 2, 2, 1, 3, 1, 1, 3}};
		
		sdm.loadMap("mapfile2");

		int[][] map = sdm.getMap();
		
		assertEquals(actualMap.length, map.length);
		assertEquals(actualMap[0].length, map[0].length);
		for(int i = 0; i < actualMap.length; i++)
		{
			for(int j = 0; j < actualMap[i].length; j++)
			{
				assertEquals(actualMap[i][j], map[i][j]);
			}
		}
	}
	
	@Test(expected = FileNotFoundException.class)
	public final void testIllegalInputFileName() throws IOException
	{
		sdm.loadMap("mapfi");
	}
	
	@Test(expected = NumberFormatException.class)
	public final void testIllegalIntegerFormat() throws IOException
	{
		sdm.loadMap("mapfile3");
	}
	
	@Test(expected = NullPointerException.class)
	public final void testIllegalFileFormat() throws IOException
	{
		sdm.loadMap("mapfile4");
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
		int[][] sceneMap = sdm.getSceneType(0, 0, 6, 4);
		
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
		sceneMap = sdm.getSceneType(-1, -2, 7, 5);
		
		for(int i = 0; i < sceneMap.length; i++)
		{
			for(int j = 0; j < sceneMap[i].length; j++)
			{
				assertEquals(checkMap2[i][j], sceneMap[i][j]);
			}
		}
	}

	@Test
	public final void testGetAllKindOfTypeMap1() throws IOException
	{
		// initial state, size is 0
		assertEquals(0, sdm.getAllKindOfType().size());
		
		sdm.loadMap("mapfile1");
		ArrayList<Integer> list =  sdm.getAllKindOfType();
		
		// 4 types + default
		assertEquals(5, list.size());
		for(int i = 0; i < 5; i++) // check contain 0 ~ 4
		{
			assertTrue(list.contains(i));
		}
	}

	// doesn't contain "4" in mapfile2
	@Test
	public final void testGetAllKindOfTypeMap2() throws IOException
	{
		// initial state, size is 0
		assertEquals(0, sdm.getAllKindOfType().size());
		
		sdm.loadMap("mapfile2");
		ArrayList<Integer> list =  sdm.getAllKindOfType();
		
		// 4 types + default
		assertEquals(4, list.size());
		for(int i = 0; i < 4; i++) // check contain 0 ~ 3
		{
			assertTrue(list.contains(i));
		}
		assertFalse(list.contains(4));
	}
	
	
	
	/*
	@Test
	public void testLoadMap()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetSceneType()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllKindOfType()
	{
		fail("Not yet implemented");
	}
	 */
}
