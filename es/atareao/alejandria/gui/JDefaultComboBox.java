/*
 * JDefaultComboBox.java
 *
 * TODO: Descripcion
 *
 * Creado en 10 de enero de 2007, 0:26
 *
 * Copyright (C) 10 de enero de 2007, Protactino
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */

package es.atareao.alejandria.gui;

//
//********************************IMPORTACIONES*********************************
//
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import es.atareao.alejandria.lib.StringUtils;
/**
 *
 * @author Protactino
 */
public class JDefaultComboBox extends JComboBox implements JComboBox.KeySelectionManager{
    //
    //********************************CONSTANTES********************************
    //
    
    //
    // *********************************CAMPOS*********************************
    //
    private String searchFor;
    private long lap;
    //
    //******************************CONSTRUCTORES*******************************
    //
    /**
     * Crea una nueva instancia de JDefaultComboBox
     */
    public JDefaultComboBox() {
        super();
        this.setModel(new SortedListComboBoxModel());
        this.setEditable(false);
        lap = new java.util.Date().getTime();
        
        setKeySelectionManager(this);
        JTextField  tf;
        if(getEditor() != null){
            tf = (JTextField)getEditor().getEditorComponent();
            if(tf != null){
                tf.setDocument(new CBDocument());        
                addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        JTextField tf = (JTextField)getEditor().getEditorComponent();
                        String text = tf.getText();
                        SortedListComboBoxModel aModel = getComboBoxModel();
                        String current;
                        for(int i = 0; i < aModel.getSize(); i++){
                            current = aModel.getElementAt(i).toString();
                            if(current.toLowerCase().startsWith(text.toLowerCase())){
                                tf.setText(current);
                                tf.setSelectionStart(text.length());
                                tf.setSelectionEnd(current.length());
                                break;
                            }
                        }
                    }
                });        //new AutocompleteComboBox(this);
            }
        }
        this.setElements(new Vector<DefaultComboBoxElement>());
    }
    public JDefaultComboBox(DefaultComboBoxElement[] objetos) {
        this();
        this.getComboBoxModel().addAll(objetos);
    }
    public JDefaultComboBox(Vector<DefaultComboBoxElement> objetos) {
        this((DefaultComboBoxElement[])objetos.toArray());
    }
    //
    //********************************METODOS***********************************
    //
    
    public void addElement(String key,String value){
        this.addElement(new DefaultComboBoxElement(key,value));
    }
    public void addElement(DefaultComboBoxElement objeto){
        this.getComboBoxModel().addElement(objeto);
    }
    public DefaultComboBoxElement getElementAt(int indice){
        return (DefaultComboBoxElement)this.getComboBoxModel().getElementAt(indice);
    }
    
    public DefaultComboBoxElement getElement(String key){
        for(int contador=0;contador<this.getComboBoxModel().getSize();contador++){
            if(this.getElementAt(contador).getKey().equals(key)){
                return this.getElementAt(contador);
            }
        }
        return null;
    }
    public int getIndexOf(String  key){
        for(int contador=0;contador<this.getComboBoxModel().getSize();contador++){
            if(this.getElementAt(contador).getKey().equals(key)){
                return contador;
            }
        }
        return -1;
    }
    
    public int getIndexOf(DefaultComboBoxElement objeto){
        if(objeto!=null){
            return this.getIndexOf(objeto.getKey());
        }
        return -1;  
    }/*
    @Override
    public DefaultComboBoxElement getSelectedItem() {
        return this.getElementAt(this.getSelectedIndex());
    }
*/
    public int getListSize() {
        return this.getComboBoxModel().getSize();
    }
    public void removeAllElements() {
        this.getComboBoxModel().removeAllElements();
    }
    public void removeElement(DefaultComboBoxElement objeto) {
        this.getComboBoxModel().removeElement(objeto);
    }
    public void removeElement(String key) {
        this.getComboBoxModel().removeElement(this.getElement(key));
    }
    public void removeElementAt(int indice) {
        this.getComboBoxModel().removeElementAt(indice);
    }
    /*
    @Override       
    public void setSelectedItem(Object element){
        if(element instanceof DefaultComboBoxElement){
            int seleccionado=this.getIndexOf((DefaultComboBoxElement)element);
            if(seleccionado>-1){
                super.setSelectedIndex(seleccionado);
            }
        }else{
            if(element instanceof String){
                int seleccionado=this.getIndexOf((String)element);
                if(seleccionado>-1){
                    super.setSelectedIndex(seleccionado);
                    //this.setSelectedIndex(seleccionado);
                }
            }
        }
    }*/
    public void setElements(DefaultComboBoxElement[] elements){
        this.setModel(new SortedListComboBoxModel());
        this.getComboBoxModel().addAll(elements);
    }
    public void setElements(Vector<DefaultComboBoxElement> elements){
        this.setElements(toDefaultComboBoxElementArray(elements.toArray()));
    }
    public void setSelectedKey(String key){
        int index=this.getIndexOf(key);
        this.setSelectedIndex(index);
    }
    public String getSelectedKey(){
        if(this.getSelectedItem()==null){
            return null;
        }
        return ((DefaultComboBoxElement)this.getSelectedItem()).getKey();
    }
    //
    //**************************METODOS AUXILIARES******************************
    //
    private SortedListComboBoxModel getComboBoxModel(){
        return (SortedListComboBoxModel)this.getModel();
    }
    public int buscaAnterior(DefaultComboBoxElement objeto){
        int contador=0;
        if (objeto!=null){
            for(contador=0;contador<this.getComboBoxModel().getSize();contador++){
                Object otro=this.getComboBoxModel().getElementAt(contador);
                if(StringUtils.sinAcentos(otro.toString()).compareTo(StringUtils.sinAcentos(objeto.toString()))>0){
                    return contador;
                }
            }
        }
        return contador;
    }    
    private DefaultComboBoxElement[] toDefaultComboBoxElementArray(Object[] objetos){
        DefaultComboBoxElement[] resultado=new DefaultComboBoxElement[objetos.length];
        for(int contador=0;contador<objetos.length;contador++){
            resultado[contador]=(DefaultComboBoxElement)objetos[contador];
        }
        return resultado;
    }
    public class CBDocument extends PlainDocument{
        @Override
        public void insertString(int offset, String str, AttributeSet a) throws BadLocationException{
            if (str==null) return;
            super.insertString(offset, str, a);
            if(!isPopupVisible() && str.length() != 0) fireActionEvent();
        }
    }    
    public int selectionForKey(char aKey, ComboBoxModel aModel){
        long now = new java.util.Date().getTime();
        if (searchFor!=null && aKey==KeyEvent.VK_BACK_SPACE && searchFor.length()>0){
            searchFor = searchFor.substring(0, searchFor.length() -1);
        }else{
            if(lap + 1000 < now)
                searchFor = "" + aKey;
            else
                searchFor = searchFor + aKey;
        }
        lap = now;
        String current;
        for(int i = 0; i < aModel.getSize(); i++){
            current = aModel.getElementAt(i).toString().toLowerCase();
            if (current.toLowerCase().startsWith(searchFor.toLowerCase())) return i;
        }
        return -1;
    }
    //
    //**************************METODOS DE ACCESO*******************************
    //
   
}
