package com.mycompany.pewpewgame.controllers;


import com.mycompany.pewpewgame.objects.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;


/**
 *
 * @author max
 */
public class Peli extends Application {
    
    Canvas canv;
    Group root;
    Scene scene;
    GraphicsContext gc;
    Renderer renderer;    
    Timeline gameLoop;
    KeyFrame kf;
    Player player;
    GameController gameC;
    InputHandler input;
    ImageView plr;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage){
        final long timeStart = initAll(stage);
        runGameLoop(timeStart);
        stage.show();    
    }
    
    public long initAll(Stage stage) {
        initStage(stage);
        initGameLoop();
        final long timeStart = System.currentTimeMillis();
        initGame();
        initRenderer();
        initInputs();
        return timeStart;
    }
    
    public void initGameLoop() {
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
    }
    
    public void initStage(Stage stage) {
        stage.setTitle("PewPew");
        canv = new Canvas(512,512);
        root = new Group();
        scene = new Scene(root);
        stage.setScene(scene);
        root.getChildren().add(canv);
        gc = canv.getGraphicsContext2D();
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });
    }
    
    public void initGame() {
        player = new Player(10);
        gameC = new GameController(player, gc);
    }
    
    public void initRenderer() {
        plr = new ImageView();
        plr.setImage(player.getImg());
        plr.setFitWidth(16);
        plr.setFitHeight(16);
        root.getChildren().add(plr);
        renderer = new Renderer(gc, gameC);
    }
    
    public void initInputs() {
        input = new InputHandler(gameC);
        input.registerInputs(scene); 
    }

    private void runGameLoop(long timeStart) {
        kf = new KeyFrame(Duration.seconds(0.017), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                double t = (System.currentTimeMillis() - timeStart)/1000.0;
                handleGameLoop();
            }
        });
        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();
    }
    
    private void handleGameLoop() {
        // Clear the canvas and fill background
        renderer.refreshCanvas();
                
        // Process the inputs of this frame
        input.handleInputs(player);
                
        // Update game logic
        gameC.updateAll();
                
        // Detect and handle collisions
        gameC.getCc().handleCollisions();
            
        // Render updated all objects and
        //  UI with their updated positions
        // and values
        renderer.renderAll(plr,player);
    }
}
