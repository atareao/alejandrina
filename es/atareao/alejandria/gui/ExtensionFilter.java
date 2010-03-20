/*
 * ExtensionFilter.java
 *
 * TODO: Descripcion
 *
 * Creado en 27 de diciembre de 2006, 13:03
 *
 * Copyright (C) 27 de diciembre de 2006, Protactino
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
import java.io.File;
import javax.swing.filechooser.FileFilter;
/**
 *
 * @author Protactino
 */
public class ExtensionFilter extends FileFilter{
    //
    //********************************CONSTANTES********************************
    //
    
    //
    // *********************************CAMPOS*********************************
    //
    private String _extensions[];
    private String _description;
    //
    //******************************CONSTRUCTORES*******************************
    //
    /**
     * Crea una nueva instancia de ExtensionFilter
     * @param description file description
     * @param extension extension file
     */
    public ExtensionFilter(String description,String extension) {
        this(description,new String[]{extension});
    }
    public ExtensionFilter(String description,String extensions[]) {
        this.setDescription(description);
        this.setExtensions(extensions.clone());
    }
    //
    //********************************METODOS***********************************
    //
    public boolean accept(File file) {
        if(file.isDirectory()){
            return true;
        }
        int count =this.getExtensions().length;
        String path=file.getAbsolutePath();
        for(int i=0;i<count;i++){
            String ext=this.getExtensions()[i];
            if(path.endsWith(ext) && (path.charAt(path.length()-ext.length())=='.')){
                return true;
            }
        }
        return false;
    }

    //
    //**************************METODOS AUXILIARES******************************
    //
    
    //
    //**************************METODOS DE ACCESO*******************************
    //

    public String[] getExtensions() {
        return _extensions;
    }

    public void setExtensions(String[] extensions) {
        this._extensions = extensions;
    }

    public void setDescription(String description) {
        this._description = description;
    }

    public String getDescription() {
        return(this._description==null ? this.getExtensions()[0]:this._description);
    }    

    
}
