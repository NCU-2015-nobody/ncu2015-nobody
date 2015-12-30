package com.nobody.uim;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Panel1_0 extends Panel{
	//server start and give username then login p.s show server IP
	Panel1_0(final GUI gui){
		JLabel label = new JLabel("UserID: ");
		label.setFont(new Font("標楷體",Font.BOLD,20));
		label.setBounds(190, 250, 120, 40);
		final JTextField box = new JTextField();
		box.setBounds(300, 250, 150, 40);
		this.add(label);
		this.add(box);
		//////////////
		final String ip = gui.server.IP;
		JLabel serverip = new JLabel("ServerIP:"+ip);
		serverip.setFont(new Font("標楷體",Font.BOLD,20));
		serverip.setForeground(Color.red);
		serverip.setBounds(250, 200, 300, 50);
		this.add(serverip);
		//////////////
		JButton button = new JButton("Return");	
		button.setBounds(600,410,120,40);
		button.setName("Return");
		button.addMouseListener(new Return_button(gui));
		this.add(button);
		/////////////
		JButton button2 = new JButton("Start");	
		button2.setBounds(600,250,120,40);
		button2.setName("Start");
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					gui.client.connectServer(ip);
					gui.userID=box.getText();
					////
					gui.panelcard1 = new Panel1(gui);
					gui.contentPane.add(gui.panelcard1,"1");
					gui.card.show(gui.contentPane, "1");
					///
			}
		});
		this.add(button2);
		/////////////
		this.setBak(this, ".//image//bg 800.jpg");
	}

}
