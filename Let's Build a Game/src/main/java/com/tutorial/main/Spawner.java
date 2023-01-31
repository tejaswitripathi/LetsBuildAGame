package com.tutorial.main;

import java.util.Random;

public class Spawner {
    private Handler handler;
    private HUD hud;
    private int scoreKeep = 0;
    private Random r = new Random();

    public Spawner(Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
    }
    public void tick(){
        scoreKeep++;
        if (scoreKeep >= 1000) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            if (hud.getLevel() == 1 || hud.getLevel() % 2 == 0){
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
            }
            if (hud.getLevel() == 3 || hud.getLevel() % 6 == 0) {
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
            }
            if (hud.getLevel()%6 == 0) {
                handler.addObject(new BigEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BigEnemy, handler));
            }
            if (hud.getLevel()%7 == 0) {
                handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.SmartEnemy, handler));
            }
            if (hud.getLevel() % 5 == 0){
                handler.addObject(new HealthPack(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.HealthPack, handler, false));
            }
        }
    }
}
