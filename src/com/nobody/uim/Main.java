package com.nobody.uim;

import javax.swing.JFrame;

import com.nobody.cdc.CDC;
import com.nobody.dom.DOM;
import com.nobody.tcpcm.Client;
import com.nobody.tcpsm.Server;
import com.nobody.udpbc.UDPBC;
import com.nobody.udpus.UDPUS;
public class Main {
	public static void main(String agrs[]){
		Server server = new Server() ;
		Client client = new Client() ;
		
		CDC cdc ;
		
		GUI gui = new GUI(server,client);
		gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // startup server or client
     		if(server.flag_server==1) // create server
     		{
     			// if user choose create server, enter if
     			
     			cdc = server.getCDC() ;
     			
     			/*
     			 * create UDPbc 
     			 */
     			UDPBC udpbc = new UDPBC(server, cdc) ;
     			try {
					udpbc.startUDPBroadCast() ;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
     		} // end of create server
     		
     		
     		// create client
     		
     		/*
     		 * create dom
     		 */
     		DOM dom = new DOM () ;
     		
     		
     		/*
     		 * create UDP server
     		 */
     		UDPUS udpus = new UDPUS(dom) ;
     		
     		/*
     		 * create client frame
     		 * 
     		 * add actionListener
     		 */
     		
     		
     		/*
     		 * create renderEngine
     		 * 
     		 * add three renders
     		 * 
     		 * add render thread
     		 */
        
	}
}
