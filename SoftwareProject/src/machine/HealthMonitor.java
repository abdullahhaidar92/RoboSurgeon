package machine;

import controllers.OperationController;
import models.Operation;
import views.Sensor;

public class HealthMonitor extends CommandHandler implements Runnable{

    private Sensor sensor=Sensor.getSensor();

    // declare MAX_VALUE and MIN_VALUE for pulse,pressure,temperature,oxygen
    // declare allowed differences for pulse,pressure,temperature,oxygen
    // declare previous values for pulse,pressure,temperature,oxygen

    private double MAX_PULSE=200, MIN_PULSE=-200, MAX_OXYGEN=100, MIN_OXYGEN=0;
    private double MAX_TEMP=50, MIN_TEMP=30, MIN_PRESSURE=100, MAX_PRESSURE=0;
    private double SAFE_PULSE_DIFF=100, SAFE_OXYGEN_DIFF=100;
    private double SAFE_TEMP_DIFF=100, SAFE_PRESSURE_DIFF=100;
    private double prevPulse, prevOxygen, currentPulse, currentOxygen;
    private double prevTemp, prevPressure, currentTemp, currentPressure;
    private boolean on;
    private boolean[] danger=new boolean[4];


    /*
     * There will be two instances of this class:
     * (1) The first object will be running in parallel, it monitors the patient's state.
     *       Every second it obtains the values from the sensor and
     *       a) compares it to the normal values (MAX_VALUE and MIN_VALUE defined above )
     *           Call OperationController.setDanger(OperationController.Danger.?) if any
     *           value implies danger.
     *       b) calculate the difference between the current values and the previous ones,
     *           the difference must not exceed the allowed differences above, example :
     *           sensor.pulse - prevPulse < SAFE_PULSE_DIFFERENCE
     *          Call OperationController.setDanger(OperationController.Danger.?) if the any
     *          value implies danger.
     *       Only The run method will implement all of above precautions, only the code inside the
     *       run method will be run in parallel. The run method will be similar to the one
     *       in  the sensor class .
     * (2) The second object will handle requests and forward them to its successor in
     *       the chain ( similar to System Monitor)
     *       every function checks if the patient state allows performing the request
     *       just compare the sensor current values to MAX and MIN declared above
     *
     *
     *
     * */


    @Override
    public void run() {
        while(on){
            try {
                Thread.sleep(1000);

            currentOxygen = sensor.getCurrentOxygen();
            if(currentOxygen < MIN_OXYGEN || currentOxygen > MAX_OXYGEN || (currentOxygen - prevOxygen) > SAFE_OXYGEN_DIFF) {
                OperationController.setDanger(OperationController.Danger.OXYGEN);
                danger[0]=true;
            }
            else if(danger[0])
                    OperationController.setSafe(OperationController.Danger.OXYGEN);
            prevOxygen = currentOxygen;

            currentPulse = sensor.getCurrentPulse();
            if(currentPulse < MIN_PULSE || currentPulse > MAX_PULSE || (currentPulse - prevPulse) > SAFE_PULSE_DIFF){
                OperationController.setDanger(OperationController.Danger.PULSE);
                danger[1]=true;
            }
            else if(danger[1])
                OperationController.setSafe(OperationController.Danger.PULSE);
            prevPulse = currentPulse;

            currentTemp = sensor.getCurrentTemperature();
            if(currentTemp < MIN_TEMP || currentTemp > MAX_TEMP || (currentTemp - prevTemp) > SAFE_TEMP_DIFF){
                OperationController.setDanger(OperationController.Danger.TEMPERATURE);
                danger[2]=true;
            }
            else if(danger[2])
                OperationController.setSafe(OperationController.Danger.TEMPERATURE);
            prevTemp = currentTemp;

            currentPressure = sensor.getCurrentPressure();
            if(currentPressure < MIN_PRESSURE || currentPressure > MAX_PRESSURE || (currentPressure - prevPressure) > SAFE_PRESSURE_DIFF){
                OperationController.setDanger(OperationController.Danger.PRESSURE);
                danger[3]=true;
            }
            else if(danger[3])
                OperationController.setSafe(OperationController.Danger.PRESSURE);
            prevPressure = currentPressure;
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("Health Monitor malfunction");
            }
        }
    }

