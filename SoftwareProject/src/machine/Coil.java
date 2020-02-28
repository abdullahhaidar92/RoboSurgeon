package machine;

public class Coil {
    private double magnitude;
    private double current;
    private double xPos;
    private double yPos;
    // current for safety
    // in operation check for current if overdosed
    public void notifySeed(){
        Seed.getSeed().update();
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
        notifySeed();
    }

    public double getXPos() {
        return xPos;
    }

    public void setXPos(double xPos) {
        this.xPos = xPos;
    }

    public double getYPos() {
        return yPos;
    }

    public void setYPos(double yPos) {
        this.yPos = yPos;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }
}
