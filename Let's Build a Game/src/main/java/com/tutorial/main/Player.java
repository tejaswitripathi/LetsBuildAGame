package com.tutorial.main;

import java.awt.*;

public class Player extends GameObject{

    Handler handler;

    public Player(float x, float y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
    }

    public void tick(){
        x+=velx;
        y+=vely;

        x = Game.clamp(x, 0, Game.WIDTH - 37);
        y = Game.clamp(y, 0, Game.HEIGHT - 60);

        collision();
    }

    public void render(Graphics g){
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, 32, 32);
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    private void collision(){
        for (int i = 0; i < handler.object.size(); i++){
            GameObject temp = handler.object.get(i);
            if (temp.getID() == ID.BasicEnemy || temp.getID() == ID.BigEnemy || temp.getID() == ID.FastEnemy || temp.getID() == ID.SmartEnemy){
                if (getBounds().intersects(temp.getBounds())){
                    //collision code
                    HUD.HEALTH -= 2;
                }
            }
            if (temp.getID() == ID.HealthPack) {
                if (getBounds().intersects(temp.getBounds())) {
                    //collision code
                    HUD.HEALTH += 100;
                    handler.removeObj(temp);
                }
            }
        }
    }
}
