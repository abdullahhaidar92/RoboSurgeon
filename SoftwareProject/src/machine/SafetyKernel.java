package machine;

public class SafetyKernel extends CommandHandler {

    @Override
    public boolean moveUp(double value) {
        // Ensure that the seed moves in a safe way
        // The difference between the current value and the
        // previous one should not exceed a certain LIMIT
        // same for other movements
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
    public boolean startEmission() {
        // make sure that we are only emitting in the tumor zone
        // check if the location right now is within the tumor
        // XRays.isTumor(x,y)
        return false;
    }
    

    @Override
    public boolean setEmissionDuration(double value) {
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
