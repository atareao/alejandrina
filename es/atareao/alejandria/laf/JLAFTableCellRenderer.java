/*
 * ***********************Software description*********************************
 * JLAFTableCellRenderer.java
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

package es.atareao.alejandria.laf;

//
//********************************IMPORTACIONES*********************************
//

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Lorenzo Carbonell
 */
public class JLAFTableCellRenderer extends DefaultTableCellRenderer {
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 
    private static final long serialVersionUID=0L;
    private Color whiteColor = new Color(255, 250, 240);
    private Color alternateColor = new Color(245, 245, 220);
    private Color selectedColor = new Color(61, 128, 223);
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    @Override
    public Component getTableCellRendererComponent(JTable table,Object value, boolean selected, boolean focused,int row, int column){
        super.getTableCellRendererComponent(table, value,
        selected, focused, row, column);
        // Set the background color
        Color bg;
        if (!selected) {
            bg = (row % 2 == 0 ? alternateColor : whiteColor);
        }else {
            bg = selectedColor;
        }
        setBackground(bg);
        // Set the foreground to white when selected
        Color fg;
        if (selected) {
            fg = Color.white;
        }else {
            fg = Color.black;
        }
        setForeground(fg);
        this.setHorizontalAlignment(CENTER);
        return this;
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 

    // </editor-fold> 
}
