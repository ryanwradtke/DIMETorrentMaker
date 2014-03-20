/**
 * Torrent Maker is a free piece of software created to simplify the process of
 * uploading live shows to dimeadozen.org. This file will create the necessary
 * info files, MD5 file, and the torrent. It will then upload the file to, and
 * receive the authorized torrent link from, dimeadozen. Please feel free to use
 * and edit this code.
 */
package driver;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Ryan W Radtke <RyanWRadtke@gmail.com>
 */
public class TorrentMaker extends Application {

    @Override
    public void start(Stage primaryStage) {
        AnchorPane page;
        try {
            page = (AnchorPane) FXMLLoader.load(TorrentMaker.class.getResource("/view/TorrentMaker.fxml"));
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            scene.getStylesheets().add(TorrentMaker.class.getResource("/view/TorrentMaker.css").toExternalForm());

            primaryStage.setTitle("DIME Torrent Maker");
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(TorrentMaker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(TorrentMaker.class, (java.lang.String[]) null);
    }

}
