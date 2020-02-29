package machine;

import controllers.OperationController;

public class HealthMonitor extends CommandHandler implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            OperationController.setDanger(OperationController.Danger.PULSE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean moveUp(double value) {
        return getSuccessor().moveUp(value);
    }

    @Override
    public boolean moveDown(double value) {
        return getSuccessor().moveDown(value);
    }

    @Override
    public boolean moveRight(double value) {
        return getSuccessor().moveRight(value);
    }

    @Override
    public boolean moveLeft(double value) {
        return getSuccessor().moveLeft(value);
    }

    @Override
    public boolean setRadiation(double value) {
        return false;
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
        return false;
    }

    @Override
    public boolean stopXRay() {
        return false;
    }


}
