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
public abstract class GameObject {

    public boolean visible = true;

    public Rectangle position = new Rectangle(0, 0, 0, 0);

    public abstract Rectangle getPosition();

    public abstract int getImageNo();

    public void setVisibility(boolean isVisible) {
        visible = isVisible;
    }

}
