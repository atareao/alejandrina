/*
 * CenterTableCellRenderer.java
 *
 * Created on 12 de octubre de 2007, 20:18
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

package es.atareao.alejandria.gui;

//
//********************************IMPORTACIONES*********************************
//
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Propietario
 */
public class CenterTableCellRenderer extends DefaultTableCellRenderer{
    //
    //********************************CONSTANTES********************************
    //
    
    //
    // *********************************CAMPOS*********************************
    //
    
    //
    //******************************CONSTRUCTORES*******************************
    //
    
    /** Creates a new instance of CenterTableCellRenderer */
    public CenterTableCellRenderer() {
        setHorizontalAlignment( CENTER );
    }
    //
    //********************************METODOS***********************************
    //
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        return this;
    }
    //
    //**************************METODOS AUXILIARES******************************
    //
    
    //
    //**************************METODOS DE ACCESO*******************************
    //
    
}
