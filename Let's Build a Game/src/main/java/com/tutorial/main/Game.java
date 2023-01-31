package com.tutorial.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 3249818387062750492L;
    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private Random r;
    private HUD hud;
    private Spawner spawner;
    private Menu menu;

    public enum STATE {
        Menu,
        Game,
        Help,
        End
    } ;

    public STATE gameState = STATE.Menu;

    public Game(){
        r = new Random();

        handler = new Handler();
        hud = new HUD();
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);
        new Window(WIDTH, HEIGHT, "Let's Build a Game!", this);
        spawner = new Spawner(handler, hud);

        if (gameState == STATE.Game){
            handler.object.clear();
            handler.addObject(new Player((float) WIDTH/2 - 32, (float) HEIGHT/2 - 32, ID.Player, handler));
            handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
        } else if (gameState == STATE.Menu || gameState == STATE.Help){
            for (int i = 0; i < 10; i++){
                handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler, new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255))));
            }
        } else if (gameState == STATE.End){
            for (int i = 0; i < 10; i++){
                handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler, new Color(100, 0, 0)));
            }
        }
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000){
                timer+=1000;
                /*System.out.println("FPS: " + frames);*/
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        handler.tick();
        if (gameState == STATE.Game) {
            hud.tick();
            spawner.tick();
            if (hud.HEALTH <= 0){
                hud.HEALTH = 100;
                handler.object.clear();
                gameState = STATE.End;
            }
        } else if (gameState == STATE.Menu || gameState == STATE.End){
            menu.tick();
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);
        if (gameState == STATE.Game) {
            hud.render(g);
        } else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End){
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    public static float clamp(float var, float min, float max){
        if (var >= max) return var = max;
        else if (var <= min) return var = min;
        else return var;
    }

    public static void main(String[] args){
        new Game();
    }

}
