package sample;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//Dziedziczenie po klasie Button z javaFX sprawia ze klase Field mozemy traktowac tak samo.
// + mozemy dodac swoje pola i metody
public class Field extends Button {
    private ImageView hiddenImage;
    private String path;

    public Field(String path) {
        this.path = path;
        this.hiddenImage = new ImageView(new Image(getClass().getResourceAsStream(path)));
    }

    public void showImage() {
        this.setGraphic(hiddenImage);
    }

    public void hideImage() {
        this.setGraphic(null);
    }

    public void markAsDone() {
        this.setDisabled(true);
    }

    public boolean imageEquals(Field field) {
        return this.path.equals(field.path);
    }

}
