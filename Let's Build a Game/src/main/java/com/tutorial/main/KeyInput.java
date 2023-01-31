package com.tutorial.main;

import java.awt.event.*;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private boolean[] keyDown = new boolean[4];

    public KeyInput(Handler handler){
        this.handler = handler;
        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++){
            GameObject temp = handler.object.get(i);
            if (temp.getID() == ID.Player) {
                if (key == KeyEvent.VK_W){ temp.setVely(-5); keyDown[0] = true; }
                if (key == KeyEvent.VK_S){ temp.setVely(5); keyDown[1] = true; }
                if (key == KeyEvent.VK_D){ temp.setVelx(5); keyDown[2] = true; }
                if (key == KeyEvent.VK_A){ temp.setVelx(-5); keyDown[3] = true; }
            }
        }
        if (key == KeyEvent.VK_ESCAPE) System.exit(0);
    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++){
            GameObject temp = handler.object.get(i);
            if (temp.getID() == ID.Player) {
                if (key == KeyEvent.VK_W) keyDown[0] = false; //temp.setVely(0);
                if (key == KeyEvent.VK_S) keyDown[1] = false; //temp.setVely(0);
                if (key == KeyEvent.VK_D) keyDown[2] = false; //temp.setVelx(0);
                if (key == KeyEvent.VK_A) keyDown[3] = false; //temp.setVelx(0);
                //vertical movement
                if (!keyDown[0] && !keyDown[1]) temp.setVely(0);
                //horizontal movement
                if (!keyDown[2] && !keyDown[3]) temp.setVelx(0);
            }
        }
    }
}