    @Override
    public boolean moveUp(double value) {
        if(currentOxygen < MIN_OXYGEN || currentOxygen > MAX_OXYGEN || (currentOxygen - prevOxygen) > SAFE_OXYGEN_DIFF)
            return false;
        if(currentPulse < MIN_PULSE || currentPulse > MAX_PULSE || (currentPulse - prevPulse) > SAFE_PULSE_DIFF)
            return false;
        if(currentTemp < MIN_TEMP || currentTemp > MAX_TEMP || (currentTemp - prevTemp) > SAFE_TEMP_DIFF)
            return false;
        if(currentPressure < MIN_PRESSURE || currentPressure > MAX_PRESSURE || (currentPressure - prevPressure) > SAFE_PRESSURE_DIFF)
            return false;
        return getSuccessor().moveUp(value);
    }

    @Override
    public boolean moveDown(double value) {
        if(currentOxygen < MIN_OXYGEN || currentOxygen > MAX_OXYGEN || (currentOxygen - prevOxygen) > SAFE_OXYGEN_DIFF)
            return false;
        if(currentPulse < MIN_PULSE || currentPulse > MAX_PULSE || (currentPulse - prevPulse) > SAFE_PULSE_DIFF)
            return false;
        if(currentTemp < MIN_TEMP || currentTemp > MAX_TEMP || (currentTemp - prevTemp) > SAFE_TEMP_DIFF)
            return false;
        if(currentPressure < MIN_PRESSURE || currentPressure > MAX_PRESSURE || (currentPressure - prevPressure) > SAFE_PRESSURE_DIFF)
            return false;
        return getSuccessor().moveDown(value);
    }

    @Override
    public boolean moveRight(double value) {
        if(currentOxygen < MIN_OXYGEN || currentOxygen > MAX_OXYGEN || (currentOxygen - prevOxygen) > SAFE_OXYGEN_DIFF)
            return false;
        if(currentPulse < MIN_PULSE || currentPulse > MAX_PULSE || (currentPulse - prevPulse) > SAFE_PULSE_DIFF)
            return false;
        if(currentTemp < MIN_TEMP || currentTemp > MAX_TEMP || (currentTemp - prevTemp) > SAFE_TEMP_DIFF)
            return false;
        if(currentPressure < MIN_PRESSURE || currentPressure > MAX_PRESSURE || (currentPressure - prevPressure) > SAFE_PRESSURE_DIFF)
            return false;
        return getSuccessor().moveRight(value);
    }

    @Override
    public boolean moveLeft(double value) {
        if(currentOxygen < MIN_OXYGEN || currentOxygen > MAX_OXYGEN || (currentOxygen - prevOxygen) > SAFE_OXYGEN_DIFF)
            return false;
        if(currentPulse < MIN_PULSE || currentPulse > MAX_PULSE || (currentPulse - prevPulse) > SAFE_PULSE_DIFF)
            return false;
        if(currentTemp < MIN_TEMP || currentTemp > MAX_TEMP || (currentTemp - prevTemp) > SAFE_TEMP_DIFF)
            return false;
        if(currentPressure < MIN_PRESSURE || currentPressure > MAX_PRESSURE || (currentPressure - prevPressure) > SAFE_PRESSURE_DIFF)
            return false;
        return getSuccessor().moveLeft(value);
    }

    @Override
    public boolean setRadiation(double value) {
        if(currentOxygen < MIN_OXYGEN || currentOxygen > MAX_OXYGEN || (currentOxygen - prevOxygen) > SAFE_OXYGEN_DIFF)
            return false;
        if(currentPulse < MIN_PULSE || currentPulse > MAX_PULSE || (currentPulse - prevPulse) > SAFE_PULSE_DIFF)
            return false;
        if(currentTemp < MIN_TEMP || currentTemp > MAX_TEMP || (currentTemp - prevTemp) > SAFE_TEMP_DIFF)
            return false;
        if(currentPressure < MIN_PRESSURE || currentPressure > MAX_PRESSURE || (currentPressure - prevPressure) > SAFE_PRESSURE_DIFF)
            return false;
        return getSuccessor().setRadiation(value);
    }

