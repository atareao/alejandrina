/*
 * Tapadera.java
 *
 * Created on 17 de agosto de 2007, 18:13
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package es.atareao.alejandria.lib;

/**
 *
 * @author Propietario
 */
public class Tapadera {
    //
    //********************************CONSTANTES********************************
    //
    
    //
    // *********************************CAMPOS*********************************
    //
    private String _id;
    private String _name;
    //
    //******************************CONSTRUCTORES*******************************
    //
    
    /** Creates a new instance of Tapadera */
    public Tapadera(String id, String name) {
        this.setId(id);
        this.setName(name);
    }
    public Tapadera(){
        this("","");
    }
    //
    //********************************METODOS***********************************
    //
    public String toString(){
        return this.getName();
    }
    
    public boolean equals(Object objeto){
        if(objeto instanceof Tapadera){
            if(((Tapadera)objeto).getId().equals(this.getName())){
                return true;
            }
        }
        return false;
    }
    //
    //**************************METODOS DE ACCESO*******************************
    //
    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }
    
}
