//Kaushik Siruvuri, Ishaan Sharma, Supreet Mishra

import java.awt.*;
import java.util.Random;

public class Enemy3 extends GameObject {

    private Handler handler;

    private Random r = new Random();

    public Enemy3(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 1;

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }


    public void tick() {
        x += velX;

        if (x <= 0 || x >= Game.WIDTH - 16){
            handler.downOne();
        }

        if(handler.numofEnemyBullets() < 3){
            int f = r.nextInt(handler.numofEnemy());
            if (f == 1){
                handler.addObject(new EnemyBullet((int)x+8,(int)y+8,ID.EnemyBullet,handler));
            }
        }

    }

    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect((int) x, (int) y, 16, 16);
    }



}