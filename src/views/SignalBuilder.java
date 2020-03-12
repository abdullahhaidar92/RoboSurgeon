package views;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.Random;

public class SignalBuilder {
    Signal signal=new Signal();

    public void buildXAxis(float from,float to, float step){
        NumberAxis xAxis = new NumberAxis(from,to , step);
        xAxis.setTickLabelsVisible(false);
        xAxis.setTickMarkVisible(false);
        signal.setxAxis(xAxis);
    }
    public void buildYAxis(float from,float to, float step){
       NumberAxis yAxis = new NumberAxis(from,to , step);
        yAxis.setTickLabelsVisible(false);
        //yAxis.setOpacity(0);
        signal.setyAxis(yAxis);
    }
    public void buildLineChart(){
        LineChart linechart = new LineChart(signal.getxAxis(), signal.getyAxis());
        linechart.setCreateSymbols(false);
        linechart.setLegendVisible(false);
        signal.setLinechart(linechart);
    }
    public void initialData(){
        XYChart.Series series = new XYChart.Series();
        int t=0;
        double y;
        Random rand=new Random();
        ArrayList<XYChart.Data> array=new ArrayList();
        array.add(new XYChart.Data(0,0));
        for(int i=0;i<20;i++)
        {
            t+=1;
            y=0.5+rand.nextInt(1)+rand.nextDouble();
            y=t%2==0?-y:y;
            array.add(new XYChart.Data(t,y));
        }
        series.getData().setAll(array);
        signal.setSeries(series);
        signal.getLineChart().getData().add(series);
        signal.setStylesheet();
    }
    public Signal getSignal(){
        return signal;
    }


}
