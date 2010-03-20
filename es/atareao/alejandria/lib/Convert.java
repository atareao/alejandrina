/*
 * Convert.java
 *
 * Created on 28 de junio de 2006, 21:46
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package es.atareao.alejandria.lib;

import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;

/**
 *
 * @author Protactino
 */
public class Convert {
    
    /** Creates a new instance of Convert */
    public Convert() {
    }
    //*************************************************************************
    //Conversiones a boolean
    //*************************************************************************
    // boolean valor
    // char valor
    // double valor
    // float valor
    // int valor
    // long valor
	// Object valor
	// String valor
    public static boolean toBoolean(Boolean valor){
    	return valor.booleanValue();
    }
    public static boolean toBoolean(char valor){
    	boolean converted=false;
    	if(toInt(valor)==0){
    		converted=true;
    	}
    	return converted;
    }
    public static boolean toBoolean(Double valor){
    	boolean converted=false;
    	if(valor==0){
    		converted=true;
    	}
    	return converted;
    }
    public static boolean toBoolean(float valor){
    	boolean converted=false;
    	if(valor==0){
    		converted=true;
    	}
    	return converted;
    }
    public static boolean toBoolean(int valor){
    	boolean converted=false;
    	if(valor==0){
    		converted=true;
    	}
    	return converted;
    }
    public static boolean toBoolean(long valor){
    	boolean converted=false;
    	if(valor==0){
    		converted=true;
    	}
    	return converted;
    }
    public static boolean toBoolean(Object valor) throws Exception{
        if(valor instanceof Boolean){
            Boolean converted=(Boolean)valor;
            return converted.booleanValue();
        }else{
            if(valor instanceof String){
                return toBoolean((String)valor);
            }else{
                throw new ClassCastException("The object is not an instance of boolean");
            }
        }
    }
    public static boolean toBoolean(String valor){
        if(valor!=null){
            if(valor.toLowerCase().equals("t")){
                return true;
            }else if(valor.toLowerCase().equals("f")){
                return false;
            }
            return Boolean.valueOf(valor.toLowerCase()).booleanValue();
        }else{
            return false;
        }
    }
    //*************************************************************************
    //Conversiones a char
    //*************************************************************************
    // boolean valor
    // char valor
    // double valor
    // float valor
    // int valor
    // long valor
	// Object valor
	// String valor
    public static char toChar(boolean valor){
    	char converted='0';
    	if(valor==false){
    		converted='1';
    	}
    	return converted;
    }
    public static char toChar(Character valor){
    	return valor.charValue();
    }
    public static char toChar(Double valor){
        char converted=toChar(toInt(valor));
        return converted;
    }
    public static char toChar(float valor){
        char converted=toChar(toInt(valor));
        return converted;
    }
    public static char toChar(int valor){
        char converted=(char)valor;
        return converted;
    }
    public static char toChar(long valor){
        char converted=toChar(toInt(valor));
        return converted;
    }
    public static char toChar(Object valor) throws Exception{
        if(valor instanceof Character){
            Character converted=(Character)valor;
            return converted.charValue();
        }else{
            throw new ClassCastException("The object is not an instance of char");
        }
    }
    public static char toChar(String valor){
        char converted=toChar(toInt(valor));
        return converted;
    }
    //*************************************************************************
    //Conversiones a double
    //*************************************************************************
    // boolean valor
    // char valor
    // double valor
    // float valor
    // int valor
    // long valor
	// Object valor
	// String valor
    public static double toDouble(boolean valor){
    	double converted=1;//false
    	if(valor==true){
    		converted =0;
    	}
    	return converted;
    }
    public static double toDouble(char valor){
    	return toDouble(toInt(valor));
    }
    public static double toDouble(Double valor){
    	return valor.doubleValue();
    }
    public static double toDouble(float valor){
    	Float converted=new Float(valor);
    	return converted.doubleValue();
    }
    public static double toDouble(int valor){
    	Integer converted=new Integer(valor);
    	return converted.doubleValue();
    }
    public static double toDouble(long valor){
    	Long converted=new Long(valor);
    	return converted.doubleValue();
    }
    public static double toDouble(Object valor){
        if(valor instanceof Double){
            return ((Double)valor).doubleValue();
        }
        if(valor instanceof String){
            String strValor=(String)valor;
            if((strValor==null)||(strValor.length()<=0)){
                return 0;
            }
            return toDouble(strValor);
        }
        return 0.0;
    }
    public static double toDouble(String valor){
        double ans=0;
        if((valor==null)||(valor.length()<=0)){
            return 0;
        }
        char coma=(new DecimalFormatSymbols(Locale.getDefault())).getDecimalSeparator();
        String modificado=valor.replace(coma,'.');
        try{
            ans=Double.parseDouble(modificado);
        }catch(Exception ex){
            ans=0;
        }
        return ans;
    }
    //*************************************************************************
    //Conversiones a float
    //*************************************************************************
    // boolean valor
    // char valor
    // double valor
    // float valor
    // int valor
    // long valor
	// Object valor
	// String valor
    public static float toFloat(boolean valor){
    	float converted=1;//false
    	if(valor==true){
    		converted =0;
    	}
    	return converted;
    }
    public static float toFloat(char valor){
    	return toFloat(toInt(valor));
    }
    public static float toFloat(double valor){
    	Double converted=new Double(valor);
    	return converted.floatValue();
    }
    public static float toFloat(Float valor){
    	return valor.floatValue();
    }
    public static float toFloat(int valor){
    	Integer converted=new Integer(valor);
    	return converted.floatValue();
    }
    public static float toFloat(long valor){
    	Long converted=new Long(valor);
    	return converted.floatValue();
    }
    public static float toFloat(Object valor) throws Exception{
        if(valor instanceof Float){
            Float converted=(Float)valor;
            return converted.floatValue();
        }else{
            throw new ClassCastException("The object is not an instance of Float");
        }
    }
    public static float toFloat(String valor){
        if(valor.compareTo("")==0){
            return 0;
        }
    	return Float.parseFloat(valor);
    }
    //*************************************************************************
    //Conversiones a int
    //*************************************************************************
    // boolean valor
    // char valor
    // double valor
    // float valor
    // int valor
    // long valor
	// Object valor
	// String valor
    public static int toInt(boolean valor){
    	int converted=1;//false
    	if(valor==true){
    		converted =0;
    	}
    	return converted;
    }
    public static int toInt(char valor){
    	int converted=(int)valor;
    	return converted;
    }
    public static int toInt(double valor){
    	Double converted=new Double(valor);
    	return converted.intValue();
    }
    public static int toInt(float valor){
    	Float converted=new Float(valor);
    	return converted.intValue();
    }
    public static int toInt(Integer valor){
    	return valor.intValue();
    }
    public static int toInt(long valor){
    	Long converted=new Long(valor);
    	return converted.intValue();
    }
    public static int toInt(Object valor){
        if(valor instanceof Integer){        
            Integer converted=(Integer)valor;
            return converted.intValue();
        } else if(valor instanceof String){
            String st=(String)valor;
            return toInt(st);
        }else{
            throw new ClassCastException("The object is not an instance of Integer");
        }
    }
    public static int toInt(String valor){
        if(valor.length()>0){
            return Integer.valueOf(valor);        
        }
        return 0;
    }
    //*************************************************************************
    //Conversiones a long
    //*************************************************************************
    // boolean valor
    // char valor
    // double valor
    // float valor
    // int valor
    // long valor
	// Object valor
	// String valor
    public static long toLong(boolean valor){
    	long converted=1;//false
    	if(valor==true){
    		converted =0;
    	}
    	return converted;
    }
    public static long toLong(char valor){
    	long converted=toLong(toInt(valor));
    	return converted;
    }
    public static long toLong(double valor){
    	Double converted=new Double(valor);
    	return converted.longValue();
    }
    public static long toLong(float valor){
    	Float converted=new Float(valor);
    	return converted.longValue();
    }
    public static long toLong(int valor){
		Integer converted=new Integer(valor);
    	return converted.longValue();
    }
    public static long toLong(Long valor){
    	return valor.longValue();
    }
    public static long toLong(Object valor){
        if(valor instanceof Long){
            Long converted=(Long)valor;
            return converted.longValue();
        }else{
            throw new ClassCastException("The object is not an instance of Long");
        }
    }
    public static long toLong(String valor){
        if((valor!=null)&&(!valor.equals(""))){
            return Long.valueOf(valor);
        }else{
            return 0;
        }
    }
    //*************************************************************************
    //Conversiones a Object
    //*************************************************************************
    // boolean valor
    // char valor
    // double valor
    // float valor
    // int valor
    // long valor
	// Object valor
	// String valor
    public static Object toObject(boolean valor){
    	return new Boolean(valor);
    }
    public static Object toObject(char valor){
    	return new Character(valor);
    }
    public static Object toObject(double valor){
    	return new Double(valor);
    }
    public static Object toObject(float valor){
    	return new Float(valor);
    }
    public static Object toObject(int valor){
    	return new Integer(valor);
    }
    public static Object toObject(long valor){
    	return new Long(valor);
    }
    public static Object toObject(String valor){
    	return new String(valor);
    }
    public static Object toObject(Date valor){
        return valor;
    }
    public static Object toObject(Object valor){
        return valor;
    }
    //*************************************************************************
    //Conversiones a String
    //*************************************************************************
    // boolean valor
    // char valor
    // double valor
    // float valor
    // int valor
    // long valor
	// Object valor
	// String valor
    public static String toString(boolean valor){
    	return (String.valueOf(valor)).toUpperCase();
    }
    public static String toString(char valor){
    	return String.valueOf(valor);
    }
    public static String toString(char[] valor){
    	return String.valueOf(valor);
    }
    public static String toString(char[] valor, int offset, int count){
    	return String.valueOf(valor,offset,count);
    }
    public static String toString(double valor){
    	return String.valueOf(valor);
    }
    public static String toString(float valor){
    	return String.valueOf(valor);
    }
    public static String toString(int valor){
    	return String.valueOf(valor);
    }
    public static String toString(long valor){
    	return String.valueOf(valor);
    }
	public static String toString(Object valor){
    	return String.valueOf(valor);
    }
    public static String toString(StringBuffer valor){
    	return valor.toString();
    }
    public static String toString(float valor, int decimales){
    	String converted=toString(valor);
    	String derecha=null;
    	String izquierda=null;
    	String coma=null;
    	int pos=-1;
    	if(converted.contains(".")==true){
    		pos=converted.indexOf(".");
    		coma=".";
    	}
    	if(converted.contains(",")==true){
    		pos=converted.indexOf(",");
    		coma=",";
    	}
    	if(pos>-1){
    		int intdesdederecha=converted.length()-pos;
    		izquierda=izquierdaCadena(converted,pos);
    		derecha=derechaCadena(converted,intdesdederecha);
    		if(derecha.length()>decimales){
    			derecha=izquierdaCadena(derecha,decimales);
    		}else{
    			if(derecha.length()<decimales){
    				derecha=derecha+repiteCadena("0",decimales-derecha.length());
    			}
                }
    	} else{
            izquierda=izquierdaCadena(converted,pos);
            derecha=repiteCadena("0",decimales);
    	}
    	converted=izquierda+coma+derecha;
    	return converted;    	
    }
    public static String toString(double valor,int decimales){
    	String converted=toString(valor);
    	String derecha=null;
    	String izquierda=null;
    	String coma=null;
    	int pos=-1;
    	if(converted.contains(".")==true){
    		pos=converted.indexOf(".");
    		coma=".";
    	}
    	if(converted.contains(",")==true){
    		pos=converted.indexOf(",");
    		coma=",";
    	}
    	if(pos>-1){
    		int intdesdederecha=converted.length()-pos;
    		izquierda=izquierdaCadena(converted,pos);
    		derecha=derechaCadena(converted,intdesdederecha-1);
    		if(derecha.length()>decimales){
    			derecha=izquierdaCadena(derecha,decimales);
    		}else{
    			if(derecha.length()<decimales){
    				derecha=derecha+repiteCadena("0",decimales-derecha.length());
    			}
                }
    	} else{
            izquierda=izquierdaCadena(converted,pos);
            derecha=repiteCadena("0",decimales);
    	}
    	converted=izquierda+coma+derecha;
    	return converted;    	
    }
    public static String toString(Vector vector){
        return toString(vector,"");
    }
    public static String toString(Vector vector,String separador){
        StringBuffer resultado=new StringBuffer();
        Iterator iterator=vector.iterator();
        while(iterator.hasNext()){
                resultado.append(toString(iterator.next()));
                if(iterator.hasNext()){
                    resultado.append(separador);
                }
        }
        return resultado.toString();
    }
    public static String toStringFormatted(String valor,String formato){
    	return rellenaCadena(valor,formato);
    }
    public static String toStringDateBc3(Date valor)throws ParseException{
        String converted=null;
        SimpleDateFormat sdf=new SimpleDateFormat("ddMMyyyy");
        converted=sdf.format(valor);
        return converted;
    }

