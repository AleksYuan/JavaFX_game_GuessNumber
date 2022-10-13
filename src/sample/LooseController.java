package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LooseController extends WarningController {

    @FXML
    private javafx.scene.Parent parent;

    @FXML
    public void out() {
        Platform.exit();
    }

}
