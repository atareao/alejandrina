/*
 * ***********************Software description*********************************
 * Preferencias.java
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

package es.atareao.alejandria.lib;

//
//********************************IMPORTACIONES*********************************
//

import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @author Lorenzo Carbonell
 */
public class Preferencias {
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    public Preferencias(INIFile iniFile){
        this.setIniFile(iniFile);
        this.load();
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    public void load(){
        this.init();
        this.setSections(toVector(this.getIniFile().getAllSectionNames()));
        for(String section:this.getSections()){
            String[] keys=this.getIniFile().getPropertyNames(section);
            this.getKeys().put(section,toVector(keys));
            for(String key:keys){
                String clave=this.getIniFile().getStringProperty(section,key);
                this.getValues().put(key, clave);
            }
        }
    }
    public void save(){
        if(this.getSections().size()>0){
            for(String section:this.getSections()){
                if(this.getKeys().size()>0){
                    for(String key:this.getKeys().get(section)){
                        String value=this.getValues().get(key);
                        if(value!=null){
                            this.getIniFile().setStringProperty(section,key,value,null);
                        }
                    }
                }
            }
        }
        this.getIniFile().save();
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 
    private void init(){
        this.setSections(new Vector<String>());
        this.setKeys(new Hashtable<String,Vector<String>>());
        this.setValues(new Hashtable<String,String>());
    }
    private static Vector<String> toVector(String[] array){
        Vector<String> vector=new Vector<String>();
        if(array.length>0){
            for(String elemento:array){
                vector.add(elemento);
            }
        }
        return vector;
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 
    private INIFile _iniFile;
    private Vector<String> _sections;
    private Hashtable<String,Vector<String>> _keys;
    private Hashtable<String,String> _values;
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 
    public INIFile getIniFile() {
        return _iniFile;
    }

    public void setIniFile(INIFile iniFile) {
        this._iniFile = iniFile;
    }
    
    public void setPreferencia(String key,String value){
        this.getValues().put(key, value);
    }
    
    public String getPreferencia(String key){
        if(this.getValues().containsKey(key)){
            return this.getValues().get(key);
        }
        return null;
    }

    private Vector<String> getSections() {
        return _sections;
    }

    private void setSections(Vector<String> sections) {
        this._sections = sections;
    }

    private Hashtable<String, Vector<String>> getKeys() {
        return _keys;
    }

    private void setKeys(Hashtable<String, Vector<String>> keys) {
        this._keys = keys;
    }

    private Hashtable<String, String> getValues() {
        return _values;
    }

    private void setValues(Hashtable<String, String> values) {
        this._values = values;
    }

    // </editor-fold> 
}
