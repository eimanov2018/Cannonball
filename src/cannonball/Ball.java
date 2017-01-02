/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannonball;

import java.awt.Rectangle;

/**
 *
 * @author Emil, Konul, Leyla, Safura
 */
public class Ball extends GameObject {

    public int dx = 0;
    public int dy = 0;    
    public int type = 0;

    public int weight = 0;
    public boolean visible = false;
    @Override
    public Rectangle getPosition() {
        return position;
    }

    @Override
    public int getImageNo() {
        return type;
    }

}
