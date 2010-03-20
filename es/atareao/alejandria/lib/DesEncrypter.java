/*
 * DesEncrypter.java
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
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 *
 * @author Protactino
 */
public class DesEncrypter {
    //
    //********************************CONSTANTES********************************
    //
    
    //
    // *********************************CAMPOS*********************************
    //
    Cipher ecipher;
    Cipher dcipher;
    //
    //******************************CONSTRUCTORES*******************************
    //
    /**
     * Crea una nueva instancia de DesEncrypter
     */
    public DesEncrypter(SecretKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        ecipher = Cipher.getInstance("DES");
        dcipher = Cipher.getInstance("DES");
        ecipher.init(Cipher.ENCRYPT_MODE, key);
        dcipher.init(Cipher.DECRYPT_MODE, key);
    }
    public DesEncrypter(String keyString) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException{
        SecretKeyFactory skf=SecretKeyFactory.getInstance("DES");
        byte[] utf8 = keyString.getBytes("UTF8");
        DESKeySpec dks=new DESKeySpec(utf8);
        SecretKey key=skf.generateSecret(dks);
        ecipher = Cipher.getInstance("DES");
        dcipher = Cipher.getInstance("DES");
        ecipher.init(Cipher.ENCRYPT_MODE, key);
        dcipher.init(Cipher.DECRYPT_MODE, key);
    }
    public DesEncrypter() throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException{
        String keyString="euldlmdc";
        SecretKeyFactory skf=SecretKeyFactory.getInstance("DES");
        byte[] utf8 = keyString.getBytes("UTF8");
        DESKeySpec dks=new DESKeySpec(utf8);
        SecretKey key=skf.generateSecret(dks);
        ecipher = Cipher.getInstance("DES");
        dcipher = Cipher.getInstance("DES");
        ecipher.init(Cipher.ENCRYPT_MODE, key);
        dcipher.init(Cipher.DECRYPT_MODE, key);
    }
    //
    //**************************METODOS DE ACCESO*******************************
    //
    
    //
    //********************************METODOS***********************************
    //
    public String encrypt(String str) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException{
        // Encode the string into bytes using utf-8
        byte[] utf8 = str.getBytes("UTF8");
        // Encrypt
        byte[] enc = ecipher.doFinal(utf8);
        // Encode bytes to base64 to get a string
        return Base64.encodeBytes(enc);
    }
    public String decrypt(String str) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
        // Decode base64 to get bytes
        byte[] dec = Base64.decode(str);
        // Decrypt
        byte[] utf8 = dcipher.doFinal(dec);
        // Decode using utf-8
        return new String(utf8, "UTF8");
    }    
    //
    //**************************METODOS AUXILIARES******************************
    //
}