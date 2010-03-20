/*
 * DefaultComboBoxModel.java
 *
 * Created on 7 de agosto de 2007, 21:15
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package es.atareao.alejandria.gui;

/**
 *
 * @author Propietario
 */
public class DefaultComboBoxElement implements Comparable{
    //
    //********************************CONSTANTES********************************
    //
    //
    // *********************************CAMPOS*********************************    
    //
    private String _key;
    private String _value;
    //
    //******************************CONSTRUCTORES*******************************
    //
    /** Creates a new instance of Wrapper */

    /** Creates a new instance of DefaultComboBoxModel
     * @param key
     * @param value 
     */
    public DefaultComboBoxElement(String key,String value) {
        this.setKey(key);
        this.setValue(value);
    }
    //
    //********************************METODOS***********************************
    //    
    @Override
    public String toString(){
        return this.getValue();
    }
    @Override
    public boolean equals(Object object){
        if(object instanceof DefaultComboBoxElement){
            if(((DefaultComboBoxElement)object).getValue().equals(this.getValue())){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (this._value != null ? this._value.hashCode() : 0);
        return hash;
    }
    public boolean equalsKey(Object object){
        if(object instanceof DefaultComboBoxElement){
            if(((DefaultComboBoxElement)object).getKey().equals(this.getKey())){
                return true;
            }
        }
        return false;
    }
    public String getKey() {
        return _key;
    }

    public void setKey(String key) {
        this._key = key;
    }

    public String getValue() {
        return _value;
    }

    public void setValue(String value) {
        this._value = value;
    }

    public int compareTo(Object o) {
        if(o instanceof DefaultComboBoxElement){
            return this.getValue().compareTo(((DefaultComboBoxElement)o).getValue());
        }
        return -1;
    }
}
