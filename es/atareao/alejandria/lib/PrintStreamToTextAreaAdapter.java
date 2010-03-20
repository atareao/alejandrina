/*
 * PrintStreamToTextAreaAdapter.java
 *
 * Copyright (c) 2010 Lorenzo Carbonell
 * email: lorenzo.carbonell.cerezo@gmail.com
 * website: http://www.atareao.es
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
