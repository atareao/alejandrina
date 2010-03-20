/*
 * ***********************Software description*********************************
 * ValueChangeEvent.java
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
package es.atareao.alejandria.gui;

//
//********************************IMPORTACIONES*********************************
//
import java.util.EventObject;

/**
 *
 * @author Lorenzo Carbonell
 */
public class ValueChangeEvent extends EventObject {
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    /**
     * Constructs a new <code>ValueChangeEvent</code>.
     *
     * @param source The bean that fired the event.
     * @param propertyName The programmatic name of the property
     * that was changed.
     * @param oldValue The old value of the property.
     * @param newValue The new value of the property.
     */
    public ValueChangeEvent(Object source, String propertyName, Object oldValue, Object newValue) {
        super(source);
        this.propertyName = propertyName;
        this.newValue = newValue;
        this.oldValue = oldValue;
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 
    /**
     * name of the property that changed. May be null, if not known.
     * @serial
     */
    private String propertyName;
    /**
     * New value for property. May be null if not known.
     * @serial
     */
    private Object newValue;
    /**
     * Previous value for property. May be null if not known.
     * @serial
     */
    private Object oldValue;
    /**
     * Propagation ID. May be null.
     * @serial
     * @see #getPropagationId.
     */
    private Object propagationId;
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 
    /**
     * Gets the programmatic name of the property that was changed.
     *
     * @return The programmatic name of the property that was changed.
     * May be null if multiple properties have changed.
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * Sets the new value for the property, expressed as an Object.
     *
     * @return The new value for the property, expressed as an Object.
     * May be null if multiple properties have changed.
     */
    public Object getNewValue() {
        return newValue;
    }

    /**
     * Gets the old value for the property, expressed as an Object.
     *
     * @return The old value for the property, expressed as an Object.
     * May be null if multiple properties have changed.
     */
    public Object getOldValue() {
        return oldValue;
    }

    /**
     * Sets the propagationId object for the event.
     *
     * @param propagationId The propagationId object for the event.
     */
    public void setPropagationId(Object propagationId) {
        this.propagationId = propagationId;
    }

    /**
     * The "propagationId" field is reserved for future use. In Beans 1.0
     * the sole requirement is that if a listener catches a PropertyChangeEvent
     * and then fires a PropertyChangeEvent of its own, then it should
     * make sure that it propagates the propagationId field from its
     * incoming event to its outgoing event.
     *
     * @return the propagationId object associated with a bound/constrained
     * property update.
     */
    public Object getPropagationId() {
        return propagationId;
    }
    // </editor-fold> 
}
