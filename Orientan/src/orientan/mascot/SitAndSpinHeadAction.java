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
 * @author user
 */
public class SitAndSpinHeadAction {

    private Timeline timeline = new Timeline();
    private ArrayList<Image> image = new ArrayList<Image>();
    private double time = 0;
    private double duration = 0;

    public SitAndSpinHeadAction(Stage mascotStage, ImageView MascotimageView, Action SitAndSpinHeadActionConfig, TimelineManger animationManger) {
        //生成時間軸
        duration = SitAndSpinHeadActionConfig.getAnimation().get(0).getDuration() / 10;
        for (int i = 0; i < SitAndSpinHeadActionConfig.getAnimation().size(); i++) {
            //image.add(new Image(new File(System.getProperty("user.dir") + "\\img" + Walk.getAnimation().get(i).getImage()).toURI().toString()));
            image.add(new Image(new File(System.getProperty("user.dir") + "\\img" + SitAndSpinHeadActionConfig.getAnimation().get(i).getImage()).toURI().toString()));
        }
        for (int i = 0; i < SitAndSpinHeadActionConfig.getAnimation().size(); i++) {
            if (i == 0) {
                timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, new KeyValue(MascotimageView.imageProperty(), image.get(i))));
                continue;
            }
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(time = time + (double) SitAndSpinHeadActionConfig.getAnimation().get(i - 1).getDuration() / 10), new KeyValue(MascotimageView.imageProperty(), image.get(i))));
            if (i == SitAndSpinHeadActionConfig.getAnimation().size() - 1) {
                timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(time = time + (double) SitAndSpinHeadActionConfig.getAnimation().get(i).getDuration() / 10), new KeyValue(MascotimageView.imageProperty(), image.get(i))));
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