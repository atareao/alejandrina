/*
 * ***********************Software description*********************************
 * ImageUtils.java
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

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

//********************************IMPORTACIONES*********************************
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
//
import javax.swing.ImageIcon;
/**
 *
 * @author Lorenzo Carbonell
 */
public class ImageUtils {
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
 

    /** Devuelve la lista de formatos disponibles a leer por ImageIO 
    * @return un array de strings con los mismos. 
    */ 
    public static String[] getUsableFormats(){ 
        return ImageIO.getReaderFormatNames(); 
    } 
    public static BufferedImage toBufferedImage(ImageIcon icon){
        return toBufferedImage(icon.getImage());

    }
    
    public static BufferedImage rotate(BufferedImage image, int angle) { 
      AffineTransform rotationTransform = new AffineTransform();
      double radians=Math.toRadians(angle);
      rotationTransform.rotate(radians, image.getWidth() / 2.0 , image.getHeight() / 2.0);
      AffineTransformOp rotationTransformOp = new AffineTransformOp(rotationTransform , AffineTransformOp.TYPE_BICUBIC); 
      return rotationTransformOp.filter(image,null); 
    } 
    /** Calcula el factor de escala minimo y en base a eso 
    * escala la imagen segun dicho factor. 
    * @param nMaxWidth maximo tamaño para el ancho 
    * @param nMaxHeight nmaximo tamaño para el alto 
    * @param imagen ImageUtils que vamos a escalar 
    * @return Devuelve la imagen escalada para poderla trastocar o null si hay error 
    */ 
    public static BufferedImage scale(final BufferedImage imagen,final int maximoAncho, final int maximoAlto) { 
        // Comprobacion de parametros 
        if (imagen == null || maximoAlto == 0 || maximoAncho == 0) { 
            return null; 
        } 
        // Capturo ancho y alto de la imagen 
        int anchoImagen = imagen.getHeight(); 
        int altoImagen = imagen.getWidth(); 
        // Calculo la relacion entre anchos y altos de la imagen 
        double escalaX = (double)maximoAncho / (double)anchoImagen; 
        double escalaY = (double)maximoAlto / (double)altoImagen; 
        // Tomo como referencia el minimo de las escalas 
        double fEscala = Math.min(escalaX, escalaY); 
        // Devuelvo el resultado de aplicar esa escala a la imagen 
        return scale(fEscala, imagen); 
    } 
    /** Escala una imagen en porcentaje. 
    * @param factorEscala ejemplo: factorEscala=0.6 (escala la imagen al 60%) 
    * @param srcImg una imagen BufferedImage 
    * @return un BufferedImage escalado 
    */ 
    public static BufferedImage scale(final double factorEscala, final BufferedImage srcImg,RenderingHints opciones) { 
        // Comprobacion de parametros 
        if (srcImg == null) { 
            return null; 
        } 
        // Compruebo escala nula 
        if (factorEscala == 1 ) { 
            return srcImg; 
        } 
        // La creo con esas opciones 
        AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(factorEscala, factorEscala), opciones); 
        // Devuelve el resultado de aplicar el filro sobre la imagen 
        return op.filter(srcImg, null); 
    } 
    /** Escala una imagen en porcentaje. 
    * @param factorEscala ejemplo: factorEscala=0.6 (escala la imagen al 60%) 
    * @param srcImg una imagen BufferedImage 
    * @return un BufferedImage escalado 
    */ 
    public static BufferedImage scale(final double factorEscala, final BufferedImage srcImg) { 
        // Cargo las opciones de renderizado que me apetezcan 
        RenderingHints opciones = new RenderingHints(null); 
        opciones.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
        opciones.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY); 
        opciones.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE); 
        opciones.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON); 
        opciones.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR); 
        opciones.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY); 
        opciones.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE); 
        opciones.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); 
        return scale(factorEscala,srcImg,opciones);
    } 


    /** Metodo que guarda una imagen en disco 
    * @param imagen ImageUtils a almacenar en disco 
    * @param rutaFichero Ruta de la imagen donde vamos a salvar la imagen 
    * @param formato Formato de la imagen al almacenarla en disco 
    * @return Booleano indicando si se consiguio salvar con exito la imagen 
    */ 
    public static boolean save(final BufferedImage imagen,final String rutaFichero, final String formato) { 
        // Comprobacion de parametros 
        if (imagen != null && rutaFichero != null && formato != null) { 
            try { 
                ImageIO.write( imagen, formato, new File( rutaFichero )); 
                return true; 
            } catch (Exception e){ 
                // Fallo al guardar 
                return false; 
            } 
        } else { 
            // Fallo en los parametros 
            return false; 
        } 
    } 
    public static BufferedImage load(File file) throws IOException{
        return ImageIO.read(file); 
    }
//preserves image's colormodel. Assumes image is loaded
    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage)
            return (BufferedImage) image;
        ColorModel cm = getColorModel(image);
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        return copy(createBufferedImage(cm, width, height), image);
    }
 
    public static BufferedImage toBufferedImage(Image image, int type) {
        if (image instanceof BufferedImage && ((BufferedImage)image).getType() == type)
            return (BufferedImage) image;
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        return copy(new BufferedImage(width, height, type), image);
    }
 
    //Returns target. Assumes source is loaded
    public static BufferedImage copy(BufferedImage target, Image source) {
        Graphics2D g = target.createGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return target;
    }
 
    public static ColorModel getColorModel(Image image) {
        try {
            PixelGrabber pg = new PixelGrabber(image, 0,0,1,1, false);
            pg.grabPixels();
            return pg.getColorModel();
        } catch (InterruptedException e) {
            throw new RuntimeException("Unexpected interruption", e);
        }
    }
 
    public static BufferedImage createBufferedImage(ColorModel cm, int w, int h) {
        WritableRaster raster = cm.createCompatibleWritableRaster(w, h);
        boolean isRasterPremultiplied = cm.isAlphaPremultiplied();
        return new BufferedImage(cm, raster, isRasterPremultiplied, null);
    }
 
    //you may want to use MediaTracker directly
    public static Image load(Image image) {
        ImageIcon ii = new ImageIcon(image);
        if (ii.getImageLoadStatus() != MediaTracker.COMPLETE)
            throw new IllegalArgumentException("unable to load image");
        return image;
    }

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 

    // </editor-fold> 
}
