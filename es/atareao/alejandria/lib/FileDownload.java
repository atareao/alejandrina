/*
 * FileDownload.java
 * 
 * Created on 28-oct-2007, 13:03:21
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.atareao.alejandria.lib;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Propietario
 */
public class FileDownload {
    private URL _url;
    private File _path;
    private File _file;
    
    public FileDownload(URL url,File path){
        this.setUrl(url);
        this.setPath(path);
        this.setFile(createFile());
    }
    public boolean save(){
        boolean resultado=false;
        InputStream in = null;
        try {
            in = new BufferedInputStream(this.getUrl().openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            RandomAccessFile file = new RandomAccessFile(this.getFile(), "rw");
            byte[] buffer = new byte[4096];
            int read=0;
            while(read!=-1){
                read = in.read(buffer);
                out.write(buffer, 0, read);
            }
            file.write(out.toByteArray());
            // close file
            file.close();
            resultado=true;
        } catch (IOException ex) {
            Logger.getLogger(FileDownload.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(FileDownload.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }
    private File createFile(){
        String path=this.getPath().getAbsolutePath();
        String fileName=this.getUrl().getFile();
        if(!path.endsWith(File.pathSeparator)){
            path=path+File.pathSeparator;
        }
        fileName=path+fileName;
        return new File(fileName);
    }
    public URL getUrl() {
        return _url;
    }

    public void setUrl(URL url) {
        this._url = url;
    }

    public File getPath() {
        return _path;
    }

    public void setPath(File path) {
        this._path = path;
    }

    public File getFile() {
        return _file;
    }

    public void setFile(File file) {
        this._file = file;
    }
    
/*
 // url to image
    URL url = new URL(args[0]);
    // input from image
    InputStream in = new BufferedInputStream(url.openStream());
    // downloaded bytes
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    // output file
    RandomAccessFile file = new RandomAccessFile("image","rw");
    // download buffer
    byte[] buffer = new byte[4096]; 
 
    // download the bytes
    for (int read=0;(read=in.read(buffer))!=-1;out.write(buffer,0,read));
    // write all data out to the file
    file.write(out.toByteArray());
    // close file
    file.close();
 */
}
