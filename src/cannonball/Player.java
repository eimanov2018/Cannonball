/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannonball;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class Player extends GameObject  {

    public int dx = 0;
    public int dy = 0;
    public int angle = 5;
    public int currentImage = 0;
    public int health  = 100;
    public void update(ArrayList<Ball> balls) {
        int lastX = position.x;
        int lastY = position.y;

        position.setBounds(
                position.x + dx,
                position.y,
                position.width,
                position.height);
        boolean intersects = false;
        for (Ball ball : balls) {
            if (ball.position.intersects(position)) {
                intersects = true;
               System.out.println("health nmnmis "+health);

            }
        }
      
        if (intersects) {
            health-=10;
            System.out.println("health is "+health);
        }
        position.setBounds(position.x,
                position.y + dy,
                position.width,
                position.height);
        
        
//        for (Ball ball : balls) {
//            if (ball.position.intersects(position)) {
//                //ball.setVisibility(true);
//            }
//        }
    }

    @Override
    public Rectangle getPosition() {
        return position;
    }

    public String getHealth(){
        return Integer.toString(health);
    }
    @Override
    public int getImageNo() {
        return 0;
    }
    public void update(GameLogic gameLogic) {
        update(gameLogic.ball);
    }

}
