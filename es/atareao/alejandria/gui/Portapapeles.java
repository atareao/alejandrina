/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.atareao.alejandria.gui;

//
//********************************IMPORTACIONES*********************************
//
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author Lorenzo Carbonell
 */
public class Portapapeles implements ClipboardOwner{

    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    /**
    * Empty implementation of the ClipboardOwner interface.
    * @param aClipboard
    * @param aContents 
    */
    @Override
    public void lostOwnership( Clipboard aClipboard, Transferable aContents) {
        //do nothing
    }

    /**
    * Place a String on the clipboard, and make this class the
    * owner of the Clipboard's contents.
    * @param aString 
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
            }catch (UnsupportedFlavorException ex){
                //highly unlikely since we are using a standard DataFlavor
                ErrorDialog.manejaError(ex);
            }catch (IOException ex) {
                ErrorDialog.manejaError(ex);
            }
        }
    return result;
    }

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 

    // </editor-fold> 
}
