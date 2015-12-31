package com.nobody.uim;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Panel3 extends Panel{
	Panel3(GUI gui){
		JLabel banner=new JLabel();
		banner.setBounds(0,0,180,50);
		banner.setFont(new Font("標楷體",Font.BOLD,40));
		banner.setForeground(Color.blue);
		this.add(banner);
		gui.client.inputMoves(96);
		banner.setText(gui.client.message());
		/////////////////
		this.setBak(this, ".//image//bg 800.jpg");
	}

}
