/*
 * JMTable
 *
 * File created on 10-mar-2010
 * Copyright (c) 2009 Lorenzo Carbonell
 * email: lorenzo.carbonell.cerezo@gmail.com
 * website: http://www.atareao.es
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package es.atareao.alejandria.gui;

import es.atareao.alejandria.laf.JLAFTableCellRenderer;
import es.atareao.alejandria.laf.JLAFTableHeaderRenderer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

/**
 *
 * @author atareao
 */

public class JMTable extends JTable{
    private int _filaAnterior=-1;
    private int _filaActual = -1;
    private int _columnaAnterior=-1;
    private int _columnaActual=-1;
    private int _filaModificada=-1;
    private boolean _doingDragAndDrop=false;
    private String _searchFor="";
    private OperadorDeTablas _operador;
    private long _lap;
    private JMTable _table=this;

    //
    //******************************CONSTRUCTORES*******************************
    //
    public JMTable(){
        setUI(new DragDropRowTableUIOrdered());
        getTableHeader().setDefaultRenderer(new JLAFTableHeaderRenderer());
        setDefaultRenderer(Object.class, new JLAFTableCellRenderer());
        setIntercellSpacing(new Dimension(1, 1));
        setRowHeight(20);
        setShowHorizontalLines(true);
        setShowVerticalLines(true);
        setGridColor(Color.lightGray);
        setCellSelectionEnabled(true);
        getSelectionModel().addListSelectionListener(new SelectionListener(this));
        //
        addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(FocusEvent evt) {
                System.out.println("Fila actual = "+_filaActual);
                System.out.println("Fila anterior = "+_filaAnterior);
                System.out.println("Fila modificada = "+_filaModificada);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable table = (JTable)evt.getComponent();
                int row = rowAtPoint(evt.getPoint());
                int col = columnAtPoint(evt.getPoint());
                table.editCellAt(row, col);
                setRowSelectionInterval(row,row);
                _filaActual=row;
            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                if(_doingDragAndDrop){
                    int fromRow=((DragDropRowTableUIOrdered)getUI()).getFromRow();
                    int toRow=((DragDropRowTableUIOrdered)getUI()).getToRow();
                    saveRow(fromRow,getSelectedColumn());
                    saveRow(toRow,getSelectedColumn());
                }
                _doingDragAndDrop=false;
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                _doingDragAndDrop=true;
                stopCellEditing();
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                int nfila=getSelectedRow();
                int filas=getRowCount();
                if(((nfila+1)==filas)&&(evt.getKeyCode()==40)){//Tecla abajo
                    if((getValueAt(nfila,1)==null)||((String)getValueAt(nfila,1)).length()==0){
                        if(saveRow(nfila,getSelectedColumn())){
                            newRow();
                        }
                        return;
                    }
                    newRow();
                    return;
                }
                if(evt.getKeyCode()==127){
                    evt.consume();
                    int[] filass=getSelectedRows();
                    if(filass.length>0){
                        for(int seleccionado=filass.length-1;seleccionado>-1;seleccionado--){
                            int selectedRow=filass[seleccionado];
                            if(selectedRow>=0){
                                deleteRow(selectedRow);
                            }
                        }
                        refresca();
                    }
                    return;
                }
                if((getSelectedColumn()>-1)&&(getSelectedColumn()<getColumnCount()-1)&&(getColumn(getSelectedColumn())!=null)){
                    if(getColumn(getSelectedColumn()).getCellEditor() instanceof JBasicComboBoxTableCellEditor){
                        editCellAt(getSelectedRow(),getSelectedColumn());
                        if((evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)&&(_searchFor.length()>0)){
                            _searchFor=_searchFor.substring(0,_searchFor.length()-1);
                            return;
                        }
                        long now = new java.util.Date().getTime();
                        if(_lap+1000>now){
                            _searchFor+=evt.getKeyChar();
                        }else{
                            _searchFor=""+evt.getKeyChar();
                        }
                        _lap=now;
                        if(_searchFor.length()>=1){
                            for(Object elemento:((JBasicComboBoxTableCellEditor)getColumn(getSelectedColumn()).getCellEditor()).getElements()){
                                if(elemento.toString().toLowerCase().startsWith(_searchFor.toLowerCase())){
                                    ((JBasicComboBoxTableCellEditor)getColumn(getSelectedColumn()).getCellEditor()).setSelectedElement(elemento);
                                    //_searchFor="";
                                }
                            }
                        }
                        return;
                    }
                }
            }
        });
    }
    //
    //********************************METODOS***********************************
    //
    public void focusLost(){
        if(_filaActual!=-1){
            stopCellEditing();
            saveRow(_filaActual,_columnaActual);
        }
    }
    public void refresca(){
        this.getOperador().refresca();
    }

    public boolean saveRow(int row,int column){
        return this.getOperador().saveRow(row,column);
    }

    public boolean saveRow(){
        if((_filaActual>-1)&&(_filaActual<getRowCount()-1)){
            return this.getOperador().saveRow(this._filaActual,this._columnaActual);
        }
        return false;
    }
    public boolean newRow(){
        return this.getOperador().newRow();
    }

    public boolean deleteRow(int Row){
        return this.getOperador().deleteRow(Row);
    }


/**
  * If someone was editing the table, this method will stop the editing
  * and adjust the cell accordingly
  *
  * @return true if success
  * @see #com.sun.swing.table.TableCellEditor
  */
