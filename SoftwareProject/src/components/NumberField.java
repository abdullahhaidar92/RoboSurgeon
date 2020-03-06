package components;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class NumberField extends TextField {
        private boolean hasDot;
        public NumberField() {
            this.addEventFilter(KeyEvent.KEY_TYPED, t -> {
                char ar[] = t.getCharacter().toCharArray();
                char ch = ar[t.getCharacter().toCharArray().length - 1];
                if(ch=='.') {
                    if(hasDot){
                        System.out.println("Invalid decimal number");
                        t.consume();
                    }
                    hasDot = true;
                }
                else if (!(ch >= '0' && ch <= '9') ) {
                    System.out.println("Invalid number");
                    t.consume();
                }
            });
        }

}
