package Logic;

import javax.swing.*;
import java.io.File;

public class Methods {
    public static long folderSize(File directory) {
        long length = 0;
        for (File file : directory.listFiles()) {
            if (file.isFile())
                length += file.length();
            else
                length += folderSize(file);
        }
        return length;
    }
    public static void showProperties(String type,String location,String size,String created,String contains)
    {
        JOptionPane.showMessageDialog(null,"<html>Type: " + type + "<br>Location: " +  location + "<br>Size: " +
                size + "<br>Created: " + created+ "<br>Contains: " + contains,"Properties" , JOptionPane.PLAIN_MESSAGE);
    }
}
