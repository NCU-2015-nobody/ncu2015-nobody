package com.nobody.uim;

import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.nobody.tcpcm.Client;
import com.nobody.tcpsm.Server;

public class GUI extends JFrame{
	boolean flag = false ;
	CardLayout card=null;
	Container contentPane =null;
	JPanel panelcard0=null; //start menu
	JPanel panelcard1_0=null; //Server start
	JPanel panelcard1_1=null; //Client login
	JPanel panelcard1=null; //waiting login
	JPanel panelcard2=null; //choose char
	JPanel panelcard3=null; //loading
	String userID ="";
	//////// 
	public Server server ;
	public Client client ;
	////////
	GUI(Server server,Client client){
		this.server=server;
		this.client=client;
		this.setTitle("GUI test");
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		contentPane = this.getContentPane();
		////////////////////////
		card= new CardLayout();
		contentPane.setLayout(card);
		///////////////////////
		panelcard0 = new Panel0(this);
		//////////////////////
		//panelcard1 = new Panel1(this);
		//////////////////////
		//panelcard1_0 = new Panel1_0(this);
		//////////////////////
		panelcard1_1 = new Panel1_1(this);
		/////////////////////
		//panelcard2 = new Panel2(this);
		/////////////////////
		contentPane.add(panelcard0,"0");
		//contentPane.add(panelcard1,"1");
		//contentPane.add(panelcard1_0,"1_0");
		contentPane.add(panelcard1_1,"1_1");
		//contentPane.add(panelcard2,"2");
	}
	public boolean getFlag(){
		return flag ;
	}

}
