/*
 * PrintStreamToTextAreaAdapter.java
 *
 * TODO: Descripcion
 *
 * Creado en 18 de octubre de 2006, 7:51
 *
 * Copyright (C) 18 de octubre de 2006, Protactino
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

package es.atareao.alejandria.lib;
//
//********************************IMPORTACIONES*********************************
//
import java.io.PrintStream;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *
 * @author Protactino
 */
public class PrintStreamToTextAreaAdapter extends PrintStream{
    //
    //********************************CONSTANTES********************************
    //
    
    //
    // *********************************CAMPOS*********************************
    //
    private JTextArea _jTextArea;
    private boolean _toBoth;
    private static final PrintStream standardOutput = System.out;

    //
    //******************************CONSTRUCTORES*******************************
    //
    /**
     * Crea una nueva instancia de PrintStreamToTextAreaAdapter
     */
   public PrintStreamToTextAreaAdapter ( JTextArea jTextArea , boolean toBoth )
    {
        super ( System.out );
        this.setJTextArea(jTextArea);
        this.setToBoth(toBoth);
    }

    //
    //**************************METODOS DE ACCESO*******************************
    //

    public JTextArea getJTextArea() {
        return _jTextArea;
    }

    public void setJTextArea(JTextArea jTextArea) {
        this._jTextArea = jTextArea;
    }

    public boolean isToBoth() {
        return _toBoth;
    }

    public void setToBoth(boolean toBoth) {
        this._toBoth = toBoth;
    }
    
    //
    //********************************METODOS***********************************
    //
    @Override
    public void println ( final String s ){
        if(s!=null){
            if (this.isToBoth()){
                standardOutput.println(s);
            }
            if ( SwingUtilities.isEventDispatchThread() ){
                this.getJTextArea().append(s+"\n");
            }
            else{
                try{
                   SwingUtilities.invokeLater ( new Runnable(){ 
                       public void run(){ 
                          PrintStreamToTextAreaAdapter.this.getJTextArea().append(s+"\n");
                       }
                   });
                }
                finally{;}
            }
        }
    }

    //
    //**************************METODOS AUXILIARES******************************
    //
    
}
