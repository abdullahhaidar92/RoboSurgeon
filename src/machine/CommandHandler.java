package machine;

public abstract class CommandHandler {
    protected double SAFE_RADIATION_LEVEL = 100;
    private MoveStrategy moveStrategy;
    private CommandHandler successor;

    public abstract boolean moveUp(double value);

    public abstract boolean moveDown(double value);

    public abstract boolean moveRight(double value);

    public abstract boolean moveLeft(double value);

    public abstract boolean setRadiation(double value);

    public abstract boolean setEmissionDuration(int value);

    public abstract boolean startEmission();

    public abstract boolean stopEmission();

    public abstract boolean startXRay();

    public abstract boolean stopXRay();

    public void setSuccessor(CommandHandler successor) {
        this.successor = successor;
    }

    public CommandHandler getSuccessor() {
        return successor;
    }

    public void setStrategy(MoveStrategy strategy) {
        this.moveStrategy = strategy;
    }

    public MoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

}
