/*  (swing1.1) */
 
package es.atareao.alejandria.gui;


import java.io.*;
import java.util.*;
import javax.swing.tree.*;
//import jp.gr.java_conf.tame.swing.attribute.*;


/**
 * @version 1.0 01/11/99
 */
public class CheckNode extends DefaultMutableTreeNode {

    protected static final long serialVersionUID=0L;
    public final static int SINGLE_SELECTION = 0;
   public final static int DIG_IN_SELECTION = 4;
   protected int selectionMode;
   protected boolean isSelected;

  public CheckNode() {
    this(null);
  }

  public CheckNode(Object userObject) {
    this(userObject, true, false);
  }

  public CheckNode(Object userObject, boolean allowsChildren
                                    , boolean isSelected) {
    super(userObject, allowsChildren);
    this.isSelected = isSelected;
    setSelectionMode(DIG_IN_SELECTION);
  }


  public void setSelectionMode(int mode) {
    selectionMode = mode;
  }

  public int getSelectionMode() {
    return selectionMode;
  }

  public void setSelected(boolean isSelected) {
    this.isSelected = isSelected;
    
    if ((selectionMode == DIG_IN_SELECTION)
        && (children != null)) {
      Enumeration en = children.elements();      
      while (en.hasMoreElements()) {
        CheckNode node = (CheckNode)en.nextElement();
        node.setSelected(isSelected);
      }
    }
  }
  
  public boolean isSelected() {
    return isSelected;
  }


  // If you want to change "isSelected" by CellEditor,
  /*
  public void setUserObject(Object obj) {
    if (obj instanceof Boolean) {
      setSelected(((Boolean)obj).booleanValue());
    } else {
      super.setUserObject(obj);
    }
  }
  */

}


