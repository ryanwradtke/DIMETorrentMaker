/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ryan W Radtke <RyanWRadtke@gmail.com>
 */
public class StringSplit {
 
    public static void main(String[] args) {
        String path = "/Users/Ryan/Desktop/Test.file";
        
        System.out.println(path);
        
        String[] pathArry = path.split("\\\\");
        
        System.out.println(pathArry[pathArry.length - 1]);
    }
}
