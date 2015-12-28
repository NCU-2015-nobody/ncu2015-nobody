package com.nobody.uim;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.nobody.tcpcm.Client;
import com.nobody.tcpsm.Server;
public class Return_button implements MouseListener{
	GUI gui;
	Return_button(GUI gui){
		this.gui=gui;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(gui.client.online_flag==1){
			gui.client.inputMoves(99);
		
		}
		gui.card.show(gui.contentPane, "0");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
