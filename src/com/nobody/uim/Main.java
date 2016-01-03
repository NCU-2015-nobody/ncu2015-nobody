package com.nobody.uim;

import java.io.IOException;
import javax.swing.JFrame;
import com.nobody.cdc.CDC;
import com.nobody.dom.DOM;
import com.nobody.renderEngine.GameCanvas;
import com.nobody.renderEngine.GameFrame;
import com.nobody.renderEngine.Listener;
import com.nobody.renderEngine.RenderThread;
import com.nobody.renderEngine.SRE;
import com.nobody.renderEngine.SceneDataModule;
import com.nobody.renderEngine.SceneRender;
import com.nobody.renderEngine.UIRender;
import com.nobody.tcpcm.Client;
import com.nobody.tcpsm.Server;
import com.nobody.udpbc.UDPBC;
import com.nobody.udpus.UDPUS;

public class Main {
	public static void main(String agrs[]) {
		Server server = new Server();
		Client client = new Client();
		boolean isServer = false;

		CDC cdc;

		GUI gui = new GUI(server, client);
		gui.setVisible(true);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		boolean flag = false;
		System.out.println("main cliick hash code :" + gui.hashCode());
		while (flag == false) {
			flag = gui.getFlag();
			// System.out.println(flag);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("exit while loop");
		gui.setVisible(false);
		// startup server or client
		System.out.println("server.flag_server" + server.flag_server);
		if (server.flag_server == 1) // create server
		{
			// if user choose create server, enter if

			cdc = server.getCDC();
			isServer = true;

			/*
			 * create UDPbc
			 */
			UDPBC udpbc = new UDPBC(server, cdc);
			try {
//				Thread.sleep(5000);
				udpbc.startUDPBroadCast();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// start monster thread
			try {
				System.out.println("In the Main, try updateMonsterThread")  ;
				cdc.updateMonsterThread();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} // end of create server
		else// create client
		{
			/*
			 * create dom
			 */
			DOM dom = new DOM(client);
			client.getDOM(dom);
			/*
			 * create UDP server
			 */
			UDPUS udpus = new UDPUS(dom, isServer);
			udpus.start();

			/*
			 * create sdm
			 */
			SceneDataModule sdm = new SceneDataModule();
			try {
				sdm.loadMap("mapfile.txt");
			} catch (IOException e) {
				e.printStackTrace();
			}

			/*
			 * create canvas and listener for the frame
			 */
			GameCanvas canvas = new GameCanvas(33, 21);
			canvas.loadSceneImage(sdm.getAllKindOfType());

			Listener listener = new Listener(client);

			/*
			 * create renderEngine
			 * 
			 * SceneRender SpriteRender UIRender
			 */
			SceneRender background = new SceneRender(sdm, dom, canvas);
			background.renderScene();
			
			SRE spriteRender = new SRE(dom, canvas);
			
			UIRender uiSystem = new UIRender(dom, canvas);

			/*
			 * create RenderThread and startup thread
			 */
			RenderThread renderThread = new RenderThread(background, spriteRender, uiSystem);
			Thread thread = new Thread(renderThread);

			/*
			 * create frame
			 */
			GameFrame frame = new GameFrame(canvas, listener);
			
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}

			thread.start();
		}
	}
}
