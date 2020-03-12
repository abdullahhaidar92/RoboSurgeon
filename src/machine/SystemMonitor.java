package machine;

import controllers.OperationController;
import utility.Dates;
import java.util.Date;


public class SystemMonitor extends CommandHandler {

    private Machine machine = Machine.getMachine();
    private int MAX_CURRENT=100;
    private int SAFE_RADIATION_QUANTITY=600;
    private Date startEmissionTime=new Date();
    private double radiationQuantity;
    private double radiationLevel;
    private double radiationDuration;


    @Override
    public boolean moveUp(double value) {
        boolean flag = true;
        if (machine.getTopCoil().getCurrent() > MAX_CURRENT) {
            machine.getTopCoil().setCurrent(MAX_CURRENT);
            flag = false;
        }
        if (machine.getBottomCoil().getCurrent() > MAX_CURRENT) {
            machine.getBottomCoil().setCurrent(MAX_CURRENT);
            flag = false;
        }
        OperationController.setMoveError("System Monitor : Adjusting current value of the coils");
        return getSuccessor().moveUp(value) && flag;

    }

    @Override
    public boolean moveDown(double value) {
        boolean flag = true;
        if (machine.getTopCoil().getCurrent() > MAX_CURRENT) {
            machine.getTopCoil().setCurrent(MAX_CURRENT);
            flag = false;
        }
        if (machine.getBottomCoil().getCurrent() > MAX_CURRENT) {
            machine.getBottomCoil().setCurrent(MAX_CURRENT);
            flag = false;
        }
        OperationController.setMoveError("System Monitor : Adjusting current value of the coils");
        return getSuccessor().moveDown(value) && flag;
    }

    @Override
    public boolean moveRight(double value) {
        boolean flag = true;
        if (machine.getLeftCoil().getCurrent() > MAX_CURRENT) {
            machine.getLeftCoil().setCurrent(MAX_CURRENT);
            flag = false;
        }
        if (machine.getRightCoil().getCurrent() > MAX_CURRENT) {
            machine.getRightCoil().setCurrent(MAX_CURRENT);
            flag = false;
        }
        OperationController.setMoveError("System Monitor : Adjusting current value of the coils");
        return getSuccessor().moveRight(value) && flag;
    }

    @Override
    public boolean moveLeft(double value) {
        boolean flag = true;
        if (machine.getLeftCoil().getCurrent() > MAX_CURRENT) {
            machine.getLeftCoil().setCurrent(MAX_CURRENT);
            flag = false;
        }
        if (machine.getRightCoil().getCurrent() > MAX_CURRENT) {
            machine.getRightCoil().setCurrent(MAX_CURRENT);
            flag = false;
        }
        return getSuccessor().moveLeft(value) && flag;
    }

    @Override
    public boolean setRadiation(double value) {
        radiationLevel=value;
        return getSuccessor().setRadiation(value);
    }

    @Override
    public boolean setEmissionDuration(int value) {
        radiationDuration=value;
        return getSuccessor().setEmissionDuration(value);
    }

    @Override
    public boolean startEmission() {
        if(radiationLevel >= SAFE_RADIATION_QUANTITY) {
            OperationController.setEmissionError("System Monitor : Maximum quantity of radiation already reached");
            return false;
        }
        if(radiationQuantity + radiationLevel*radiationDuration > SAFE_RADIATION_QUANTITY) {
            OperationController.setEmissionError("System Monitor : Radiation will exceed the allowed quantity");
            return false;
        }

        startEmissionTime=new Date();
        return getSuccessor().startEmission();
    }

    @Override
    public boolean stopEmission() {
         long timeDiff=Dates.subtract(new Date(),startEmissionTime);
         radiationQuantity+=timeDiff*radiationLevel;
         return getSuccessor().stopEmission();
    }

    @Override
    public boolean startXRay() {
        long timeDiff = Dates.subtract(new Date(), machine.getXRay().getLastTimeOn());
        if (timeDiff > 200) {
            return getSuccessor().startXRay();
        }
        OperationController.setXrayError("System Monitor : XRay must be off for al least 0.2 seconds");
        return false;
    }

    @Override
    public boolean stopXRay() {
        return getSuccessor().stopXRay();
    }

    public void setSafeRadiationQuantity(int value){
        SAFE_RADIATION_QUANTITY=value;
    }
    public void setMaxCurrent(int value){
        MAX_CURRENT=value;
    }
}
