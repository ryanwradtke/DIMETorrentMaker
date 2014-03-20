/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import resources.*;

/**
 * FXML Controller class
 *
 * @author Ryan W Radtke <RyanWRadtke@gmail.com>
 */
public class TorrentMakerController implements Initializable {

    @FXML
    private Label messageBar;
    @FXML
    private TextField sourcePathInput;
    @FXML
    private TextField outputFileInput;
    @FXML
    private TextField artistInfoInput;
    @FXML
    private TextField titleInfoInput;
    @FXML
    private TextField venueInfoInput;
    @FXML
    private TextField formatInfoInput;
    @FXML
    private TextArea trackInfoInput;
    @FXML
    private TextArea lineupInfoInput;
    @FXML
    private TextArea artworkInfoInput;
    @FXML
    private TextArea sourceInfoInput;
    @FXML
    private TextArea notesInfoInput;

    File sourceDirectory;
    File outputFile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void sourceButtonFired(ActionEvent event) {
        messageBar.setText("");

        DirectoryChooser fc = new DirectoryChooser();

        fc.setTitle("Choose a source directory.");

        sourceDirectory = fc.showDialog(null);
        sourcePathInput.setText(sourceDirectory.getPath());

        trackInfoInput.setText(FileTools.fileList(sourceDirectory));

    }

    @FXML
    private void outputButtonFired(ActionEvent event) {
        messageBar.setText("");

        DirectoryChooser fc = new DirectoryChooser();

        if (sourcePathInput.getText().isEmpty()) {
            messageBar.setText("Please choose a source directory.");
        } else {
            fc.setTitle("Choose a save directory.");
            File saveDirectory = fc.showDialog(null);
            if (saveDirectory.getPath().equals(sourcePathInput.getText())) {
                outputFileInput.setText("**Please don't place your .torrent in your source directory.");
                messageBar.setText("Please don't place your .torrent in your source directory.");

            } else {
                String s = saveDirectory.getPath() + System.getProperty("file.separator")
                        + new File(sourcePathInput.getText()).getName() + ".torrent";
                outputFile = new File(s);
                outputFileInput.setText(s);
            }
        }

    }

    @FXML
    private void createTorrentButtonFired(ActionEvent event) {
        messageBar.setText("");

        if (this.checkEmptyFields()) {
            messageBar.setText("Please fill all required fields.");
            return;
        }

        //Creates MD5 file and returns a string to be used in info file.
        String md5 = "";

        messageBar.setText("Creating MD5 file...");
        try {
            md5 = MD5Checksum.makeMD5File(sourceDirectory);
        } catch (Exception ex) {
            messageBar.setText("Unable to create MD5 file, please check your source directory.");
            Logger.getLogger(TorrentMakerController.class.getName()).log(Level.SEVERE, null, ex);
        }

        messageBar.setText("Successfully created MD5 file!");

        String[] info = new String[]{
            outputFile.getName(),
            artistInfoInput.getText(),
            titleInfoInput.getText(),
            venueInfoInput.getText(),
            formatInfoInput.getText(),
            trackInfoInput.getText(),
            lineupInfoInput.getText(),
            md5,
            artworkInfoInput.getText(),
            sourceInfoInput.getText(),
            notesInfoInput.getText()};

        messageBar.setText("Creating Info file...");
        try {
            InfoFile.makeInfoFile(sourceDirectory, info);
        } catch (Exception ex) {
            messageBar.setText("Unable to create Info file, please check your source directory.");
            Logger.getLogger(TorrentMakerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        messageBar.setText("Successfully created Info file!");
        
        TorrentProcessor tp = new TorrentProcessor();
        File[] fileArray = sourceDirectory.listFiles();

        messageBar.setText("Creating torrent file...");

        tp.setAnnounceURL(Constants.ANNOUNCEURL);
        tp.setPieceLength(1);
        tp.setName(sourceDirectory.getName());
        tp.addFiles(fileArray);
        tp.setCreator(Constants.CLIENT);

        messageBar.setText("Hashing the files...");
        tp.generatePieceHashes();

        messageBar.setText("Hash complete: Saving...");
        try {
            FileOutputStream fos = new FileOutputStream(outputFile);
            fos.write(tp.generateTorrent());
        } catch (IOException iOException) {
            messageBar.setText("Unable to create Torrent File!");
        }

        messageBar.setText("Successfully created Torrent file!  Please close the program.");
    }

    private boolean checkEmptyFields() {

        if (sourceInfoInput.getText().isEmpty()) {
            return true;
        }
        if (outputFileInput.getText().isEmpty() || outputFileInput.getText().contains("**Please")) {
            return true;
        }

        if (artistInfoInput.getText().isEmpty()) {
            return true;
        }
        if (titleInfoInput.getText().isEmpty()) {
            return true;
        }
        if (venueInfoInput.getText().isEmpty()) {
            return true;
        }
        if (trackInfoInput.getText().isEmpty()) {
            return true;
        }
        if (lineupInfoInput.getText().isEmpty()) {
            return true;
        }
        if (sourceInfoInput.getText().isEmpty()) {
            return true;
        }
        return false;
    }

}
