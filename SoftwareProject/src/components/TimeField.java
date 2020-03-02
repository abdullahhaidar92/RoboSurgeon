package components;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class TimeField extends HBox {

    private NumFieldFX hours=new NumFieldFX();
    private NumFieldFX minutes=new NumFieldFX();
    private Label label=new Label(" : ");
    public TimeField(){
        getChildren().addAll(hours,label,minutes);
        setSpacing(10);
    }
    public void setPrefWidth(int width){
        hours.setPrefWidth(width/2-5);
        minutes.setPrefWidth(width/2-5);

    }

    public String getValue(){
        return hours.getText()+":"+minutes.getText()+":00";
    }
    public void setHours(int h){
        hours.setText(""+h);
    }
    public void setMinutes(int m){
        minutes.setText(""+m);
    }
    private class NumFieldFX extends TextField {
        public NumFieldFX() {
            this.addEventFilter(KeyEvent.KEY_TYPED, t -> {
                char ar[] = t.getCharacter().toCharArray();
                char ch = ar[t.getCharacter().toCharArray().length - 1];
                if (!(ch >= '0' && ch <= '9')) {
                    System.out.println("The char you entered is not a number");
                    t.consume();
                }
            });
        }
    }
}
