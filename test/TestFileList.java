
import java.io.File;
import resources.FileTools;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ryan W Radtke <RyanWRadtke@gmail.com>
 */
public class TestFileList {
    public static void main(String[] args) {
        System.out.println(FileTools.fileList(new File("~/Desktop/This is a Torrent Test")));
    }
}
