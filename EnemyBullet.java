//Kaushik Siruvuri, Ishaan Sharma, Supreet Mishra

import java.awt.*;

public class EnemyBullet extends GameObject {

    private Handler handler;
    private HUD hud;
    public EnemyBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velY = 2;

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }


    public void tick() {
        y += velY;


        if (y >= Game.HEIGHT) handler.removeObject(this);
        collision();
    }

    public void collision() {

        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    //collision code
                    hud.HEALTH -= 1;
                    handler.removeObject(this);
                }
            }

            if (tempObject.getId() == ID.Bullet) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    //collision code
                    handler.removeObject(tempObject);
                    handler.removeObject(this);
                }
            }

        }

    }

    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}
