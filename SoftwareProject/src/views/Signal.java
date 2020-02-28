package views;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Signal {
    private  NumberAxis xAxis;
    private  NumberAxis yAxis;
    private  LineChart linechart;
    private  XYChart.Series series;

    public void add(int x,double y){
        if(x>20) {
            xAxis.setLowerBound(xAxis.getLowerBound() + 1);
            xAxis.setUpperBound(xAxis.getUpperBound() + 1);
            series.getData().remove(0);
        }

        series.getData().add(new XYChart.Data(x,y));
    }

    public void setColor(String color){
        getLineChart().getStylesheets().add(getClass().getResource("/css/signal"+color+".css").toExternalForm());

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
}