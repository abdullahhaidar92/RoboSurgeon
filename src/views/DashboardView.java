package views;

import components.Dashboard;
import javafx.stage.StageStyle;
import models.CurrentUser;
import application.UserProducer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;


    public abstract class DashboardView<T> extends Stage {
        public static final int WIDTH=1300;
        public static final int HEIGHT=800;
        private final ObservableList<T> tvObservableList = FXCollections.observableArrayList();
        private final TableView<T> table = new TableView<>(tvObservableList);
        private Scene scene ;
        private boolean createEnabled=false;
        private boolean detailsEnabled=false;
        private boolean editEnabled=false;
        private boolean deleteEnabled=false;
        private Button createBtn=new Button("Create");
        private TextField searchField=new TextField();
        private Label title=new Label();
        private Button close=new Button(" X ");
        HBox searchBox;
        Dashboard controller;
        public DashboardView()
        {
            super(StageStyle.UNDECORATED);
            BorderPane header=new BorderPane();
            header.setCenter(new HBox(title));
            header.setRight(close);
            header.getStyleClass().add("header");
            header.setPadding(new Insets(8));

            searchField.setMinWidth(WIDTH*0.99);
            searchField.setMinHeight(50);
            searchField.setFocusTraversable(false);
            searchBox=new HBox(searchField);
            searchBox.setMinWidth(WIDTH);
            searchBox.setSpacing(14);
            searchBox.setAlignment(Pos.CENTER);
            searchBox.setPadding(new Insets(5));
            
            createBtn.setMinHeight(48);
            createBtn.setMinWidth(WIDTH*0.12);
            createBtn.getStyleClass().add("create");

            close.getStyleClass().add("close");
            close.setFocusTraversable(false);
            close.setOnAction(e->{
                this.close();
                new UserProducer().getUserFactory(CurrentUser.getCurrentUser().getRole()).getPortal().show();
            });

            setTableappearance();
            setScene(new Scene(new VBox(header,searchBox,table)));
            getScene().getStylesheets().add(getClass().getResource("/css/dashboard.css").toExternalForm());
        }


        public void setController(Dashboard controller){
            this.controller=controller;
        }

        public Button getCreateButton(){
            return createBtn;
        }

        public TextField getSearchField() {
            return searchField;
        }

        public void setHeaderTitle(String title){
            this.title.setText(title);
        }


        public void addStringColumn(String name){
            addStringColumn(name,name);
        }
        public void addIntegerColumn(String name){
            addIntegerColumn(name,name);
        }

        public void addStringColumn(String columnName,String fieldName){
            TableColumn<T, String> colName = new TableColumn<>(columnName);
            colName.setCellValueFactory(new PropertyValueFactory<>(fieldName));
            table.getColumns().add(colName);
        }
        public void addIntegerColumn(String columnName,String fieldName){
            TableColumn<T, Integer> colName = new TableColumn<>(columnName);
            colName.setCellValueFactory(new PropertyValueFactory<>(fieldName));
            table.getColumns().add(colName);
        }

        public void setTableappearance() {
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            table.setPrefWidth(WIDTH);
            table.setPrefHeight(HEIGHT*0.8);
        }


        public void addActionButtons() {
            TableColumn<T, Void> colBtn = new TableColumn("Actions");
            colBtn.setCellFactory(new MyCallBack());
            table.getColumns().add(colBtn);

        }

        private  class MyCallBack implements Callback<TableColumn<T, Void>, TableCell<T, Void>> {
            @Override
            public TableCell<T, Void> call(final TableColumn<T, Void> param) {
                final TableCell<T, Void> cell = new TableCell<T, Void>() {

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                             HBox box=new HBox();
                             Button detailsBtn,editBtn,deleteBtn;

                                box.setSpacing(11);
                                box.setAlignment(Pos.CENTER);


                            if(detailsEnabled) {
                                detailsBtn=new Button("details");
                                detailsBtn.getStyleClass().add("details");
                                detailsBtn.setOnAction(e->{
                                    if(controller!=null)
                                        controller.showDetails(getIndex());
                                });
                                box.getChildren().add(detailsBtn);
                            }
                            if(editEnabled){
                                editBtn=new Button("edit");
                                editBtn.getStyleClass().add("edit");
                                editBtn.setOnAction(e->{
                                    if(controller!=null)
                                        controller.showEditForm(getIndex());
                                });
                                box.getChildren().add(editBtn);
                            }

                            if(deleteEnabled){
                                deleteBtn=new Button("delete");
                                deleteBtn.getStyleClass().add("delete");
                                deleteBtn.setOnAction(e->{
                                    if(controller!=null)
                                        controller.showDeleteForm(getIndex());
                                });
                                box.getChildren().add(deleteBtn);
                            }

                            setGraphic(box);
                        }
                    }
                };
                return cell;
            }
        }



        public void enableCreate(){
            createEnabled=true;
            searchField.setMinWidth(WIDTH*0.85);
            searchBox.getChildren().add(createBtn);
        }
        public void enableDetails(){
            detailsEnabled=true;
        }
        public void enableEdit(){
            editEnabled=true;
        }
        public void enableDelete(){
            deleteEnabled=true;
        }

        public boolean createEnabled(){
            return createEnabled;
        }
        public boolean detailsEnabled(){
            return detailsEnabled;
        }
        public boolean editEnabled(){
            return editEnabled;
        }
        public boolean deleteEnabled(){
            return deleteEnabled;
        }
        public void addData(T data){
            tvObservableList.add(data);
        }

        public ObservableList<T> getTableData(){
            return tvObservableList;
        }

        public ObservableList<TableColumn<T, ?>> getTableColumns(){
            return table.getColumns();
        }



    }




















