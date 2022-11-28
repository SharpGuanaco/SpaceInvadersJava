//Kaushik Siruvuri, Ishaan Sharma, Supreet Mishra

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private boolean[] keyDown = new boolean[3];

    public KeyInput(Handler handler) {
        this.handler = handler;


        keyDown[0] = false; // right key
        keyDown[1] = false; //left key
        keyDown[2] = false; //space key

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();


        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_LEFT) {
                    tempObject.setVelX(-5);
                    keyDown[0] = true;
                }
                if (key == KeyEvent.VK_RIGHT) {
                    tempObject.setVelX(5);
                    keyDown[1] = true;
                }
                if (key == KeyEvent.VK_SPACE) {
                    if (handler.numOfBullets() < 3) {
                        handler.addObject(new Bullet((int) tempObject.getX() + 8, (int) tempObject.getY() - 32, ID.Bullet, handler));
                    }
                    keyDown[2] = true;
                }
            }
        }

        if (key == KeyEvent.VK_ESCAPE) System.exit(1);

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Player) {
                // Player 1 Key Events
                if (key == KeyEvent.VK_LEFT) keyDown[0] = false;    //tempObject.setVelX(0);
                if (key == KeyEvent.VK_RIGHT) keyDown[1] = false;    //tempObject.setVelX(0);
                if (key == KeyEvent.VK_SPACE) keyDown[2] = false;
                // horizontal key unbinding
                if (!keyDown[0] && !keyDown[1]) tempObject.setVelX(0);
            }
        }

    }

}
