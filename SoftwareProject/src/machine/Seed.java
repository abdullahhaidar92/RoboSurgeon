package machine;

import java.text.DecimalFormat;

public class Seed {
    private double xPos;
    private double yPos;
    private double radiation;
    private Coil leftCoil;
    private Coil rightCoil;
    private Coil topCoil;
    private Coil bottomCoil;
    private static Seed seed;

    private Seed (){
    }

    public static Seed getSeed(){
        if(seed==null)
            seed=new Seed();
        return seed;
    }

    public void move(double x, double y){
        this.xPos =x;
        this.yPos =y;
    }
    public void update(){
        double width=Math.abs(rightCoil.getXPos()-leftCoil.getXPos());
        double height=Math.abs(bottomCoil.getYPos()-topCoil.getYPos());
        xPos=1-leftCoil.getMagnitude()/(rightCoil.getMagnitude()+leftCoil.getMagnitude());
        yPos=1-topCoil.getMagnitude()/(topCoil.getMagnitude()+bottomCoil.getMagnitude());
        DecimalFormat decimalFormat=new DecimalFormat("##.00");
        xPos=Double.valueOf(decimalFormat.format(xPos));
        yPos=Double.valueOf(decimalFormat.format(yPos));
        //notify XRAY screen
    }

    public void setCoils(Coil leftCoil, Coil rightCoil, Coil topCoil, Coil bottomCoil) {
        this.leftCoil = leftCoil;
        this.rightCoil = rightCoil;
        this.topCoil = topCoil;
        this.bottomCoil = bottomCoil;
    }

    public double getRadiation() {
        return radiation;
    }

    public void setRadiation(double radiation) {
        this.radiation = radiation;
    }

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }
}
