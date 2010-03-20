/*
 * JImage.java
 *
 * TODO: Descripcion
 *
 * Creado en 7 de diciembre de 2006, 10:01
 *
 * Copyright (C) 7 de diciembre de 2006, Protactino
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */

package es.atareao.alejandria.gui;

//
//********************************IMPORTACIONES*********************************
//
import es.atareao.alejandria.lib.Convert;
import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


/**
 *
 * @author Protactino
 */
public class JImage extends JPanel{



    //
    //********************************CONSTANTES********************************
    //
    public enum HorizontalAlignment {LEFT,CENTER,RIGHT}
    public enum VerticalAlignment {TOP,MIDDLE,BOTTOM}
    //
    // *********************************CAMPOS*********************************
    //
    private BufferedImage _bufferedImage;
    private Dimension _dimension;
    private String _resourceUrl;
    private double _scaleWidth = 1.0;
    private double _scaleHeight = 1.0;
    private boolean _adjustImage=false;
    private VerticalAlignment _verticalImageAlignment=VerticalAlignment.MIDDLE;
    private HorizontalAlignment _horizontalImageAlignment=HorizontalAlignment.CENTER;

    //
    //******************************CONSTRUCTORES*******************************
    //
    /**
     * Crea una nueva instancia de JImage
     */
    public JImage() {
        this.setOpaque(false);
    }
    //
    //********************************METODOS***********************************
    //
    /*
    * escala una imagen en porcentaje.
    * @param scale ejemplo: scale=0.6 (escala la imágen al 60%)
    * @param srcImg una imagen BufferedImage
    * @return un BufferedImage escalado
    */
    public static BufferedImage scale(double scalex,double scaley, BufferedImage srcImg) {
        if ((scalex == 1 )&&(scaley==1)) {
            return srcImg;
        }
        int width=(int)(srcImg.getWidth()*scalex);
        int height=(int)(srcImg.getHeight()*scaley);
        BufferedImage bufferedResizedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedResizedImage.createGraphics();
        /** preparación de la transformación */
	g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
	// Draw the resized image
	g2d.drawImage(srcImg, 0, 0, width, height, null);
	g2d.dispose();
        return bufferedResizedImage;
    }
    @Override
    public void paint(Graphics g) {
        BufferedImage in = this.getBufferedImage();
        if(in!=null){
            int x=0;
            int y=0;
            super.paintComponent(g);    // paint background
            if(this.isAdjustImage()){
                this.setScaleWidth(Convert.toDouble(this.getWidth())/Convert.toDouble(in.getWidth()));
                this.setScaleHeight(Convert.toDouble(this.getHeight())/Convert.toDouble(in.getHeight()));
            }
            switch(this.getVerticalImageAlignment()){
                case MIDDLE:
                    y=(int)(Math.abs(this.getHeight() - in.getHeight() * this.getScaleHeight()) / 2);
                    break;
                case BOTTOM:
                    y=(int) Math.abs(this.getHeight()-in.getHeight()*this.getScaleHeight());
                    break;
                default:
                    y=0;
                    break;
            }
            switch(this.getHorizontalImageAlignment()){
                case CENTER:
                    x=(int) (Math.abs(this.getWidth() - in.getWidth() * this.getScaleWidth()) / 2);
                    break;
                case RIGHT:
                    x=(int) Math.abs((this.getWidth()-in.getWidth()*this.getScaleWidth()));
                    break;
                default:
                    x=0;
                    break;
            }
            Graphics2D g2d=(Graphics2D)g;
            BufferedImage si=JImage.scale(this.getScaleWidth(),this.getScaleHeight(), in);
            g2d.drawImage(si, null, x, y);
            g2d.setComposite(AlphaComposite.Src);//Esto es lo que permite el alfa
            g2d.dispose();
        }
    }


    //
    //**************************METODOS AUXILIARES******************************
    //
    //
    //**************************METODOS DE ACCESO*******************************
    //
    public String getResourceUrl(){
        return this._resourceUrl;
    }
    public void setResourceUrl(String resourceUrl){
        this._resourceUrl=resourceUrl;
        URL url=getClass().getResource(resourceUrl);
        this.setImage(url);
    }
    public void setImage(String file){
        File filename=new File(file);
        this.setImage(filename);
    }
    public void setImage(File file){
        try {
            this.setImage(file.toURI().toURL());
        } catch (MalformedURLException ex) {
            ErrorDialog.manejaError(ex);
        }
    }
    public void setImage(URI imageURI){
        try {
            this.setImage(imageURI.toURL());
        } catch (MalformedURLException ex) {
            ErrorDialog.manejaError(ex);
        }
    }
    public void setImage(URL imageURL){
        try {
            this._resourceUrl=imageURL.toString();
            BufferedImage bi=ImageIO.read(imageURL);
            MediaTracker t = new MediaTracker( this );
            t.addImage(bi,0);
            t.waitForAll();
            this.setBufferedImage(bi);
        }catch ( InterruptedException e ){
            Thread.currentThread().interrupt();
        } catch (IOException ex) {
            ErrorDialog.manejaError(ex);
        }
    }
    
    public Dimension getDimension() {
        return _dimension;
    }

    public void setDimension(Dimension dimension) {
        this._dimension = dimension;
    }

    public double getScaleWidth() {
        return _scaleWidth;
    }

    public void setScaleWidth(double scaleWidth) {
        this._scaleWidth = scaleWidth;
    }

    public double getScaleHeight() {
        return _scaleHeight;
    }

    public void setScaleHeight(double scaleHeight) {
        this._scaleHeight = scaleHeight;
    }

    public BufferedImage getBufferedImage() {
        return _bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this._bufferedImage = bufferedImage;
    }
    /**
     * @return the _verticalImageAlignment
     */
    public VerticalAlignment getVerticalImageAlignment() {
        return _verticalImageAlignment;
    }

    /**
     * @param verticalImageAlignment the _verticalImageAlignment to set
     */
    public void setVerticalImageAlignment(VerticalAlignment verticalImageAlignment) {
        this._verticalImageAlignment = verticalImageAlignment;
    }

    /**
     * @return the _horizontalImageAlignment
     */
    public HorizontalAlignment getHorizontalImageAlignment() {
        return _horizontalImageAlignment;
    }

    /**
     * @param horizontalImageAlignment the _horizontalImageAlignment to set
     */
    public void setHorizontalImageAlignment(HorizontalAlignment horizontalImageAlignment) {
        this._horizontalImageAlignment = horizontalImageAlignment;
    }
    /**
     * @return the _adjustImage
     */
    public boolean isAdjustImage() {
        return _adjustImage;
    }

    /**
     * @param adjustImage the _adjustImage to set
     */
    public void setAdjustImage(boolean adjustImage) {
        this._adjustImage = adjustImage;
        this.repaint();
    }
}
