/*
 * ***********************Software description*********************************
 * JCheckBoxTreeCellRenderer.java
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
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

/**
 *
 * @author Lorenzo Carbonell
 */
public class JCheckBoxTreeCellRenderer extends JCheckBox implements TreeCellRenderer{
    
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 
    private static final long serialVersionUID = -0L;

    private static long getSerialVersionUID() {
        return serialVersionUID;
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    public JCheckBoxTreeCellRenderer(){
        Font fontValue;
        fontValue = UIManager.getFont("Tree.font");
        if (fontValue != null) {
          this.setFont(fontValue);
        }
        Boolean booleanValue = (Boolean) UIManager.get("Tree.drawsFocusBorderAroundIcon");
        this.setFocusPainted((booleanValue != null)&& (booleanValue.booleanValue()));
        this.setSelectionBorderColor(UIManager.getColor("Tree.selectionBorderColor"));
        this.setSelectionForeground(UIManager.getColor("Tree.selectionForeground"));
        this.setSelectionBackground(UIManager.getColor("Tree.selectionBackground"));
        this.setTextForeground(UIManager.getColor("Tree.textForeground"));
        this.setTextBackground(UIManager.getColor("Tree.textBackground"));
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Metodos  ">
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,boolean selected, boolean expanded, boolean leaf, int row,boolean hasFocus) {
        if (selected) {
            this.setForeground(this.getSelectionForeground());
            this.setBackground(this.getSelectionBackground());
        } else {
            this.setForeground(this.getTextForeground());
            this.setBackground(this.getTextBackground());
        }

        if ((value != null) && (value instanceof CheckedTreeNode)) {
            CheckedTreeNode v=(CheckedTreeNode)value;
            this.setText(v.getUserObject().toString());
            this.setMarked(v.isMarked());
        }
        return this;
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Metodos auxiliares  ">

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 
    private Color _selectionBorderColor;
    private Color _selectionForeground;
    private Color _selectionBackground;
    private Color _textForeground;
    private Color _textBackground;
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Metodos de acceso  ">

    private Color getSelectionBorderColor() {
        return _selectionBorderColor;
    }

    private void setSelectionBorderColor(Color selectionBorderColor) {
        this._selectionBorderColor = selectionBorderColor;
    }

    private Color getSelectionForeground() {
        return _selectionForeground;
    }

    private void setSelectionForeground(Color selectionForeground) {
        this._selectionForeground = selectionForeground;
    }

    private Color getSelectionBackground() {
        return _selectionBackground;
    }

    private void setSelectionBackground(Color selectionBackground) {
        this._selectionBackground = selectionBackground;
    }

    private Color getTextForeground() {
        return _textForeground;
    }

    private void setTextForeground(Color textForeground) {
        this._textForeground = textForeground;
    }

    private Color getTextBackground() {
        return _textBackground;
    }

    private void setTextBackground(Color textBackground) {
        this._textBackground = textBackground;
    }

    public void setMarked(boolean isMarked){
        this.setSelected(isMarked);
    }
    public boolean isMarked(){
        return this.isSelected();
    }
    // </editor-fold> 
}
