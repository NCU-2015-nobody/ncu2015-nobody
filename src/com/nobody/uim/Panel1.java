package com.nobody.uim;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Panel1 extends Panel{
	Thread t=null;
	public static boolean run = true;
	Panel1(final GUI gui){
		JLabel id=new JLabel("現在登入人數:");
		id.setBounds(250,250,180,50);
		id.setFont(new Font("標楷體",Font.BOLD,20));
		this.add(id);
		/////////////////
		final JLabel online=new JLabel();
		online.setBounds(400,250,180,50);
		online.setFont(new Font("標楷體",Font.BOLD,20));
		this.add(online);
		/////////////////
		JButton button = new JButton("Return");	
		button.setBounds(600,410,120,40);
		button.setName("Return");
		button.addMouseListener(new Return_button(gui));
		this.add(button);
		/////////////////
		this.setBak(this, ".//image//bg 800.jpg");
		///////////////
		t = t =new Thread() {
	        public void run() {
	        	while(run){
	        		gui.client.inputMoves(95);
	        		int number=Integer.parseInt(gui.client.message());
		        	online.setText(String.valueOf(number));
		        	gui.repaint();
		        	try {
		        		Thread.sleep(1000);
		        	} catch (InterruptedException e) {
		        		// TODO Auto-generated catch block
		        		e.printStackTrace();
		        	}
		        	if(number==4){
		        		gui.panelcard2 = new Panel2(gui);
		        		gui.contentPane.add(gui.panelcard2,"2");
		        		gui.card.show(gui.contentPane, "2");
			        	try {
			        		Thread.sleep(1000);
			        	} catch (InterruptedException e) {
			        		// TODO Auto-generated catch block
			        		e.printStackTrace();
			        	}
			        	break;
		        	}
		        	
		        	
	        	}
	        }
		};t.start();
		
	}
	public static void close(){
		run=false;
	}
}
