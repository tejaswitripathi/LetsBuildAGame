package com.tutorial.main;

import java.awt.*;

public class HealthPack extends GameObject{

    private Handler handler;
    private boolean isTaken = false;

    public HealthPack(int x, int y, ID id, Handler handler, boolean isTaken){
        super(x, y, id);
        this.handler = handler;
        velx = 0;
        vely = 0;
        this.isTaken = isTaken;
    }

    public void tick(){
        return;
    }
    public void render(Graphics g){
        g.setColor(Color.green);
        g.fillRect((int) x, (int) y, 7, 7);
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 7, 7);
    }

    public boolean isTaken(){ return isTaken; }
    public void take(boolean take) { isTaken = take; }

}