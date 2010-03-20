/*
 * ***********************Software description*********************************
 * Thumbnail.java
 * 
 * 
 * ***********************Software description*********************************
 * 
 * Copyright (C) 2008 - Lorenzo Carbonell
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * **************************Software License***********************************
 * 
 */

package es.atareao.alejandria.lib;

//
//********************************IMPORTACIONES*********************************
//
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Lorenzo Carbonell
 */
public class Thumbnail {
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    public static void create(URI src,URI dest,int thumbWidth,int thumbHeight,int quality){
        create(new File(src),new File(dest),thumbWidth,thumbHeight,quality);
    }
    public static void create(File src,File dest,int thumbWidth,int thumbHeight,int quality){
        create(src.getPath(),dest.getPath(),thumbWidth,thumbHeight,quality);
    }
    public static void create(String src,String dest,int thumbWidth,int thumbHeight,int quality){
        try {
            Image image = Toolkit.getDefaultToolkit().getImage(src);
            MediaTracker mediaTracker = new MediaTracker(new Container());
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForID(0);
            double thumbRatio = (double) thumbWidth / (double) thumbHeight;
            int imageWidth = image.getWidth(null);
            int imageHeight = image.getHeight(null);
            double imageRatio = (double) imageWidth / (double) imageHeight;
            if (thumbRatio < imageRatio) {
                thumbHeight = (int) (thumbWidth / imageRatio);
            } else {
                thumbWidth = (int) (thumbHeight * imageRatio);
            }
            BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = thumbImage.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
            ImageIO.write(thumbImage, "jpeg", new File(dest));
        } catch (IOException ex) {
            Logger.getLogger(Thumbnail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Thumbnail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 

    // </editor-fold> 
}
