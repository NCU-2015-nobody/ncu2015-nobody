package com.nobody.tcpsm;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.net.*;



public class Server {
	public static int onlineCount=0;
	public String IP="";
	public static ServerSocket ss = null;
	static Vector<String>client = new Vector<String>();
	public static CDC cdc = null;
	static Thread listener;
	static Vector<Userfile> table = new Vector<Userfile>();
	public Server(){
			cdc=new CDC();
	}
	public void initTCPServer() throws IOException  {
		try {			
			ss = new ServerSocket(9999);
			System.out.println("伺服器已啟動...");
			InetAddress myComputer = InetAddress.getLocalHost() ;
			System.out.println("IP:"+myComputer.getHostAddress());
			IP=myComputer.getHostAddress().toString();
			listener=new Thread(){
				public void run(){
					Socket s = null;
					while (true) {
						try {
							s = ss.accept();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							System.out.println("與Client 連線中斷");	
							//e.printStackTrace();
							break;
						}
			            Thread t = new Thread(new MyThread(s,client,cdc,table));
			            t.start();
					}
				}
			};listener.start();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("伺服器啟動失敗...");
			throw e1;
		}
	}
	public void shutdown() throws InterruptedException{
		try {
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public Vector<String> getClientTable(){
		return client;
	}
	public Vector<Userfile> charTable(){
		return table;
	}
	
}
