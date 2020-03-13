package machine;

import java.text.DecimalFormat;

public class Machine extends CommandHandler {
    private static Coil leftCoil;
    private static Coil rightCoil;
    private static Coil topCoil;
    private static Coil bottomCoil;
    private static Seed seed;
    private static XRay xRay;
    private static Machine machine;
    private static int emissionDuration;
    public static double totalMagnitude=100;
    private MultiVersionEnvironment environment;

    public static Machine getMachine() {
        if (machine == null)
            machine = new Machine();
        return machine;
    }

    public void start() {
        leftCoil = new Coil();
        rightCoil = new Coil();
        topCoil = new Coil();
        bottomCoil = new Coil();
        seed = Seed.getSeed();
        seed.setCoils(leftCoil, rightCoil, topCoil, bottomCoil);
        xRay=XRay.getXRay();

        leftCoil.setMagnitude(SAFE_RADIATION_LEVEL/2);
        rightCoil.setMagnitude(SAFE_RADIATION_LEVEL/2);
        topCoil.setMagnitude(SAFE_RADIATION_LEVEL/2);
        bottomCoil.setMagnitude(SAFE_RADIATION_LEVEL/2);

        environment=MultiVersionEnvironment.getEnvironment();
        environment.initialze();
    }


    public void setLeftCoilMagnitude(double value) {
        leftCoil.setMagnitude(value);
    }

    public void setRightCoilMagnitude(double value) {
        rightCoil.setMagnitude(value);
    }

    public void setTopCoilMagnitude(double value) {
        topCoil.setMagnitude(value);
    }

    public void setBottomCoilMagnitude(double value) {
        bottomCoil.setMagnitude(value);
    }


    public Coil getLeftCoil() {  return leftCoil; }

    public Coil getRightCoil() {
        return rightCoil;
    }
    public Coil getTopCoil() {  return topCoil;   }
    public Coil getBottomCoil() {
        return bottomCoil;
    }


    public Seed getSeed() {
        return seed;
    }

    public void setSeed(Seed seed) {
        seed = seed;
    }

    public double getInnerWidth() {
        return rightCoil.getXPos() - leftCoil.getXPos();
    }

    public double getInnerHeight() {
        return bottomCoil.getYPos() - topCoil.getYPos();
    }

    @Override
    public boolean moveUp(double value) {
        double y=Machine.getMachine().getSeed().getyPos()-value/getInnerHeight();
        if(y<0)
            return false;
        double x=Seed.getSeed().getxPos();
        move(x,y);
        return true;
    }

    private void move(double x,double y){

        environment.initialze();
        double[] magnitudes = environment.compute(new double[]{x,y,totalMagnitude});
        double topM=magnitudes[0];
        double bottomM=magnitudes[1];
        double leftM=magnitudes[2];
        double rightM=magnitudes[3];
       /* DecimalFormat format=new DecimalFormat("##.00");
        topM=Double.valueOf(format.format(topM));
        bottomM=Double.valueOf(format.format(bottomM));
        leftM=Double.valueOf(format.format(leftM));
        rightM=Double.valueOf(format.format(rightM));

        */

        setTopCoilMagnitude(topM);
        setBottomCoilMagnitude(bottomM);
        setLeftCoilMagnitude(leftM);
        setRightCoilMagnitude(rightM);
    }

    @Override
    public boolean moveDown(double value) {
        double y=Machine.getMachine().getSeed().getyPos()+value/getInnerHeight();
        if(y>1)
            return false;
        double x=Seed.getSeed().getxPos();
        move(x,y);
        return true;
    }

    @Override
    public boolean moveRight(double value) {
        double x=Machine.getMachine().getSeed().getxPos()+value/getInnerWidth();
        if(x>1)
            return false;
        double y=Seed.getSeed().getyPos();
        move(x,y);
        return true;
    }

    @Override
    public boolean moveLeft(double value) {
        double x=Machine.getMachine().getSeed().getxPos()-value/getInnerWidth();
        if(x>1)
            return false;
        double y=Seed.getSeed().getyPos();
        move(x,y);
        return true;
    }

    @Override
    public boolean setRadiation(double value) {
        seed.setRadiation(value);
        return true;
    }

    @Override
    public boolean setEmissionDuration(int value) {

        emissionDuration=value;
        return true;
    }

    @Override
    public boolean startEmission() {
        return true;
    }

    @Override
    public boolean stopEmission() {
        return true;
    }

    @Override
    public boolean startXRay() {
        xRay.setOn();
        return true;
    }

    @Override
    public boolean stopXRay() {
         xRay.setOff();
         return true;
    }



    public static XRay getXRay() {
        return xRay;
    }


    public static int getEmissionDuration() {
        return emissionDuration;
    }
}
