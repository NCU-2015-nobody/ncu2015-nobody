package com.nobody.udpus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.nobody.dom.DOM;

public class UDPUS extends Thread {
	private int port = 5566;
	private Socket socket;
	private ServerSocket serverSocket;
	private DOM dom;
	private boolean flag;

	public UDPUS(DOM dom) {
		this.dom = dom;
		flag = true;
		try {
			serverSocket = new ServerSocket(port);
		} catch (Exception e) {
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
						String data[] = msg.split(",");
						if (data[0].equals("add")) {
							if (data[1].equals("chara")) {
								dom.addVirtualCharacter(Integer.valueOf(data[2]), Integer.valueOf(data[3]),
										Integer.valueOf(data[4]), Integer.valueOf(data[5]), Integer.valueOf(data[6]));
							}
							if (data[1].equals("mon")) {
								dom.addMonster(Integer.valueOf(data[2]), Integer.valueOf(data[3]),
										Integer.valueOf(data[4]), Integer.valueOf(data[5]), Integer.valueOf(data[6]));
							}
						}

						if (data[0].equals("update")) {
							if (data[1].equals("chara")) {
								dom.updateVirtualCharacter(Integer.valueOf(data[2]), Integer.valueOf(data[3]),
										Integer.valueOf(data[4]), Integer.valueOf(data[5]), Integer.valueOf(data[6]));
							}
							if (data[1].equals("mon")) {
								dom.updateMonsterStatus(Integer.valueOf(data[2]), Integer.valueOf(data[3]),
										Integer.valueOf(data[4]), Integer.valueOf(data[5]), Integer.valueOf(data[6]));
							}
						}
					}
				}
				br.close();
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
