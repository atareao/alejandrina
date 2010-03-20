/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.atareao.alejandria.tree;

//
//********************************IMPORTACIONES*********************************
//

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Lorenzo Carbonell
 */
public class Nodo {
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    public static boolean selectNode(JTree tree, Object userObject){
        DefaultTreeModel dtm=(DefaultTreeModel)tree.getModel();
        DefaultMutableTreeNode raiz=(DefaultMutableTreeNode)dtm.getRoot();
        return lookForObject(tree,raiz,userObject);
    }
    private static boolean lookForObject(JTree tree,DefaultMutableTreeNode dmtn, Object userObject){
        if(dmtn.getUserObject().equals(userObject)){
            tree.setSelectionPath(new TreePath(dmtn.getPath()));
            return true;
        }else{
            if(dmtn.getChildCount()>0){
                while(dmtn.children().hasMoreElements()){
                    DefaultMutableTreeNode hijo=(DefaultMutableTreeNode)dmtn.children().nextElement();
                    if(lookForObject(tree,hijo,userObject)){
                        return true;
                    }
                }                
            }
        }
        return false;
    }
   public static void addNodo(JTree tree,DefaultMutableTreeNode nodo) {
        if (nodo != null) {
            DefaultMutableTreeNode padre = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
            //DefaultMutableTreeNode hijo=new DefaultMutableTreeNode(userObject);
            ((DefaultTreeModel) tree.getModel()).insertNodeInto(nodo, padre, padre.getChildCount());
            TreePath tp = new TreePath(nodo.getPath());
            tree.scrollPathToVisible(tp);
            tree.setSelectionPath(tp);
        }
    }

    public static void dropNodo(JTree tree,DefaultMutableTreeNode nodo) {
        if (nodo != null) {
            TreePath tp = new TreePath(nodo.getParent());
            ((DefaultTreeModel) tree.getModel()).removeNodeFromParent(nodo);
            tree.scrollPathToVisible(tp);
            tree.setSelectionPath(tp);
        }
    }

    public static void editNodo(JTree tree,DefaultMutableTreeNode nodo, DefaultMutableTreeNode newnodo) {
        if ((nodo != null) && (newnodo != null)) {
            TreePath tp = new TreePath(nodo.getPath());
            if (tp != null) {
                ((DefaultMutableTreeNode) tp.getLastPathComponent()).setUserObject(newnodo.getUserObject());
                tree.scrollPathToVisible(tp);
                tree.setSelectionPath(tp);
            }
        }
    }

    public static Object getUserObjectInTreePath(TreePath tp, int elemento) {
        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) tp.getPathComponent(elemento);
        return dmtn.getUserObject();
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 

    // </editor-fold> 
}