    public static String toStringDateSql(Date valor){
        String converted=null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if(valor==null){
            valor=DateUtils.Ahora();
        }
        converted=sdf.format(valor);
        return converted;
    }
    public static String toString(Date valor){
    	String converted=null;
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
    	converted=sdf.format(valor);
    	return converted;
    }
    public static String toStringLong(Date valor){
    	String converted=null;
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
    	converted=sdf.format(valor);
    	return converted;
    }
    public static String toStringLongFormat(Date valor){
        String converted=null;
        SimpleDateFormat sdf=new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy ");
    	converted=sdf.format(valor);
    	return converted;
    }
    public static String toStringLocaleDate(Date valor){
    	String converted=null;
    	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    	converted=sdf.format(valor);
    	return converted;
    }
    public static String toStringLocaleTime(Date valor){
    	String converted=null;
    	SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
    	converted=sdf.format(valor);
    	return converted;
    }
    //*************************************************************************
    //Conversiones a Date
    //*************************************************************************
    public static Date toDate(Object valor){
        if(valor instanceof Date){
            Date converted=(Date)valor;
            return converted;
        }else{
            throw new ClassCastException("The object is not an instance of Date");
        }
    }
    public static Date toDateBc3(String valor)throws ParseException{
        if(valor.compareTo("")==0){
            valor="01011980";
        }
        Date converted=null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        if(valor.length()>6){
            sdf=new SimpleDateFormat("ddMMyyyy");
        }else{
            sdf=new SimpleDateFormat("ddMMyy");
        }
        converted=sdf.parse(valor);
        return converted;
    }
    public static Date toDate(String valor) throws ParseException{
        Date converted=null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        converted=sdf.parse(valor);
        return converted;
    }
    public static Date toDate(int dia, int mes, int año,int segundos, int minutos, int horas) throws ParseException{
    	String _dia=rellenaCadena(Convert.toString(dia),"00");
    	String _mes=rellenaCadena(Convert.toString(mes),"00");
    	String _año=rellenaCadena(Convert.toString(añoDe2a4(año)),"0000");
    	String _segundos=rellenaCadena(Convert.toString(segundos),"00");
    	String _minutos=rellenaCadena(Convert.toString(minutos),"00");
    	String _horas=rellenaCadena(Convert.toString(horas),"00");
    	StringBuffer st=new StringBuffer();
    	st.append(_año);
    	st.append(_mes);
    	st.append(_dia);
    	st.append(_horas);
    	st.append(_minutos);
    	st.append(_segundos);
    	return toDateLong(st.toString());
    }
    public static Date toDate(int dia, int mes, int año) throws ParseException{
    	String _dia=rellenaCadena(Convert.toString(dia),"00");
    	String _mes=rellenaCadena(Convert.toString(mes),"00");
    	String _año=rellenaCadena(Convert.toString(añoDe2a4(año)),"0000");
    	StringBuffer st=new StringBuffer();
    	st.append(_año);
    	st.append(_mes);
    	st.append(_dia);
    	return toDate(st.toString());
    }
    public static Date toDateLong(String valor) throws ParseException{
        Date converted=null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        converted=sdf.parse(valor);
        return converted;
    }
    public static Date toDateSql(String valor){
        try {
            Date converted = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            converted = sdf.parse(valor);
            return converted;
        } catch (ParseException ex) {
            return DateUtils.Ahora();
        } catch(NullPointerException ex){
            return DateUtils.Ahora();
        }
    }
    //*************************************************************************
    //Conversiones a Vector
    //*************************************************************************
    public static Vector toVector(String valor,String separador){
        String izquierda="";
        String derecha=valor;
        Vector <String> resultado=new Vector<String>();
        while(derecha.contains(separador)==true){
            int pos=derecha.indexOf(separador);
            izquierda=izquierdaCadena(derecha,pos);
            derecha=derechaCadena(derecha,derecha.length()-pos-1);//-1 para quitar el separador actual
            resultado.add(izquierda);
        }
        if(resultado.size()==0){
            resultado.add(valor);
        }
        return resultado;
    }
    public static Vector toVectorInt(String valor,String separador){
        String izquierda="";
        String derecha=valor;
        Vector <Integer> resultado=new Vector<Integer>();
        while(derecha.contains(separador)==true){
            int pos=derecha.indexOf(separador);
            izquierda=izquierdaCadena(derecha,pos);
            derecha=derechaCadena(derecha,derecha.length()-pos-1);//-1 para quitar el separador actual
            resultado.add(Convert.toInt(izquierda));
        }
        if(resultado.size()==0){
            resultado.add(Convert.toInt(valor));
        }
        return resultado;
    }
    //*************************************************************************
    //Mñtodos Auxiliares
    //*************************************************************************
    private static String rellenaCadena(String cadena,String formato){
        if(cadena.length()==formato.length()){
            return cadena;
        }
        String relleno=izquierdaCadena(formato,1);
        String resultado=new String();
        if(cadena.length()<formato.length()){
                resultado=repiteCadena(relleno,formato.length()-cadena.length())+cadena;
        }else{
                if(cadena.length()>formato.length()){
                    return cadena;
                }
        }
        return resultado;
    }
	public static String izquierdaCadena(String cadena,int longitud){
		String resultado;
		int inicio=0;
		int fin=longitud;
		if(cadena.length()>=longitud){
			resultado=cadena.substring(inicio,fin);
		}else{
			resultado=cadena;
		}
		return resultado;
	}
	public static String derechaCadena(String cadena, int longitud){
		String resultado;
		int inicio=cadena.length()-longitud;
		int fin=cadena.length();
		if(cadena.length()>=longitud){
			resultado=cadena.substring(inicio,fin);
		}else{
			resultado=cadena;
		}
		return resultado;
	}
	private static String medioCadena(String cadena, int inicio, int longitud){
		String resultado;
		int fin=inicio+longitud-1;
		if(cadena.length()>=fin){
			resultado=cadena.substring(inicio,fin);
		}else{
			resultado=cadena;
		}
		return resultado;
	}
	private static String repiteCadena(String cadena,int veces){
		StringBuffer sb=new StringBuffer();
		for (int i=0; i<veces; i++)
		{
			sb.append(cadena);
		}
		return sb.toString();
	}
	private static int añoDe2a4(int año){
            int resultado=0;
            if(año<100){
    		if(año<80){
    			resultado=2000+año;
    		}else{
    			resultado=1900+año;
    		}
    		return resultado;
            }else{
    		return año;
            }
        }
}