    @Override
    public boolean setEmissionDuration(int value) {
        if(currentOxygen < MIN_OXYGEN || currentOxygen > MAX_OXYGEN || (currentOxygen - prevOxygen) > SAFE_OXYGEN_DIFF)
            return false;
        if(currentPulse < MIN_PULSE || currentPulse > MAX_PULSE || (currentPulse - prevPulse) > SAFE_PULSE_DIFF)
            return false;
        if(currentTemp < MIN_TEMP || currentTemp > MAX_TEMP || (currentTemp - prevTemp) > SAFE_TEMP_DIFF)
            return false;
        if(currentPressure < MIN_PRESSURE || currentPressure > MAX_PRESSURE || (currentPressure - prevPressure) > SAFE_PRESSURE_DIFF)
            return false;
        return getSuccessor().setEmissionDuration(value);
    }

    @Override
    public boolean startEmission() {
        if(currentOxygen < MIN_OXYGEN || currentOxygen > MAX_OXYGEN || (currentOxygen - prevOxygen) > SAFE_OXYGEN_DIFF)
            return false;
        if(currentPulse < MIN_PULSE || currentPulse > MAX_PULSE || (currentPulse - prevPulse) > SAFE_PULSE_DIFF)
            return false;
        if(currentTemp < MIN_TEMP || currentTemp > MAX_TEMP || (currentTemp - prevTemp) > SAFE_TEMP_DIFF)
            return false;
        if(currentPressure < MIN_PRESSURE || currentPressure > MAX_PRESSURE || (currentPressure - prevPressure) > SAFE_PRESSURE_DIFF)
            return false;
        return getSuccessor().startEmission();
    }

    @Override
    public boolean stopEmission() {
        if(currentOxygen < MIN_OXYGEN || currentOxygen > MAX_OXYGEN || (currentOxygen - prevOxygen) > SAFE_OXYGEN_DIFF)
            return false;
        if(currentPulse < MIN_PULSE || currentPulse > MAX_PULSE || (currentPulse - prevPulse) > SAFE_PULSE_DIFF)
            return false;
        if(currentTemp < MIN_TEMP || currentTemp > MAX_TEMP || (currentTemp - prevTemp) > SAFE_TEMP_DIFF)
            return false;
        if(currentPressure < MIN_PRESSURE || currentPressure > MAX_PRESSURE || (currentPressure - prevPressure) > SAFE_PRESSURE_DIFF)
            return false;
        return getSuccessor().stopEmission();
    }

    @Override
    public boolean startXRay() {
        if(currentOxygen < MIN_OXYGEN || currentOxygen > MAX_OXYGEN || (currentOxygen - prevOxygen) > SAFE_OXYGEN_DIFF)
            return false;
        if(currentPulse < MIN_PULSE || currentPulse > MAX_PULSE || (currentPulse - prevPulse) > SAFE_PULSE_DIFF)
            return false;
        if(currentTemp < MIN_TEMP || currentTemp > MAX_TEMP || (currentTemp - prevTemp) > SAFE_TEMP_DIFF)
            return false;
        if(currentPressure < MIN_PRESSURE || currentPressure > MAX_PRESSURE || (currentPressure - prevPressure) > SAFE_PRESSURE_DIFF)
            return false;
        return getSuccessor().startXRay();
    }

    @Override
    public boolean stopXRay() {
        if(currentOxygen < MIN_OXYGEN || currentOxygen > MAX_OXYGEN || (currentOxygen - prevOxygen) > SAFE_OXYGEN_DIFF)
            return false;
        if(currentPulse < MIN_PULSE || currentPulse > MAX_PULSE || (currentPulse - prevPulse) > SAFE_PULSE_DIFF)
            return false;
        if(currentTemp < MIN_TEMP || currentTemp > MAX_TEMP || (currentTemp - prevTemp) > SAFE_TEMP_DIFF)
            return false;
        if(currentPressure < MIN_PRESSURE || currentPressure > MAX_PRESSURE || (currentPressure - prevPressure) > SAFE_PRESSURE_DIFF)
            return false;
        return getSuccessor().stopXRay();
    }

    public void setOn() {
        on = true;
    }
    public void setOff() {
        on = false;
    }
}