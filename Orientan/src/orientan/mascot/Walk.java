/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orientan.mascot;

import java.io.File;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import orientan.config.Action;
import orientan.mascotEnvironment.mascotenvironment;

/**
 *
 * @author zp
 */
public class Walk extends MascotAction {

    private ArrayList<Image> image = new ArrayList<Image>();
    private double time = 0;
    private double duration = 0;
    private double deltaX = -10;
    //private double deltaY = 0;
    private Timeline timeline = new Timeline();

    public Walk(Stage mascotStage, ImageView MascotimageView, Action walkConfig, TimelineManger animationManger, String imgPath) {
        this.duration = walkConfig.getAnimation().get(0).getDuration();
        this.imagePath = imgPath;
        //this.deltaX=walkConfig.getAnimation().get(0).getVelocity();
        time = 0;
        //事件監聽  
        EventHandler onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                //如果超出左右邊界，就變換前進方向與圖片翻轉
                //if(mascotStage.getX()<=-20||mascotStage.getX()>=primScreenBounds.getWidth())
                if (MascotimageView.getRotate() == 180) {
                    deltaX = Math.abs(deltaX);
                } else if (MascotimageView.getRotate() == 0) {
                    deltaX = Math.abs(deltaX) * -1;
                }
                if (mascotStage.getX() <= mascotenvironment.getLeftWall() || mascotStage.getX() >= mascotenvironment.getRightWall()) {

                    deltaX = deltaX * (-1);
                    MascotimageView.setRotationAxis(Rotate.Y_AXIS);
                    if (deltaX > 0) {
                        MascotimageView.setRotate(180);
                    } else {
                        MascotimageView.setRotate(0);
                    }
                }
                mascotStage.setX((mascotStage.getX() + deltaX));
            }
        };
        //時間軸生成
        duration = walkConfig.getAnimation().get(0).getDuration() / 10;
        for (int i = 0; i < walkConfig.getAnimation().size(); i++) {
            //image.add(new Image(new File(System.getProperty("user.dir") + "\\img" + Walk.getAnimation().get(i).getImage()).toURI().toString()));
            image.add(new Image(new File(imagePath + walkConfig.getAnimation().get(i).getImage()).toURI().toString()));
        }
        for (int i = 0; i < walkConfig.getAnimation().size(); i++) {
            if (i == 0) {
                timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, onFinished, new KeyValue(MascotimageView.imageProperty(), image.get(i))));
                continue;
            }
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(time = time + (double) walkConfig.getAnimation().get(i - 1).getDuration() / 10), onFinished, new KeyValue(MascotimageView.imageProperty(), image.get(i))));
            if (i == walkConfig.getAnimation().size() - 1) {
                timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(time = time + (double) walkConfig.getAnimation().get(i).getDuration() / 10), new KeyValue(MascotimageView.imageProperty(), image.get(i))));
            }
        }
        animationManger.getTimelineList().add(timeline);
    }

    public void play() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void play(int circleTime) {
        timeline.setCycleCount(circleTime);
        timeline.play();
    }
}
