/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 *
 * @author Ryan W Radtke <RyanWRadtke@gmail.com>
 */
public class InfoFile {

    public static void makeInfoFile(File sourceDirectory, String[] info)
            throws Exception {

        String lineBreak = "\n\n------------------------------------------------"
                + "---------------------------------------\n\n";
        
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(sourceDirectory.getPath() + "/info.txt"), "utf-8"))) {

            writer.append("\nTorrent Title:\n" + info[0]);
            writer.append(lineBreak);
            writer.append("Artist Name:\n" + info[1]);
            writer.append("\n\nBootleg Title:\n" + info[2]);
            writer.append("\n\nVenue, City, Country, Date (YYYY-MM-DD):\n" + info[3]);
            writer.append(lineBreak);
            writer.append("Format (NTSC or PAL), Audio Codec, A/V Bit Rates, "
                    + "Aspect Ratio: (For Videos)\n" + info[4]);
            writer.append(lineBreak);
            writer.append("Track Listing:\n" + info[5]);
            writer.append(lineBreak);
            writer.append("Line Up:\n" + info[6]);
            writer.append(lineBreak);
            writer.append("MD5 Checksums:\n" + info[7]);
            writer.append(lineBreak);
            writer.append("Info on Artwork:\n" + info[8]);
            writer.append(lineBreak);
            writer.append("Source and/or Lineage:\n" + info[9]);
            writer.append(lineBreak);
            writer.append("Notes:\n" + info[10]);
        }
    }
}
