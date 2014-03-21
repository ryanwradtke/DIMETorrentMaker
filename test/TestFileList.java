
import java.io.File;
import java.nio.file.Path;
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
         File file = new File("/Users/Ryan/Desktop");
         File[] files = file.listFiles();
         Path path = files[0].toPath();
         
         
         System.out.println(path.subpath((path.getNameCount() - 1), path.getNameCount()));
         
//         for (File f : files) {
//             System.out.println(f.toPath());
//         }
    }
}
