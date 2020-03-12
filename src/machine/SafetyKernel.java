package machine;


import controllers.OperationController;

public class SafetyKernel extends CommandHandler {
    private Machine machine = Machine.getMachine();
    private int MAX_XPOS=10;
    private int MAX_YPOS=10;
    private static int MAX_Duration=12;
    public static int MAX_Radiation=12;
    private Seed seed =Seed.getSeed();

    @Override
    public boolean moveUp(double value) {
        // Ensure that the seed moves in a safe way
        // The difference between the current value and the
        // previous one should not exceed a certain LIMIT
        // same for other movements
        boolean flag = true;
        if (Math.abs(value) > MAX_YPOS )
        {
            flag =  false;
            value= MAX_YPOS;
            OperationController.setMoveError("Safety Kernel : Adjusting current value of the YPos");
        }
        return getSuccessor().moveUp(value) && flag;

    }

    @Override
    public boolean moveDown(double value) {
        boolean flag = true;
        if (Math.abs(value) > MAX_YPOS )
        {
            flag =  false;
            value=MAX_YPOS;
            OperationController.setMoveError("Safety Kernel : Adjusting current value of the YPos");
        }
        return getSuccessor().moveDown(value) && flag;
    }

    @Override
    public boolean moveRight(double value) {
        boolean flag = true;
        if (Math.abs(value) > MAX_XPOS )
        {
            flag =  false;
            value= MAX_XPOS;
            OperationController.setMoveError("Safety Kernel : Adjusting current value of the XPos");
        }
        return getSuccessor().moveRight(value) && flag;
    }

    @Override
    public boolean moveLeft(double value) {
        boolean flag = true;
        if (Math.abs(value) > MAX_XPOS )
        {
            flag =  false;
            value=MAX_XPOS;
            OperationController.setMoveError("Safety Kernel : Adjusting current value of the XPos");
        }
        return getSuccessor().moveLeft(value) && flag;
    }

    @Override
    public boolean setRadiation(double value) {
        boolean flag = true;

        if (value > MAX_Radiation )
        {
            flag =  false;
            value=MAX_Radiation;
            OperationController.setEmissionError("Safety Kernel : Adjusting current value of the Radiation");
        }
        return getSuccessor().setRadiation(value) && flag;
    }

    @Override
    public boolean startEmission() {
        // make sure that we are only emitting in the tumor zone
        // check if the location right now is within the tumor
        // XRays.isTumor(x,y)
        boolean isTumor = XRay.getXRay().isTumor(seed.getxPos(),seed.getyPos());
        //System.out.println("Safety Kernel :"+isTumor);
        //System.out.println("Safety Kernel :"+seed.getxPos() +" "+seed.getyPos());
        if (isTumor)
            return getSuccessor().startEmission();
        else
        {
            OperationController.setEmissionError("Safety Kernel : the seed is not in a location safe for emission");
            OperationController.setXrayError("Safety Kernel : the seed is not in a location safe for emission");
            return false;
        }
    }


    @Override
    public boolean setEmissionDuration(int value) {
        boolean flag = true;

        if (value > MAX_Duration )
        {
            flag =  false;
            value=  MAX_Duration;
            OperationController.setEmissionError("Safety Kernel : Adjusting current value of the Duration");
        }
        return getSuccessor().setEmissionDuration(value) && flag;
    }


    @Override
    public boolean stopEmission() {
        return getSuccessor().stopEmission();
    }

    @Override
    public boolean startXRay() {
        return getSuccessor().startXRay();
    }

    @Override
    public boolean stopXRay() {
        return getSuccessor().stopXRay();
    }

    public static int getMAX_Duration()
    {
        return MAX_Duration;
    }
}