/*
 * TextTransfer.java
 *
 * TODO: Descripcion
 *
 * Creado en 6 de diciembre de 2006, 14:38
 *
 * Copyright (C) 6 de diciembre de 2006, Protactino
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */

package es.atareao.alejandria.gui;
//
//********************************IMPORTACIONES*********************************
//
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.Toolkit;
import java.io.*;
/**
 *
 * @author Protactino
 */
public final class TextTransfer implements ClipboardOwner {
    //
    //********************************CONSTANTES********************************
    //
    
    //
    // *********************************CAMPOS*********************************
    //
    
    //
    //******************************CONSTRUCTORES*******************************
    //
    /**
     * Crea una nueva instancia de TextTransfer
     */
    public TextTransfer() {
    }
    //
    //********************************METODOS***********************************
    //
    /**
    * Empty implementation of the ClipboardOwner interface.
    */
    public void lostOwnership( Clipboard aClipboard, Transferable aContents) {
        //do nothing
    }
    /**
    * Place a String on the clipboard, and make this class the
    * owner of the Clipboard's contents.
    */
    public void setClipboardContents( String aString ){
        StringSelection stringSelection = new StringSelection( aString );
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents( stringSelection, this );
    }
    /**
    * Get the String residing on the clipboard.
    *
    * @return any text found on the Clipboard; if none found, return an
    * empty String.
    */
    public String getClipboardContents() {
        String result = "";
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        //odd: the Object param of getContents is not currently used
        Transferable contents = clipboard.getContents(null);
        boolean hasTransferableText =(contents != null)&&contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        if ( hasTransferableText ) {
            try {
                result = (String)contents.getTransferData(DataFlavor.stringFlavor);
                }catch (Exception ex){
                    ErrorDialog.manejaError(ex,false);
                }
            }
        return result;
    }    
    //
    //**************************METODOS AUXILIARES******************************
    //
    
    //
    //**************************METODOS DE ACCESO*******************************
    //
    
}
