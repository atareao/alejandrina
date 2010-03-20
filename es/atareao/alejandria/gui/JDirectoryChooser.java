/*
 * ***********************Software description*********************************
 * JDirectoryChooser.java
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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Lorenzo Carbonell
 */
public class JDirectoryChooser extends JFileChooser {
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 
    private final String mFileDescription = "Directory";
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    public JDirectoryChooser() {
    	super();
    	setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
    	setFileFilter( new DirectoryFilter()) ;
    }
    public JDirectoryChooser(File dir){
        super(dir);
    	setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
    	setFileFilter( new DirectoryFilter()) ;        
    }
    public JDirectoryChooser(File dir,FileSystemView fsv){
        super(dir,fsv);
    	setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
    	setFileFilter( new DirectoryFilter()) ;        
    }
    public JDirectoryChooser(FileSystemView fsv){
        super(fsv);
    	setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
    	setFileFilter( new DirectoryFilter()) ;        
    }
    public JDirectoryChooser(String dir){
        super(dir);
    	setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
    	setFileFilter( new DirectoryFilter()) ;        
    }    
    public JDirectoryChooser(String dir,FileSystemView fsv){
        super(dir,fsv);
    	setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
    	setFileFilter( new DirectoryFilter()) ;        
    }    
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    public int showChooser(final JFrame aParent , final String aSelectButton ) {
        return showDialog(aParent, aSelectButton );
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 

    // </editor-fold> 
}
