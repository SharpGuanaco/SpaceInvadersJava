//Kaushik Siruvuri, Ishaan Sharma, Supreet Mishra

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Player extends GameObject {

    Random r = new Random();
    Handler handler;
    private BufferedImage image;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    public void tick() {
        x += velX;

        x = Game.clamp(x, 0, Game.WIDTH - 32);



        collision();
    }

    public void collision() {

        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.Enemy2 || tempObject.getId() == ID.Enemy3 || tempObject.getId() == ID.EnemyBoss) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    //collision code
                    HUD.HEALTH -= 1;
                }
            }

        }

    }

    public void render(Graphics g) {
            g.setColor(Color.white);
            g.fillRect((int) x, (int) y, 32, 32);
    }
}
