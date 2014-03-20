/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.File;

/**
 *
 * @author Ryan W Radtke <RyanWRadtke@gmail.com>
 */
public class FileTools {

    public static String fileList(File sourceDirectory) {
        

        StringBuilder sb = new StringBuilder();

        File[] fileList = sourceDirectory.listFiles();

            for (File f : fileList) {
                if (!extFilter(f)) {
                    sb.append(f.getName()).append("\n");
                }
            }
        return sb.toString();
    }
    
    public static boolean extFilter(File f) {
        String[] fileType = new String[]{"md5", "txt", "DS"};
        boolean contains = false;
        
        for (String s: fileType) {
            if (f.toString().contains(s)) {
                contains = true;
            }
        }
        return contains;
    }
}
