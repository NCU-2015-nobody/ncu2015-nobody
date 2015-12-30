package com.nobody.renderEngine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameCanvas extends JPanel implements UpdateScene, UpdateUI
{
	private int blockWidth;
	private int blockHeight;

	private final int sceneTypeMax = 7;
	private BasicBlock[][] sceneBlocks;
	private Image[] sceneImage;
	
	/*
	 * for RenderSpriteEngine
	 */
	
	private Image uiBackground;
	private Image[] characterIcon;
	private Image hp;
	private Image hpBackground;
	private Image[] skill;
	private Image[] skillCD;
	private int[] hpPecentage;
	private boolean[] cdFlag;
	
	public GameCanvas(int blockWidth, int blockHeight)
	{
		this.blockWidth = blockWidth;
		this.blockHeight = blockHeight;
		
		sceneBlocks = new BasicBlock[blockHeight][blockWidth];
		sceneImage = new Image[sceneTypeMax];
		
		characterIcon = new Image[4];
		skill = new Image[4];
		skillCD = new Image[4];

		initialBlocks();
		initialHPPercentage();
		initialCDFlag();
		
		loadUIImage();
	}
	
	@Override
	public void updateScene(int[][] sceneType)
	{
		updateSceneBlocks(sceneType);
		
		//this.repaint();
	}
	
	/*
	 * for RenderSpriteEngine
	 * 
	@Override
	public void updateSprite()
	{
		
	}
	*/
	
	@Override
	public void updateUI(int[] hpPecentage, boolean[] cdFlag)
	{
		this.hpPecentage = hpPecentage;
		this.cdFlag = cdFlag;
		
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        
        drawScene(g);
        
        drawSprites(g);
        
        drawUI(g);
    }
	
	public void loadSceneImage(ArrayList<Integer> list)
	{
		BufferedImage picture = null;
		
		for(int i = 0; i < list.size(); i++)
		{
			try
			{
				picture = ImageIO.read(new File("./image/" + BasicBlock.TYPE_NAME[list.get(i)] + ".jpg"));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			sceneImage[list.get(i)] = new ImageIcon(picture).getImage();
		}
	}
	
	private void loadSpriteImage()
	{
		/*
		 * for RenderSpriteEngine
		 */
	}
	
	private void loadUIImage()
	{
		BufferedImage picture = null;
		
		try
		{
			picture = ImageIO.read(new File("./image/hp.jpg"));
			hp = setIconSize(new ImageIcon(picture), 100, 30).getImage();

			picture = ImageIO.read(new File("./image/hp_background.jpg"));
			hpBackground = new ImageIcon(picture).getImage();
			
			picture = ImageIO.read(new File("./image/ui_background.jpg"));
			uiBackground = setIconSize(new ImageIcon(picture), 180, 60).getImage();
			
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		
		for(int i = 0; i < characterIcon.length; i++)
		{
			try
			{
				picture = ImageIO.read(new File("./image/" + (i + 1) + ".png"));
				characterIcon[i] = setIconSize(new ImageIcon(picture), 50, 50).getImage();

				picture = ImageIO.read(new File("./image/" + (i + 1) + "_skill.png"));
				skill[i] = setIconSize(new ImageIcon(picture), 15, 15).getImage();
				
				picture = ImageIO.read(new File("./image/" + (i + 1) + "_skill_cd.png"));
				skillCD[i] = setIconSize(new ImageIcon(picture), 15, 15).getImage();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private void drawScene(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;
		
		for(int i = 0; i < sceneBlocks.length; i++)
		{
			for(int j = 0; j < sceneBlocks[i].length; j++)
			{
				g2D.drawImage(sceneImage[sceneBlocks[i][j].getType()], (j * 25), (i * 25), null);
			}
		}
	}
	
	private void drawSprites(Graphics g)
	{
		/*
		 * for RenderSpriteEngine
		 */
	}
	
	private void drawUI(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;
		
		for(int i = 0; i < characterIcon.length; i++)
		{
			// draw background
			g2D.drawImage(uiBackground, 35  + i * 40 + i * 150 - 10, 425 - 5, null);
			
			// draw character icon
			g2D.drawImage(characterIcon[i], 35  + i * 40 + i * 150, 425, null);
			
			// draw HP
			g2D.drawImage(hpBackground, 35  + i * 40 + i * 150 + 50 + 5, 425 + 5, null);
			if(hpPecentage[i] != 0)
			{
				hp = setIconSize(new ImageIcon(hp), hpPecentage[i], 30).getImage();
				g2D.drawImage(hp, 35  + i * 40 + i * 150 + 50 + 5, 425 + 5, null);
			}
			
			// draw skill and CD state
			g2D.drawImage(skill[i], 35  + i * 40 + i * 150 + 50 + 5, 425 + 35, null);
			if(cdFlag[i])
			{
				g2D.drawImage(skillCD[i], 35  + i * 40 + i * 150 + 50 + 5, 425 + 35, null);
			}
		}
	}
	
	private void updateSceneBlocks(int[][] sceneType)
	{
		//System.out.println("---update blocks---");
		for(int i = 0; i < sceneBlocks.length; i++)
		{
			for(int j = 0; j < sceneBlocks[i].length; j++)
			{
				sceneBlocks[i][j].setType(sceneType[i][j]);
				//System.out.print(sceneBlocks[i][j].getType() + " ");
			}
			//System.out.println();
		}
	}
	
	private void initialBlocks()
	{
		for(int i = 0; i < sceneBlocks.length; i++)
		{
			for(int j = 0; j < sceneBlocks[i].length; j++)
			{
				sceneBlocks[i][j] = new BasicBlock(BasicBlock.DEFAULT);
			}
		}
	}
	
	private void initialHPPercentage()
	{
		hpPecentage = new int[4];
		for(int i = 0; i < hpPecentage.length; i++)
		{
			hpPecentage[i] = 100;
		}
	}
	
	private void initialCDFlag()
	{
		cdFlag = new boolean[4];
		for(int i = 0; i < cdFlag.length; i++)
		{
			cdFlag[i] = false;
		}
	}
	
	private ImageIcon setIconSize(ImageIcon originIcon, int x, int y)
	{
		Image img = originIcon.getImage();
		Image newimg = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		return new ImageIcon(newimg);
	}

	@Override
	public int getBlockWidth()
	{
		return this.blockWidth;
	}

	@Override
	public int getBlockHeight()
	{
		return this.blockHeight;
	}
}
