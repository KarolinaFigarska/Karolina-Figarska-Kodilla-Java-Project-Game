package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private int counter = 0;
    private List<Button> buttonList = new ArrayList<>();
    private int lastIndex;

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("memory");
        FlowPane flow = new FlowPane();
        flow.setVgap(8);
        flow.setHgap(4);
        int imageId;

        for (int i=1; i<=8;i++) {
            Button button = new Button("");
            if (i <= 4) {
                imageId =i;
            } else {
                imageId= i-4;
            }
            button.setPrefHeight(120);
            button.setPrefWidth(120);
            button.setId(String.valueOf(imageId));
            buttonList.add(button);

            button.setOnAction(event -> {
                counter++;
                System.out.println("counter: " + counter);
                Button clickedButton = (Button) event.getSource();
                Image image = new Image(getClass().getResourceAsStream("/"+clickedButton.getId()+".png"));
                clickedButton.setGraphic(new ImageView(image));
                if (counter % 2 == 0) {
                    if (buttonList.get(lastIndex).getId().equals(clickedButton.getId())) {
                        clickedButton.setDisable(true);
                        buttonList.get(lastIndex).setDisable(true);
                    } else {
                        buttonList.get(lastIndex).setGraphic(null);
                        counter = 1;
                    }
                }
                lastIndex = buttonList.indexOf(clickedButton);
                System.out.println("index: " + lastIndex);
                System.out.println();
            });
            flow.getChildren().add(button);
        }
        primaryStage.setScene(new Scene(flow, 1100, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
