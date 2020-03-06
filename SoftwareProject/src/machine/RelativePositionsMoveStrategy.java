package machine;

public class RelativePositionsMoveStrategy implements MoveStrategy {
    private double leftCoilMagnitude;
    private double rightCoilMagnitude;
    private double topCoilMagnitude;
    private double bottomCoilMagnitude;
    private double totalMagnitude;

    public void setTotalMagnitude(double totalMagnitude) {
        this.totalMagnitude = totalMagnitude;
    }

    @Override
    public double getTotalMagnitude() {
        return totalMagnitude;
    }

    @Override
    public void moveUp(double yPos ) {
        moveVertical(yPos);
    }

    @Override
    public void moveDown(double yPos ) {
        moveVertical(yPos);
    }

    @Override
    public void moveRight(double xPos ) { moveHorizontal(xPos); }

    @Override
    public void moveLeft(double xPos ) {
        moveHorizontal(xPos);
    }

    @Override
    public void setCoilMagnitudes(double top, double bottom, double right, double left) {
        topCoilMagnitude=top;
        leftCoilMagnitude=left;
        rightCoilMagnitude=right;
        bottomCoilMagnitude=bottom;
    }

    @Override
    public double getLeftCoilMagnitude() {
        return leftCoilMagnitude;
    }

    @Override
    public double getRightCoilMagnitude() {
        return rightCoilMagnitude;
    }

    @Override
    public double getBottomCoilMagnitude() {
        return bottomCoilMagnitude;
    }

    @Override
    public double getTopCoilMagnitude() {
        return topCoilMagnitude;
    }
    private void moveHorizontal(double xPos){
        if(xPos==0){
            leftCoilMagnitude=totalMagnitude;
            rightCoilMagnitude=0;
            return;
        }
        double ratio=(1.0-xPos)/xPos;
        leftCoilMagnitude=(ratio/(ratio+1))*totalMagnitude;
        rightCoilMagnitude=totalMagnitude-leftCoilMagnitude;

        // System.out.println(leftCoilMagnitude);
        // System.out.println(rightCoilMagnitude);
    }
    private void moveVertical(double yPos){
        if(yPos==0){
            topCoilMagnitude=totalMagnitude;
            bottomCoilMagnitude=0;
            return;
        }
        double ratio=(1.0-yPos)/yPos;
        topCoilMagnitude=(ratio/(ratio+1))*totalMagnitude;
        bottomCoilMagnitude=totalMagnitude-topCoilMagnitude;

    }
}
