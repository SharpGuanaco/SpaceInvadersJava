//Kaushik Siruvuri, Ishaan Sharma, Supreet Mishra

import java.awt.*;

public class HUD {

    public static float HEALTH = 3;

    public static int bossHEALTH = 10;

    private int score = 0;

    public int level = 1;

    public void tick() {
        score++;
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(15, 15, 32, 32);
        g.fillRect(60, 15, 32, 32);
        g.fillRect(105, 15, 32, 32);
        g.setColor(Color.black);
        g.fillRect(15, 15, (int)((3-HEALTH)*40), 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 32, 32);
        g.drawRect(60, 15, 32, 32);
        g.drawRect(105, 15, 32, 32);



        g.drawString("Score:" + score, 10, 64);
        g.drawString("Level:" + level, 10, 80);

    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


}
