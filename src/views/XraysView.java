package views;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import controllers.OperationController;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import machine.Seed;

public class XraysView extends VBox {
    OperationDashboard operationDashboard;
    OperationController operationController;
    Canvas canvas = new Canvas(850, 500);
    GraphicsContext context = canvas.getGraphicsContext2D();
    private Seed seed= Seed.getSeed();
    private int width=850,height=500;
    private double x =seed.getxPos()*width , y =seed.getyPos()*height, stepX = 10, stepY = 10;
    private ArrayList<Tumor> tumors = new ArrayList();
    Label xValue = new Label(""+x);
    Label yValue = new Label(""+y);
    private AnimationTimer anime;
    private int emissionDuration=1;

    public XraysView(OperationDashboard dashboard){
        setMinWidth(dashboard.getWidth()*0.7);
        setMinHeight(dashboard.getHeight()*0.75);
        operationDashboard = dashboard;
        Label xLabel = new Label("x: ");
        Label yLabel = new Label(" y: ");
        xLabel.setFont(Font.font("Consolas", 34));
        yLabel.setFont(Font.font("Consolas", 34));
        xValue.setFont(Font.font("Consolas", 34));
        yValue.setFont(Font.font("Consolas", 34));
        HBox coordinates = new HBox();
        coordinates.getChildren().addAll(xLabel, xValue, yLabel, yValue);
        coordinates.setAlignment(Pos.CENTER);
        coordinates.getStyleClass().add("xrayLable");
        StackPane stackPane = new StackPane();
        setSpacing(20);
        setPadding(new Insets(15,4,4,4));
        File file = new File("BrainTumor/Tumor3.jpg");
        Image image = new Image(file.toURI().toString());
        ImageView imageView = new ImageView(image);

        imageView.setFitHeight(500);
        imageView.setFitWidth(850);

        stackPane.getChildren().addAll(imageView, canvas);
        this.getChildren().addAll(coordinates, stackPane);
        this.setAlignment(Pos.TOP_CENTER);
        getStyleClass().add("xrays");

        init();
        draw();
        this.setOnMouseEntered(e -> {
            this.requestFocus();
        });


    }
    public void init(){
        Random rand = new Random();
        //number of tumors in an image ranges between 2 and 4
        int nbTumor = rand.nextInt(5) % 3 + 2;
        for(int i = 0 ; i < nbTumor; i++){
            double r = rand.nextDouble() * 70 + 6;
            tumors.add(new Tumor(440 * rand.nextDouble() + 210,
                    220 * rand.nextDouble() + 120,
                    r, 1 - (1 / (r + 1))));
        }
        initAnimation();
    }
    public void draw(){
        context.setFill(Color.ORANGE);
        for (Tumor tumor : tumors)
            context.fillOval(tumor.x, tumor.y, tumor.radius, tumor.radius);
        drawSeed();
    }
    public void render() {
        context.clearRect(0,0, 850, 500);
        draw();
    }
    public void drawSeed(){
        context.setFill(Color.RED);
        context.fillOval(x, y, 15, 15);
    }
    public void affectTumor(){
        final double opacity = 0.7;
        context.setGlobalAlpha(opacity);
    }

    public void setStepX() {
        stepX = operationDashboard.getSeedControl().getStepX();
    }


    public void setStepY() {
        stepY = operationDashboard.getSeedControl().getStepY();
    }

    public void moveRight(){
        setStepX();
        operationController.moveRight(stepX);
        x =Double.parseDouble(new DecimalFormat("##.00").format(seed.getxPos()*width));
        xValue.setText(String.valueOf(x));
        render();


    }
    public void moveLeft(){
        setStepX();
        operationController.moveLeft(stepX);
        x =Double.parseDouble(new DecimalFormat("##.00").format(seed.getxPos()*width));
        xValue.setText(String.valueOf(x));
        render();
    }
    public void moveUp(){
        setStepY();
        operationController.moveUp(stepY);
        y =Double.parseDouble(new DecimalFormat("##.00").format(seed.getyPos()*height));
        yValue.setText(String.valueOf(y));
        render();


    }
    public void moveDown(){
        setStepY();
        operationController.moveDown(stepY);
        y =Double.parseDouble(new DecimalFormat("##.00").format(seed.getyPos()*height));
        yValue.setText(String.valueOf(y));
        render();
    }
    public boolean isTumor(){
        double x = Double.valueOf(xValue.getText().trim());
        double y = Double.valueOf(yValue.getText().trim());
        double distance = 0.0;
        for(Tumor tumor: tumors){
            // if distance between (x,y) of seed and (x,y) of tumor
            // is smaller than radius of tumor or radius of the seed
            System.out.println("XRays tumor x : "+tumor.x +"y :"+y);
            distance = Math.sqrt(Math.pow(tumor.x - x,2) + Math.pow(tumor.y - y,2));
            if(tumor.radius < 25 && distance <= 25) return true;
            else if(distance <= tumor.radius) return true;
        }
        return false;
    }

    public void doAnimation(int i){
        render();
        context.setStroke(Color.WHITE);
        switch (i){
            case 0: context.strokeOval(x - 2.5,y - 2.5,30,30);
                break;
            case 1: context.strokeOval(x - 10,y - 10,45,45);
                break;
            default: context.strokeOval(x - 17.5,y - 17.5,60,60);
                break;
        }
    }
    public void initAnimation(){
        anime = new AnimationTimer(){
            long prevNanos = 0;
            double limit = 0;
            int i = 0;
            double timer = 0;
            @Override
            public void handle(long now) {
                if(limit > emissionDuration){
                    render();
                    draw();
                    this.stop();
                }
                if (prevNanos == 0) {
                    prevNanos = now;
                    return;
                }
                long deltaNanos = now - prevNanos;
                prevNanos = now;
                double dt = deltaNanos / 1.0e9;
                limit += dt;
                timer += dt;
                if(timer > 0.2) {
                    doAnimation(i++);
                    i = i % 3;
                    timer = 0;
                }
            }
        };
    }


    public class Tumor{
        double x;
        double y;
        double radius;
        double opacity;

        public Tumor(double x, double y, double radius, double opacity) {
            this.x = x;
            this.y = y;
            this.radius = radius;
            this.opacity = opacity;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getRadius() {
            return radius;
        }

        public double getOpacity() {
            return opacity;
        }
    }




    public void setOperationController(OperationController operationController) {
        this.operationController = operationController;
    }

    public ArrayList<Tumor> getTumors() {
        return tumors;
    }

    public int getXRayWidth() {
        return width;
    }

    public int getXRayHeight() {
        return height;
    }


    public void setError(String message){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NOT A TUMOR");
            alert.setContentText("You are trying to emit radiation on a safe area in the brain!"
                    + "\n"+ "Tumors are represented by orange circles!");
            alert.show();

    }

    public void startEmission(){
        initAnimation();
        anime.start();
    }
    public void stopEmission(){
        render();
        draw();
        anime.stop();
    }

    public void setEmissionDuration(int emissionDuration) {
        this.emissionDuration = emissionDuration;
    }
}
