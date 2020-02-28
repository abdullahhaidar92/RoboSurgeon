package machine;

public class SafetyKernel extends CommandHandler {

    @Override
    public boolean moveUp(double value) {
        return false;
    }

    @Override
    public boolean moveDown(double value) {
        return false;
    }

    @Override
    public boolean moveRight(double value) {
        return false;
    }

    @Override
    public boolean moveLeft(double value) {
        return false;
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
