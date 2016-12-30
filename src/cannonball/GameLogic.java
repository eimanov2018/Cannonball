/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannonball;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class GameLogic {

    Player player = new Player();
    Ball ballT = new Ball();
    ArrayList<Ball> ball = new ArrayList<>();
    ArrayList<GameObject> gameObject = new ArrayList<>();

    public GameLogic() {
        player.position.setBounds(250, 260, 50, 50);
//        for (Ball ball : ball) {
//            gameObject.add(ball);
//            // player.register(ball);
//        }
        gameObject.add(player);

    }

    public void update() {
        player.update(ball);

        ballT.position.setBounds(ballT.position.x + ballT.dx,
                ballT.position.y + ballT.dy,
                ballT.position.width,
                ballT.position.height);

    }
    public KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("Key pressed: " + e.getKeyCode());
            if (e.getKeyCode() == 37 || e.getKeyCode() == 65) {
                // player.stateGame.goingLeft = true;

                int i;
                for (i = 0; i > -20; i--) {
                    player.dx = i;
                }
                i = 0;

            }
            if (e.getKeyCode() == 38 || e.getKeyCode() == 87) {
                // player.stateGame.goingRight = true;

                int i;
                for (i = 0; i > -20; i--) {
                    player.dy = i;
                }
                i = 0;

            }
            if (e.getKeyCode() == 40 || e.getKeyCode() == 83) {
                // player.stateGame.goingRight = true;

                int i;
                for (i = 0; i < 20; i++) {
                    player.dy = i;
                }
                i = 0;

            }
            if (e.getKeyCode() == 32) {
                // ballT.setVisibility(true);
                ballT.position.setBounds(player.position);
                int i = 0;
//                for (int j = 30; j > 0; j--) {
                    for (i = 0; i < 30; i++) {
                        ballT.dx = i;
                    }
                    ballT.dx --;
                    for (i = 0; i > -30; i--) {
                        ballT.dy = i;
                    }
                    ballT.dy ++;

                    i = 0;
//                }
//                for (int j = 30; j > 0; j--) {
//                    for (i = 0; i < 30; i++) {
//                        ballT.dx = i;
//                    }
//                    for (i = 0; i < 30; i++) {
//                        ballT.dy = i;
//                    }
//                    i = 0;
//                }

                gameObject.add(ballT);
                System.out.println("dx = " + ballT.dx + "dy = " + ballT.dy);

            }
            if (e.getKeyCode() == 39 || e.getKeyCode() == 68) {
                //player.stateGame.goingRight = true;

                int i;
                for (i = 0; i < 20; i++) {
                    player.dx = i;
                }
                i = 0;

            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("Key released: " + e.getKeyCode());
            if (e.getKeyCode() == 37
                    || e.getKeyCode() == 39
                    || e.getKeyCode() == 65
                    || e.getKeyCode() == 68
                    || e.getKeyCode() == 40
                    || e.getKeyCode() == 83
                    || e.getKeyCode() == 38
                    || e.getKeyCode() == 87) {
                player.dx = 0;
                player.dy = 0;
//                player.stateGame.goingRight = false;
//                player.stateGame.goingLeft = false;

            }
        }
    };

}
