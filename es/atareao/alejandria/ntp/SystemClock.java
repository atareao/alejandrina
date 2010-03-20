/*
 * SystemClock.java
 * 
 * Created on 11-nov-2007, 20:05:41
 * 
 * This code is copyright (c) Lorenzo Carbonell 2007
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * and open the template in the editor.
 */

package es.atareao.alejandria.ntp;

//
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Calendar;
//********************************IMPORTACIONES*********************************
import java.util.GregorianCalendar;
//
import es.atareao.alejandria.lib.Convert;
/**
 *
 * @author Propietario
 */
public class SystemClock {
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
    public static void updateTime(long offset) throws IOException, InterruptedException{
        setSystemTime(System.currentTimeMillis()+offset);
    }
    public static long getTime(){
        return System.currentTimeMillis();
    }
    public static int getHour(){
        return get(Calendar.HOUR_OF_DAY);
    }
    public static int getMinute(){
        return get(Calendar.MINUTE);
    }
    public static int getSecond(){
        return get(Calendar.SECOND);
    }
    public static int getMillisecond(){
        return get(Calendar.MILLISECOND);
    }
    //
    //**************************METODOS AUXILIARES******************************
    //
    private static int get(int field){
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return calendar.get(field);
    }
    private static void setSystemTime(long time) throws IOException, InterruptedException{
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(time);
        String comando="cmd";
        StringBuffer entrada=new StringBuffer();
        entrada.append("time ");
        entrada.append(calendar.get(Calendar.HOUR_OF_DAY));
        entrada.append(":");
        entrada.append(calendar.get(Calendar.MINUTE));
        entrada.append(":");
        entrada.append(calendar.get(Calendar.SECOND));
        entrada.append(",");
        entrada.append(calendar.get(Calendar.MILLISECOND));
        Process proceso = Runtime.getRuntime().exec(comando);
        BufferedOutputStream out = new BufferedOutputStream(proceso.getOutputStream());
        out.write(entrada.toString().getBytes());
        out.write("\r\n".getBytes());
        out.flush();
        out.close();
        proceso.waitFor();
        System.out.println(comando+" "+entrada.toString());
    }
    //
    //**************************METODOS DE ACCESO*******************************
    //

}
