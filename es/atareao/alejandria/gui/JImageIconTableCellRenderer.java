/*
 * ***********************Software description*********************************
 * JImageIconTableCellRenderer.java
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
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
/**
 *
 * @author Lorenzo Carbonell
 */
public class JImageIconTableCellRenderer extends JLabel implements TableCellRenderer{
    //
    private Color alternateColor = new Color(245, 245, 220);

    
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    public JImageIconTableCellRenderer(ImageIcon image){
        this.setImage(image);
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }
    public JImageIconTableCellRenderer(String imageUrl){
        this.setImage(imageUrl);
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }    
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Border mEmptyBorder = BorderFactory.createEmptyBorder();
        Border mHighLightBorder = UIManager.getBorder("Table.focusCellHighlightBorder");
        Color mFocusCellForeground=table.getSelectionForeground();
        Color mFocusCellBackground=table.getSelectionBackground();
        Color mCellForeground=table.getForeground();
        Color mCellBackground=table.getBackground();
        if (isSelected) {
            setForeground(mFocusCellForeground);
            setBackground(mFocusCellBackground);
        }else{
            setForeground(mCellForeground);
            setBackground((row % 2 == 0 ? alternateColor : mCellBackground));
        }
        if(hasFocus){
            setBorder(mHighLightBorder);
        }else{
            setBorder(mEmptyBorder);
        }
        return this;
    }

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 
    private ImageIcon _image;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  ">
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 
   public ImageIcon getImage() {
        return _image;
    }

    public void setImage(ImageIcon image) {
        this._image = image;
        this.setIcon(this._image);
    }
    public void setImage(String imageUrl){
        this._image=new ImageIcon(getClass().getResource(imageUrl));
        this.setIcon(this._image);
    }
    // </editor-fold> 
}
