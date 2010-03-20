/*
 * NewInterface.java
 *
 * Created on 24 de agosto de 2007, 20:16
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package es.atareao.alejandria.lib;

import java.awt.Color;
import java.util.Date;

/**
 *
 * @author Propietario
 */
public interface Suceso extends Comparable {
    int compareTo(Object o);

    boolean equals(Object o);

    Date getDate();

    String getId();

    String getResumen();

    void setDate(Date date);

    void setId(String id);

    void setResumen(String resumen);

    String toString();
    
    Color getColor();
    
}
