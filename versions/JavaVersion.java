public class JavaVersion {
    private static double leftCoilMagnitude=5;
    private static double rightCoilMagnitude=6;
    private static double topCoilMagnitude=7;
    private static double bottomCoilMagnitude=8;
    private static double totalMagnitude=10;
    private static double xPos, yPos;


    public static void main(String[] args) {
        execute(args);
        printResults();
    }

    public static void execute(String[] args) {

        if (args == null)
            throw new NullPointerException();
        if (args.length < 3)
            throw new IllegalArgumentException();

        try {
            xPos = Double.parseDouble(args[1]);
            yPos = Double.parseDouble(args[2]);
            totalMagnitude = Double.parseDouble(args[3]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

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

    private static void printResults() {
        System.out.println(topCoilMagnitude);
        System.out.println(bottomCoilMagnitude);
        System.out.println(leftCoilMagnitude);
        System.out.println(rightCoilMagnitude);
    }
}
