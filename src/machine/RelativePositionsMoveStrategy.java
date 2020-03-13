package machine;

public class RelativePositionsMoveStrategy implements MoveStrategy {
    private static double leftCoilMagnitude;
    private static double rightCoilMagnitude;
    private static double topCoilMagnitude;
    private static double bottomCoilMagnitude;

    public void execute(double xPos, double yPos, double totalMagnitude) {

        if (xPos == 0) {
            leftCoilMagnitude = totalMagnitude;
            rightCoilMagnitude = 0;
        } else {
            double ratioX = (1.0 - xPos) / xPos;
            leftCoilMagnitude = (ratioX / (ratioX + 1)) * totalMagnitude;
            rightCoilMagnitude = totalMagnitude - leftCoilMagnitude;
        }

        if (yPos == 0) {
            topCoilMagnitude = totalMagnitude;
            bottomCoilMagnitude = 0;
        } else {
            double ratioY = (1.0 - yPos) / yPos;
            topCoilMagnitude = (ratioY / (ratioY + 1)) * totalMagnitude;
            bottomCoilMagnitude = totalMagnitude - topCoilMagnitude;
        }

    }

    public double[] getResults() {
        double[] result = new double[4];
        result[0] = topCoilMagnitude;
        result[1] = bottomCoilMagnitude;
        result[2] = leftCoilMagnitude;
        result[3] = rightCoilMagnitude;
        return result;
    }
}
