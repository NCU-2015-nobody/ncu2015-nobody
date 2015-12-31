package com.nobody.renderEngine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

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
	
	
	private Image[] picture = new Image[100]; //欲繪製圖片
	private Image[] preLoad = new Image[68];//先載入圖片
	private Point p[] = new Point[100]; //傳入的絕對坐標
	private int attacked=0; //上次攻擊狀態
	private Point p_player; //主角座標
	int attack_con = 0; //計算攻擊圖片持續時間
	int a[] = new int[20];
	
	
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
	 */
	
	public void updateSprite(Vector v) throws IOException{
		
		//v=attack+dir+job+point+filenaame+point
		
		String s[] = new String[100]; //傳入的圖片名稱
		int attackNow; //存攻擊與否
		int dir; //存面對方向
		int job; //存主角職業
		
		
		//取出繪圖所需資訊
		p_player = (Point) v.get(3);
		v.remove(3);
		job = (int) v.get(2);
		v.remove(2);
		dir = (int) v.get(1);
		v.remove(1);
		attackNow = (int) v.get(0);
		v.remove(0);
		//取出繪圖所需資訊
		
		//判斷是否需顯示攻擊圖片
		if(attacked == 0){
			if(attackNow == 1){
				attack_con = 3;
				v = selectAttack(dir,job,v);
				/*
				 * 攻擊動畫
				 */
			}
			else if(attack_con > 0){
				v = selectAttack(dir,job,v);
				/*
				 * 繼續攻擊動畫
				 */
				
				System.out.println("attack");
				attack_con--;
			}
		}
		else if(attack_con > 0){
			v = selectAttack(dir,job,v);
			/*
			 * 繼續攻擊動畫
			 */
			System.out.println("attack");
			attack_con--;
		}
		//判斷攻擊結束
		
		
		attacked = attackNow;//記錄上一次攻擊狀況
		
		
		//清空上次update的資訊
		for(int i=0;i<100;i++)	{
			p[i] = null;
			s[i] = null;
		}
		
		
		//把vector v 分成 "檔名" 和 "坐標" 兩個陣列
		for(int i = 0;i<v.size();i++){
			if(v.get(i) instanceof String){
				s[i/2] = (String) v.get(i);
				System.out.println(i/2+"//"+s[i/2]);
				switch(s[i/2]){
					//死亡
					case "1-2-4":
						picture[i/2] = preLoad[66];
						break;
						
					//怪物走路
					case "0-0-0":
						animation(0, i, 0);
						break;
					case "0-0-1":
						animation(1, i, 3);
						break;
					case "0-0-2":
						animation(2, i, 6);
						break;
					case "0-0-3":
						animation(3, i, 9);
						break;
						
					//怪物站立
					case "0-1-0":
						picture[i/2] = preLoad[0];
						break;
					case "0-1-1":
						picture[i/2] = preLoad[3];
						break;
					case "0-1-2":
						picture[i/2] = preLoad[6];
						break;
					case "0-1-3":
						picture[i/2] = preLoad[9];
						break;
						
					//戰士走路
					case "1-0-0":
						animation(4, i, 12);
						break;
					case "1-0-1":
						animation(5, i, 15);
						break;
					case "1-0-2":
						animation(6, i, 18);
						break;
					case "1-0-3":
						animation(7, i, 21);
						break;
						
					//戰士站立
					case "1-1-0":
						picture[i/2] = preLoad[12];
						break;
					case "1-1-1":
						picture[i/2] = preLoad[15];
						break;
					case "1-1-2":
						picture[i/2] = preLoad[18];
						break;
					case "1-1-3":
						picture[i/2] = preLoad[21];
						break;
						
					//牧師走路
					case "2-0-0":
						animation(8, i, 24);
						break;
					case "2-0-1":
						animation(9, i, 27);
						break;
					case "2-0-2":
						animation(10, i, 30);
						break;
					case "2-0-3":
						animation(11, i, 33);
						break;
						
					//牧師站立
					case "2-1-0":
						picture[i/2] = preLoad[24];
						break;
					case "2-1-1":
						picture[i/2] = preLoad[27];
						break;
					case "2-1-2":
						picture[i/2] = preLoad[30];
						break;
					case "2-1-3":
						picture[i/2] = preLoad[33];
						break;
						
					//法師走路
					case "3-0-0":
						animation(12, i, 36);
						break;
					case "3-0-1":
						animation(13, i, 39);
						break;
					case "3-0-2":
						animation(14, i, 42);
						break;
					case "3-0-3":
						animation(15, i, 45);
						break;
						
					//法師站立
					case "3-1-0":
						picture[i/2] = preLoad[36];
						break;
					case "3-1-1":
						picture[i/2] = preLoad[39];
						break;
					case "3-1-2":
						picture[i/2] = preLoad[42];
						break;
					case "3-1-3":
						picture[i/2] = preLoad[45];
						break;
					
					//弓手走路
					case "4-0-0":
						animation(16, i, 48);
						break;
					case "4-0-1":
						animation(17, i, 51);
						break;
					case "4-0-2":
						animation(18, i, 54);
						break;
					case "4-0-3":
						animation(19, i, 57);
						break;
						
					//弓手站立
					case "4-1-0":
						picture[i/2] = preLoad[48];
						break;
						
					case "4-1-1":
						picture[i/2] = preLoad[51];
						break;
					case "4-1-2":
						picture[i/2] = preLoad[54];
						break;
					case "4-1-3":
						picture[i/2] = preLoad[57];
						break;
						
					//戰士攻擊
					case "1-3-0":
						picture[i/2] = preLoad[60];
						break;
					
					//牧師攻擊
					case "2-3-0":
						picture[i/2] = preLoad[64];
						break;
						
					//法師攻擊
					case "3-3-0":
						picture[i/2] = preLoad[61];
						break;
					case "3-3-1":
						picture[i/2] = preLoad[62];
						break;
					case "3-3-2":
						picture[i/2] = preLoad[63];
						break;
						
					//弓手攻擊
					case "4-3-0":
						picture[i/2] = preLoad[65];
						break;

				}
			}
			else if (v.get(i) instanceof Point) p[i/2] = (Point) v.get(i);
			else System.out.println("error");
		}
		
		//this.repaint();
		
	}
	
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
	
	void loadSpriteImage() throws IOException{
		
		//將所有該出現的圖片讀進picture陣列中
		Toolkit tk = Toolkit.getDefaultToolkit();
		for(int i = 0;i<68;i++){
			preLoad[i] = tk.getDefaultToolkit().getImage(i+".png");
			tk.prepareImage(preLoad[i], -1, -1, null);
		}
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
	
	private void drawSprites(Graphics g){
		Graphics2D g2D = (Graphics2D) g;
		for(int i=0;i<100;i++){
			if(p[i]!=null){
				g2D.drawImage(picture[i],(int)p[i].getX()-(int)p_player.getX()+400
										,(int)p[i].getY()-(int)p_player.getY()+250
										,this);
				//p[i].getX()-x+400, p[i].getY()-y+200
			}
		}
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
	
	private Vector selectAttack(int dir,int job,Vector v){
		Point p_attack1 = new Point();
		Point p_attack2 = new Point();
		Point p_attack3 = new Point();
		
		switch(dir){
		case 0:
			p_attack1.setLocation(375, 250);
			p_attack2.setLocation(375-25, 250);
			p_attack3.setLocation(375-50, 250);
			break;
		case 1:
			p_attack1.setLocation(400, 225);
			p_attack2.setLocation(400, 225-25);
			p_attack3.setLocation(400, 225-50);
			break;
		case 2:
			p_attack1.setLocation(425, 250);
			p_attack2.setLocation(425+25, 250);
			p_attack3.setLocation(425+50, 250);
			break;
		case 3:
			p_attack1.setLocation(400, 275);
			p_attack2.setLocation(400, 275+25);
			p_attack3.setLocation(400, 275+50);
			break;
		}
		switch(job){
		case 1:
			v.add("1-3-0");
			v.add(p_attack1);
			break;
		case 2:
			v.add("2-3-0");
			v.add(p_attack1);
			break;
			
		case 3:
			v.add("3-3-0");
			v.add(p_attack1);
			v.add("3-3-1");	
			v.add(p_attack2);	
			v.add("3-3-2");
			v.add(p_attack3);
			break;
		case 4:
			v.add("4-3-0");
			v.add(p_attack1);
			v.add("4-3-0");	
			v.add(p_attack2);	
			v.add("4-3-0");
			v.add(p_attack3);
			break;
		}
		return v;
	}
	
	private void animation(int index, int i, int j){
		switch(a[index]){
		case 0:
			picture[i/2] = preLoad[j];
			if(picture[i/2]==null) System.out.println("error");
			System.out.println("1");
			break;
		case 1:
			picture[i/2] = preLoad[j+1];
			System.out.println("2");
			break;
		case 2:
			picture[i/2] = preLoad[j];
			System.out.println("3");
			break;
		case 3:
			picture[i/2] = preLoad[j+2];
			break;
		}
		a[index]++;
		a[index] = a[index] % 4;
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
