package com.nobody.uim;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class Panel2 extends Panel {

	int char_flag = 0;
	String character[] = new String[5];
	int flag_select[] = new int[5];
	boolean run = true;

	Panel2(GUI gui) {
		//
		flag_select[0] = 0;
		flag_select[1] = 0;
		flag_select[2] = 0;
		flag_select[3] = 0;
		flag_select[4] = 0;
		//
		character[0] = "Press";// 沒選時
		character[1] = "Press";
		character[2] = "Press";
		character[3] = "Press";
		character[4] = "Press";
		//
		JLayeredPane layer = gui.getLayeredPane();
		////////
		System.out.println(gui.userID);
		gui.client.inputID(91, gui.userID);
		////////
		JLabel banner = new JLabel("選擇角色");
		banner.setBounds(0, 0, 180, 50);
		banner.setFont(new Font("標楷體", Font.BOLD, 40));
		banner.setForeground(Color.blue);
		this.add(banner);
		//// char pic and button
		pic_create(".//image//1-1.png", ".//image//1-2.png", 120, 100);
		pic_create(".//image//2-1.png", ".//image//2-2.png", 270, 100);
		pic_create(".//image//3-1.png", ".//image//3-2.png", 420, 100);
		pic_create(".//image//4-1.png", ".//image//4-2.png", 570, 100);
		//// select tag 1 戰, 2牧, 3法, 4弓
		select_create(120, 350, 1, layer, gui);
		select_create(270, 350, 2, layer, gui);
		select_create(420, 350, 3, layer, gui);
		select_create(570, 350, 4, layer, gui);
		////
		this.setBak(this, ".//image//bg 800.jpg");
		/// call a thread to listen other client with server
		listener(gui);
		///////

	}

	void pic_create(String pic1, String pic2, int x, int y) {
		final Icon icon1 = new ImageIcon(pic1);
		final Icon icon2 = new ImageIcon(pic2);
		final JLabel pic = new JLabel(icon1);
		pic.setBounds(x, y, icon1.getIconWidth(), icon1.getIconHeight());
		pic.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				pic.setIcon(icon2);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				pic.setIcon(icon1);
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
		this.add(pic);

	}

	void select_create(int x, int y, final int flag, JLayeredPane layer, final GUI gui) {
		Icon icon1 = new ImageIcon(".//image//click.png");
		JLabel pic = new JLabel(icon1);

		pic.setBounds(x, y, icon1.getIconWidth(), icon1.getIconHeight());
		pic.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (char_flag == 0 && flag_select[flag] == 0) {
					gui.client.selectchar(92, flag);
					String message = gui.client.message();
					if (message.compareTo("ok") == 0) {
						char_flag = flag;
					}
				} else if (char_flag == flag) {
					gui.client.selectchar(93, flag);
					String message = gui.client.message();
					if (message.compareTo("ok") == 0) {
						character[flag] = "Press";
						char_flag = 0;
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
		this.add(pic);
		//////////////
		final JLabel id = new JLabel("Press");
		id.setBounds(x + 5, y + 10, 180, 50);
		id.setFont(new Font("標楷體", Font.BOLD, 35));
		id.setName(Integer.toString(flag));
		layer.add(id);
		//////////
		Thread t = new Thread() {
			public void run() {
				while (run) {
					id.setText(character[flag]);
					gui.repaint();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		t.start();
		//////////
	}

	void buttonrenew(GUI gui) {
		gui.client.inputMoves(94);
		String message = gui.client.message();
		///
		flag_select[0] = 0;
		flag_select[1] = 0;
		flag_select[2] = 0;
		flag_select[3] = 0;
		flag_select[4] = 0;
		///
		character[0] = "Press";// 沒選時
		character[1] = "Press";
		character[2] = "Press";
		character[3] = "Press";
		character[4] = "Press";
		///
		String wordbox[] = message.split(",");
		for (int i = 0; i < wordbox.length; i++) {

			int number = Integer.parseInt(wordbox[i].split(" ")[0]);
			if (number != 0) {
				String name = wordbox[i].split(" ")[1];
				character[number] = name;
				flag_select[number] = 1;
			}
		}
		///
	}

	void listener(final GUI gui) {
		Thread t = new Thread() {
			public void run() {
				while (true) {
					buttonrenew(gui);
					gui.repaint();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int con = 0;
					for (int i = 1; i <= 4; i++) {
						if (flag_select[i] != 0) {
							con++;
						}
					}
					if (con == 4) {
						gui.client.char_flag = char_flag;
						gui.panelcard3 = new Panel3(gui);
						gui.contentPane.add(gui.panelcard3, "3");
						gui.card.show(gui.contentPane, "3");
						run = false;
						System.out.println("complete.");
						gui.flag = true;

						break;
					}
				}

			}
		};
		t.start();
	}

}
