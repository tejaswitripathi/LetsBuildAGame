package com.tutorial.main;

import java.awt.*;

public class BasicEnemy extends GameObject{

    private Handler handler;

    public BasicEnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        velx = 5;
        vely = 5;
    }

    public void tick(){
        x += velx;
        y += vely;

        if (y <= 0 || y >= Game.HEIGHT-32) vely*=-1;
        if (x <= 0 || x >= Game.WIDTH-16) velx*=-1;

        handler.addObject(new BasicTrail(x, y, ID.BasicTrail, Color.red, 16, 16, 0.02f, handler));
    }
    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 16, 16);
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 16, 16);
    }
}
