
package controllers;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import views.XraysView;

public class XraysController implements EventHandler<KeyEvent> {
    private XraysView view;

    public XraysController(XraysView view) {
        this.view = view;
    }

    @Override
    public void handle(KeyEvent event) {
        if(event.getCode() == KeyCode.UP)       moveUp();
        if(event.getCode() == KeyCode.LEFT)     moveLeft();
        if(event.getCode() == KeyCode.DOWN)     moveDown();
        if(event.getCode() == KeyCode.RIGHT)    moveRight();
        if(event.getCode() == KeyCode.SPACE)    emitAnimation();
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

    private void moveUp() {
        view.moveUp();
    }
    private void emitAnimation(){
        view.emitAnimation();
    }
}
