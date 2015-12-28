package com.nobody.uim;

import javax.swing.JFrame;

import com.nobody.tcpcm.Client;
import com.nobody.tcpsm.Server;
public class Main {
	public static Server server = new Server();
	public  static Client client = new Client();
	public static void main(String agrs[]){
		GUI gui = new GUI(server,client);
		gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
