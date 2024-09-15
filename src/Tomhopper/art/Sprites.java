/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.art;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * A class for finding the BufferedImage of a PNG file in the art package.
 * 
 * @author cdwan
 */
public class Sprites {

    /**
     * Returns the BufferedImage of a given PNG file name.
     * 
     * @param picName Given picture/file name
     * @return BufferedImage of PNG file
     */
    public static BufferedImage getImage(String picName) {
        String imagePath = "src/TomHopper/art/" + picName + ".png";
        try {
            BufferedImage myPicture = ImageIO.read(new File(imagePath));
            return myPicture;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
}
