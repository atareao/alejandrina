/*
 * Suceso.java
 *
 * Created on 24 de agosto de 2007, 7:47
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
//
//***********************************PAQUETE************************************
//

package es.atareao.alejandria.lib;

import java.awt.Color;
import java.util.Date;
//
//********************************IMPORTACIONES*********************************
//

/**
 *
 * @author Propietario
 */
public class DefaultSuceso implements Suceso{
    //
    //********************************CONSTANTES********************************
    //
    
    //
    // *********************************CAMPOS*********************************
    //
    private String _id;
    private String _resumen;
    private Date _date;
    //
    //******************************CONSTRUCTORES*******************************
    //
    
    /** Creates a new instance of Suceso */
    public DefaultSuceso(String id,String resumen,Date date) {
        this.setId(id);
        this.setResumen(resumen);
        this.setDate(date);
    }
    //
    //********************************METODOS***********************************
    //
    public String toString(){
        return this.getResumen();
    }
    
    public int compareTo(Object o) {
        if(o instanceof DefaultSuceso){
            if(this.equals(o)){
                return 0;
            }
            return -((DefaultSuceso)o).getDate().compareTo(this.getDate());
        }else{
            throw new ClassCastException("No es un Suceso");
        }
    }
    
    public boolean equals(Object o){
        if(o instanceof DefaultSuceso){
            return ((DefaultSuceso)o).getId().equals(this.getId());
        }else{
            return false;
        }
    }
    //
    //**************************METODOS AUXILIARES******************************
    //
    
    //
    //**************************METODOS DE ACCESO*******************************
    //

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getResumen() {
        return _resumen;
    }

    public void setResumen(String resumen) {
        this._resumen = resumen;
    }

    public Date getDate() {
        return _date;
    }

    public void setDate(Date date) {
        this._date = date;
    }

    public Color getColor() {
        return Color.WHITE;
    }
}
