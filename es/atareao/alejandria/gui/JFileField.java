/*
 * ***********************Software description*********************************
 * JFileField.java
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
import java.io.File;
import javax.swing.JTextField;
/**
 *
 * @author Lorenzo Carbonell
 */
public class JFileField extends JTextField{
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    public JFileField(){
        this.setEditable(false);
        this.setBackground(java.awt.SystemColor.info);
        this.setText("");
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 
    
    private File _file;
   
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 
    public File getFile() {
        return _file;
    }

    public void setFile(File file) {
        this._file = file;
        this.setText(file.getAbsolutePath());
    }

    // </editor-fold> 
}
