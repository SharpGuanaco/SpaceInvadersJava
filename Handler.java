//Kaushik Siruvuri, Ishaan Sharma, Supreet Mishra

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    public void clearEnemies() {

        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            if (tempObject.getId() != ID.Player) {
                object.clear();
            }
        }
    }

    public void clearPlayer() {

        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            if (tempObject.getId() == ID.Player) {
                object.clear();
            }
        }
    }

    public int numOfBullets(){
        int counter = 0;
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            if (tempObject.getId() == ID.Bullet) {
                counter ++;
            }
        }
        return counter;
    }

    public int numofEnemyBullets(){
        int counter = 0;
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            if (tempObject.getId() == ID.EnemyBullet) {
                counter ++;
            }
        }
        return counter;
    }

    public int numofEnemy(){
        int counter = 0;
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            if (tempObject.getId() == ID.EnemyBoss || tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.Enemy2 || tempObject.getId() == ID.Enemy3) {
                counter ++;
            }
        }
        return counter;
    }

    public void clearAll() {

        for (int i = 0; i < object.size(); i++) {
                object.clear();
            }
        }

    public void downOne(){
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.Enemy2 || tempObject.getId() == ID.Enemy3) {
                tempObject.setY(tempObject.getY()+16);
                tempObject.setVelX(tempObject.getVelX()*-1);
            }
        }
    }

    public int lowestY(){
        int prev = 0;
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.Enemy2 || tempObject.getId() == ID.Enemy3) {
                if (prev < tempObject.getY()){
                    prev = (int)tempObject.getY();
                }
            }
        }
        return prev;
    }

    public void addObject(GameObject object) {
        this.object.add(object);
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);
    }

}