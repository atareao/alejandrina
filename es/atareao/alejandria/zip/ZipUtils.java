/*
 * ZipUtils.java
 *
 * Created on 26 de agosto de 2007, 1:07
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

package es.atareao.alejandria.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
//
//********************************IMPORTACIONES*********************************
import es.atareao.alejandria.lib.FileUtils;
//

/**
 *
 * @author Propietario
 */
public class ZipUtils {
    //
    //********************************CONSTANTES********************************
    //
    private static final int BUFFER_SIZE=8192 ;
    //
    // *********************************CAMPOS*********************************
    //
    
    //
    //******************************CONSTRUCTORES*******************************
    //
    
    //
    //********************************METODOS***********************************
    //
    public static void addFilesToNewZip(File zipFile,File file) throws IOException {
        addFilesToNewZip(zipFile,new File[]{file});
    }
    public static void addFilesToNewZip(File zipFile,File[] files) throws IOException {
        byte[] buf = new byte[BUFFER_SIZE];
        //
        zipFile.createNewFile();
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));

        // Compress the files
        for (int i = 0; i < files.length; i++) {
            InputStream in = new FileInputStream(files[i]);
            // Add ZIP entry to output stream.
            out.putNextEntry(new ZipEntry(files[i].getName()));
            // Transfer bytes from the file to the ZIP file
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            // Complete the entry
            out.closeEntry();
            in.close();
        }
        // Complete the ZIP file
        out.close();
    }        
    public static void addFilesToNewZip(File zipFile,File[] files,File[] fileNames) throws IOException {
        byte[] buf = new byte[BUFFER_SIZE];
        //
        zipFile.createNewFile();
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));

        // Compress the files
        for (int i = 0; i < files.length; i++) {
            InputStream in = new FileInputStream(files[i]);
            // Add ZIP entry to output stream.
            out.putNextEntry(new ZipEntry(fileNames[i].getName()));
            // Transfer bytes from the file to the ZIP file
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            // Complete the entry
            out.closeEntry();
            in.close();
        }
        // Complete the ZIP file
        out.close();
    }   
    public static void addFilesToExistingZip(File zipFile,File file) throws IOException {
        addFilesToExistingZip(zipFile,new File[]{file});
    }
    public static void addFilesToExistingZip(File zipFile,File[] files) throws IOException {
        // get a temp file
        File tempFile = File.createTempFile(zipFile.getName(), null);
        // delete it, otherwise you cannot rename your existing zip to it.
        tempFile.delete();
        boolean renameOk=zipFile.renameTo(tempFile);
        if (!renameOk){
            throw new RuntimeException("could not rename the file "+zipFile.getAbsolutePath()+" to "+tempFile.getAbsolutePath());
        }
        byte[] buf = new byte[BUFFER_SIZE];
        //
        ZipInputStream zin = new ZipInputStream(new FileInputStream(tempFile));
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));

        ZipEntry entry = zin.getNextEntry();
        while (entry != null) {
            String name = entry.getName();
            boolean notInFiles = true;
            for (File f : files) {
                if (f.getName().equals(name)) {
                    notInFiles = false;
                    break;
                }
            }
            if (notInFiles) {
                // Add ZIP entry to output stream.
                out.putNextEntry(new ZipEntry(name));
                // Transfer bytes from the ZIP file to the output file
                int len;
                while ((len = zin.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            }
            entry = zin.getNextEntry();
        }
        // Close the streams		
        zin.close();
        // Compress the files
        for (int i = 0; i < files.length; i++) {
            InputStream in = new FileInputStream(files[i]);
            // Add ZIP entry to output stream.
            out.putNextEntry(new ZipEntry(files[i].getName()));
            // Transfer bytes from the file to the ZIP file
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            // Complete the entry
            out.closeEntry();
            in.close();
        }
        // Complete the ZIP file
        out.close();
        tempFile.delete();
    }
    public static boolean containsFileExistingZip(File zipFile,File file) throws FileNotFoundException, IOException{
        String fileName=file.getName();
        ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFile));
        ZipEntry entry = zin.getNextEntry();
        while (entry != null) {
            String name = entry.getName();
            if(fileName.equals(name)){
                return true;
            }
            entry = zin.getNextEntry();
        }
        // Close the streams		
        zin.close();  
        return false;
    }
    public static void removeFileFromExistingZip(File zipFile,File file) throws IOException {
        // get a temp file
        File tempFile = File.createTempFile(zipFile.getName(), null);
        // delete it, otherwise you cannot rename your existing zip to it.
        tempFile.delete();
        FileUtils.copy(zipFile, tempFile);
        if(!tempFile.exists()){
            throw new RuntimeException("could not rename the file "+zipFile.getAbsolutePath()+" to "+tempFile.getAbsolutePath());
        }
        byte[] buf = new byte[BUFFER_SIZE];
        //
        ZipInputStream zin = new ZipInputStream(new FileInputStream(tempFile));
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));

        ZipEntry entry = zin.getNextEntry();
        while (entry != null) {
            String name = entry.getName();
            if(!file.getName().equals(name)){
                // Add ZIP entry to output stream.
                out.putNextEntry(new ZipEntry(name));
                // Transfer bytes from the ZIP file to the output file
                int len;
                while ((len = zin.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            }
            entry = zin.getNextEntry();
        }
        // Close the streams		
        zin.close();
        out.close();
        tempFile.delete();
    }
    public static boolean extractFileFromExistingZip(File zipFile,File file,File destination) throws FileNotFoundException, IOException{
        return extractFileFromExistingZip(zipFile.getAbsolutePath(),file.getName(),destination);
    }
    public static boolean extractFileFromExistingZip(String zipFileName,String fileName,File destination) throws FileNotFoundException, IOException{
        //File tempFile = File.createTempFile(zipFileName, null);
        // delete it, otherwise you cannot rename your existing zip to it.
        //tempFile.delete();
        
        // Create a ZipInputStream to read the zip file
        BufferedOutputStream dest = null;
        FileInputStream fis = new FileInputStream( zipFileName );
        ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
        // Loop over all of the entries in the zip file
        int count;
        byte data[] = new byte[ BUFFER_SIZE ];
        ZipEntry entry;
        while( ( entry = zis.getNextEntry() ) != null ){
            if( !entry.isDirectory()){
                String entryName = entry.getName();
                if(entryName.equals(fileName)){
                    String destFN = entry.getName();
                    // Write the file to the file system
                    FileOutputStream fos = new FileOutputStream( destination );
                    dest = new BufferedOutputStream( fos, BUFFER_SIZE );
                    while( (count = zis.read( data, 0, BUFFER_SIZE ) ) != -1 ){
                        dest.write( data, 0, count );
                    }
                    dest.flush();
                    dest.close();
                }
            }
        }
        zis.close();
        return true;
    }
    //
    //**************************METODOS AUXILIARES******************************
    //
    private static File getTempFile(String fileName) throws IOException{
        File tempDir=new File(System.getProperty("user.dir")+File.separator+"tmp");
        File tempFile = File.createTempFile(fileName,".tmp",tempDir);
        tempFile.delete();
        return tempFile;
        
    }
    //
    //**************************METODOS DE ACCESO*******************************
    //
    
}
