package com.nobody.uim;

import javax.swing.JFrame;

import com.nobody.tcpcm.Client;
import com.nobody.tcpsm.Server;
public class Main {
	public static void main(String agrs[]){
		Server server = new Server() ;
		Client client = new Client() ;
		
		GUI gui = new GUI(server,client);
		gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // startup server or client
     		if(server.flag_server==1) // create server
     		{
     			// if user choose create server, enter if
     			
     			
     			
     			/*
     			 * create UDPbc 
     			 */
     			
     			
     			
     		} // end of create server
     		
     		
     		// create client
     		
     		/*
     		 * create dom
     		 */
     		
     		
     		/*
     		 * create TCP server and startup
     		 */
     		
     		
     		/*
     		 * create UDP server
     		 */
     		
     		
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
