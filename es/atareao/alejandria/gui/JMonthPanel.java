/*
 * JMonthPanel.java
 *
 * Created on 21 de agosto de 2007, 19:44
 */

package es.atareao.alejandria.gui;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import es.atareao.alejandria.lib.DateUtils;
import es.atareao.alejandria.lib.Suceso;

/**
 *
 * @author  Propietario
 */
public class JMonthPanel extends javax.swing.JPanel {
    

    /** Creates new form JMonthPanel */
    public JMonthPanel() {
        initComponents();
        this.setFecha(DateUtils.Ahora());
        
    }
    private void ejecutaEvento(java.beans.PropertyChangeEvent evt, int semana) {                                          
// TODO: Agrege su codigo aqui:
        if(evt.getPropertyName().equals("selectedWeek")){
            this.setSelectedWeek(semana);        
        }
        if(evt.getPropertyName().equals("selectedEvent")){
            this.firePropertyChange("selectedEvent",this._selectedEvent,evt.getNewValue());
            this._selectedEvent = evt.getNewValue();
        }        
        if(evt.getPropertyName().equals("selectedDate")){
            this.firePropertyChange("selectedDate",this.getSelectedDate(),evt.getNewValue());
            this.setSelectedDate((Date)evt.getNewValue());
        }
        
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" C�digo Generado  ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jTitulos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jWeekCell1 = new es.atareao.alejandria.gui.JWeekCell();
        jWeekCell2 = new es.atareao.alejandria.gui.JWeekCell();
        jWeekCell3 = new es.atareao.alejandria.gui.JWeekCell();
        jWeekCell4 = new es.atareao.alejandria.gui.JWeekCell();
        jWeekCell5 = new es.atareao.alejandria.gui.JWeekCell();
        jWeekCell6 = new es.atareao.alejandria.gui.JWeekCell();

        setLayout(new java.awt.GridLayout(0, 1));

        jTitulos.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setBackground(new java.awt.Color(231, 238, 236));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel1.setForeground(new java.awt.Color(63, 125, 145));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Semana");
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(63, 125, 145), 1, true));
        jLabel1.setOpaque(true);
        jTitulos.add(jLabel1);

        jLabel2.setBackground(new java.awt.Color(231, 238, 236));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel2.setForeground(new java.awt.Color(63, 125, 145));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Lunes");
        jLabel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(63, 125, 145), 1, true));
        jLabel2.setOpaque(true);
        jTitulos.add(jLabel2);

        jLabel3.setBackground(new java.awt.Color(231, 238, 236));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel3.setForeground(new java.awt.Color(63, 125, 145));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Martes");
        jLabel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(63, 125, 145), 1, true));
        jLabel3.setOpaque(true);
        jTitulos.add(jLabel3);

        jLabel4.setBackground(new java.awt.Color(231, 238, 236));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel4.setForeground(new java.awt.Color(63, 125, 145));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Mi\u00e9rcoles");
        jLabel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(63, 125, 145), 1, true));
        jLabel4.setOpaque(true);
        jTitulos.add(jLabel4);

        jLabel5.setBackground(new java.awt.Color(231, 238, 236));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel5.setForeground(new java.awt.Color(63, 125, 145));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Jueves");
        jLabel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(63, 125, 145), 1, true));
        jLabel5.setOpaque(true);
        jTitulos.add(jLabel5);

        jLabel6.setBackground(new java.awt.Color(231, 238, 236));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel6.setForeground(new java.awt.Color(63, 125, 145));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Viernes");
        jLabel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(63, 125, 145), 1, true));
        jLabel6.setOpaque(true);
        jTitulos.add(jLabel6);

        jLabel7.setBackground(new java.awt.Color(255, 249, 231));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel7.setForeground(new java.awt.Color(63, 125, 145));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("S\u00e1bado");
        jLabel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(63, 125, 145), 1, true));
        jLabel7.setOpaque(true);
        jTitulos.add(jLabel7);

        jLabel8.setBackground(new java.awt.Color(255, 249, 231));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel8.setForeground(new java.awt.Color(63, 125, 145));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Domingo");
        jLabel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(63, 125, 145), 1, true));
        jLabel8.setOpaque(true);
        jTitulos.add(jLabel8);

        add(jTitulos);

        jWeekCell1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jWeekCell1PropertyChange(evt);
            }
        });

        add(jWeekCell1);

        jWeekCell2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jWeekCell2PropertyChange(evt);
            }
        });

        add(jWeekCell2);

        jWeekCell3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jWeekCell3PropertyChange(evt);
            }
        });

        add(jWeekCell3);

        jWeekCell4.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jWeekCell4PropertyChange(evt);
            }
        });

        add(jWeekCell4);

        jWeekCell5.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jWeekCell5PropertyChange(evt);
            }
        });

        add(jWeekCell5);

        jWeekCell6.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jWeekCell6PropertyChange(evt);
            }
        });

        add(jWeekCell6);

    }// </editor-fold>//GEN-END:initComponents

    private void jWeekCell6PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jWeekCell6PropertyChange
        this.ejecutaEvento(evt,6);
    }//GEN-LAST:event_jWeekCell6PropertyChange

    private void jWeekCell5PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jWeekCell5PropertyChange
        this.ejecutaEvento(evt,5);
    }//GEN-LAST:event_jWeekCell5PropertyChange

    private void jWeekCell4PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jWeekCell4PropertyChange
        this.ejecutaEvento(evt,4);
    }//GEN-LAST:event_jWeekCell4PropertyChange

    private void jWeekCell3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jWeekCell3PropertyChange
        this.ejecutaEvento(evt,3);
    }//GEN-LAST:event_jWeekCell3PropertyChange

    private void jWeekCell2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jWeekCell2PropertyChange
        this.ejecutaEvento(evt,2);
    }//GEN-LAST:event_jWeekCell2PropertyChange

    private void jWeekCell1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jWeekCell1PropertyChange
        this.ejecutaEvento(evt,1);
    }//GEN-LAST:event_jWeekCell1PropertyChange
    private void siSelecciona(int seleccionado){
        this.seleccionaOno(seleccionado,true);
    }
    private void noSelecciona(int seleccionado){
        this.seleccionaOno(seleccionado,false);
    }
    private void seleccionaOno(int seleccionado,boolean selecciona){
        switch (seleccionado){
            case 1:
                    jWeekCell1.setSelected(selecciona);
                    break;
            case 2:
                    jWeekCell2.setSelected(selecciona);
                    break;
            case 3:
                    jWeekCell3.setSelected(selecciona);
                    break;
            case 4:
                    jWeekCell4.setSelected(selecciona);
                    break;
            case 5:
                    jWeekCell5.setSelected(selecciona);
                    break;
            case 6:
                    jWeekCell6.setSelected(selecciona);
                    break;
        }
    }    
    public boolean add(Vector<Suceso> sucesos){
        for(Suceso suceso : sucesos){
            this.add(suceso);
        }
        return true;
    }
    public boolean add(Suceso suceso){
        if(DateUtils.monthOfYear(suceso.getDate())!=DateUtils.monthOfYear(this.getFecha())){
            return false;
        }
        int semana=DateUtils.weekOfMonth(suceso.getDate());
        if(DateUtils.dayOfWeek(DateUtils.firstDayOfMonth(this.getFecha()))>3){
            semana+=1;
        }
        switch (semana){
            case 1:
                return jWeekCell1.add(suceso);
            case 2:
                return jWeekCell2.add(suceso);
            case 3:
                return jWeekCell3.add(suceso);
            case 4:
                return jWeekCell4.add(suceso);
            case 5:
                return jWeekCell5.add(suceso);
            case 6:
                return jWeekCell6.add(suceso);
        }
        return false;
    }
    public boolean remove(Suceso suceso){
        if(DateUtils.monthOfYear(suceso.getDate())!=DateUtils.monthOfYear(this.getFecha())){
            return false;
        }
        int semana=DateUtils.weekOfMonth(suceso.getDate());
        if(DateUtils.dayOfWeek(DateUtils.firstDayOfMonth(this.getFecha()))>3){
            semana+=1;
        }
        switch (semana){
            case 1:
                return jWeekCell1.remove(suceso);
            case 2:
                return jWeekCell2.remove(suceso);
            case 3:
                return jWeekCell3.remove(suceso);
            case 4:
                return jWeekCell4.remove(suceso);
            case 5:
                return jWeekCell5.remove(suceso);
            case 6:
                return jWeekCell6.remove(suceso);
        }
        return false;
    }
    
    // Declaraci�n de varibales -no modificar//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jTitulos;
    private es.atareao.alejandria.gui.JWeekCell jWeekCell1;
    private es.atareao.alejandria.gui.JWeekCell jWeekCell2;
    private es.atareao.alejandria.gui.JWeekCell jWeekCell3;
    private es.atareao.alejandria.gui.JWeekCell jWeekCell4;
    private es.atareao.alejandria.gui.JWeekCell jWeekCell5;
    private es.atareao.alejandria.gui.JWeekCell jWeekCell6;
    // Fin de declaraci�n de variables//GEN-END:variables
    private int _selectedWeek;
    private Object _selectedEvent;
    private Date _fecha;
    private Date _selectedDate;

    public int getSelectedWeek() {
        return _selectedWeek;
    }

    public void setSelectedWeek(int selectedWeek) {
        this.noSelecciona(this._selectedWeek);
        this._selectedWeek = selectedWeek;
    }

    public Date getFecha() {
        return _fecha;
    }
    public void setFecha(String fecha){
        try {
            this.setFecha(DateUtils.toDate(fecha));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }
    public void setFecha(Date fecha) {
        this._fecha = DateUtils.goodDate(fecha);
        this.jWeekCell1.setMes(DateUtils.monthOfYear(fecha));
        this.jWeekCell2.setMes(DateUtils.monthOfYear(fecha));
        this.jWeekCell3.setMes(DateUtils.monthOfYear(fecha));
        this.jWeekCell4.setMes(DateUtils.monthOfYear(fecha));
        this.jWeekCell5.setMes(DateUtils.monthOfYear(fecha));
        this.jWeekCell6.setMes(DateUtils.monthOfYear(fecha));
        //
        Date primero=DateUtils.firstMondayOfTable(fecha);
        this.jWeekCell1.setFecha(primero);
        this.jWeekCell2.setFecha(DateUtils.addDays(primero,7));
        this.jWeekCell3.setFecha(DateUtils.addDays(primero,14));
        this.jWeekCell4.setFecha(DateUtils.addDays(primero,21));
        this.jWeekCell5.setFecha(DateUtils.addDays(primero,28));
        this.jWeekCell6.setFecha(DateUtils.addDays(primero,35));
    }

    public Object getSelectedEvent() {
        return _selectedEvent;
    }

    public void setSelectedEvent(Object selectedEvent) {
        this.firePropertyChange("selectedEvent",this._selectedEvent,selectedEvent);
        this._selectedEvent = selectedEvent;
        if(selectedEvent instanceof Suceso){
            Suceso suceso=(Suceso)selectedEvent;
            if(DateUtils.monthOfYear(suceso.getDate())==DateUtils.monthOfYear(this.getFecha())){
                switch (DateUtils.weekOfMonth(suceso.getDate())){
                    case 1:
                        jWeekCell1.setSelectedEvent(suceso);
                        break;
                    case 2:
                        jWeekCell2.setSelectedEvent(suceso);
                        break;
                    case 3:
                        jWeekCell3.setSelectedEvent(suceso);
                        break;
                    case 4:
                        jWeekCell4.setSelectedEvent(suceso);
                        break;
                    case 5:
                        jWeekCell5.setSelectedEvent(suceso);
                        break;
                    case 6:
                        jWeekCell6.setSelectedEvent(suceso);
                        break;
                }
            }
        }
    }

    public void setSelectedDate(Date selectedDate) {
        this._selectedDate = selectedDate;
    }
    public Date getSelectedDate(){
        return this._selectedDate;
    }
}