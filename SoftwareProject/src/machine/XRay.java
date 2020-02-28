package machine;

import java.util.Date;

public class XRay {
    private boolean on;
    private Date lastTimeOn;

    public void setOn(){
        on=true;
    }
    public void setOff(){
        if(on) {
            lastTimeOn=new Date();
            on=false;
        }
    }

    public Date getLastTimeOn() {
        return lastTimeOn;
    }

    public boolean isOn() {
        return on;
    }
}
