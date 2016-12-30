/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannonball;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Emil
 */
public class GameGraphics extends JPanel {

    public GameLogic gameLogic;
    Image[] ball;
    Image player;
    Image background;
    Image opponent;
    private Font smallerFont = new Font("Verdana", Font.BOLD, 20);
    private String healthPl = null;
    private String healthOp = null;

    public GameGraphics() {
        ball = new Image[3];
        try {
            background = ImageIO.read(new File("images/background.png"));
            ball[0] = ImageIO.read(new File("images/ball.png"));
            ball[1] = ImageIO.read(new File("images/ball1.png"));
            ball[2] = ImageIO.read(new File("images/ball2.png"));
            player = ImageIO.read(new File("images/cannon.png"));
            opponent = ImageIO.read(new File("images/opponent.png"));
            
        } catch (IOException e) {
            System.out.println("problems loading images" + e.getMessage());
            System.exit(1);
        }

    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background,
                    0, 0, g.getClipBounds().width, g.getClipBounds().height,
                    0, 0, background.getWidth(null), background.getHeight(null), null);
        
        g.setFont(smallerFont);
        
        int fromX = 0;
        int toX = 0;
        int imX1 = 0, imY1 = 0, imX2 = 0, imY2 = 0;
        int c=0;
        for (GameObject gameObject : gameLogic.gameObject) {
            Image draw;
            if (!gameObject.visible) continue;
            if (gameObject instanceof Player) {
                if(c==0){
                draw = player;
                imX1 = 0;
                imY1 = 0;
                imX2 = draw.getWidth(null);
                imY2 = draw.getHeight(null);
                fromX = gameLogic.player.position.x;
                toX = gameLogic.player.position.x + gameLogic.player.position.width;
                c++;
                }
                else{
                draw = opponent;
                imX1 = 0;
                imY1 = 0;
                imX2 = draw.getWidth(null);
                imY2 = draw.getHeight(null);
                fromX= gameLogic.opponent.position.x;
                toX = gameLogic.opponent.position.x+gameLogic.opponent.position.width;
                }
                
            } else {
                fromX = gameObject.position.x;
                toX = gameObject.position.x + gameObject.position.width;
                draw = ball[gameObject.getImageNo()];
                imX1 = 0;
                imY1 = 0;
                imX2 = draw.getWidth(null);
                imY2 = draw.getHeight(null);
            }
            g.drawImage(draw, fromX,
                    gameObject.position.y,
                    toX,
                    gameObject.position.y + gameObject.position.height,
                    imX1, imY1, imX2, imY2,
                    null
            );
            healthPl = gameLogic.player.getHealth();
            healthOp = gameLogic.opponent.getHealth();
            g.setColor(Color.RED);
            Graphics2D g2 = (Graphics2D) g;
            int stringWidth = g2.getFontMetrics().stringWidth(healthOp);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.drawString(healthOp, getToolkit().getScreenSize().width-stringWidth, 30);
            g.drawString(healthPl, 0, 30);
        }
    }
}
