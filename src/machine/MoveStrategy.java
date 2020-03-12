package machine;

public interface MoveStrategy {

    void execute(double xPos, double yPos, double totalMagnitude);

    double[] getResults();

}
