/*
 * ***********************Software description*********************************
 * JDateChooserDialog.java
 *
 *
 * ***********************Software description*********************************
 *
 * Copyright (C) 7 de septiembre de 2008 - Lorenzo Carbonell
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
 *
 */
package es.atareao.alejandria.gui;

import java.awt.Component;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JPanel;
import es.atareao.alejandria.lib.Convert;
import es.atareao.alejandria.lib.DateUtils;
import java.beans.PropertyChangeEvent;

/**
 *
 * @author  Lorenzo Carbonell
 */
public class JDateChooserDialog extends JPanel {
    
    // <editor-fold defaultstate="collapsed" desc=" Constantes  ">
    public static final long serialVersionUID=0L;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Constructores  ">
    /** Creates new form JDateChooserDialog
     * @param component 
     */
    public JDateChooserDialog(Component component) {
        this.setLocation(component.getX(),component.getY());
        initComponents();
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jPropertyDateChanged(evt);
            }
        });
        this.setDate(DateUtils.Ahora());
    }
    private void jPropertyDateChanged(PropertyChangeEvent evt){
        if((evt.getPropertyName().equals("DayChanged"))){
            this.firePropertyChange("SelectedDateChanged",evt.getOldValue(),evt.getNewValue());
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jMonth = new es.atareao.alejandria.gui.JMonthChooser();
        jYear = new javax.swing.JSpinner();
        jDay = new es.atareao.alejandria.gui.JLittleMonth();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setMaximumSize(new java.awt.Dimension(250, 25));
        jPanel1.setMinimumSize(new java.awt.Dimension(250, 25));
        jPanel1.setPreferredSize(new java.awt.Dimension(250, 25));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jMonth.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jMonthPropertyChange(evt);
            }
        });
        jPanel1.add(jMonth);

        jYear.setMaximumSize(new java.awt.Dimension(75, 25));
        jYear.setMinimumSize(new java.awt.Dimension(75, 25));
        jYear.setPreferredSize(new java.awt.Dimension(75, 25));
        jYear.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jYearStateChanged(evt);
            }
        });
        jPanel1.add(jYear);

        add(jPanel1);

        jDay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDayPropertyChange(evt);
            }
        });
        add(jDay);
    }// </editor-fold>//GEN-END:initComponents

    private void jYearStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jYearStateChanged
        if(this.getDate()!=null){
            try {
                int year = Convert.toInt(this.jYear.getValue());
                int month = DateUtils.monthOfYear(this.getDate());
                int firstDay=1;
                Date tfecha = DateUtils.toDate(firstDay, month, year);
                int lastDay=DateUtils.dayOfMonth(DateUtils.lastDayOfMonth(tfecha));
                int day=DateUtils.dayOfMonth(this.jDay.getSelectedDay());
                if(day>lastDay){
                    day=lastDay;
                }
                
                Date fecha = DateUtils.toDate(day, month, year);
                Date oldFecha = this.getDate();
                //
                jMonth.setDate(fecha);
                jDay.setMonth(fecha);
                this._date = fecha;
                this.firePropertyChange("YearChanged", oldFecha, fecha);
            } catch (ParseException ex) {
                ErrorDialog.manejaError(ex);
            }
        }
    }//GEN-LAST:event_jYearStateChanged

    private void jDayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDayPropertyChange
        if(this.getDate()!=null){
            if(evt.getPropertyName().equals("ModifiedSelectedDay")){
                try {
                    int year = DateUtils.year(this.getDate());
                    int month = DateUtils.monthOfYear(this.getDate());
                    int day = DateUtils.dayOfMonth(this.jDay.getSelectedDay());
                    Date fecha = DateUtils.toDate(day, month, year);
                    Date oldFecha = this.getDate();
                    //
                    this._date = fecha;
                    this.firePropertyChange("DayChanged", oldFecha, fecha);
                } catch (ParseException ex) {
                    ErrorDialog.manejaError(ex);
                }
            }
        }
    }//GEN-LAST:event_jDayPropertyChange

    private void jMonthPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jMonthPropertyChange
        if(this.getDate()!=null){
            if(evt.getPropertyName().equals("SelectedMonthChanged")){
                try {
                    int year = DateUtils.year(this.getDate());
                    int month = DateUtils.monthOfYear(this.jMonth.getDate());
                    int firstDay=1;
                    Date tfecha = DateUtils.toDate(firstDay, month, year);
                    int lastDay=DateUtils.dayOfMonth(DateUtils.lastDayOfMonth(tfecha));
                    int day=DateUtils.dayOfMonth(this.jDay.getSelectedDay());
                    if(day>lastDay){
                        day=lastDay;
                    }
                    Date fecha = DateUtils.toDate(day, month, year);
                    Date oldFecha = this.getDate();
                    jDay.setMonth(fecha);
                    this._date = fecha;
                    this.firePropertyChange("MonthChanged", oldFecha, fecha);
                } catch (ParseException ex) {
                    ErrorDialog.manejaError(ex);
                }
            }
        }
    }//GEN-LAST:event_jMonthPropertyChange
    
   
    // <editor-fold defaultstate="collapsed" desc=" Metodos  ">
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Metodos auxiliares  ">
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Campos  ">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private es.atareao.alejandria.gui.JLittleMonth jDay;
    private es.atareao.alejandria.gui.JMonthChooser jMonth;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner jYear;
    // End of variables declaration//GEN-END:variables
    private Date _date;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Metodos de acceso  ">
    public Date getDate() {
        return _date;
    }

    public void setDate(Date date) {
        if(date==null){
            date=DateUtils.Ahora();
        }
        jYear.setValue(DateUtils.year(date));
        jMonth.setDate(date);
        jDay.setMonth(date);
        jDay.setSelectedDay(date);
        this._date = date;
    }
    // </editor-fold>
}
