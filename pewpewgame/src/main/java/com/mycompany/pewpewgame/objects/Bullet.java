/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pewpewgame.objects;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author max
 */
public class Bullet extends GameObject{
   
    public Bullet(int x, int y, Orientation o){
        super(x,y);
        this.orientation = new Orientation(o.current);
    }

}
