package com.nobody.renderEngine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.nobody.dom.DOM;
import com.nobody.tcpcm.Client;

public class Listener implements KeyListener
{
	private Client client;
	
	public Listener(Client client)
	{
		this.client = client;
	}
	
	@Override
    public void keyTyped(KeyEvent e) {
        //System.out.println("Keyboard input on screen " + curFocus + ": " + e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if((key >= 37 && key <= 40) || key == 90)
        {
            client.inputMoves(key);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
