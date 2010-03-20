/*
 * GeneradorUUID.java
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

/**
 *
 * @author Protactino
 */
public class GeneradorUUID {
    //
    //********************************CONSTANTES********************************
    //
    
    //
    // *********************************CAMPOS*********************************
    //
    
    //
    //******************************CONSTRUCTORES*******************************
    //

    //
    //********************************METODOS***********************************
    //
    public static String crearUUID() {
        return java.util.UUID.randomUUID().toString();
    }
    //
    //**************************METODOS AUXILIARES******************************
    //
    
    //
    //**************************METODOS DE ACCESO*******************************
    //
    
}
