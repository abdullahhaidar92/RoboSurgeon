package machine;



    public class Mo {
        private static double  leftCoilMagnitude;
        private static double  rightCoilMagnitude;
        private static double topCoilMagnitude;
        private static double bottomCoilMagnitude;
        private static double totalMagnitude;
        private static double xPos,yPos;


        public static void main(String[] args){
            execute(args);
            printResults();
        }

        public static void execute(String[] args) {

            if(args==null)
                throw new NullPointerException();
            if(args.length!=3)
                throw new IllegalArgumentException();

            try {
                xPos = Double.parseDouble(args[0]);
                yPos = Double.parseDouble(args[0]);
                totalMagnitude = Double.parseDouble(args[0]);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }

            if(xPos==0){
                leftCoilMagnitude=totalMagnitude;
                rightCoilMagnitude=0;
            }
            else {
                double ratioX = (1.0 - xPos) / xPos;
                leftCoilMagnitude = (ratioX / (ratioX + 1)) * totalMagnitude;
                rightCoilMagnitude = totalMagnitude - leftCoilMagnitude;
            }

            if(yPos==0){
                topCoilMagnitude=totalMagnitude;
                bottomCoilMagnitude=0;
            }
            else {
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





