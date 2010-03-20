/*
 * ResourceLoader.java
 *
 * Copyright (c) 2010 Lorenzo Carbonell
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


package es.atareao.alejandria.lib;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import javax.swing.ImageIcon;
//
//********************************IMPORTACIONES*********************************
//

/**
 *
 * @author Protactino
 */
public class ResourceLoader {
    //
    //********************************CONSTANTES********************************
    //
    
    //
    // *********************************CAMPOS*********************************
    //
    
    private Object _objeto;
    //
    //******************************CONSTRUCTORES*******************************
    //
    /**
     * Crea una nueva instancia de ResourceLoader
     */
    public ResourceLoader(Object objeto) {
        this.setObjeto(objeto);
    }
    //
    //********************************METODOS***********************************
    //
    public static Image loadImage(Object objeto,String recurso){
        ResourceLoader rl=new ResourceLoader(objeto);
        return rl.loadImage(recurso);
    }
    public Image loadImage(String recurso){
        ImageIcon icon=new ImageIcon(getURL(recurso));
        return icon.getImage();
    }
    public ImageIcon loadImageIcon(String recurso){
        ImageIcon icon=new ImageIcon(getURL(recurso));
        return icon;
        
    }
    public InputStream getResourceAsStream(String recurso){
        return this.getObjeto().getClass().getResourceAsStream(recurso);
    }
    //
    //**************************METODOS AUXILIARES******************************
    //
    private URL getURL(String recurso) {
	//URLClassLoader urlLoader = (URLClassLoader)this.getObjeto().getClass().getClassLoader();
	//URL fileLocation = urlLoader.findResource(recurso);
	return this.getObjeto().getClass().getResource(recurso);
    }

    //
    //**************************METODOS DE ACCESO*******************************
    //

    public Object getObjeto() {
        return _objeto;
    }

    public void setObjeto(Object objeto) {
        this._objeto = objeto;
    }
    
}
