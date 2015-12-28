package com.nobody.tcpcm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
	private String address ;// 連線的ip
    private int port ;// 連線的port
    InetSocketAddress isa = null;
    BufferedReader reader;
    PrintWriter writer;
    Socket s;
    Thread t;
    public  int online_flag=0;
    public int char_flag = 0;
	public Client(){
		port=9999;
	}
	public Boolean connectServer(String serverip){
		t =new Thread() {
	        public void run() {
	            s = new Socket();
	            isa = new InetSocketAddress(serverip, port);	                    
	            try {
	            	s.connect(isa, 10000); 
	            	online_flag=1;
	                reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
	                System.out.println("從伺服器傳來的資料：" + reader.readLine());
	                while(true){
	                	//System.out.println(online_flag);
	                }
	            } catch (Exception e) {
	            	 System.out.println("與server連線失敗");
	            	
	            }
	            //////
	        }
	    };
	    t.start();
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    if(online_flag==1){
	    	System.out.println("連線成功");
	    	return true;
	    } else{
	    	System.out.println("連線失敗");
	    	return false;
	    	}
	}
	public void inputMoves(int Movecode){
		while(online_flag==0){
			System.out.println("waiting");
		}
		//Userfile u= new Userfile();
		try {
			writer = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		writer.println(s.getLocalSocketAddress().toString()+" "+Movecode);	
	}
	public void inputID(int Movecode,String id){
		while(online_flag==0){
			System.out.println("waiting");
		}
		//Userfile u= new Userfile();
		try {
			writer = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		writer.println(s.getLocalSocketAddress().toString()+" "+Movecode+" "+id);	
	}
	public void selectchar(int Movecode,int num){
		while(online_flag==0){
			System.out.println("waiting");
		}
		//Userfile u= new Userfile();
		try {
			writer = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		writer.println(s.getLocalSocketAddress().toString()+" "+Movecode+" "+num);	
	}
	public String message() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "0";
	}
	public String address(){
		return s.getLocalSocketAddress().toString();
	}
	public int character(){
		return char_flag;
	}
}
