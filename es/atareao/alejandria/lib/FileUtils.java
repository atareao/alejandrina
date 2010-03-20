/*
 * FileUtils.java
 * 
 * Created on 28-oct-2007, 13:25:57
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.atareao.alejandria.lib;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Propietario
 */
public class FileUtils {
    public static String readTextFromFile(File file) throws FileNotFoundException, IOException{
        //...checks on aFile are elided
        StringBuilder contents = new StringBuilder();
        //use buffering, reading one line at a time
        //FileReader always assumes default encoding is OK!
        BufferedReader input =  new BufferedReader(new FileReader(file));
        String line = null; //not declared within while loop
        /*
        * readLine is a bit quirky :
        * it returns the content of a line MINUS the newline.
        * it returns null only for the END of the stream.
        * it returns an empty String if two newlines appear in a row.
        */
        while (( line = input.readLine()) != null){
            contents.append(line);
            contents.append(System.getProperty("line.separator"));
        }
        input.close();
        return contents.toString();
    }
    public static void writeTextToFile(String text, File file) throws IOException{
        if (file == null) {
            throw new IllegalArgumentException("El archivo no puede ser nulo.");
        }
        //use buffering
        Writer  output = new BufferedWriter(new FileWriter(file));
        output.write(text);
        output.flush();
        output.close();
    }
    public static boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return (path.delete());
    }
    public static String readTextFromJar(String s) {
        InputStream is = null;
        BufferedReader br = null;
        StringBuffer sb=new StringBuffer();
        String nl="\n\r";
        String line;
        try { 
            is = FileUtils.class.getResourceAsStream(s);
            br = new BufferedReader(new InputStreamReader(is));
            while (null != (line = br.readLine())) {
                sb.append(nl);
                sb.append(line);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (is != null) {
                    is.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static boolean download(URL url, File dest) throws IOException {
        boolean resultado = false;
        InputStream in = null;
        in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        RandomAccessFile file = new RandomAccessFile(dest, "rw");
        byte[] buffer = new byte[4096];
        int read = 0;
        read = in.read(buffer);
        while (read != -1) {
            out.write(buffer, 0, read);
            read = in.read(buffer);
        }
        file.write(out.toByteArray());
        file.close();
        resultado = true;
        in.close();
        return resultado;
    }
    public static boolean move(File source,File dest){
        try {
            copy(source, dest);
            return source.delete();
        } catch (Exception ex) {
        }
        return false;
    }
    /*
     *public static void copyFile(File in, File out) 
        throws IOException 
    {
        FileChannel inChannel = new
            FileInputStream(in).getChannel();
        FileChannel outChannel = new
            FileOutputStream(out).getChannel();
        try {
            inChannel.transferTo(0, inChannel.size(),
                    outChannel);
        } 
        catch (IOException e) {
            throw e;
        }
        finally {
            if (inChannel != null) inChannel.close();
            if (outChannel != null) outChannel.close();
        }

     */  
    public static boolean copy(File source, File dest) throws IOException,FileNotFoundException{
        boolean resultado = false;
        FileChannel in = null;
        FileChannel out = null;
        try {
            in = new FileInputStream(source).getChannel();
            out = new FileOutputStream(dest).getChannel();
            long size = in.size();
            /*
             * Eliminado
             *
             * MappedByteBuffer buf = in.map(FileChannel.MapMode.READ_ONLY, 0, size);
             * out.write(buf);
             */
            /*
             * Añadido nuevo
             * 
             * in.transferTo(0, size, out);
             */
            /*
             * Nueva modificación
             * 
             * Daba problemas copiando ficheros grandes
             * inChannel.transferTo(0, size, outl);
             * Se cambia por el siguiente número mágico para Windows, 64Mb - 32Kb
             * Y se itera
             * 
             * long position = 0;
             * while ( position < size ){
             *      position += in.transferTo( position, maxCount, out);
             * }            
             * 
             * 
             */
            int maxCount = (64 * 1024 * 1024) - (32 * 1024);
            long position = 0;
            while ( position < size ){
                position += in.transferTo( position, maxCount, out);
            }            
            resultado = true;
        } catch (FileNotFoundException ex) {
            throw ex;
        } catch (IOException ex) {
           throw ex;
        }finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
        return resultado;
    }
    public static boolean rename(File source,File dest){
        return dest.renameTo(source);
    }
    /**
     * This method will get the path to the jar or class file no-matter where the jar is called from.
     * Get the applications path.
     * 
     * 
     * @return the applications path
     *
     */
    public static File getApplicationPath() {
        //This method will get the path to the jar or class file no-matter where the jar is called from.
        // Get the applications path.
        /*
        File appPath = new File(System.getProperty("java.class.path"));
        appPath = appPath.getCanonicalFile().getParentFile();
        return appPath.toString();
         */
        return new File(System.getProperty("user.dir"));
    }

    public static String getFileSeparator() {
        return System.getProperty("file.separator");
    }

    public static File addPathFile(File path, File file) {
        return new File(path.getAbsolutePath(), file.getName());
    }

    public static File addPathFile(String path, String file) {
        if (path.endsWith(getFileSeparator())) {
            return new File(path + file);
        } else {
            return new File(path + getFileSeparator() + file);
        }
    }

    public static File getAbsolutePathFromApplication(File file) {
        return addPathFile(getApplicationPath(), file);
    }

    public static File getAbsolutePathFromApplication(String fileName) {
        return addPathFile(getApplicationPath(), new File(fileName));
    }

    public static String getExtension(File file) {
        return getExtension(file.getName());
    }

    public static String getExtension(String fileName) {
        if(fileName.indexOf(".")>-1){
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return null;
    }

    public static String getFileNameWithoutExtension(File file) {
        return getFileNameWithoutExtension(file.getName());
    }

    public static String getFileNameWithoutExtension(String fileName) {
        if(fileName.lastIndexOf(".")>-1){
            return fileName.substring(0, fileName.lastIndexOf("."));
        }
        return fileName;
    }

    public static File getTempFile(String fileName) throws IOException {
        File tempDir = new File(System.getProperty("user.dir") + File.separator + "tmp");
        File tempFile = File.createTempFile(fileName, ".tmp", tempDir);
        tempFile.delete();
        return tempFile;

    }
    public static void upload(File src,File dst) throws MalformedURLException, UnknownHostException, IOException{
        String host=dst.toURI().toURL().getHost();
        int port=dst.toURI().toURL().getPort();
        Socket socket = new Socket(host, port);
        InputStream in = new BufferedInputStream(new FileInputStream(src));
        OutputStream out =new BufferedOutputStream(socket.getOutputStream());
        int buffer_size=socket.getSendBufferSize();
        byte[] buffer = new byte[buffer_size];
        while (true) {
            int nBytes = in.read(buffer, 0, buffer_size);
            if (nBytes < 0) {
                break;
            }
            out.write(buffer, 0, nBytes);
        }
        out.flush();
        out.close();
        in.close();
    }
}
