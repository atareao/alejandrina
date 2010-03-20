/*
 * PrintStreamToTextAreaAdapter.java
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
//
//********************************IMPORTACIONES*********************************
//
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class ReadWriteTextFile {
    /**
    * Fetch the entire contents of a text file, and return it in a String.
    * This style of implementation does not throw Exceptions to the caller.
    *
    * @param aFile is a file which already exists and can be read.
    */
    static public String getContents(File aFile) {
        //...checks on aFile are elided
        StringBuilder contents = new StringBuilder();
        try {
            //use buffering, reading one line at a time
            //FileReader always assumes default encoding is OK!
            BufferedReader input =  new BufferedReader(new FileReader(aFile));
            try {
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
            }finally{
                input.close();
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return contents.toString();
    }
}