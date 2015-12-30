package com.nobody.renderEngine;

import javax.swing.JFrame;

public class GameFrame extends JFrame
{
	private GameCanvas canvas;
	private Listener listener;
	
	public GameFrame(GameCanvas canvas, Listener listener)
	{
		super("Nobody");

		this.canvas = canvas;
		this.listener = listener;
		
		this.setSize(815, 535);
		
		this.add(this.canvas);
		this.addKeyListener(this.listener);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
