/*
 * JContactoField.java
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


package es.atareao.alejandria.gui;


//
//********************************IMPORTACIONES*********************************
//
import javax.swing.JTextField;
import es.atareao.alejandria.lib.Tapadera;

/**
 *
 * @author Protactino
 */
public class JTapaderaField extends JTextField{
    //
    //********************************CONSTANTES********************************
    //
    
    //
    // *********************************CAMPOS*********************************
    //
    private Tapadera _tapadera;
    //
    //******************************CONSTRUCTORES*******************************
    //
    /**
     * Crea una nueva instancia de JContactoField
     */
    public JTapaderaField() {
        this.setEditable(false);
        this.setText("");
        this.setBackground(java.awt.SystemColor.info);
    }
    //
    //********************************METODOS***********************************
    //
    
    //
    //**************************METODOS AUXILIARES******************************
    //
    
    //
    //**************************METODOS DE ACCESO*******************************
    //

    public Tapadera getTapadera() {
        if(_tapadera==null){
            _tapadera=new Tapadera();
        }
        return _tapadera;
    }

    public void setTapadera(Tapadera tapadera) {
        this._tapadera = tapadera;
        this.setText(tapadera.toString());
    }

    public void setTapadera(String id,String name){
        this._tapadera=new Tapadera(id,name);
        this.setText(this._tapadera.toString());
    }
}
