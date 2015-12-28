package com.nobody.uim;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;



public class Panel0 extends Panel{
	Panel0(GUI gui){
		////////////setButton
		//buttonCreate("Start as Server",panel,340,300,120,40);
		JButton button_server = new JButton("Start as Server");	
		button_server.setBounds(340,270,120,40);
		button_server.setName("Server");
		button_server.addMouseListener(new ServerClick(gui));
		this.add(button_server);
		//buttonCreate("Start as Client",panel,340,370,120,40);
		JButton button_client = new JButton("Start as Client");	
		button_client.setBounds(340,340,120,40);
		button_client.setName("Client");
		button_client.addMouseListener(new ClientClick(gui.contentPane,gui.card,gui.client));
		this.add(button_client);
		//buttonCreate("Exit",panel,350,440,100,40);
		JButton button_exit = new JButton("Exit");	
		button_exit.setBounds(350,410,100,40);
		button_exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
						System.exit(0);
			}
		});
		this.add(button_exit);
		////////////////
		this.setBak(this, ".//bg 800.jpg");
	}
}
