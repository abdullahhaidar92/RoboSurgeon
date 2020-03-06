package machine;

import views.XraysView;

import java.util.Date;

public class XRay {
    private boolean on;
    private Date lastTimeOn;
    private  XraysView xraysView;
    public static XRay xRay;

    public static XRay getXRay(){
        if(xRay==null)
            xRay=new XRay();
        return xRay;
    }

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

    public boolean isTumor(double x, double y){
        for(XraysView.Tumor tumor: xraysView.getTumors()){
            // if distance between (x,y) of seed and (x,y) of tumor
            // is smaller than radius of tumor or radius of the seed
            double distance = Math.sqrt(Math.pow(tumor.getX() - x,2) + Math.pow(tumor.getY() - y,2));
            if(tumor.getRadius() < 25 && distance <= 25) return true;
            else if(distance <= tumor.getRadius()) return true;
        }
        return false;
    }

    private XRay(){

    }
    public void setXraysView(XraysView xraysView) {
        this.xraysView = xraysView;
    }
}
