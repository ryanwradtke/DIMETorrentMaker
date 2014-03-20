/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.security.MessageDigest;

/**
 *
 * @author Ryan W Radtke <RyanWRadtke@gmail.com>
 */
public class MD5Checksum {
    
    public static String makeMD5File(File sourceDirectory)
            throws Exception {

        StringBuilder sb = new StringBuilder();
        File[] fileArray = sourceDirectory.listFiles();

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(sourceDirectory.getPath() + "/checksum.md5"), "utf-8"))) {

            for (File f : fileArray) {
                if (!FileTools.extFilter(f)) {

                    byte[] b = createChecksum(f.toString());
                    String result = "";

                    for (int i = 0; i < b.length; i++) {
                        result += Integer.toString(
                                (b[i] & 0xff) + 0x100, 16).substring(1);
                    }

                    writer.append(f.getName() + "\t" + result + "\n");
                    sb.append(f.getName()).append("\t").append(result).append("\n");
                }
            }
        }
        return sb.toString();
    }

    public static byte[] createChecksum(String filename) throws Exception {
        MessageDigest complete;
        try (InputStream fis = new FileInputStream(filename)) {
            byte[] buffer = new byte[1024];
            complete = MessageDigest.getInstance("MD5");
            int numRead;
            do {
                numRead = fis.read(buffer);
                if (numRead > 0) {
                    complete.update(buffer, 0, numRead);
                }
            } while (numRead != -1);
        }
        return complete.digest();
    }

}
