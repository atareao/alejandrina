/*
 * ***********************Software description*********************************
 * DirectoryFilter.java
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
import javax.swing.filechooser.FileFilter;
/**
 *
 * @author Lorenzo Carbonell
 */
public class DirectoryFilter extends FileFilter{
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 
    private final String mFileDescription = "Directory";
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Metodos  ">
    @Override
    public boolean accept( final File aFilterFile ) {
        return ( aFilterFile.isDirectory() );
    }

    @Override
    public String getDescription() {
        return mFileDescription;
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Metodos auxiliares  ">

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Metodos de acceso  ">

    // </editor-fold> 
}
