/*
 * SotedListModel.java
 *
 * Created on 24 de agosto de 2007, 8:12
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
//
//***********************************PAQUETE************************************
//

package es.atareao.alejandria.gui;

//
//********************************IMPORTACIONES*********************************
//
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;
import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;

/**
 *
 * @author Propietario
 */
public class SortedListComboBoxModel extends AbstractListModel implements MutableComboBoxModel{
    //
    //********************************CONSTANTES********************************
    //
    
    //
    // *********************************CAMPOS*********************************
    //
    private SortedSet<Object> _model;
    private Object _selectedElement;
    
    //
    //******************************CONSTRUCTORES*******************************
    //
    
    /** Creates a new instance of SotedListModel */
    public SortedListComboBoxModel() {
        this.setModel(new TreeSet<Object>());
        
    }
    public SortedListComboBoxModel(Object elements[]) {
        this();
        this.addAll(elements);
    }
    public SortedListComboBoxModel(Vector<Object> elements) {
        this(elements.toArray());
    }
    
    //
    //********************************METODOS***********************************
    //

    public int getSize() {
        return this.getModel().size();
    }

    public Object getElementAt(int index) {
        if((this.getModel().size()>0)&&(this.getModel().size()>index)&&(index>-1)){
            return this.getModel().toArray()[index];
        }
        return null;
    }
    public void addElement(Object element) {
        if (this.getModel().add(element)) {
            fireContentsChanged(this, 0, getSize());
        }
    }

    public void addAll(Object elements[]) {
        Collection<Object> c = Arrays.asList(elements);
        this.getModel().addAll(c);
        fireContentsChanged(this, 0, getSize());
    }

    public void clear() {
        this.getModel().clear();
        fireContentsChanged(this, 0, getSize());
    }
    
    public void removeAllElements(){
        this.clear();
    }

    public boolean contains(Object element) {
        return this.getModel().contains(element);
    }
    
    public int getElementIndex(Object element){
        Object[] elementos=this.getModel().toArray();
        for(int contador=0;contador<elementos.length;contador++){
            if(element.equals(elementos[contador])){
                return contador;
            }
        }
        return -1;
    }

    public Object firstElement() {
        // Return the appropriate element
        return this.getModel().first();
    }

    public Iterator iterator() {
        return this.getModel().iterator();
    }

    public Object lastElement() {
        // Return the appropriate element
        return this.getModel().last();
    }

    public void removeElement(Object element) {
        boolean removed = this.getModel().remove(element);
        if (removed) {
            fireContentsChanged(this, 0, getSize());
        }
    }
    public void insertElementAt(Object obj, int index) {
        this.addElement(obj);
    }

    public void removeElementAt(int index) {
        this.removeElement(this.getElementIndex(index));
    }

    public void setSelectedItem(Object anItem) {
        this._selectedElement=anItem;
    }

    public Object getSelectedItem() {
        return this._selectedElement;
    }
    //
    //**************************METODOS AUXILIARES******************************
    //
    
    //
    //**************************METODOS DE ACCESO*******************************
    //
    private SortedSet<Object> getModel() {
        return _model;
    }

    private void setModel(SortedSet<Object> model) {
        this._model = model;
    }
}
