package sample;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class Controller implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setEasy();
        setTextToLabel(rule, "src/layout/txt/rules.txt");
        setTextToLabel(info, "src/layout/txt/info.txt");
        setMinValue(0);
        setMaxValue(10);
        setRandomNumber();
        setRangeNumber();
    }

    @FXML
    private Label rangeNumber;

    @FXML
    public Label labelLive;

    @FXML
    public Label labelLiveProperties;

    @FXML
    int liveCount;

    @FXML
    int randomNumber;

    public static String variants ="Пробуй еще, у тебя минус одна попытка! \nТы выбирал: ";

    @FXML
    private TextField minValue;

    @FXML
    private TextField maxValue;

    @FXML
    private Label rule;

    @FXML
    private Label info;

    @FXML
    private TextField data;

    @FXML
    private ToggleButton easy;

    @FXML
    private ToggleButton average;

    @FXML
    private ToggleButton hard;

    @FXML
    private ToggleButton genius;

    public void setRangeNumber() {
        String res = "из диапазона от " + minValue.getText() + " до " + maxValue.getText() + " включительно";
        rangeNumber.setText(res);
    }

    @FXML
    private void okSettings() {
        getToggleTrue();
        setLabelLive();
        setMinValue(Integer.parseInt(minValue.getText()));
        setMaxValue(Integer.parseInt(maxValue.getText()));
        setRandomNumber();
        setRangeNumber();

    }

    public static void setVariants() {
        Controller.variants = "Пробуй еще, у тебя минус одна попытка! \nТы выбирал: ";
    }

    public void getToggleTrue() {
        liveCount = easy.isSelected() ? 10 : average.isSelected() ? 7 : hard.isSelected() ? 5 : genius.isSelected() ? 3 : 10;
    }

    public void setToggleFalse() {
        easy.setSelected(false);
        average.setSelected(false);
        hard.setSelected(false);
        genius.setSelected(false);
    }

    public void setToggleLevel(ToggleButton tb, int liveCount) {
        setLiveCount(liveCount);
        setLabelLive();
        setLabelLiveProperties();

        setToggleFalse();
        tb.setSelected(true);
    }

    public void setEasy() {
        setToggleLevel(easy, 10);
    }

    public void setAverage() {
        setToggleLevel(average, 7);
    }

    public void setHard() {
        setToggleLevel(hard, 5);
    }

    public void setGenius() {
        setToggleLevel(genius, 3);
    }


    public void setRandomNumber() {
        int min = Integer.parseInt(minValue.getText());
        int max = Integer.parseInt(maxValue.getText());
        randomNumber = (int) (Math.random() * (max + 1 - min) + min);
        System.out.println(randomNumber);
        setVariants();
    }

    public void setMaxValue(int value) {
        maxValue.setText(Integer.toString(value));
    }

    public void setMinValue(int value) {
        this.minValue.setText(Integer.toString(value));
    }

    public void setTextToLabel(Label label, String path) {
        StringBuilder sb = new StringBuilder();
        Path pathToFile = Paths.get(path);
        try (Stream stream = Files.lines(pathToFile)) {
            stream.forEach(s -> sb.append(s).append("\n"));
            label.setText(sb.toString());
        } catch (IOException ex) {
            // Handle exception
        }
    }

    public void setLabelLiveProperties() {
        labelLiveProperties.setText(liveCount + " - попыток, для определения загаданного числа");
    }

    public void setLabelLive() {
        labelLive.setText("Количество оставшихся попыток - " + liveCount);
    }

    public void setLiveCount(int liveCount) {
        this.liveCount = liveCount;
    }


    private void checkGuessNumber() {
        try {
            Integer.parseInt(data.getText());
        } catch (Exception e) {
            Main.joinAction();
        }
    }

    private int getQuessData() {
        try {
            return Integer.parseInt(data.getText());
        } catch (Exception e) {
            return Integer.parseInt(maxValue.getText()) + 1;
        }
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    @FXML
    public void OK(ActionEvent event) {
        checkGuessNumber();
        if (liveCount >= 1) {
            int res = getQuessData();
            if (res == randomNumber) {
                Main.win();
                setRandomNumber();
            } else {
                if (liveCount == 1) {
                    liveCount -= 1;
                    setLabelLive();
                    Main.loose();
                    okSettings();
                } else {
                    liveCount -= 1;
                    setLabelLive();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    variants+=res + "; ";
                    alert.setTitle("Не угадал!");
                    alert.setHeaderText(null);
                    alert.setContentText(variants);
                    alert.showAndWait();

                }
            }
        }
    }
}
