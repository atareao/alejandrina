/*
 * ***********************Software description*********************************
 * JCheckBoxTreeCellEditor.java
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
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreePath;
/**
 *
 * @author Lorenzo Carbonell
 */
public class JCheckBoxTreeCellEditor extends AbstractCellEditor implements TreeCellEditor {


    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 
    private static final long serialVersionUID = 0L;
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    /**
     * 
     */
    public JCheckBoxTreeCellEditor(JTree tree) {
        this.setTree(tree);
        this.setCheckBox(new JCheckBox());
        this.getCheckBox().setHorizontalAlignment(SwingConstants.CENTER);
        this.getCheckBox().addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBoxItemStateChanged(evt);
            }
        });        

    }

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Metodos  ">
   
    public Object getCellEditorValue() {
        Object value=this.getTree().getEditingPath().getLastPathComponent();
        this.getTree().setSelectionPath(this.getTree().getEditingPath());
        if((value!=null)&&(value instanceof CheckedTreeNode)){
            CheckedTreeNode v=(CheckedTreeNode)value;
            return new CheckedTreeNode(v.getUserObject(),this.isMarked());
        }
        return null;
    }
    /*
    private boolean isIndiseCheckBox(EventObject e) {
        if (e instanceof MouseEvent) {
            MouseEvent me = (MouseEvent) e;
            JTree tree = (JTree) e.getSource();
            TreePath path = tree.getPathForLocation(me.getX(), me.getY());
            if (path != null) {
                Rectangle r = tree.getPathBounds(path);
                int x = me.getX() - r.x;
                int y = me.getY() - r.y;
                if (x > this.getCheckBox().getX() && x < this.getCheckBox().getPreferredSize().width) {
                    return true;
                }
            }
        }
        return false;            
    }

    @Override
    public boolean isCellEditable(EventObject e) {
        return isIndiseCheckBox(e);
    }

    @Override
    public boolean shouldSelectCell(EventObject e) {
        return !isIndiseCheckBox(e);
    }
     */ 
        
    public Component getTreeCellEditorComponent(JTree tree, Object value,boolean selected, boolean expanded, boolean leaf, int row) {
        this.getTree().setSelectionPath(this.getTree().getEditingPath());
        if((value!=null)&&(value instanceof CheckedTreeNode)){
            CheckedTreeNode ctn=(CheckedTreeNode)value;
            this.getCheckBox().setText(ctn.getUserObject().toString());
            this.setMarked(ctn.isMarked());
        }else{
            this.getCheckBox().setSelected(false);
        }
        if(selected){
            this.getCheckBox().setForeground(UIManager.getColor("Tree.selectionForeground"));
            this.getCheckBox().setBackground(UIManager.getColor("Tree.selectionBackground"));
        } else {
            this.getCheckBox().setForeground(UIManager.getColor("Tree.textForeground"));
            this.getCheckBox().setBackground(UIManager.getColor("Tree.textBackground"));
        }        
        return this.getCheckBox();
    }
    public void setMarked(boolean isMarked){
        this.getCheckBox().setSelected(isMarked);
    }
    public boolean isMarked(){
        boolean marked=this.getCheckBox().isSelected();
        return marked;
    }
    
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" M�todos auxiliares  "> 
    private void checkBoxItemStateChanged(java.awt.event.ItemEvent evt) {
        this.stopCellEditing();
    }

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 
    private JCheckBox _checkBox;
    private JTree _tree;
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" M�todos de acceso  "> 
    private JCheckBox getCheckBox() {
        return _checkBox;
    }

    private void setCheckBox(JCheckBox checkBox) {
        this._checkBox = checkBox;
    }

    private JTree getTree() {
        return _tree;
    }

    private void setTree(JTree tree) {
        this._tree = tree;
    }

    // </editor-fold> 
}
