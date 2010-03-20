/*
 * JDefaultTree.java
 *
 * TODO: Descripcion
 *
 * Creado en 4 de enero de 2007, 20:39
 *
 * Copyright (C) 4 de enero de 2007, Protactino
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
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;


/**
 *
 * @author Protactino
 */
public class JDefaultTree extends JTree{
    //
    //********************************CONSTANTES********************************
    //
    
    //
    // *********************************CAMPOS*********************************
    //
    private DefaultMutableTreeNode _root;
    private DefaultMutableTreeNode _copiado=null;
    private boolean _cortar=false;
    //
    //******************************CONSTRUCTORES*******************************
    //
    /**
     * Crea una nueva instancia de JDefaultTree
     */
    public JDefaultTree(DefaultMutableTreeNode root){
        //super(new DefaultTreeModel(root));
        this.setRoot(root);        
        this.setSelectionModel(new DefaultTreeSelectionModel());
        this.getSelectionModel().setSelectionMode(javax.swing.tree.TreeSelectionModel.SINGLE_TREE_SELECTION);
    }
    public JDefaultTree() {
        this(new DefaultMutableTreeNode());
    }
    //
    //********************************METODOS***********************************
    //
    public DefaultMutableTreeNode getSelected(){
        return (DefaultMutableTreeNode) this.getSelectionPath().getLastPathComponent();
    }
    
    public void setSelected(DefaultMutableTreeNode seleccionado){
        if(seleccionado!=null){
            TreePath tp=new TreePath(seleccionado.getPath());
            if(tp!=null){
                this.scrollPathToVisible(tp);
                this.setSelectionPath(tp);
            }
        }
    }
    
    public DefaultMutableTreeNode getSelectedGrantParent(){
        return (DefaultMutableTreeNode) this.getSelectedParent().getParent();
    }
    
    public DefaultMutableTreeNode getSelectedParent(){
        return (DefaultMutableTreeNode) this.getSelected().getParent();
    }
    
    public Object getSelectedUserObject(){
        return this.getSelected().getUserObject();
    }
    
    public Object getSelectedGrantParentUserObject(){
        return this.getSelectedGrantParent().getUserObject();
    }

    public Object getSelectedParentUserObject(){
        return this.getSelectedParent().getUserObject();
    }
    //
    //***************************METODOS TREENODE*******************************
    //
    public DefaultMutableTreeNode add(DefaultMutableTreeNode nodo){
        if(nodo!=null){
            DefaultMutableTreeNode hijo=copyNode(nodo);
            DefaultMutableTreeNode padre=this.getSelected();
            if(padre!=null){
                int donde=this.searchPrevious(padre,hijo);
                ((DefaultTreeModel)this.getModel()).insertNodeInto(hijo,padre,donde);
                this.setSelected(hijo);
                return hijo;
            }
        }
        return null;
    }
    
    public DefaultMutableTreeNode add(Object userObject){
        DefaultMutableTreeNode hijo=new DefaultMutableTreeNode(userObject);
        return this.add(hijo);
    }
    
    public boolean contains(DefaultMutableTreeNode padre,Object userObject){
        if(indexOf(padre,userObject)!=-1){
            return true;
        }
        return false;
    }
    public DefaultMutableTreeNode edit(DefaultMutableTreeNode hijo){
        if(hijo!=null){
            remove();
            return add(hijo);
        }
        return null;
    }
    public DefaultMutableTreeNode edit(Object userObject){
        DefaultMutableTreeNode hijo=new DefaultMutableTreeNode(userObject);
        return this.edit(hijo);
    }
    public int getPathCount(){
        if(this.getSelectionPath()!=null){
            return this.getSelectionPath().getPathCount();
        }
        return -1;
    }
    public int indexOf(DefaultMutableTreeNode padre,Object userObject){
        if((padre!=null)&&(userObject!=null)){
            for(int contador=0;contador<padre.getChildCount();contador++){
                DefaultMutableTreeNode hijo=(DefaultMutableTreeNode)padre.getChildAt(contador);
                if(hijo.getUserObject().equals(userObject)){
                    return contador;
                }
            }
        }
        return -1;
    }
    
    public DefaultMutableTreeNode remove(){
        DefaultMutableTreeNode seleccionado=this.getSelected();
        DefaultMutableTreeNode padre=this.getSelectedParent();
        if(seleccionado!=null){
            ((DefaultTreeModel)this.getModel()).removeNodeFromParent(seleccionado);
            this.setSelected(padre);
            return seleccionado;
        }
        return null;
    }
    
    public int searchPrevious(DefaultMutableTreeNode padre,DefaultMutableTreeNode hijo){
        if((padre!=null)&&(hijo!=null)){
            if(padre.getChildCount()!=0){
                int contador=0;
                for(contador=0;contador<padre.getChildCount();contador++){
                    Object objeto=((DefaultMutableTreeNode)padre.getChildAt(contador)).getUserObject();
                    if(hijo.getUserObject().toString().compareTo(objeto.toString())<0){
                        return contador;
                    }
                }
                return contador;
            }
        }
        return 0;
    }
    
    //
    //**************************METODOS AUXILIARES******************************
    //
    private static DefaultMutableTreeNode copyNode(DefaultMutableTreeNode fromNode){
        DefaultMutableTreeNode toNode=null;
        if(fromNode!=null){
            toNode=(DefaultMutableTreeNode)fromNode.clone();
            for(int contador=0;contador<fromNode.getChildCount();contador++){
                DefaultMutableTreeNode hijo=(DefaultMutableTreeNode)fromNode.getChildAt(contador);
                toNode.insert(copyNode(hijo),contador);
            }
        }
        return toNode;
    }
    //
    //**************************METODOS DE ACCESO*******************************
    //

    public DefaultMutableTreeNode getRoot() {
        return _root;
    }

    public void setRoot(DefaultMutableTreeNode root) {
        this._root = root;
        this.setModel(new DefaultTreeModel((TreeNode)root));
    }

    public DefaultMutableTreeNode getCopiado() {
        return _copiado;
    }
    public DefaultMutableTreeNode getCopiadoParent() {
        return (DefaultMutableTreeNode)_copiado.getParent();
    }
    public DefaultMutableTreeNode getCopiadoGrantParent() {
        return (DefaultMutableTreeNode)_copiado.getParent().getParent();
    }
    public Object getCopiadoUserObject() {
        return this.getCopiado().getUserObject();
    }
    public Object getCopiadoParentUserObject() {
        return this.getCopiadoParent().getUserObject();
    }
    public Object getCopiadoGrantParentUserObject() {
        return this.getCopiadoGrantParent().getUserObject();
    }

    public void setCopiado(DefaultMutableTreeNode copiado) {
        this._copiado = copiado;
    }
    public void setCopiado(){
        this.setCopiado(this.getSelected());
    }
    
    public boolean isCortar() {
        return _cortar;
    }

    public void setCortar(boolean cortar) {
        this._cortar = cortar;
    }
}
