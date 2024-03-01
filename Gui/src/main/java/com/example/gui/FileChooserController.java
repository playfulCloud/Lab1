package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileChooserController {
    @FXML
    Button buttonChooseFile = new Button("Open a file");
    @FXML
    Button buttonChooseDirectory = new Button("Open a directory");
    @FXML
    Label label = new Label();

    List<String> pathsToProcess = new ArrayList<String>();
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

    private void processSelection(File selection) {
        if (selection != null) {
            pathsToProcess.add(selection.getAbsolutePath());
            label.setText(pathsToProcess.toString());
        } else {
            System.out.println("No file or directory has been selected");
        }
    }
}