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
/**
 *
 * @author Lorenzo Carbonell
 */
public class SimpleLengthValidator extends SimpleStringValidator{
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    public SimpleLengthValidator(String value,int length,boolean biggerThan){
        super(value);
        this.setLength(length);
        this.setBiggerThan(biggerThan);
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    public boolean validate(){
        String cadena=this.getValue();
        if(this.isBiggerThan()){
            if(cadena.length()>this.getLength()){
                return true;
            }
        }else{
            if((cadena==null)||(cadena.length()<this.getLength())){
                return true;
            }
        }        
        return false;
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 
    private int _length;
    private boolean _biggerThan;
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 
    public int getLength() {
        return _length;
    }

    public void setLength(int length) {
        this._length = length;
    }

    public boolean isBiggerThan() {
        return _biggerThan;
    }

    public void setBiggerThan(boolean biggerThan) {
        this._biggerThan = biggerThan;
    }
    // </editor-fold> 
}
