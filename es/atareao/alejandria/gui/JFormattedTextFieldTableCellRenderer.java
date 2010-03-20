/*
 * JCheckBoxTableCellRenderer.java
 *
 * Created on 16 de agosto de 2007, 22:08
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package es.atareao.alejandria.gui;

import java.awt.Color;
import java.awt.Component;
import java.text.ParseException;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Propietario
 */
public class JFormattedTextFieldTableCellRenderer extends DefaultTableCellRenderer{
    private final static long serialVersionUID=0L;
    /**
     * Creates a new instance of JCheckBoxTableCellRenderer
     * @param mask 
     */
    public JFormattedTextFieldTableCellRenderer(String mask) {
        setHorizontalAlignment(SwingConstants.CENTER);
        this.setMaskFormatter(mask);
    }
    /*
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Border mEmptyBorder = BorderFactory.createEmptyBorder();
        Border mHighLightBorder = UIManager.getBorder("Table.focusCellHighlightBorder");
        Color mFocusCellForeground=table.getSelectionForeground();
        Color mFocusCellBackground=table.getSelectionBackground();
        Color mCellForeground=table.getForeground();
        Color mCellBackground=table.getBackground();
        if(table.isCellEditable(row, column))    {
            if (isSelected) {
                setForeground(mFocusCellForeground);
                setBackground(mFocusCellBackground);
            }else{
                setForeground(mCellForeground);
                setBackground(mCellBackground);
            }
        }else{
            if (isSelected) {
                //setForeground(Color.GRAY);
                setForeground(mFocusCellBackground);
                setBackground(mFocusCellBackground);
            }else{
                //setForeground(Color.GRAY);
                setForeground(mCellBackground);
                setBackground(mCellBackground);
            }
            
        }
        if(hasFocus){
            setBorder(mHighLightBorder);
        }else{
            setBorder(mEmptyBorder);
        }
        this.setValue(value);
        return this;
    }    
    */

    private MaskFormatter _maskFormatter;

    public void setMaskFormatter(String mask) {
        try {
            this.setMaskFormatter(new MaskFormatter(mask));
        } catch (ParseException ex) {
            ErrorDialog.manejaError(ex);
        }
    }

    public MaskFormatter getMaskFormatter() {
        return _maskFormatter;
    }

    public void setMaskFormatter(MaskFormatter maskFormatter) {
        this._maskFormatter = maskFormatter;
        this._maskFormatter.setValueContainsLiteralCharacters(false);
    }


    @Override
    public void setValue(Object value) {
        super.setValue(value);
        String valor="";
        if(value!=null){
            if(value instanceof String){
                String conv=((String)value).trim();
                if(conv.length()>0){
                    try {
                        valor=this.getMaskFormatter().valueToString(conv);
                        this.setText(valor);
                        return;
                    } catch (ParseException ex) {
                        valor=conv;
                        //ErrorDialog.manejaError(ex);
                    }
                }
            }
        }
        this.setText(valor);
    }
}
