//Kaushik Siruvuri, Ishaan Sharma, Supreet Mishra

import java.awt.*;

public class Bullet extends GameObject {

    private Handler handler;
    private HUD hud;

    public Bullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velY = -5;

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }


    public void tick() {
        y += velY;


        if (y <= 0) handler.removeObject(this);

        collision();
    }

    public void collision() {

        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.Enemy2 || tempObject.getId() == ID.Enemy3) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    //collision code
                    handler.removeObject(tempObject);
                    handler.removeObject(this);
                }
            } else if (tempObject.getId() == ID.EnemyBoss) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    //collision code
                    hud.bossHEALTH -= 1;
                    handler.removeObject(this);
                }
            }

        }

    }

    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}
