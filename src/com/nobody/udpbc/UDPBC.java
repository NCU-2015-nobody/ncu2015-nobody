package com.nobody.udpbc;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;
import com.nobody.cdc.*;
import com.nobody.cdc.Character;
import com.nobody.tcpsm.Server;

public class UDPBC {

	private Server tcpsm;
	private CDC cdc;
	private Socket socket[] = new Socket[4];
	private InetSocketAddress isa[] = new InetSocketAddress[4];
	private BufferedWriter bw[] = new BufferedWriter[4];
	private int port;
	private boolean flag;

	public UDPBC(Server tcpsm, CDC cdc) {
		this.tcpsm = tcpsm;
		this.cdc = cdc;
		// Set the port to 5566
		this.port = 5567;
		this.flag = true;

		// init the socket
		for (int i = 0; i < 4; i++) {
			this.socket[i] = new Socket();
		}
	}

	public void startUDPBroadCast() throws InterruptedException {
		Vector<String> ipList = this.tcpsm.getClientTable();
		Vector<String> ipList2 = new Vector<String>();
		for (String ip : ipList) {
			ip = ip.substring(1);
			ip = ip.split(":")[0];
			ipList2.add(ip);
			System.out.println("ip:" + ip);
		}

		ipList = ipList2;

		for (int i = 0; i < 4; i++) {
			try { // connect to the UDP Server
					// if (i == 0) {
					// port = 5566;
					// } else {
				port = 5567;
				// }
				System.out.println("Connect with" + ipList.get(i) + ", port:" + port);
				// if (i == 0) {
				this.isa[i] = new InetSocketAddress("127.0.0.1", port);
				// } else {
				this.isa[i] = new InetSocketAddress(ipList.get(i), port);
				// }
				this.socket[i].connect(isa[i]);
				this.bw[i] = new BufferedWriter(new OutputStreamWriter(socket[i].getOutputStream()));
				System.out.println("Connect success");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Some thing wrong! with connect" + ipList.get(i));
			}
		}

		Vector<Object> updateInfo = cdc.getUpdateInfo();
		System.out.println("get update info from cdc.");
		Iterator<Object> it = updateInfo.iterator();
		String msg = "";

		// first time call getUpdate is add
		while (it.hasNext()) {
			Object info = it.next();
			if (info instanceof Character) {
				Character chara = (Character) info;
				msg = "add,chara," + chara.clientID + "," + chara.position.getX() + "," + chara.position.getY() + ","
						+ chara.direction + "," + chara.HP;
			}

			if (info instanceof Monster) {
				Monster mon = (Monster) info;
				msg = "add,mon," + mon.objectID + "," + mon.position.getX() + "," + mon.position.getY() + ","
						+ mon.direction + "," + mon.HP + "," + mon.isBoss;
			}

			for (int i = 0; i < 4; i++) {
				try {
					System.out.println("first time broadcast message " + msg);
					bw[i].write(msg);
					bw[i].newLine();
					bw[i].flush();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// other time call getUpdate is update
		while (flag == true) {
			Thread.sleep(200);

			while (it.hasNext()) {
				Object info = it.next();
				if (info instanceof Character) {
					Character chara = (Character) info;
					msg = "update,chara," + chara.clientID + "," + chara.position.getX() + "," + chara.position.getY()
							+ "," + chara.direction + "," + chara.HP;
				}

				if (info instanceof Monster) {
					Monster mon = (Monster) info;
					msg = "update,mon," + mon.objectID + "," + mon.position.getX() + "," + mon.position.getY() + ","
							+ mon.direction + "," + mon.HP + "," + mon.isBoss;
				}

				for (int i = 0; i < 4; i++) {
					try {
						System.out.println("no first time Boardcast msg " + msg);
						bw[i].write(msg);
						bw[i].newLine();
						bw[i].flush();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		// End of the Program
		for (int i = 0; i < 4; i++) {
			try {
				bw[i].close();
				socket[i].close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
