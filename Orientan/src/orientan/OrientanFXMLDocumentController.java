/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orientan;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import ImageSetChooser.*;

/**
 *
 * @author user
 */
public class OrientanFXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        ImageSetChooser chooserlayout =new ImageSetChooser();
        chooserlayout.ImageSetChooser();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
