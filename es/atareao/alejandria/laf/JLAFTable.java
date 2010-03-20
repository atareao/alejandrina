/*
 * ***********************Software description*********************************
 * JLAFTable.java
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
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author Lorenzo Carbonell
 */
public class JLAFTable extends JTable{
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 
    private static final long serialVersionUID=0L;
    
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    public JLAFTable(){
            this.getTableHeader().setDefaultRenderer(new JLAFTableHeaderRenderer());
            this.setDefaultRenderer(Object.class, new JLAFTableCellRenderer());
            this.setIntercellSpacing(new Dimension(1, 1));
            this.setRowHeight(20);
            this.setShowHorizontalLines(true);
            this.setShowVerticalLines(true);
            this.setGridColor(Color.lightGray);
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    public void setColumnWidth(int column,int width){
        this.getColumn(column).setMinWidth(width);
        this.getColumn(column).setMaxWidth(width);
        this.getColumn(column).setWidth(width);        
        this.getColumn(column).setPreferredWidth(width);
    }    
    public TableColumn getColumn(int column){
        return this.getColumnModel().getColumn(column);
    }
    public void setcenter(){
        this.getColumn(0).getCellRenderer();
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 

    // </editor-fold> 
}
