//Kaushik Siruvuri, Ishaan Sharma, Supreet Mishra

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private HUD hud;
    private Random r = new Random();

    public Menu(Game game, Handler handler, HUD hud) {
        this.game = game;
        this.hud = hud;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (game.gameState == Game.STATE.MENU) {

            //play button
            if (mouseOver(mx, my, 210, 150, 200, 64)) {
                game.gameState = Game.STATE.GAME;
                hud.setLevel(1);
                hud.setScore(0);
                handler.clearEnemies();
                handler.addObject(new Player(Game.WIDTH / 2 - 32, 400, ID.Player, handler));
                for(int h = 0; h < 3; h++) {
                    for (int c = 0; c < 10; c++) {
                        handler.addObject(new BasicEnemy(50 + (50 * c), 50+(50*h), ID.BasicEnemy, handler));
                    }
                }


            }

            //help button
            if (mouseOver(mx, my, 210, 250, 200, 64)) {
                game.gameState = Game.STATE.HELP;
            }

            //quit button
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                System.exit(1);
            }
        }
        // Back button for help
        if (game.gameState == Game.STATE.HELP) {
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                game.gameState = Game.STATE.MENU;
                return;
            }
        }

        // Back button for END
        if (game.gameState == Game.STATE.END) {
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                game.gameState = Game.STATE.MENU;
                return;
            }
        }
        if (game.gameState == Game.STATE.WIN) {
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                game.gameState = Game.STATE.MENU;
                return;
            }
        }



    }


    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else return false;
        } else return false;

    }

    public void tick() {

    }

    public void render(Graphics g) {

        if (game.gameState == Game.STATE.MENU) {

            Font fnt = new Font("arial", 1, 40);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Social Distancing Invaders!", 60, 80);

            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Play", 270, 190);

            g.drawRect(210, 250, 200, 64);
            g.drawString("Help", 270, 290);


            g.drawRect(210, 350, 200, 64);
            g.drawString("Quit", 270, 390);

        } else if (game.gameState == Game.STATE.HELP) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);


            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 240, 70);

            g.setFont(fnt3);
            g.drawString("Use the Left and Right Arrow keys to Move Your Ship!", 70, 150);
            g.drawString("Use Space to Shoot Down Enemy Ships!", 130, 225);
            g.drawString("The Game Ends When You Get Hit Three Times!", 100, 300);


            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 270, 390);
        } else if (game.gameState == Game.STATE.END) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);


            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Game over", 200, 70);

            g.setFont(fnt3);
            g.drawString("You lost with a score of " + hud.getScore() + " at level " + hud.getLevel(), 140, 200);

            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Try Again", 240, 395);
        } else if (game.gameState == Game.STATE.WIN) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);


            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("You WON!", 200, 70);

            g.setFont(fnt3);
            g.drawString("You won with a score of " + hud.getScore() + " at level " + hud.getLevel(), 140, 200);
            g.drawString("You are officially virus free!", 200,300);

            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Menu", 240, 395);
        }


    }
}
