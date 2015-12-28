package com.nobody.uim;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PopoutError extends JFrame{
	PopoutError(String message){
		this.setTitle("Error");
		this.setSize(200, 100);
		this.setLocation(250, 250);
	
		JLabel label = new JLabel(message);
		label.setFont(new Font("標楷體",Font.BOLD,20));
		label.setBounds(0, 0, 50, 50);
		this.add(label);
		this.setVisible(true);
		
	}
}
