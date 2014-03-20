
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import resources.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ryan W Radtke <RyanWRadtke@gmail.com>
 */
public class TestDrectory {

    public static void main(String[] args) {
        String path = "/Users/Ryan/Desktop/This is a Torrent Test";
        File sourceDirectory = new File(path);
        File[] fileArray = sourceDirectory.listFiles();

        TorrentProcessor tp = new TorrentProcessor();

        String createdBy = "Java Test";

        String comment = "This is a test";

        String torrentFileString = "/Users/Ryan/Desktop/test.torrent";
        File torrentFile = new File(torrentFileString);

        String announceURL = "http://bt.dimeadozen.org/announce.php";

        String fileType = "flac";
        String md5;
        String[] info = new String[11];

        System.out.println(torrentFile.getName());

        System.out.println("Making MD5 file...............");
        try {
            md5 = MD5Checksum.makeMD5File(sourceDirectory);
        } catch (Exception exception) {
            System.out.println("Unable to make MD5 file.");
            return;
        }
        System.out.println("Success!");

        info[0] = torrentFile.getName();
        info[1] = "Test";
        info[2] = "Test";
        info[3] = "Test";
        info[4] = "Test";
        info[5] = FileTools.fileList(sourceDirectory);
        info[6] = "Test";
        info[7] = md5;
        info[8] = "Test";
        info[9] = "Test";
        info[10] = "Test";

        System.out.println("Creating info................");

        try {
            InfoFile.makeInfoFile(sourceDirectory, info);
        } catch (Exception ex) {
            System.out.println("Unable to create Info file");
            return;
        }
        System.out.println("Success!");

        System.out.println("Creating torrent file.................");

        tp.setAnnounceURL(Constants.ANNOUNCEURL);
        tp.setPieceLength(1);
        tp.setName("test");
        tp.addFiles(fileArray);
        tp.setComment(comment);
        tp.setCreator(createdBy);

        try {
            System.out.println("Hashing the files...");
            System.out.flush();
            tp.generatePieceHashes();
            System.out.println("Hash complete... Saving...");
            FileOutputStream fos = new FileOutputStream(torrentFileString);
            fos.write(tp.generateTorrent());
            System.out.println("Torrent created successfully!!!");
        } catch (IOException e) {
            System.err.println("Error when writing to the torrent file...");
            System.exit(1);
        }

        System.out.println("Success!");

    }
}
