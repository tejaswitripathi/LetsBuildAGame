package com.tutorial.main;

import java.awt.*;

public class FastEnemy extends GameObject {

    private Handler handler;

    public FastEnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        velx = 8;
        vely = 8;
    }

    public void tick(){
        x += velx;
        y += vely;

        if (y <= 0 || y >= Game.HEIGHT-32) vely*=-1;
        if (x <= 0 || x >= Game.WIDTH-16) velx*=-1;

        handler.addObject(new BasicTrail(x, y, ID.BasicTrail, Color.pink, 10, 10, 0.03f, handler));
    }
    public void render(Graphics g){
        g.setColor(Color.pink);
        g.fillRect((int) x, (int) y, 10, 10);
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 16, 16);
    }


}
