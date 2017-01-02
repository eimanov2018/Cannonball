/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannonball;

import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author Emil, Konul, Leyla, Safura
 */
public class Main extends JFrame {

    public static JFrame f = new JFrame("Cannonball");
    static GameGraphics gGraphics = new GameGraphics();
    static GameLogic gameLogic = new GameLogic();
    static boolean exitPressed = false;
    static Timer timer = new Timer(50, e -> {
        gameLogic.update();
        gGraphics.repaint();
    });

    public static void main(String[] args) {
        // TODO code application logic here
        f.getContentPane().add(gGraphics);
        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        DisplayMode dm = new DisplayMode(800, 600, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
        f.setSize(f.getToolkit().getScreenSize());
              //  f.setSize(600, 600);

        // f.setUndecorated(true);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gGraphics.gameLogic = gameLogic;
        timer.start();
        f.addKeyListener(gameLogic.keyListener);

    } 


}
