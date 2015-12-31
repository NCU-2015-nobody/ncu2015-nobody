package com.nobody.uim;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import com.nobody.tcpcm.Client;
import com.nobody.tcpsm.Server;

public class ServerClick implements MouseListener {

	GUI gui;

	ServerClick(GUI gui) {
		this.gui = gui;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("test click: " + e.getComponent().getName());
		/// initserver
		try {
			gui.server.initTCPServer();
			System.out.println(gui.server.IP);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// ///init client
		// client.connectServer("192.168.0.107");
		// //client to server
		// //////////////////////////////////////
		// //change to 1
		// gui.panelcard1_0 = new Panel1_0(gui);
		// gui.contentPane.add(gui.panelcard1_0,"1_0");
		// gui.card.show(gui.contentPane, "1_0");
		// //////////////////////////////////////////

		boolean atflag = true;
		System.out.println("Server cliick hash code :" + gui.hashCode());
		while (atflag == true) {
			if (gui.server.getClientTable().size() == 4) {
				System.out.println("Client size change to 4");
				gui.flag = true;
				atflag = false;
			}
		}
		System.out.println("gui getflag = " + gui.getFlag() + ",gui flag = " + gui.flag + ",exit client size  loop");
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
