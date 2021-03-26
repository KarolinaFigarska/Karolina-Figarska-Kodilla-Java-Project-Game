package sample;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.*;
import java.util.List;

public class Main extends Application {

    private int counter = 0;
    private List<Field> fields = new ArrayList<>();
    private Field targetField = null;
    private Label label = new Label("Liczba ruchów: 0");

    private FlowPane flow = new FlowPane(Orientation.HORIZONTAL);
    private GridPane gameBoard = new GridPane();


    private static final List<String> FIELD_IMAGE_PATHS = Arrays.asList("/1.png", "/2.png", "/3.png", "/4.png", "/5.png", "/6.png");

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Memory Game");

        flow.setVgap(20);
        flow.setHgap(4);

        flow.getChildren().add(gameBoard);
        generateFields(gameBoard);

        Scene scene = new Scene(flow, 1350, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        Button newGame = new Button();
        newGame.setText("Nowa gra");
        newGame.setOnAction((e) -> {
            resetGame();
        });

        flow.getChildren().add(newGame);

        label.setFont(new Font("Arial", 20));
        flow.getChildren().add(label);
    }

    //Utworzenie pól i dodanie ich do FlowPane
    private void generateFields(GridPane gridPane) {
        fields.clear();
        //Przygotowanie listy pól
        for (String path : FIELD_IMAGE_PATHS) {
            for (int i = 0; i < 2; i++) {
                Field field = createField(path);
                fields.add(field);
            }
        }

        //Wymieszanie listy pól
        Collections.shuffle(fields);

        //Dodanie pól na ekran
        for (int i = 0; i < fields.size(); i++) {
            gridPane.add(fields.get(i), i % 4, i / 4, 1, 1);
        }

    }

    //Tworzenie pojedynczego pola
    private Field createField(String path) {
        Field field = new Field(path);

        field.setPrefHeight(220);
        field.setPrefWidth(220);

        field.setOnAction(event -> {
            //Counter zlicza ilosc ruchow potrzebnych na skonczenie rozgrywki
            counter++;
            System.out.println("counter: " + counter);
            label.setText("Liczba ruchów: " + counter);
            Field clickedField = (Field) event.getSource();
            //Pokazanie obrazka na polu
            clickedField.showImage();
            //Sprawdzenie czy jeden obrazek jest juz odsloniety
            if (targetField != null) {
                //Jesli oba obrazki sie zgadzaja ustaw je jako odgadniete
                if (targetField.imageEquals(clickedField)) {
                    clickedField.markAsDone();
                    targetField.markAsDone();
                } else { //Jesli nie udalo sie odgadnac obu obrazkow ukryj je
                    clickedField.hideImage();
                    targetField.hideImage();
                }
                //Wyczysc targetField, tura skonczona
                targetField = null;
            } else { //Jesli to pierwszy obrazek tylko ustaw targetField
                targetField = clickedField;
            }
        });

        return field;
    }

    private void resetGame() {
        counter = 0;
        targetField = null;
        label.setText("Liczba ruchów: 0");
        //Usuniecie wszystkich obrazkow z planszy
        gameBoard.getChildren().clear();
        //Wygenerowanie nowych
        generateFields(gameBoard);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
