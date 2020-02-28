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
        leftCoil.setXPos(0);
        rightCoil.setXPos(100);
        xRay=new XRay();
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

    public static Coil getRightCoil() {
        return rightCoil;
    }


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
        MoveStrategy strategy=getMoveStrategy();
        double newY=Machine.getMachine().getSeed().getyPos()-value/getInnerWidth();
        if(newY<0)
            return false;
        strategy.setCoilMagnitudes(topCoil.getMagnitude(),bottomCoil.getMagnitude(),rightCoil.getMagnitude(),leftCoil.getMagnitude());
        strategy.moveUp(newY);
        double topM=strategy.getTopCoilMagnitude();
        double bottomM=strategy.getBottomCoilMagnitude();
        DecimalFormat format=new DecimalFormat("##.00");
        topM=Double.valueOf(format.format(topM));
        bottomM=Double.valueOf(format.format(bottomM));
        setTopCoilMagnitude(topM);
        setBottomCoilMagnitude(bottomM);
        return true;
    }

    @Override
    public boolean moveDown(double value) {
        MoveStrategy strategy=getMoveStrategy();
        double newY=Machine.getMachine().getSeed().getyPos()+value/getInnerWidth();
        if(newY >1)
            return false;
        strategy.setCoilMagnitudes(topCoil.getMagnitude(),bottomCoil.getMagnitude(),rightCoil.getMagnitude(),leftCoil.getMagnitude());
        strategy.moveDown(newY);
        double topM=strategy.getTopCoilMagnitude();
        double bottomM=strategy.getBottomCoilMagnitude();
        DecimalFormat format=new DecimalFormat("##.00");
        topM=Double.valueOf(format.format(topM));
        bottomM=Double.valueOf(format.format(bottomM));
        setTopCoilMagnitude(topM);
        setBottomCoilMagnitude(bottomM);
        return true;
    }

    @Override
    public boolean moveRight(double value) {

        MoveStrategy strategy=getMoveStrategy();
        double newX=Machine.getMachine().getSeed().getxPos()+value/getInnerWidth();
        if(newX>1)
            return false;

        strategy.setCoilMagnitudes(topCoil.getMagnitude(),bottomCoil.getMagnitude(),rightCoil.getMagnitude(),leftCoil.getMagnitude());
        strategy.moveRight(newX);
        double leftM=strategy.getLeftCoilMagnitude();
        double rightM=strategy.getRightCoilMagnitude();
        DecimalFormat format=new DecimalFormat("##.00");
        leftM=Double.valueOf(format.format(leftM));
        rightM=Double.valueOf(format.format(rightM));
        setLeftCoilMagnitude(leftM);
        setRightCoilMagnitude(rightM);
        return false;
    }

    @Override
    public boolean moveLeft(double value) {
        MoveStrategy strategy=getMoveStrategy();
        double newX=Machine.getMachine().getSeed().getxPos()-value/getInnerWidth();
        if (newX < 0)
            return false;
        strategy.setCoilMagnitudes(topCoil.getMagnitude(),bottomCoil.getMagnitude(),rightCoil.getMagnitude(),leftCoil.getMagnitude());
        strategy.moveLeft(newX);
        double leftM=strategy.getLeftCoilMagnitude();
        double rightM=strategy.getRightCoilMagnitude();
        DecimalFormat format=new DecimalFormat("##.00");
        leftM=Double.valueOf(format.format(leftM));
        rightM=Double.valueOf(format.format(rightM));
        setLeftCoilMagnitude(leftM);
        setRightCoilMagnitude(rightM);
        return false;
    }

    @Override
    public boolean setRadiation(double value) {
        seed.setRadiation(value);return true;
    }

    @Override
    public boolean setEmissionDuration(double value) {
        return false;
    }

    @Override
    public boolean startEmission() {
        return false;
    }

    @Override
    public boolean stopEmission() {
        return false;
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

    public static Coil getTopCoil() {
        return topCoil;
    }

    public static XRay getXRay() {
        return xRay;
    }
}
