package com.tutorial.main;

import java.awt.*;
import java.util.Random;

public class MenuParticle extends GameObject{

    private Handler handler;
    Random r = new Random();
    private Color col;
    int dir = 0;

    public MenuParticle(int x, int y, ID id, Handler handler, Color col){
        super(x, y, id);
        this.handler = handler;
        dir = r.nextInt(2);
        if (dir == 0){
            velx = 2;
            vely = 8;
        } else if (dir == 1){
            velx = 8;
            vely = 2;
        }
        this.col = col;
    }

    public void tick(){
        x += velx;
        y += vely;

        if (y <= 0 || y >= Game.HEIGHT-32) vely*=-1;
        if (x <= 0 || x >= Game.WIDTH-16) velx*=-1;

        handler.addObject(new BasicTrail(x, y, ID.BasicTrail, col, 10, 10, 0.03f, handler));
    }
    public void render(Graphics g){
        g.setColor(col);
        g.fillRect((int) x, (int) y, 10, 10);
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 16, 16);
    }


}