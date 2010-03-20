/*
 * Patron.java
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
import java.util.regex.Pattern;

/**
 *
 * @author Protactino
 */
public class Patron {
    //
    //********************************CONSTANTES********************************
    //
    
    //
    // *********************************CAMPOS*********************************
    //
    private Pattern _patron;
    private boolean _mayusminus=false;
    //
    //******************************CONSTRUCTORES*******************************
    //
    /**
     * Crea una nueva instancia de Patron
     */
    public Patron(Pattern patron,boolean mayusminus) {
        this.setPatron(patron);
        this.setMayusminus(mayusminus);
    }
    public Patron(Pattern patron) {
        this(patron,false);
    }
    public Patron(String patronStr,boolean mayusminus){
        this.setPatron(patronStr);
        this.setMayusminus(mayusminus);
    }
    public Patron(String patronStr){
        this(patronStr,false);
    }
    //
    //********************************METODOS***********************************
    //
    public boolean encaja(String cadena){
        String normalizada=normalizaCadena(this.isMayusminus(),cadena);
        boolean coincide=this.getPatron().matcher(normalizada).find();
        return coincide;
    }
    //
    //**************************METODOS AUXILIARES******************************
    //
    public static String sinAcentos(String cadena){
        String temporal=cadena.trim().toLowerCase();
        temporal=temporal.replace('á','a');
        temporal=temporal.replace('é','e');
        temporal=temporal.replace('í','i');
        temporal=temporal.replace('ó','o');
        temporal=temporal.replace('ú','u');
        return temporal;
    }
    
    public static String sinAcentosDiferencial(String cadena){
        String temporal=cadena.trim();
        temporal=temporal.replace('á','a');
        temporal=temporal.replace('é','e');
        temporal=temporal.replace('í','i');
        temporal=temporal.replace('ó','o');
        temporal=temporal.replace('ú','u');
        temporal=temporal.replace('Á','A');
        temporal=temporal.replace('É','E');
        temporal=temporal.replace('Í','I');
        temporal=temporal.replace('Ó','O');
        temporal=temporal.replace('Ú','U');
        return temporal;
    }
    private static String normalizaCadena(boolean mayusminus,String cadena){
        String resultado;
        if(mayusminus){
            resultado=sinAcentosDiferencial(cadena);
        }else{
            resultado=sinAcentos(cadena);
        }
        return resultado;
    }
    private static String normalizaPatron(boolean mayusminus,String patron){
        String patronStr;
        if(mayusminus){
            patronStr="^"+patron+"$";
            patronStr=sinAcentosDiferencial(patronStr);
            patronStr=patronStr.replaceAll("\\*",".+");//[A-Za-z0-9]+
        }else{
            patronStr="^"+patron.toLowerCase()+"$";
            patronStr=sinAcentos(patronStr);
            patronStr=patronStr.replaceAll("\\*",".+");//[a-z0-9]+
        }
        return patronStr;
    }
    //
    //**************************METODOS DE ACCESO*******************************
    //

    public Pattern getPatron() {
        return _patron;
    }

    public void setPatron(Pattern patron) {
        this._patron = patron;
    }

    public void setPatron(String patron){
        this._patron=Pattern.compile(normalizaPatron(this.isMayusminus(),patron));
    }

    public boolean isMayusminus() {
        return _mayusminus;
    }

    public void setMayusminus(boolean mayusminus) {
        this._mayusminus = mayusminus;
    }
    
}
