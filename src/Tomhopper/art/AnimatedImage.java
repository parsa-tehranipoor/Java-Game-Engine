/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.art;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * A class for Animation.
 * 
 * @author cdwan
 */
public class AnimatedImage {

    // List of AnimatedImageParts
    private final ArrayList<AnimatedImagePart> images;
    // Number of images in AnimatedImage
    private final int numImages;
    // Index for which image we are on, counter for how long we've been on this image
    private int index, counter;

    /**
     * Constructs an AnimatedImage Object.
     * 
     * @param imgs List of AnimatedImageParts
     */
    private AnimatedImage(ArrayList<AnimatedImagePart> imgs) {
        this.images = imgs;
        numImages = imgs.size();
        index = 0;
        counter = 0;
    }

    /**
     * For every tick, it checks if the amount of time on the current 
     * AnimatedImagePart is used up. If it is, then it moves onto the next
     * AnimatedImagePart.
     */
    public void tick() {
        ++counter;
        if (counter == images.get(index).getTime()) {
            counter = 0;
            ++index;
            index %= numImages;
        }
    }

    /**
     * Gets the current image being shown on the Animation.
     * 
     * @return Current BufferedImage shown
     */
    public BufferedImage getImage() {
        BufferedImage ret = images.get(index).getImage();
        tick();
        return ret;
    }

    /**
     * A class for an AnimatedImagePart which is just an image and a time.
     */
    public static class AnimatedImagePart {

        // Image for this specific part
        private final BufferedImage image;
        // How long this image should be shown
        private final int time;

        /**
         * Constructs an AnimatedImagePart.
         * 
         * @param img Given BufferedImage
         * @param t Time
         */
        public AnimatedImagePart(BufferedImage img, int t) {
            image = img;
            this.time = t;
        }

        /**
         * Gets the image of this AnimatedImagePart.
         * 
         * @return BufferedImage
         */
        public BufferedImage getImage() {
            return image;
        }

        /**
         * Gets the time for this AnimatedImagePart.
         * 
         * @return Time
         */
        public int getTime() {
            return time;
        }

    }

    /**
     * A class for building an AnimatedImage.
     */
    public static class AnimatedImageBuilder {
        
        // A list of AnimatedImageParts
        private ArrayList<AnimatedImagePart> images;

        /**
         * Constructs an AnimatedImageBuilder Object.
         */
        public AnimatedImageBuilder() {
            images = new ArrayList();
        }
        
        /**
         * Adds an image as an AnimatedImagePart to the list of AnimatedImageParts.
         * 
         * @param image BufferedImage to be added
         * @param time Time
         * @return This AnimatedImageBuilder
         */
        public AnimatedImageBuilder addImage(BufferedImage image, int time) {
            images.add(new AnimatedImagePart(image, time));
            return this;
        }

        /**
         * Builds an AnimatedImage.
         * 
         * @return AnimatedImage
         */
        public AnimatedImage build() {
            return new AnimatedImage(images);
        }
    }
}
