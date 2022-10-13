package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class WarningController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    public void clickOk() {
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }


}
