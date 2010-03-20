/*
 * DefaultEventCellRenderer.java
 *
 * Created on 1 de septiembre de 2007, 8:59
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

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import es.atareao.alejandria.lib.Suceso;
//
//********************************IMPORTACIONES*********************************
//

/**
 *
 * @author Propietario
 */
public class DefaultEventCellRenderer extends JLabel implements ListCellRenderer{
    //
    //********************************CONSTANTES********************************
    //
    Border mEmptyBorder = BorderFactory.createEmptyBorder();
    Border mHighLightBorder = UIManager.getBorder("Table.focusCellHighlightBorder");    
    
    //
    // *********************************CAMPOS*********************************
    //
    
    //
    //******************************CONSTRUCTORES*******************************
    //
    
    /** Creates a new instance of DefaultEventCellRenderer */
    public DefaultEventCellRenderer() {
        this.setOpaque(true);
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

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        this.setBackground(((Suceso)value).getColor());
        this.setText(((Suceso)value).getResumen());
        if ((isSelected)&&(list.hasFocus())) {
            setBorder(mHighLightBorder);
        } else {
            setBorder(mEmptyBorder);
        }
        return this;        
    }
    
}
