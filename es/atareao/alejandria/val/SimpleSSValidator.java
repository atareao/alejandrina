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
import es.atareao.alejandria.lib.Convert;

/**
 *
 * @author Lorenzo Carbonell
 */
public class SimpleSSValidator extends SimpleStringValidator{
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    public SimpleSSValidator(String nif){
        super(nif);
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    public boolean validate(){
        return esSS(this.getValue());
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
    public static boolean esSS(String nss){
        if(nss!=null){
            String ss=limpiaCadena(nss);
            if((ss.length()>0)&&(ss.length()==12)){
                long d=0;
                String a=ss.substring(0,2);
                String b=ss.substring(2,10);
                String c=ss.substring(10,12);
                if(Convert.toInt(b)<10000000){
                    d=Convert.toLong(b)+Convert.toLong(a)*10000000;
                }else{
                    d=Convert.toLong(a+Convert.toString(Convert.toLong(b)));
                }
                String cc=Convert.toString(d%97);
                if(cc.length()<2){
                    cc="0"+cc;
                }
                if(cc.equals(c)){
                    return true;
                }
                return false;
            }
        }
        return true;
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 

    // </editor-fold> 
}
