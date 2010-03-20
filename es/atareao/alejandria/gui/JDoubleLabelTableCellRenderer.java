/*
 * ***********************Software description*********************************
 * JDoubleLabelTableCellRenderer.java
 * 
 * 
 * ***********************Software description*********************************
 * 
 * Copyright (C) 2007 - Lorenzo Carbonell
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
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
/**
 *
 * @author Lorenzo Carbonell
 */
public class JDoubleLabelTableCellRenderer extends JDoubleLabel implements TableCellRenderer{


    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    public JDoubleLabelTableCellRenderer(){
        super();
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(value!=null){
            if(value instanceof String[]){
                String[] valores=(String[])value;
                this.setHText(valores[0]);
                this.setVText(valores[1]);
            }
        }
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
