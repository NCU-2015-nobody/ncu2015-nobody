package com.nobody.tcpsm;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;
import com.nobody.cdc.CDC ;





public class MyThread extends Thread{
    Socket s;
    Vector<String> clist=null;
    Vector<Userfile> table = null;
    CDC cdc = new CDC();
    MyThread(Socket s,Vector<String> Client,CDC cdc,Vector<Userfile> table) {
        this.s = s;
        clist=Client;
        this.table=table;
        this.cdc=cdc;
    }
    @Override
    public void run() {
        int c_number=0;
        try {
            System.out.println(s.getRemoteSocketAddress() + "　已連線...");
            clist.addElement(s.getRemoteSocketAddress().toString());
            for(int i=0;i<clist.size();i++){
            	if(clist.elementAt(i).compareTo(s.getRemoteSocketAddress().toString())==0){
            		c_number=i;
            	}
            }
            table.addElement(new Userfile(c_number));
            c_number=Server.onlineCount;
            Server.onlineCount++;
            BufferedReader r = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter w = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
            w.println("你好．．．我是伺服器，這是伺服器的訊息   你是"+s.getRemoteSocketAddress().toString());
            ///
            while (true) {             
                String getMessage=r.readLine();//client command   (show, get ?,)
                //System.out.println ( "傳遞的訊息是：" + getMessage);
                /////////////////////////////   
                switch(getMessage.split(" ")[1]){
                	case "37":
                		//w.println("East");
                		cdc.updateCharacterStatus(findchar(s.getRemoteSocketAddress().toString()),0);
                		break;
                	case "38":
                		//w.println("North");
                		cdc.updateCharacterStatus(findchar(s.getRemoteSocketAddress().toString()),1);
                		break;
                	case "39":
                		//w.println("West");
                		cdc.updateCharacterStatus(findchar(s.getRemoteSocketAddress().toString()),2);
                		break;
                	case "40":
                		//w.println("South");
                		cdc.updateCharacterStatus(findchar(s.getRemoteSocketAddress().toString()),3);
                		break;
                	case "90":
//                		w.println("attack");
                		cdc.characterAttack(findchar(s.getRemoteSocketAddress().toString()));
                		break;
                	case "99":
                		w.println("exit");
                		s.close();
                		break;
                	case "91":
                		////set id
//                		System.out.println(getMessage);
                		 for(int i=0;i<clist.size();i++){
                         	if(clist.elementAt(i).compareTo(s.getRemoteSocketAddress().toString())==0){
                         		c_number=i;
                         	}
                        }
                		int target = 0;
                        for(int i=0;i<table.size();i++){
                        	if(table.elementAt(i).Ip_number==c_number){
                        		target=i;
                        	}
                        }
//                        System.out.println(c_number);
//                        System.out.println(clist.elementAt(c_number));
//                        System.out.println(table.elementAt(target).Ip_number);
                        table.elementAt(target).Id=getMessage.split(" ")[2];                
                		break;
                	case "92":
                		////sele_char
                		//1.check //fail
                		for(int i =0;i<table.size();i++){
                			if(table.elementAt(i).character == Integer.parseInt(getMessage.split(" ")[2])){
                				w.println("Fail");
                				break;
                			}
                		}            		
                		//ok             		
                		//changetable
                		 for(int i=0;i<clist.size();i++){
                         	if(clist.elementAt(i).compareTo(s.getRemoteSocketAddress().toString())==0){
                         		c_number=i;
                         	}
                         }
                         for(int i=0;i<table.size();i++){
                         	if(table.elementAt(i).Ip_number==c_number){
                         		table.elementAt(i).character=Integer.parseInt(getMessage.split(" ")[2]);
                         	}
                         }
                         w.println("ok"); 
                		break;
                	case "93":
                		////canel_char
                		//changetable
                		System.out.println(getMessage);
                        for(int i=0;i<table.size();i++){
                        	if(table.elementAt(i).character==Integer.parseInt(getMessage.split(" ")[2])){
                        		System.out.println(table.elementAt(i).character);
                        		System.out.println(table.elementAt(i).Id);
                        		table.elementAt(i).character=0;               
                        	}
                        }
                        w.println("ok"); 
                		break;
                	case "94":
                		////check_char
                		//read table
                		//rewrite message 1, xxx ,2, asd ,3, abc ,4, gg
                		String message="";
                		for(int i =0;i<table.size();i++){
                			message=message+table.elementAt(i).character+" "+table.elementAt(i).Id+",";
                		}
                		w.println(message);
                		break;
                	case "95":
                		w.println(Server.onlineCount);
                		break;
                	case "96":
                		//final check
                		w.println(findchar(s.getRemoteSocketAddress().toString()));
                		cdc.addVirtualCharacter(findchar(s.getRemoteSocketAddress().toString()));
                		break;
                	default:
                		w.println("None");
                		
                		break;
                }
                
                //////////////////////////////
                
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.err.println(s.getRemoteSocketAddress() + " 已離線");
            for(int i=0;i<clist.size();i++){
            	if(clist.elementAt(i).compareTo(s.getRemoteSocketAddress().toString())==0){
            		c_number=i;
            	}
            }
            clist.removeElementAt(c_number);
            int target = 0;
            for(int i=0;i<table.size();i++){
            	if(table.elementAt(i).Ip_number==c_number){
            		target=i;
            	}
            }
           table.removeElementAt(target);
        }
        Server.onlineCount--;
    }
    int findchar(String ip){
    	int character=0;
    	for(int i=0;i<clist.size();i++){
        	if(clist.elementAt(i).compareTo(ip)==0){
        		for(int j=0;j<table.size();j++){
                	if(table.elementAt(j).Ip_number==i){
                		character=table.elementAt(j).character;
                	}
                }
        	}
        }
    	return character;
    }
}
