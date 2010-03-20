/*
 * ***********************Software description*********************************
 * JTreeLabel.java
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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author Lorenzo Carbonell
 */
public class JTreeLabel extends JLabel {
    
    

    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 
    protected static final long serialVersionUID=0L;
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    JTreeLabel() {
    }

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    @Override
    public void setBackground(Color color) {
	if(color instanceof ColorUIResource) {
                color = null;
            }
	super.setBackground(color);
    } 
    @Override
    public void paint(Graphics g) {
        String str;
        if ((str = getText()) != null) {
            if (0 < str.length()) {
                if (isSelected) {
                    g.setColor(UIManager.getColor("Tree.selectionBackground"));
                } else {
                    g.setColor(UIManager.getColor("Tree.textBackground"));
                }
                Dimension d = getPreferredSize();
                int imageOffset = 0;
                Icon currentI = getIcon();
                if (currentI != null) {
                    imageOffset = currentI.getIconWidth() + Math.max(0, getIconTextGap() - 1);
                }
                g.fillRect(imageOffset, 0, d.width -1 - imageOffset, d.height);
                if (hasFocus) {
                    g.setColor(UIManager.getColor("Tree.selectionBorderColor"));
                    g.drawRect(imageOffset, 0, d.width -1 - imageOffset, d.height -1);     
                }
            }
        }
        super.paint(g);
    }
    @Override
    public Dimension getPreferredSize() {
        Dimension retDimension = super.getPreferredSize();
        if (retDimension != null) {
            retDimension = new Dimension(retDimension.width + 3,retDimension.height);
        }
        return retDimension;
    }
    
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 
    boolean isSelected;
    boolean hasFocus;
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
    
    public void setFocus(boolean hasFocus) {
        this.hasFocus = hasFocus;
    }
    // </editor-fold> 
}
