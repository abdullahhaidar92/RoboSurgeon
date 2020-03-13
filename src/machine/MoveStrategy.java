package machine;

public interface MoveStrategy {
    void moveUp(double yPos );
    void moveDown(double yPos );
    void moveRight(double xPos );
    void moveLeft(double xPos );
    void setCoilMagnitudes(double top,double bottom,double right,double left);
    double getLeftCoilMagnitude();
    double getRightCoilMagnitude();
    double getBottomCoilMagnitude();
    double getTopCoilMagnitude();
    void setTotalMagnitude(double totalMagnitude);
    double getTotalMagnitude();


}
