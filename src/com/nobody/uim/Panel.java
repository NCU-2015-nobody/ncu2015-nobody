package com.nobody.uim;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nobody.tcpcm.Client;
import com.nobody.tcpsm.Server;

public class Panel extends JPanel{
	Panel(){
		this.setOpaque(false);
		this.setLayout(null);
		this.setVisible(true);
	}
	public void setBak(JPanel panel,String pic){
		ImageIcon img = new ImageIcon(pic);
		JLabel background = new JLabel(img);
		panel.add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
	}
}
