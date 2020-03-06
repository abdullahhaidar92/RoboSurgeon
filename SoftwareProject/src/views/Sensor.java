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
    private double[] chartValues=new double[4];
    private int time;

    public static Sensor sensor;

    @Override
    public void run() {
       startRandoms();
    }

    private Sensor(Signal heartPulse,Signal abloodPressureSignal,Signal atemperatureSignal, Signal aOxygenLevel){
        heartPulseSignal=heartPulse;
        bloodPressureSignal=abloodPressureSignal;
        temperatureSignal=atemperatureSignal;
        oxygenLevelSignal=aOxygenLevel;

        heartPulse.setSensor(this);
        bloodPressureSignal.setSensor(this);
        temperatureSignal.setSensor(this);
        oxygenLevelSignal.setSensor(this);
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
            chartValues[0]=y;
            chartValues[1]=y;
            chartValues[2]=y;
            chartValues[3]=y;
            time=t;
            notifySignals();

        }
    }

    public void notifySignals(){
        heartPulseSignal.update();
        bloodPressureSignal.update();
                temperatureSignal.update();
        oxygenLevelSignal.update();
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

    public double getChartValue(String type){
        switch (type){
            case "pulse":return chartValues[0];
            case "pressure":return chartValues[1];
            case "temperature":return chartValues[2];
            case "oxygen":return chartValues[3];
        }
        return 0;
    }

    public int getTime() {
        return time;
    }

    public static Sensor getSensor(Signal heartPulse,Signal abloodPressureSignal,Signal atemperatureSignal,Signal aOxygenLevel){
        if(sensor==null)
            sensor =new Sensor(heartPulse,abloodPressureSignal,atemperatureSignal,aOxygenLevel);
        return sensor;
    }

    public static Sensor getSensor(){
        return sensor;
    }

}
