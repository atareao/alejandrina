/*
 * FixedSizePlainDocument.java
 *
 * Created on 12 de octubre de 2007, 20:00
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

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
//
//********************************IMPORTACIONES*********************************
//

/**
 *
 * @author Propietario
 */
public class FixedSizePlainDocument extends PlainDocument{
    //
    //********************************CONSTANTES********************************
    //
    
    //
    // *********************************CAMPOS*********************************
    //
    int maxSize;
    //
    //******************************CONSTRUCTORES*******************************
    //
    
    /** Creates a new instance of FixedSizePlainDocument */
    public FixedSizePlainDocument(int limit) {
        maxSize = limit;
    }
    //
    //********************************METODOS***********************************
    //
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if ((getLength() + str.length()) <= maxSize) {
            super.insertString(offs, str, a);
        } else {
            throw new BadLocationException("Insertion exceeds max size of document", offs);
        }
    }
    //
    //**************************METODOS AUXILIARES******************************
    //
    
    //
    //**************************METODOS DE ACCESO*******************************
    //
    
}
