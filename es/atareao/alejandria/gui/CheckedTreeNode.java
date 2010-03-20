/*
 * ***********************Software description*********************************
 * CheckedTreeNode.java
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

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Lorenzo Carbonell
 */
public class CheckedTreeNode extends DefaultMutableTreeNode{
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 
        private static final long serialVersionUID = 0L;
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    public CheckedTreeNode(Object o,boolean isMarked){
        super(o);
        this.setMarked(isMarked);
    }
    public CheckedTreeNode(Object o){
        this(o,false);
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Metodos  ">

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Metodos auxiliares  ">

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 
    private boolean _marked = false;
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Metodos de acceso  ">
    public boolean isMarked(){
        return this._marked;
    }
    public void setMarked(boolean isMarked){
        this._marked=isMarked;
    }
    // </editor-fold> 
}
