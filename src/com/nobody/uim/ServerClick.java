package com.nobody.uim;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import com.nobody.tcpcm.Client;
import com.nobody.tcpsm.Server;

public class ServerClick implements MouseListener{

	GUI gui;
	ServerClick(GUI gui){
		this.gui=gui;

	}
	@Override
	public  void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("test click: "+e.getComponent().getName());
		///initserver
		try {
			gui.server.initTCPServer();
			System.out.println(gui.server.IP);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		///init client
//		client.connectServer("192.168.0.107");
//		//client to server
		//change to 1
		gui.panelcard1 = new Panel1(gui);
		gui.contentPane.add(gui.panelcard1,"1");
		gui.card.show(gui.contentPane, "1");
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
