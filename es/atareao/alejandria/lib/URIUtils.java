/*
 * ***********************Software description*********************************
 * URIUtils.java
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

package es.atareao.alejandria.lib;

//
//********************************IMPORTACIONES*********************************
//
import java.net.URI;
import java.net.URISyntaxException;
import es.atareao.alejandria.gui.ErrorDialog;

/**
 *
 * @author Lorenzo Carbonell
 */
public class URIUtils {
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    public static URI getUserDir(){
        try {
            String curDir = System.getProperty("user.dir");
            return new URI(curDir);
        } catch (URISyntaxException ex) {
            ErrorDialog.manejaError(ex,false);
        }
        return null;
    }
    public static URI relativeAgainstUserDir(URI uri){
        return getUserDir().relativize(uri);
    }
    public static URI resolveAgainstUserDir(URI uri){
        return getUserDir().resolve(uri);
    }
    public static String getSuffix(URI uri) {
        String fileName=uri.toString();
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }    
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 

    // </editor-fold> 
}
