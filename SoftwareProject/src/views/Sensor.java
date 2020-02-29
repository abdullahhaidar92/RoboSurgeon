package views;

import java.util.Random;

public class Sensor extends Thread {

    public static final int MAX_PULSE=70;
    public static final int MIN_PULSE=-70;
    public static final int MAX_PRESSURE=140;
    public static final int MIN_PRESSURE=60;
    public static final int MAX_TEMPERATURE=45;
    public static final int MIN_TEMPERATURE=30;
    public static final int MAX_OXYGEN=5;
    public static final int MIN_OXYGEN=0;

    private double currentPulse;
    private double currentPressure;
    private double currentTemperature;
    private double currentOxygen;

    private Signal heartPulseSignal;
    private Signal bloodPressureSignal;
    private Signal temperatureSignal;
    private Signal oxygenLevelSignal;

    private boolean on;

    @Override
    public void run() {
       startRandoms();
    }

    public Sensor(Signal heartPulse,Signal asignal1,Signal abloodPressureSignal,Signal atemperatureSignal){
        heartPulseSignal=heartPulse;
        bloodPressureSignal=asignal1;
        temperatureSignal=abloodPressureSignal;
        oxygenLevelSignal=atemperatureSignal;
    }
    public void startRandoms(){
         Random rand=new Random();
         int t=20;
         double y;

        while (on) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            t+=1;
            y=0.5+rand.nextInt(1)+rand.nextDouble();
            y=t%2==0?-y:y;
            currentPulse=(y/1.5)*(MAX_PULSE-MIN_PULSE)+MIN_PULSE;
            currentPressure=(y/1.5)*(MAX_PRESSURE-MIN_PRESSURE)+MIN_PRESSURE;
            currentTemperature=(y/1.5)*(MAX_TEMPERATURE-MIN_TEMPERATURE)+MIN_TEMPERATURE;
            currentOxygen=(y/1.5)*(MAX_OXYGEN-MIN_OXYGEN)+MIN_OXYGEN;
            heartPulseSignal.add(t,y);
            bloodPressureSignal.add(t,y);
            temperatureSignal.add(t,y);
            oxygenLevelSignal.add(t,y);
        }
    }

    public double getCurrentPulse() {
        return currentPulse;
    }

    public double getCurrentPressure() {
        return currentPressure;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public double getCurrentOxygen() {
        return currentOxygen;
    }

    public void setON(){
        on=true;
    }

    public void setOFF(){
        on=false;
    }
}
