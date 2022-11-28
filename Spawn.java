//Kaushik Siruvuri, Ishaan Sharma, Supreet Mishra

import java.util.Random;

public class Spawn {

    private Handler handler;
    private HUD hud;
    private Random r = new Random();
    private boolean boop = false;

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void tick() {
        if (handler.numofEnemy() <= 0){
            hud.setLevel(hud.getLevel()+1);
            boop = true;
        }
        if (boop == true) {
            if (hud.getLevel() == 2) {
                handler.clearEnemies();
                for (int h = 0; h < 2; h++) {
                    for (int c = 0; c < 10; c++) {
                        handler.addObject(new Enemy2(50 + (50 * c), 50 + (50 * h), ID.Enemy2, handler));
                    }
                }
                handler.clearPlayer();
                handler.addObject(new Player(Game.WIDTH / 2 - 32, 400, ID.Player, handler));
                boop = false;
            } else if (hud.getLevel() == 3) {
                handler.clearEnemies();
                for (int h = 0; h < 4; h++) {
                    for (int c = 0; c < 10; c++) {
                        handler.addObject(new Enemy3(50 + (50 * c), 50 + (50 * h), ID.Enemy3, handler));
                    }
                }
                handler.clearPlayer();
                handler.addObject(new Player(Game.WIDTH / 2 - 32, 400, ID.Player, handler));
                boop = false;
            } else if (hud.getLevel() == 4) {
                handler.clearEnemies();
                        handler.addObject(new EnemyBoss(50, 100, ID.EnemyBoss, handler));
                handler.clearPlayer();
                handler.addObject(new Player(Game.WIDTH / 2 - 32, 400, ID.Player, handler));
                boop = false;
            }
        }
    }

}


