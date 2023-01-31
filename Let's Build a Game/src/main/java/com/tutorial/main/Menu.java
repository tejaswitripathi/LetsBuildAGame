package com.tutorial.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private Random r = new Random();
    private HUD hud;

    public Menu(Game game, Handler handler, HUD hud){
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }

    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        //play button
        if (game.gameState == Game.STATE.Menu){
            if (mouseOver(mx, my, 210, 150, 200, 64)){
                game.gameState = Game.STATE.Game;
                handler.object.clear();
                handler.addObject(new Player((float) game.WIDTH/2 - 32, (float) game.HEIGHT/2 - 32, ID.Player, handler));
                handler.addObject(new BasicEnemy(r.nextInt(game.WIDTH), r.nextInt(game.HEIGHT), ID.BasicEnemy, handler));
            }
        }

        //quit button
        if (game.gameState == Game.STATE.Menu){
            if (mouseOver(mx, my, 210, 350, 200, 64)){
                System.exit(1);
            }
        }

        //help button
        if (game.gameState == Game.STATE.Menu){
            if (mouseOver(mx, my, 210, 250, 200, 64)){
                game.gameState = Game.STATE.Help;
            }
        }

        //back button
        if (game.gameState == Game.STATE.Help){
            if (mouseOver(mx, my, 210, 350, 200, 64)){
                game.gameState = Game.STATE.Menu;
                return;
            }
        }

        //Try Again button
        if (game.gameState == Game.STATE.End){
            if (mouseOver(mx, my, 210, 350, 200, 64)){
                game.gameState = Game.STATE.Game;
                handler.object.clear();
                hud.setScore(0);
                hud.setLevel(1);
                handler.addObject(new Player((float) game.WIDTH/2 - 32, (float) game.HEIGHT/2 - 32, ID.Player, handler));
                handler.addObject(new BasicEnemy(r.nextInt(game.WIDTH), r.nextInt(game.HEIGHT), ID.BasicEnemy, handler));
            }
        }
    }
    public void mouseReleased(MouseEvent e){

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if (mx > x && mx < width + x){
            if (my > y && my < height + y){
                return true;
            } else return false;
        } else return false;

    }

    public void tick(){

    }
    public void render(Graphics g){
        if (game.gameState == Game.STATE.Menu){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Menu", 240, 70);

            g.setFont(fnt2);
            g.drawString("Play", 270, 190);
            g.drawString("Help", 270, 290);
            g.drawString("Quit", 270, 390);

            g.drawRect(210, 150, 200, 64);
            g.drawRect(210, 250, 200, 64);
            g.drawRect(210, 350, 200, 64);
        } else if (game.gameState == Game.STATE.Help){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 255, 70);

            g.setFont(fnt3);
            g.drawString("Use WASD to move and dodge projectiles.", 100, 200);
            g.setColor(Color.green);
            g.drawString("The green dots are health packs!", 150, 220);
            g.setColor(Color.white);

            g.setFont(fnt2);
            g.drawString("Back", 270, 390);
            g.drawRect(210, 350, 200, 64);
        } else if (game.gameState == Game.STATE.End){

            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.red);
            g.drawString("GAME OVER", 160, 70);

            g.setColor(Color.white);
            g.setFont(fnt3);
            g.drawString("You made it to level " + hud.getLevel(), 200, 200);
            g.setColor(Color.green);
            g.drawString("Your score: " + hud.getScore(), 230, 220);
            g.setColor(Color.white);

            g.setFont(fnt2);
            g.drawString("Try Again", 240, 390);
            g.drawRect(210, 350, 200, 64);
        }


    }
}
