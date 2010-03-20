/*
 * Usuario.java
 *
 * TODO: Descripcion
 *
 * Creado en 14 de septiembre de 2006, 11:50
 *
 * Copyright (C) 14 de septiembre de 2006, Protactino
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
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Protactino
 */
public class Usuario {
    //
    //********************************CONSTANTES********************************
    //
    String defectoName="usuario";
    static char[] defectoPassword={'1','2','3','4'};
    //
    // *********************************CAMPOS*********************************
    //
    private String _name;
    private String _hash;
    //
    //******************************CONSTRUCTORES*******************************
    //
    /**
     * Crea una nueva instancia de Usuario
     */
    public Usuario(String name,String hash) {
        this.setName(name);
        this.setHash(hash);
    }
    public Usuario() throws UnsupportedEncodingException{
        this.setName(defectoName);
        this.setHash(toHash(defectoPassword));
    }
    public Usuario(String name,char[] password) throws UnsupportedEncodingException {
        this(name,toHash(password));
    }
    //
    //**************************METODOS DE ACCESO*******************************
    //
    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getHash() {
        return _hash;
    }

    public void setHash(String hash) {
        this._hash = hash;
    }
   
    //
    //********************************METODOS***********************************
    //

    //
    //**************************METODOS AUXILIARES******************************
    //
    public static String toHash(char[] password) throws UnsupportedEncodingException{
        MD5 md5 = new MD5();
        md5.Update(password.toString(), null);
        String hash = md5.asHex();
        return hash;
    }
    
    public boolean equals(Object obj) {
        if(obj instanceof Usuario){
            Usuario comparado=(Usuario)obj;
            if(comparado.getName().equals(this.getName())){
                if(comparado.getHash().equals(this.getHash())==false){
                    return true;
                }
            }
        }
        return false;
    }
}
