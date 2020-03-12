package components;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class TimeField extends HBox {

    private Spinner<Integer> hours=new Spinner<>();
    private Spinner<Integer>  minutes=new Spinner();
    private Label label=new Label(" : ");
    public TimeField(){
        label.setStyle("-fx-font-size:20");
        getChildren().addAll(hours,label,minutes);
        setSpacing(10);
        minutes.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        hours.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
    }
    public void setPrefWidth(int width){
        hours.setPrefWidth(width/2-5);
        minutes.setPrefWidth(width/2-5);

    }

    public String getValue(){
        return hours.getValue()+":"+minutes.getValue()+":00";
    }
    public void setHours(int h){
        int h1=h%24;
        hours.getValueFactory().setValue(h1);
    }
    public void setMinutes(int m){
        int m1=m%60;
        minutes.getValueFactory().setValue(m1);
    }

}
