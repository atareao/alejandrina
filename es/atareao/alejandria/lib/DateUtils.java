/*
 * DateUtils.java
 *
 * Created on 22 de agosto de 2007, 20:25
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

package es.atareao.alejandria.lib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//
//********************************IMPORTACIONES*********************************
//

/**
 *
 * @author Propietario
 */
public class DateUtils {
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
    private static int get(Date date,int tipo){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(tipo);
    }
    public static int dayOfMonth(Date fecha){
        return get(fecha,Calendar.DAY_OF_MONTH);
    }
    public static int dayOfWeek(Date fecha){
        switch(get(fecha,Calendar.DAY_OF_WEEK)){
            case Calendar.MONDAY:
                return 0;
            case Calendar.TUESDAY:
                return 1;
            case Calendar.WEDNESDAY:
                return 2;
            case Calendar.THURSDAY:
                return 3;
            case Calendar.FRIDAY:
                return 4;
            case Calendar.SATURDAY:
                return 5;
            case Calendar.SUNDAY:
                return 6;
            default:
                return -1;
        }
    }
    public static int dayOfYear(Date fecha){
        return get(fecha,Calendar.DAY_OF_YEAR);
    }
    
    public static int monthOfYear(Date fecha){
        return get(fecha,Calendar.MONTH);
    }
    public static String monthOfYearString(Date fecha){
        switch (monthOfYear(fecha)){
            case 0:
                return "Enero";
            case 1:
                return "Febrero";
            case 2:
                return "Marzo";
            case 3:
                return "Abril";
            case 4:
                return "Mayo";
            case 5:
                return "Junio";
            case 6:
                return "Julio";
            case 7:
                return "Agosto";
            case 8:
                return "Septiembre";
            case 9:
                return "Octubre";
            case 10:
                return "Noviembre";
            case 11:
                return "Diciembre";
            default:
                return "";
        }
    }
    public static int year(Date fecha){
        return get(fecha,Calendar.YEAR);
    }
    public static int weekOfMonth(Date fecha){
        return get(fecha,Calendar.WEEK_OF_MONTH);
    }
    public static int weekOfYear(Date fecha){
        return get(fecha,Calendar.WEEK_OF_YEAR);
    }
    public static Date lastDayOfMonth(Date fecha){
        int dia=1;
        int mes=monthOfYear(fecha);
        int año=year(fecha);
        Calendar calendar=Calendar.getInstance();
        calendar.set(año,mes,dia,23,59,59);
        dia=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(año,mes,dia);
        return calendar.getTime();
    }
    public static Date firstDayOfMonth(Date fecha){
        int dia=1;
        int mes=monthOfYear(fecha);
        int año=year(fecha);
        Calendar calendar=Calendar.getInstance();
        calendar.set(año,mes,dia,0,0,0);
        return calendar.getTime();
    }
    public static Date firstMondayOfTable(Date fecha){
        Date primero=firstDayOfMonth(fecha);
        int dia=0-dayOfWeek(primero);
        return addDays(primero,dia);
    }
    public static Date addDays(Date fecha,int numDias){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR,numDias);
        return calendar.getTime();
    }
    public static Date addMonths(Date fecha,int numMeses){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.MONTH,numMeses);
        return calendar.getTime();
    }
    public static Date addYears(Date fecha,int numAños){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.YEAR,numAños);
        return calendar.getTime();
    }
    public static Date Ahora(){
        Calendar calendar=Calendar.getInstance();
        return calendar.getTime();
        
    }
    public static boolean areEqualsDates(Date date1,Date date2){
        if((date1==null)||(date2==null)){
            return false;
        }
        if(DateUtils.dayOfMonth(date1)==DateUtils.dayOfMonth(date2)){
            if(DateUtils.monthOfYear(date1)==DateUtils.monthOfYear(date2)){
                if(DateUtils.year(date1)==DateUtils.year(date2)){
                    return true;
                }
            }
        }
        return false;
    }
    public static Date goodDate(Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        int año=calendar.get(Calendar.YEAR);
        int mes=calendar.get(Calendar.MONTH);
        int dia=calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(año,mes,dia,12,0,0);
        return calendar.getTime();
        
    }
    public static int getHour(Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
    public static int getMinute(Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }
    public static int getSecond(Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }
    public static Date toDate(String valor) throws ParseException{
        Date converted=null;
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        converted=sdf.parse(valor);
        return converted;
    }
    public static String toString(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
    public static Date toDate(int dia, int mes, int año,int segundos, int minutos, int horas) throws ParseException{
        Calendar calendar=Calendar.getInstance();
        calendar.set(año,mes,dia,horas,minutos,segundos);
        return calendar.getTime();
    }
    public static Date toDate(int dia, int mes, int año) throws ParseException{
        Calendar calendar=Calendar.getInstance();
        calendar.set(año,mes,dia);
        return calendar.getTime();
    }    
    //
    //**************************METODOS AUXILIARES******************************
    //
    
    //
    //**************************METODOS DE ACCESO*******************************
    //
    
}
