package controllers;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import views.XraysView;

public class XraysController implements EventHandler<KeyEvent> {
    private XraysView view;

    public XraysController(XraysView view) {
        this.view = view;
    }

    public static void addControllersToScene(Scene scene, XraysView view){
        scene.setOnKeyPressed(new XraysController(view));
    }

    @Override
    public void handle(KeyEvent event) {
        if(event.getCode() == KeyCode.UP){
            moveUp();
            event.consume();
        }
        if(event.getCode() == KeyCode.LEFT){
            moveLeft();
            event.consume();
        }
        if(event.getCode() == KeyCode.DOWN){
            moveDown();
            event.consume();
        }

        if(event.getCode() == KeyCode.RIGHT){
            moveRight();
            event.consume();
        }
    }

    private void moveRight() {
        view.moveRight();
    }

    private void moveDown() {
        view.moveDown();
    }

    private void moveLeft() {
        view.moveLeft();
    }

    private void moveUp() { view.moveUp(); }
}
