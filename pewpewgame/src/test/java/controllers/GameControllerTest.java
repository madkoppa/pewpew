/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mycompany.pewpewgame.controllers.GameController;
import com.mycompany.pewpewgame.objects.Orientation;
import com.mycompany.pewpewgame.objects.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author max
 */
public class GameControllerTest {
    GameController gameC;
    Player p;
    
    
    public GameControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        p = new Player(10);
    }
    
    @After
    public void tearDown() {
    }


}
