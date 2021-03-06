package com.nobody.udpus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.nobody.dom.DOM;

public class UDPUS extends Thread {
	private int port = 5567;
	private Socket socket;
	private ServerSocket serverSocket;
	private DOM dom;
	private boolean flag;
	private boolean isServer;

	public UDPUS(DOM dom, boolean isServer) {
		this.dom = dom;
		this.isServer = isServer;

		// if (isServer) {
		// this.port = 5566;
		// }
		flag = true;
		try {
			System.out.println("isServer:" + isServer + ", port" + this.port);
			serverSocket = new ServerSocket(port);
		} catch (Exception e) {
			System.out.println("serversocket error");
			e.printStackTrace();
		}
	}

	public void run() {
		System.out.println("Server Start");
		while (flag == true) {
			try {
				socket = serverSocket.accept();
				System.out.println("Connect with " + socket.getInetAddress());

				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				while (flag == true) {
					String msg = br.readLine();
					if (msg != null) {
						System.out.println("Get mseeage :" + msg);
						String data[] = msg.split(",");
						if (data[0].equals("add")) {
							if (data[1].equals("chara")) {
								System.out.println("call add chara");
								dom.addVirtualCharacter(Integer.valueOf(data[2]), Integer.valueOf(data[3]),
										Integer.valueOf(data[4]), Integer.valueOf(data[5]), Integer.valueOf(data[6]));
							}
							if (data[1].equals("mon")) {
								System.out.println("call add mon");
								dom.addMonster(Integer.valueOf(data[2]), Integer.valueOf(data[3]),
										Integer.valueOf(data[4]), Integer.valueOf(data[5]), Integer.valueOf(data[6]));
							}
						}

						if (data[0].equals("update")) {
							if (data[1].equals("chara")) {
								System.out.println("update chara");
								dom.updateVirtualCharacter(Integer.valueOf(data[2]), Integer.valueOf(data[3]),
										Integer.valueOf(data[4]), Integer.valueOf(data[5]), Integer.valueOf(data[6]));
							}
							if (data[1].equals("mon")) {
								System.out.println("update mon");
								dom.updateMonsterStatus(Integer.valueOf(data[2]), Integer.valueOf(data[3]),
										Integer.valueOf(data[4]), Integer.valueOf(data[5]), Integer.valueOf(data[6]));
							}
						}
					}
				}
				br.close();
				socket.close();
			} catch (Exception e) {
				System.out.println("exception 2");
				e.printStackTrace();
			}
		}
	}
}
