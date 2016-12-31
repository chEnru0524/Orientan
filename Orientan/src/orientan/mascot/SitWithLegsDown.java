/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orientan.mascot;

import java.io.File;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import orientan.config.Action;
import orientan.mascotEnvironment.mascotenvironment;

/**
 *
 * @author user
 */
public class SitWithLegsDown {
    private Timeline timeline = new Timeline();
    private Image sitImage;
    private double duration;
    public SitWithLegsDown(Stage mascotStage, ImageView MascotimageView, Action sitConfig, TimelineManger animationManger)
    {
        
        EventHandler onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                mascotStage.setY(mascotenvironment.getFloor()+12);
            }
        };
        
        
        sitImage=new Image(new File(System.getProperty("user.dir") + "\\img" + sitConfig.getAnimation().get(0).getImage()).toURI().toString());
        duration=sitConfig.getAnimation().get(0).getDuration();
        timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO,onFinished, new KeyValue(MascotimageView.imageProperty(),sitImage)));
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(duration/10), new KeyValue(MascotimageView.imageProperty(),sitImage)));
        timeline.setCycleCount(1);
    }
    public void play()
    {       
        timeline.play();
    }
}