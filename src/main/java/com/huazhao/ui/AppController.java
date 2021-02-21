package com.huazhao.ui;


import com.huazhao.model.FileMeta;
import com.huazhao.service.FileService;
import com.huazhao.task.FileScanner;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    @FXML
    public GridPane rootPane;
    @FXML
    public Label srcDirectory;
    @FXML
    public TextField searchField;
    @FXML
    public TableView<FileMeta> fileTable;
    @FXML
    public TableColumn<FileMeta,String> nameColumn;
    @FXML
    public TableColumn<FileMeta,String> sizeColumn;
    @FXML
    public TableColumn<FileMeta,String> lastModifiedColumn;

    private final FileService fileService = new FileService();
    private final FileScanner fileScanner = new FileScanner();
    @FXML
    public void choose(MouseEvent mouseEvent) {
        DirectoryChooser chooser = new DirectoryChooser();
        File root = chooser.showDialog(rootPane.getScene().getWindow());
        if(root == null){
            return;
        }
        Thread thread = new Thread(() ->{
            try {
                fileScanner.scan(root);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //会在fxml.loader执行，实例化app好调用
        StringProperty stringProperty = searchField.textProperty();
        stringProperty.addListener((observable, oldValue, newValue) -> {
            System.out.println("old:|" + oldValue + "|");
            System.out.println("new: |" + newValue + "|");

            List<FileMeta> fileList = fileService.queryName(newValue.trim());
//            for (FileMeta file : fileList) {
//                System.out.println(file);
//            }
//            System.out.println("===========================");
            Platform.runLater(() ->{
                fileTable.getItems().clear();
                fileTable.getItems().addAll(fileList);
            });
        });
    }
}