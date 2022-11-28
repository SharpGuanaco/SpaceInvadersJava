//Kaushik Siruvuri, Ishaan Sharma, Supreet Mishra

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 4088146271165387233L;

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    private Random r;

    private Thread thread;
    private boolean running = false;

    private Handler handler;

    private HUD hud;

    private Spawn spawner;

    private Menu menu;

    public enum STATE {
        MENU,
        HELP,
        GAME,
        END,
        WIN
    }

    public STATE gameState = STATE.MENU;

    public Game() {
        handler = new Handler();
        hud = new HUD();
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);

        new Window(WIDTH, HEIGHT, "Game Running", this);

        spawner = new Spawn(handler, hud);

        r = new Random();
        Random b = new Random();

        if (gameState == STATE.GAME) {
            hud.setLevel(1);
            hud.setScore(0);
            hud.HEALTH = 3;
        }
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {

        handler.tick();

        if (gameState == STATE.GAME) {
            hud.tick();
            spawner.tick();

            if (HUD.HEALTH <= 0 || handler.lowestY() > 400){
                handler.clearAll();
                gameState = STATE.END;
                HUD.HEALTH = 3;
            }

            if (HUD.bossHEALTH < 0){
                handler.clearAll();
                gameState = STATE.WIN;
                HUD.bossHEALTH = 3;
                HUD.HEALTH = 3;
            }

        } else if (gameState == STATE.MENU || gameState == STATE.END || gameState == STATE.WIN) {
            menu.tick();
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        if (gameState == STATE.GAME) {
            hud.render(g);
        } else if (gameState == STATE.MENU || gameState == STATE.HELP || gameState == STATE.END || gameState == STATE.WIN) {
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    public static float clamp(float var, float min, float max) {
        if (var >= max) {
            return var = max;
        } else if (var <= min) {
            return var = min;
        } else {
            return var;
        }
    }

    public static void main(String args[]) {
        new Game();

    }
}
