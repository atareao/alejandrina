/*
 * ***********************Software description*********************************
 * NumericTextField.java
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

/*  Nota:
 * al modificar el valor dispara la propiedad "value"
 */ 



//
//********************************IMPORTACIONES*********************************
//
import java.awt.Component;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.event.FocusEvent;
import java.text.DecimalFormat;
import javax.swing.JFormattedTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import es.atareao.alejandria.lib.Convert;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/**
 *
 * @author Lorenzo Carbonell
 */
public class JNumericField extends JFormattedTextField implements JFieldMov {
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 
    private static final long serialVersionUID=0L;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    public JNumericField() {
        super(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("0"))),0.0);
        this.setFormat(new DecimalFormat("0"));
        this.setPreferredSize(new Dimension(50,20));
        this.getDocument().addDocumentListener(new DocumentEventListener());
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if(evt.getKeyChar()==KeyEvent.VK_BACK_SPACE){                    
                    if((getText().length()==1)&&(getCaretPosition()==1)){
                        evt.consume();
                        setText("0");
                        setCaretPosition(1);
                    }
                }
            }
        });
    }
    public JNumericField(int decimals){
        this();
        this.setDecimals(decimals);
    }

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 
    private class DocumentEventListener implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) {
            firePropertyChange("value",null,getValue());
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            firePropertyChange("value",null,getValue());
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            firePropertyChange("value",null,getValue());
        }
    }
    @Override
    public void processFocusEvent(FocusEvent fe) { 
        super.processFocusEvent(fe); 
        Component focusOwner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner(); 
        if (isDisplayable() && fe.getID()==FocusEvent.FOCUS_GAINED && focusOwner==this) { 
            if(this.isOnFocusSelectAll()){
                this.setSelectionStart(0);
                this.setSelectionEnd(this.getText().length());
            }
        }   
    }
    @Override
    protected boolean processKeyBinding(final KeyStroke ks,final KeyEvent e, final int condition, final boolean pressed) {
        if (hasFocus()) {
            return super.processKeyBinding(ks, e, condition, pressed);
        } else {
            requestFocus();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    processKeyBinding(ks, e, condition, pressed);
                }
            });
        return true;
        }
    }
    private String fill(int n) {
        StringBuffer sb = new StringBuffer();
        for (int contador = 0; contador < n; contador++) {
            sb.append("0");
        }
        return sb.toString();
    }

    private DecimalFormat getDecimalFormat() {
        String df = "0";
        if (this.getDecimals() > 0) {
            df += "." + fill(this.getDecimals());
        }
        if (this.getTimesTen() > 0) {
            df += "E" + fill(this.getTimesTen());
        }
        return new DecimalFormat(df);
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 
    private int _decimals;
    private int _timesTen;
    private boolean _onFocusSelectAll=true;
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 

    // Methods to get the field value
    public int getDecimals() {
        return _decimals;
    }

    public void setDecimals(int decimals) {
        this._decimals = decimals;
        this.setFormat(this.getDecimalFormat());
    }

    public int getTimesTen() {
        return _timesTen;
    }

    public void setTimesTen(int timesTen) {
        this._timesTen = timesTen;
        this.setFormat(this.getDecimalFormat());
    }

    private void setFormat(DecimalFormat format) {
        if (format == null) {
            format=this.getDecimalFormat();
        }
        NumberFormatter nf = new NumberFormatter(format);
        nf.setAllowsInvalid(false);
        this.setFormatterFactory(new DefaultFormatterFactory(nf, nf, nf));
    }
    @Override
    public void setValue(Object value){
        super.setValue(Convert.toDouble(value));
        this.setText(this.getDecimalFormat().format(Convert.toDouble(value)));
    }
    public long getLong(){
        return Convert.toLong(this.getText());
    }
    public void setDouble(String value){
        this.setValue(Convert.toString(value));
    }
    public void setDouble(double value){
        this.setValue(value);
    }
    public double getDouble(){
        return Convert.toDouble(this.getText());
    }
    public String getString(){
        return this.getText();
    }

    public boolean isOnFocusSelectAll() {
        return _onFocusSelectAll;
    }

    public void setOnFocusSelectAll(boolean onFocusSelectAll) {
        this._onFocusSelectAll = onFocusSelectAll;
    }
    // </editor-fold> 
}
