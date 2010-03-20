/*
 * ***********************Software description*********************************
 * FilePreviewer.java
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

package es.atareao.alejandria.gui;

//
//********************************IMPORTACIONES*********************************
//
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
/**
 *
 * @author Lorenzo Carbonell
 */
public class FilePreviewer extends JComponent implements PropertyChangeListener {
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
	public FilePreviewer(JFileChooser fc) {
	    setPreferredSize(new Dimension(100, 50));
	    fc.addPropertyChangeListener(this);
	}
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Metodos  ">
	public void loadImage(File f) {
            if (f == null) {
                thumbnail = null;
            } else {
		ImageIcon tmpIcon = new ImageIcon(f.getPath());
		if(tmpIcon.getIconWidth() > 90) {
		    thumbnail = new ImageIcon(
			tmpIcon.getImage().getScaledInstance(90, -1, Image.SCALE_DEFAULT));
		} else {
		    thumbnail = tmpIcon;
		}
	    }
	}

	public void propertyChange(PropertyChangeEvent e) {
	    String prop = e.getPropertyName();
	    if(prop.equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)) {
		if(isShowing()) {
                    loadImage((File) e.getNewValue());
		    repaint();
		}
	    }
	}

	public void paint(Graphics g) {
	    if(thumbnail != null) {
		int x = getWidth()/2 - thumbnail.getIconWidth()/2;
		int y = getHeight()/2 - thumbnail.getIconHeight()/2;
		if(y < 0) {
		    y = 0;
		}

		if(x < 5) {
		    x = 5;
		}
		thumbnail.paintIcon(this, g, x, y);
	    }
	}
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Metodos auxiliares  ">

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 
    ImageIcon thumbnail = null;
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Metodos de acceso  ">

    // </editor-fold> 
}
