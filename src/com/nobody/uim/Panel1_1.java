package com.nobody.uim;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

//for Client
public class Panel1_1 extends Panel{
	Panel1_1(GUI gui){
		JLabel ServerIP = new JLabel("ServerIP: ");
		ServerIP.setFont(new Font("標楷體",Font.BOLD,20));
		ServerIP.setBounds(190, 250, 120, 40);
		JTextField box = new JTextField();
		box.setBounds(300, 250, 120, 40);
		this.add(ServerIP);
		this.add(box);
		//////////////
		JLabel userID = new JLabel("UserID: ");
		userID.setFont(new Font("標楷體",Font.BOLD,20));
		userID.setBounds(190, 300, 120, 40);
		JTextField box2 = new JTextField();
		box2.setBounds(300, 300, 120, 40);
		this.add(userID);
		this.add(box2);
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
					if(gui.client.connectServer(box.getText())==true){				
						gui.userID=box2.getText();
						/////////
						gui.panelcard1 = new Panel1(gui);
						gui.contentPane.add(gui.panelcard1,"1");
						gui.card.show(gui.contentPane, "1");
						/////////
					}else{
						//popout error
						PopoutError pop = new PopoutError("wrong connect");
					}
			}
		});
		this.add(button2);
		/////////////
		this.setBak(this, ".//bg 800.jpg");
	}
}
