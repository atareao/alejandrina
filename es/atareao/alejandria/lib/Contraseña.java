/*
 * Contraseña.java
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Protactino
 */
public class Contraseña{
    //
    //********************************CONSTANTES********************************
    //
    
    //
    // *********************************CAMPOS*********************************
    //
    private Date _fecha;
    private String _comentario;
    private String _password;
    private String _usuario;
    //
    //******************************CONSTRUCTORES*******************************
    //
    private void inicializa(){
        inicializa("Usuario","12345678",dateToString(Calendar.getInstance().getTime()),Calendar.getInstance().getTime());
    }
    private void inicializa(String usuario,String password){
        inicializa(usuario,password,dateToString(Calendar.getInstance().getTime()),Calendar.getInstance().getTime());
    }
    private void inicializa(String usuario,String password,String comentario){
        inicializa(usuario,password,comentario,Calendar.getInstance().getTime());
    }
    private void inicializa(String usuario,String password,String comentario,java.util.Date fecha){
        this.setUsuario(usuario);
        this.setPassword(password);
        this.setComentario(comentario);
        this.setFecha(fecha);    }
    /**
     * Crea una nueva instancia de Contraseña
     */
    public Contraseña() {
        inicializa();
    }
    public Contraseña(String usuario,String password){
        inicializa(usuario,password);
    }
    public Contraseña(String usuario,String password,String comentario){
        inicializa(usuario,password,comentario);
    }
    public Contraseña(String usuario,String password,String comentario,java.util.Date fecha){
        inicializa(usuario,password,comentario,fecha);
    }
    public Contraseña(Contraseña contraseña){
        inicializa(contraseña.getUsuario(),contraseña.getPassword(),contraseña.getComentario(),contraseña.getFecha());
    }
    //
    //**************************METODOS DE ACCESO*******************************
    //

    public java.util.Date getFecha() {
        return _fecha;
    }

    public void setFecha(java.util.Date fecha) {
        this._fecha = fecha;
    }

    public String getComentario() {
        return _comentario;
    }

    public void setComentario(String comentario) {
        this._comentario = comentario;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        this._password = password;
    }

    public String getUsuario() {
        return _usuario;
    }

    public void setUsuario(String usuario) {
        this._usuario = usuario;
    }

    //
    //********************************METODOS***********************************
    //
    @Override
    public String toString(){
        return this.getComentario()+" - "+dateToString(this.getFecha());
    }
    //
    //**************************METODOS AUXILIARES******************************
    //
    private static String dateToString(Date fecha){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        return sdf.format(fecha);
    }
    private static Date stringToDate(String fecha) throws ParseException{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        return sdf.parse(fecha);
    }
}
