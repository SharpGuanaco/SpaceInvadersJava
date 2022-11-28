//Kaushik Siruvuri, Ishaan Sharma, Supreet Mishra

import java.awt.*;
import java.util.Random;

public class EnemyBoss extends GameObject {

    private Handler handler;

    private Random r = new Random();

    public EnemyBoss(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 1;

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 64, 64);
    }


    public void tick() {
        x += velX;

        if(handler.numofEnemyBullets() < 16){
            int f = r.nextInt(20);
            if (f == 1){
                handler.addObject(new EnemyBullet((int)x+8,(int)y+8,ID.EnemyBullet,handler));
            }
        }

    }

    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect((int) x, (int) y, 64, 64);
    }



}