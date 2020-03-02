package components;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class TestKey implements EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent keyEvent) {
            System.out.println("Hello");
    }
}
