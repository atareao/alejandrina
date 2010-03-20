/*
 * ***********************Software description*********************************
 * SimpleNIFValidator.java
 * 
 * 
 * ***********************Software description*********************************
 * 
 * Copyright (C) 2008 - Lorenzo Carbonell
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * **************************Software License***********************************
 * 
 */

package es.atareao.alejandria.val;

//
//********************************IMPORTACIONES*********************************
//
import es.atareao.alejandria.lib.EstoEs;

/**
 *
 * @author Lorenzo Carbonell
 */
public class SimpleNIFValidator extends SimpleStringValidator{
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 
    private static String NIF_STRING_ASOCIATION = "TRWAGMYFPDXBNJZSQVHLCKET";
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    public SimpleNIFValidator(String nif){
        super(nif);
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    public boolean validate(){
        String nif=limpiaCadena(this.getValue());
        if((nif!=null)&&(nif.length()>0)){
            //si el primer caracter es una letra es un nie
            if(EstoEs.unEntero(nif.substring(0,1))){
                if(nif.length()>8){
                    String dni=nif.substring(0,nif.length()-1);
                    String letra=nif.substring(nif.length()-1,nif.length());
                    if(letra.equals(letraNIF(dni))){
                        return true;
                    }
                }
            }else{
                if(nif.length()>9){
                    String dni=nif.substring(1,nif.length()-1);
                    String letra=nif.substring(nif.length()-1,nif.length());
                    if(letra.equals(letraNIF(dni))){
                        return true;
                    }
                }
                
            }
            return false;        
        }
        return true;
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 
    private static String limpiaCadena(String cadena){
        String tmp0=cadena.replace('.',' ');
        String tmp1=tmp0.replace('-',' ');
        String tmp2 =tmp1.replace('/',' ');
        String tmp3 =tmp2.replaceAll(" ","");
        return tmp3;
    }
    public static String letraNIF(String dni) {
        int dniNumber=Integer.valueOf(dni);
        return ""+NIF_STRING_ASOCIATION.charAt(dniNumber % 23);
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 

    // </editor-fold> 
}
