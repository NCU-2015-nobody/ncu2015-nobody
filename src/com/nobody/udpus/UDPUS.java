package com.nobody.udpus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class UDPUS extends Thread {
	private int port = 5566;
	private Socket socket;
	private ServerSocket serverSocket;
	private boolean flag;

	public UDPUS() {
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

							}
							if (data[1].equals("mon")) {

							}
						}

						if (data[0].equals("update")) {
							if (data[1].equals("chara")) {

							}
							if (data[1].equals("mon")) {

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
