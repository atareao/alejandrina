/*
 * JCheckBoxTableCellRenderer.java
 *
 * Created on 16 de agosto de 2007, 22:08
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package es.atareao.alejandria.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

/**
 *
 * @author Propietario
 */
public class JImageListCellRenderer extends JLabel implements ListCellRenderer {
    final Border blueBorder=BorderFactory.createLineBorder(Color.BLUE);
    final Border emptyBorder=BorderFactory.createEmptyBorder();
    /**
     * Creates a new instance of JCheckBoxTableCellRenderer
     */
    public JImageListCellRenderer() {
        setOpaque(true);
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
        this.setMaximumSize(new Dimension(175,175));
        this.setMinimumSize(new Dimension(175,175));
        this.setPreferredSize(new Dimension(175,175));
        this.setBackground(new Color(200,200,200));
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (isSelected) {
            setForeground(list.getSelectionForeground());
            setBackground(list.getSelectionBackground());
        }else{
            setForeground(list.getForeground());
            setBackground(list.getBackground());
        }
        if(cellHasFocus){
            setBorder(BorderFactory.createLineBorder(new Color(63,145,192,255),1));
        }else{
            setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));//.createLineBorder(new Color(128,128,128,255),0));
        }
        if(value instanceof JThumbnailInterface){
            try {
                this.setIcon(new ImageIcon(getImageFromURL((((JThumbnailInterface) value).getThumbnailURI()).toURL())));
            } catch (MalformedURLException ex) {
                Logger.getLogger(JImageListCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this;
    }
    public void refresh(){
        this.repaint();
    }
    private Image getImageFromURL(URL imageURL){
        if(imageURL!=null){
            try {
                Image image = Toolkit.getDefaultToolkit().getImage(imageURL);
                MediaTracker mediaTracker = new MediaTracker(new Container());
                mediaTracker.addImage(image, 0);
                mediaTracker.waitForID(0);
                return image;
            } catch (InterruptedException ex) {
                Logger.getLogger(JImage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;        
    }
}
