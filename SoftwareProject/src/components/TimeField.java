package components;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class TimeField extends HBox {

    private NumberField hours=new NumberField();
    private NumberField minutes=new NumberField();
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

}
