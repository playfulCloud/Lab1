package com.example.gui;

import hash.me.mdb5Tool.Mdb5;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.zipperTool.Zipper;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class FileChooserController {
    @FXML
    Button buttonChooseFile = new Button("Open a file");
    @FXML
    Button buttonChooseDirectory = new Button("Open a directory");

    @FXML
    Button zipEverything = new Button("Zip selected files");

    @FXML
    Button clearPaths = new Button("clears selected files");

    @FXML
    Button generate = new Button("generate md5 for function");

    @FXML
    Button compare = new Button("Compare md5 between files");

    @FXML
    Label label = new Label();

    List<String> pathsToProcess = new ArrayList<String>();
    List<String> pathsToCompare = new ArrayList<String>();
    @FXML
    void buttonChooseFileAction(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        Stage stage = (Stage) buttonChooseFile.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        processSelection(selectedFile);
    }

    @FXML
    void buttonChooseDirectoryAction(ActionEvent e) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Open a directory");
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        Stage stage = (Stage) buttonChooseDirectory.getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(stage);
        processSelection(selectedDirectory);
    }

    @FXML
    void zipSelectedFiles(ActionEvent e) throws IOException {
        if(pathsToProcess.isEmpty()){
            throw new RuntimeException("There is nothing to zip!");
        }
        Zipper.zipEverything(pathsToProcess);

    }
    @FXML
    void clearSelectedFiles(ActionEvent e){
        pathsToProcess.clear();
        label.setText(pathsToProcess.toString());
    }

    @FXML
    void generateMdb5ForFile(ActionEvent e) throws NoSuchAlgorithmException, IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        Stage stage = (Stage) buttonChooseFile.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        Mdb5.generate(selectedFile.getAbsolutePath());
    }

    @FXML
    void compareMdb5(ActionEvent e) throws IllegalAccessException, NoSuchAlgorithmException, IOException {
        if(pathsToProcess.size() != 2){
            throw new IllegalAccessException();
        }
       boolean areEquals = Mdb5.validate(pathsToProcess.get(0), pathsToProcess.get(1));
       label.setText(areEquals+"");
    }

    private void processSelection(File selection) {
        if (selection != null) {
            pathsToProcess.add(selection.getAbsolutePath());
            label.setText(pathsToProcess.toString());
        } else {
            System.out.println("No file or directory has been selected");
        }
    }
}