package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("/layout/fxml/Game.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
    }

    @FXML
    public static void joinAction() {
        Parent root;
        try {
            root = FXMLLoader.load(Main.class.getResource("/layout/fxml/WarningData.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Warning");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public static void win() {
        Parent root;
        try {
            root = FXMLLoader.load(Main.class.getResource("/layout/fxml/Win.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(0, "layout/fxml/css/win.css");
            stage.setTitle("Win");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public static void loose() {
        Parent root;
        try {
            root = FXMLLoader.load(Main.class.getResource("/layout/fxml/Loose.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(0, "layout/fxml/css/loose.css");
            stage.setTitle("Loose");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}