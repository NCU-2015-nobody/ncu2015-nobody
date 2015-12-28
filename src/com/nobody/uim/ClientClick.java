package com.nobody.uim;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.nobody.tcpcm.Client;
import com.nobody.tcpsm.Server;

public class ClientClick implements MouseListener{
	Client client = null;
	Container contentPane=null;
	CardLayout card=null;
	ClientClick(Container contentPane,CardLayout card,Client client){
		this.client=client;
		this.contentPane=contentPane;
		this.card=card;
	}
	@Override
	public  void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("test click: "+e.getComponent().getName());
		//init client
		
		//pop out ip key in
		
		//connect to server
		
		//change to 1
		card.show(contentPane, "1_1");
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
