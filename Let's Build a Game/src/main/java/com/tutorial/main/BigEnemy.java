package com.tutorial.main;

import java.awt.*;

public class BigEnemy extends GameObject{

    private Handler handler;

    public BigEnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        velx = 2;
        vely = 2;
    }

    public void tick(){
        x += velx;
        y += vely;

        if (y <= 0 || y >= Game.HEIGHT-60) vely*=-1;
        if (x <= 0 || x >= Game.WIDTH-35) velx*=-1;

        handler.addObject(new BasicTrail(x, y, ID.BasicTrail, Color.orange, 70, 70, 0.05f, handler));
    }
    public void render(Graphics g){
        g.setColor(new Color(100, 70, 36));
        g.fillRect((int) x, (int) y, 70, 70);
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 70, 70);
    }
}