//-------------------------------------------------------------------------
    public boolean stopCellEditing() {
        try {
            int column = getEditingColumn();
            if (column > -1) {
                TableCellEditor thisCellEditor = getColumnModel().getColumn( column).getCellEditor();
                if ( thisCellEditor == null ) {
                    thisCellEditor = getDefaultEditor( getColumnClass(column) );
                }
                if ( thisCellEditor != null ) {
                    thisCellEditor.stopCellEditing();
                }
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public void setColumnWidth(int column,int width){
        getColumn(column).setMinWidth(width);
        getColumn(column).setMaxWidth(width);
        getColumn(column).setWidth(width);
        getColumn(column).setPreferredWidth(width);
    }
    public TableColumn getColumn(int column){
        return this.getColumnModel().getColumn(column);
    }

    public void setcenter(){
        this.getColumn(0).getCellRenderer();
    }

    /**
     * @return the _operador
     */
    public OperadorDeTablas getOperador() {
        return _operador;
    }

    /**
     * @param operador the _operador to set
     */
    public void setOperador(OperadorDeTablas operador) {
        this._operador = operador;
    }

    /**
     * @return the _doingDragAndDrop
     */
    public boolean isDoingDragAndDrop() {
        return _doingDragAndDrop;
    }

    /**
     * @param doingDragAndDrop the _doingDragAndDrop to set
     */
    public void setDoingDragAndDrop(boolean doingDragAndDrop) {
        this._doingDragAndDrop = doingDragAndDrop;
    }

    /**
     * @return the _columnaAnterior
     */
    public int getColumnaAnterior() {
        return _columnaAnterior;
    }

    /**
     * @param columnaAnterior the _columnaAnterior to set
     */
    public void setColumnaAnterior(int columnaAnterior) {
        this._columnaAnterior = columnaAnterior;
    }

    /**
     * @return the _columnaActual
     */
    public int getColumnaActual() {
        return _columnaActual;
    }

    /**
     * @param columnaActual the _columnaActual to set
     */
    public void setColumnaActual(int columnaActual) {
        this._columnaActual = columnaActual;
    }

    //
    //**************************METODOS AUXILIARES******************************
    //


    //
    //***************************CLASES AUXILIARES******************************
    //
    class SelectionListener implements ListSelectionListener {
        JMTable table;
        // It is necessary to keep the table since it is not possible
        // to determine the table from the event's source
        SelectionListener(JMTable table) {
            this.table = table;
        }
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getSource() == table.getSelectionModel() && table.getRowSelectionAllowed()) {
                setFilaAnterior(getFilaActual());
                setFilaActual(table.getSelectedRow());
                setColumnaAnterior(getColumnaActual());
                setColumnaActual(table.getSelectedColumn());
                System.out.println("Fila actual: "+getFilaActual());
                System.out.println("Fila anterior: "+getFilaAnterior());
                System.out.println("Columna actual: "+getColumnaActual());
                System.out.println("Columna anterior: "+getColumnaAnterior());
                if(getFilaModificada()!=-1){
                    if(saveRow(getFilaModificada(),getSelectedColumn())){
                        setFilaModificada(-1);
                    }else{
                        if(getFilaAnterior()>-1){
                            int filaAnterior=getFilaAnterior();
                            Object valorAnterior=table.getDefaultTableModel().getValueAt(filaAnterior,1);
                            System.out.println("La fila anterior es "+filaAnterior);
                            System.out.println("El valor anterior es "+valorAnterior);
                            if((valorAnterior==null)||(((String)valorAnterior).length()==0)){
                                table.getDefaultTableModel().removeRow(getFilaAnterior());
                                setFilaAnterior(-1);
                                setFilaModificada(-1);
                            }
                        }
                    }
                }
            }
        }
    }
    class Listener implements TableModelListener {
        JTable table;
        Listener(JTable table) {
            this.table = table;
        }
        @Override
        public void tableChanged(TableModelEvent e) {
            if(e.getType()==TableModelEvent.UPDATE){
                if (e.getFirstRow() != TableModelEvent.HEADER_ROW) {
                    setFilaModificada(e.getFirstRow());
                }
            }
        }
    }
    //
    //**************************METODOS DE ACCESO*******************************
    //
    public DefaultTableModel getDefaultTableModel(){
        return (DefaultTableModel)this.getModel();
    }
    
    public void setDefaultTableModel(DefaultTableModel defaultTableModel){
        defaultTableModel.addTableModelListener(new Listener(this));
        this.setModel(defaultTableModel);
    }

    /**
     * @return the _filaModificada
     */
    public int getFilaModificada() {
        return _filaModificada;
    }

    /**
     * @param filaModificada the _filaModificada to set
     */
    public void setFilaModificada(int filaModificada) {
        this._filaModificada = filaModificada;
    }

    /**
     * @return the _filaAnterior
     */
    public int getFilaAnterior() {
        return _filaAnterior;
    }

    /**
     * @param filaAnterior the _filaAnterior to set
     */
    public void setFilaAnterior(int filaAnterior) {
        this._filaAnterior = filaAnterior;
    }

    /**
     * @return the _filaActual
     */
    public int getFilaActual() {
        return _filaActual;
    }

    /**
     * @param filaActual the _filaActual to set
     */
    public void setFilaActual(int filaActual) {
        this._filaActual = filaActual;
    }

}
