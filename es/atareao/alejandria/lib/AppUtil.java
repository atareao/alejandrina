/*
 * AppUtil
 *
 * File created on 01-feb-2010
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

package es.atareao.alejandria.lib;

import es.atareao.alejandria.gui.ErrorDialog;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author atareao
 */

public class AppUtil {
    //
    //********************************CONSTANTES********************************
    //

    //
    // *********************************CAMPOS*********************************
    //

    //
    //******************************CONSTRUCTORES*******************************
    //

    //
    //********************************METODOS***********************************
    //

    //
    //**************************METODOS AUXILIARES******************************
    //

    //
    //**************************METODOS DE ACCESO*******************************
    //
    public static void verificaVersion(JFrame frame,String appName,String version){
        try {
            boolean nueva_version=false;
            File filedownload = FileUtils.addPathFile(FileUtils.getApplicationPath(), new File("version.log"));
            FileUtils.download(new URL("http://www.atareao.es/version.log"), filedownload);
            if((filedownload!=null)&&(filedownload.isFile())&&(filedownload.exists())){
                INIFile log = new INIFile(filedownload);
                String logversion=log.getStringProperty("Version", appName);
                if((logversion!=null)&&(logversion.length()>0)&&(logversion.indexOf(';')>0)&&(logversion.indexOf('.')>0)){
                    String cv=version.replace('.',';');
                    String[] data=logversion.split(";");
                    String sv=data[0].replace('.',';');
                    String[] this_version = cv.split(";");
                    String[] server_version = sv.split(";");
                    if (Convert.toInt(server_version[0]) > Convert.toInt(this_version[0])) {
                        nueva_version=true;
                    }
                    if (Convert.toInt(server_version[1]) > Convert.toInt(this_version[1])) {
                        nueva_version=true;
                    }
                    if (Convert.toInt(server_version[2]) > Convert.toInt(this_version[2])) {
                        nueva_version=true;
                    }
                    if(nueva_version){
                        if(JOptionPane.OK_OPTION==JOptionPane.showConfirmDialog(frame,"Existe una nueva versión, ¿Quieres descargarla?",appName,JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE)){
                            try {
                                Desktop.getDesktop().browse(new URI(data[1]));
                            } catch (URISyntaxException ex) {
                                ErrorDialog.manejaError(ex);
                            }
                        }
                        filedownload.delete();
                    }
                }
            }
        } catch (MalformedURLException ex) {
            ErrorDialog.manejaError(ex);
        } catch (IOException ex) {
            ErrorDialog.manejaError(ex);
        }
    }
}
