package views;

import controllers.OperationController;
import javafx.collections.ObservableList;
import javafx.css.Stylesheet;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Signal {
    private  NumberAxis xAxis;
    private  NumberAxis yAxis;
    private  LineChart linechart;
    private  XYChart.Series series;
    private Sensor sensor;
    private String type;
    private ObservableList<String> stylesheet;
    private String redStyle=getClass().getResource("/css/signalRed.css").toExternalForm();
    private String blueStyle=getClass().getResource("/css/signalBlue.css").toExternalForm();
    private boolean currentDanger=false;
    private boolean prevDanger=false;

    public void update(){
        switch(type){
            case "pulse" :add(sensor.getTime(),sensor.getChartValue("pulse"));break;
            case "pressure" :add(sensor.getTime(),sensor.getChartValue("pressure"));break;
            case "temperature" :add(sensor.getTime(),sensor.getChartValue("temperature"));break;
            case "oxygen" :add(sensor.getTime(),sensor.getChartValue("oxygen"));break;
        }
    }

    public void add(int x,double y){
        if(x>20) {
            xAxis.setLowerBound(xAxis.getLowerBound() + 1);
            xAxis.setUpperBound(xAxis.getUpperBound() + 1);
            series.getData().remove(0);
        }
        if(prevDanger!=currentDanger)
            if(currentDanger)
            try {
                stylesheet.add(redStyle);
            }catch (Exception e){
                System.out.println("Changing signal color to danger failed");
            }
            else
                try {
                    stylesheet.add(blueStyle);
                }catch (Exception e){
                    System.out.println("Changing signal color to safe failed");
                }
        prevDanger=currentDanger;
        series.getData().add(new XYChart.Data(x,y/1.4));
    }


    public synchronized void setDanger(){
       currentDanger=true;
    }


    public synchronized void setSafe(){
        currentDanger=false;
    }

    public NumberAxis getxAxis() {
        return xAxis;
    }

    public void setxAxis(NumberAxis xAxis) {
        this.xAxis = xAxis;
    }

    public NumberAxis getyAxis() {
        return yAxis;
    }

    public void setyAxis(NumberAxis yAxis) {
        this.yAxis = yAxis;
    }

    public LineChart getLineChart() {
        return linechart;
    }

    public void setLinechart(LineChart linechart) {
        this.linechart = linechart;
    }

    public XYChart.Series getSeries() {
        return series;
    }

    public void setSeries(XYChart.Series series) {
        this.series = series;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
    public void setStylesheet(){
        stylesheet=getLineChart().getStylesheets();
    }
}