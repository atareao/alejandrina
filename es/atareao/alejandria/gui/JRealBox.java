/*
 * JNumericField.java
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


package es.atareao.alejandria.gui;

//
//********************************IMPORTACIONES*********************************
//
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import es.atareao.alejandria.lib.Convert;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.JTextField;

/**
 *
 * @author Protactino
 */
public class JRealBox extends JTextField{
    //
    //********************************CONSTANTES********************************
    //
    //
    public static final String EDIT_START="StartEditing";
    public static final String EDIT_STOP="StopEditing";
    //
    public static final int PROP_NONE=-1;
    public static final int PROP_RIGHT=0;
    public static final int PROP_LEFT=1;
    public static final int PROP_UP=2;
    public static final int PROP_DOWN=3;
    
    //
    // *********************************CAMPOS*********************************
    //
    private float _valor=0;
    private int _cPos=0;
    private int _comaPos=1;
    private String _tDerecha="";
    private String _tIzquierda="";
    private int _decimales=2;
    private boolean _focusWin=false;
    //
    //******************************CONSTRUCTORES*******************************
    //
    /**
     * Crea una nueva instancia de JNumericField
     */
    public JRealBox() {
        this.setText(this.getDefault());
        this.setPreferredSize(new Dimension(50,20));
        addAncestorListener( new AncestorListener(){
            public void ancestorAdded(AncestorEvent e){
                requestFocus();
            }
            public void ancestorMoved(AncestorEvent e){}
            public void ancestorRemoved(AncestorEvent e){}
        });        
    }
    //
    //********************************EVENTOS***********************************
    //
    @Override
    protected void processKeyEvent(KeyEvent e) {
        switch (e.getID()){
            case KeyEvent.KEY_TYPED:
                OnKeyPress(e);
                break;
            case KeyEvent.KEY_PRESSED:
                OnKeyDown(e);
                break;
            case KeyEvent.KEY_RELEASED:
                if(this.isFocusWin()){
                    //OnKeyDown(e);
                    this.setFocusWin(false);      
                    switch (e.getKeyCode()){
                        case KeyEvent.VK_0:
                        case KeyEvent.VK_1:
                        case KeyEvent.VK_2:
                        case KeyEvent.VK_3:
                        case KeyEvent.VK_4:
                        case KeyEvent.VK_5:
                        case KeyEvent.VK_6:
                        case KeyEvent.VK_7:
                        case KeyEvent.VK_8:
                        case KeyEvent.VK_9:
                        case KeyEvent.VK_NUMPAD0:
                        case KeyEvent.VK_NUMPAD1:
                        case KeyEvent.VK_NUMPAD2:
                        case KeyEvent.VK_NUMPAD3:
                        case KeyEvent.VK_NUMPAD4:
                        case KeyEvent.VK_NUMPAD5:
                        case KeyEvent.VK_NUMPAD6:
                        case KeyEvent.VK_NUMPAD7:
                        case KeyEvent.VK_NUMPAD8:
                        case KeyEvent.VK_NUMPAD9:
                            this.setTIzquierda(String.valueOf(e.getKeyChar()));
                            break;
                        case KeyEvent.VK_F2:
                            e.consume();
                            return;
                        default:
                            this.setTIzquierda("0");
                            break;
                            
                    }
                    this.setTDerecha(","+this.rellena("0",this.getDecimales()));
                    this.setText(this.getTIzquierda()+this.getTDerecha());
                    this.setCPos(1);
                    break;
                    
                }
                break;
        }
    }
    @Override
    protected void processFocusEvent(FocusEvent e) {
        super.processFocusEvent(e);
        switch (e.getID()){
            case FocusEvent.FOCUS_GAINED:
                this.setFocusWin(true);
                OnGotFocus(e);
                break;
        }
    }

    @Override
    protected void processMouseEvent(MouseEvent e) {
        super.processMouseEvent(e);
        this.setFocusWin(false);
        switch (e.getID()){
            case MouseEvent.MOUSE_CLICKED:
                OnClick(e);
                break;
        }
    }
    

    private KeyEvent KeyPress(char keyChar){
        KeyEvent ke=new KeyEvent(this,KeyEvent.KEY_TYPED,0L,0,KeyEvent.VK_UNDEFINED,keyChar);
        //Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(ke);
        return ke;
        
    }
    /// <summary>
    /// Acción que realiza la caja de texto como consecuencia de que
    /// el usuario presione una tecla. Se tienen en cuenta todas las
    /// posibilidades y acciones que toma el usuario.
    /// </summary>
    /// <param name="e">Argumento del evento</param>
    protected void OnKeyPress(java.awt.event.KeyEvent evt){
        if(this.getText().equals("")){
           this.setText("0");
        }
        if((this.getSelectionlength()>0)&&(evt.getKeyChar()!='\b')){
            //SelectedText="";
            //Tizquierda=Tizquierda.Substring(SelectionLength);
            OnKeyPress(KeyPress('\b'));
            OnKeyPress(KeyPress(evt.getKeyChar()));
            return;
        }
        switch(evt.getKeyChar()){
            case '\u007f':
                this.setCPos(this.getCPos()+1);
            case '\b':
                CasoBorrar();
                break;
            case '\r':
            case '\n':
            case '\t':
                this.firePropertyChange(EDIT_STOP,PROP_NONE,PROP_RIGHT);
                break;                
            case '0':
                Caso0();
                break;
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                CasoNumero(evt.getKeyChar());
                break;
            case '.':
            case ',':
                    CasoComa();
                break;
            default:
                evt.consume();
                return;
            }
    }
    /// <summary>
    /// Acción que se realiza al recibir el enfoque en la caja de texto
    /// </summary>
    /// <param name="e"></param>
    protected void OnGotFocus(java.awt.event.FocusEvent evt){
        this.setText(this.getText());
        this.setCPos(0,false);
        this.setSelectionStart(0);
        this.setSelectionEnd(this.getText().length());
    }
    /// <summary>
    /// Acción que se realiza al hacer click sobre la caja de texto
    /// </summary>
    /// <param name="e"></param>
    protected void OnClick(java.awt.event.MouseEvent evt){
        this.setCPos(this.getSelectionStart(),true);
    } 
    protected void OnKeyDown(java.awt.event.KeyEvent evt){
            //e.Handled=true;
        if(evt.isShiftDown()==true){
            switch (evt.getKeyCode()){
                case KeyEvent.VK_UP:
                case KeyEvent.VK_KP_UP:
                    this.firePropertyChange(EDIT_STOP,PROP_NONE,PROP_UP);
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_KP_LEFT:
                    if(this.getCPos()>0){
                        this.setCPos(this.getCPos()-1,false);
                        return;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_KP_DOWN:
                    this.firePropertyChange(EDIT_STOP,PROP_NONE,PROP_DOWN);
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_KP_RIGHT:
                        if(this.getCPos()<this.getText().length()){
                            this.setSelectionEnd(this.getSelectionEnd()+1);
                            return;
                        }
                        break;
                case KeyEvent.VK_END:
                    this.setSelectionEnd(this.getText().length()-this.getCPos());
                    return;
                case KeyEvent.VK_HOME:
                    this.setSelectionEnd(this.getCPos());
                    this.setCPos(0,false);
                    return;
                default:
                    return;
                }
        }else{
            switch (evt.getKeyCode()){
                case KeyEvent.VK_UP:
                case KeyEvent.VK_KP_UP:
                    this.firePropertyChange(EDIT_STOP,PROP_NONE,PROP_UP);
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_KP_LEFT:
                    if(this.getCPos()>0){
                        this.setCPos(this.getCPos()-1,true);
                    }
                break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_KP_DOWN:
                    this.firePropertyChange(EDIT_STOP,PROP_NONE,PROP_DOWN);
                    break;
                case KeyEvent.VK_TAB:
                case KeyEvent.VK_ENTER:
                    this.firePropertyChange(EDIT_STOP,PROP_NONE,PROP_RIGHT);                    
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_KP_RIGHT:
                    if(this.getCPos()<this.getText().length()){
                        this.setCPos(this.getCPos()+1,true);
                        
                    }
                    break;
                case KeyEvent.VK_END:
                    this.setCPos(this.getText().length(),true);
                    break;
                case KeyEvent.VK_HOME:
                    this.setCPos(0,true);
                    break;
                case KeyEvent.VK_DELETE:
                    /*
                    if(this.getCPos()<this.getText().length()){
                        if (this.getSelectionlength()==0){//Si existe algo seleccionado
                            this.setCPos(this.getCPos()+1,true);
                        }
                        OnKeyPress(KeyPress('\b'));
                    }
                     */ 
                    break;
                default:
                    return;
            }
        }
        evt.consume();
    }    
    //
    //********************************METODOS***********************************
    //
    private void CasoBorrar(){
        if(this.getSelectionlength()>0){
            //si está seleccionado todo
            if(this.getSelectionlength()==this.getText().length()){
                this.setText(this.getDefault());
                this.setCPos(1);
            }else{
                if(this.getSelectedText().indexOf(",")!=-1){
                    this.setTDerecha(Izquierda(this.getTDerecha(),this.getTDerecha().length()-this.getSelectionlength()));
                    this.setText(this.getTIzquierda()+this.getTDerecha());
                    this.setCPos(this.getCPos()-this.getSelectionlength(),true);                    
                }else{
                    if(this.getSelectionStart()>0){
                        this.setTIzquierda(Izquierda(this.getText(),this.getSelectionStart()));
                    }else{
                        this.setTIzquierda("");
                    }
                    if(this.getSelectionEnd()<=this.getComaPos()){
                        this.setTDerecha(Derecha(this.getText(),this.getText().length()-this.getSelectionEnd()));
                    }else{
                        String ceros=this.rellena("0",this.getDecimales()-(this.getText().length()-this.getSelectionEnd()));
                        String loquequeda=Derecha(this.getText(),this.getText().length()-this.getSelectionEnd());
                        this.setTDerecha(","+ceros+loquequeda);
                    }
                    int cpos=this.getTIzquierda().length();
                    this.setText(this.getTIzquierda()+this.getTDerecha());
                    this.setCPos(cpos);
                }
            }
        }else{
            //¿estoy en la parte entera o en la decimal?
            if(this.getTIzquierda().indexOf(",")==-1){//parte entera
                /* si estoy en la parte entera a la derecha puedo tener un número, más o menos
                 * si solo tengo un numero, más o menos lo borro y pongo cero, en otro caso
                 * simplemento lo borro
                 */
                if(this.getTIzquierda().length()==1){
                    this.setTIzquierda("0");
                    this.setText(this.getTIzquierda()+this.getTDerecha());
                    this.setCPos(1,true);
                    return;
                }else{
                    this.setTIzquierda(this.quitaPorLaDerecha(this.getTIzquierda(),1));
                    int cpos=this.getTIzquierda().length();
                    this.setText(this.getTIzquierda()+this.getTDerecha());
                    this.setCPos(cpos);                    
                    return;
                }
            }else{//parte decimal
                if(this.getTIzquierda().indexOf(",")==this.getTIzquierda().length()-1){
                    this.setText(this.getTIzquierda()+this.getTDerecha());
                    this.setCPos(this.getCPos()-1,true);
                    return;
                }else{
                    this.setTIzquierda(this.quitaPorLaDerecha(this.getTIzquierda(),1));
                    this.setTDerecha("0"+this.getTDerecha());
                    this.setText(this.getTIzquierda()+this.getTDerecha());
                    this.setCPos(this.getCPos()-1,true);
                    return;
                }
            }
        }
    }
    /// <summary>
    /// Contempla el caso de un número
    /// </summary>
    /// <param name="caracter">número introducido</param>
    private void CasoNumero(char caracter){
        if(this.getCPos()<=this.getComaPos()){
            if((this.getCPos()==1)&&(this.getText().indexOf("0")==0)){
                this.setTIzquierda(String.valueOf(caracter));
                this.setText(this.getTIzquierda()+this.getTDerecha());
                this.setCPos(this.getCPos(),true);
            }else{
                this.setTIzquierda(this.getTIzquierda()+caracter);
                this.setText(this.getTIzquierda()+this.getTDerecha());
                this.setCPos(this.getCPos()+1,true);
            }
        }else{//parte decimal
            if(this.getText().length()-this.getCPos()>0){
                this.setTDerecha(caracter+this.Derecha(this.getTDerecha(),this.getTDerecha().length()-1));
                this.setText(this.getTIzquierda()+this.getTDerecha());
                this.setCPos(this.getCPos()+1,true);
            }else{
                if(this.getCPos()==this.getText().length()){
                    this.setTIzquierda(this.quitaPorLaDerecha(this.getTIzquierda(),1));
                    this.setText(this.getTIzquierda()+caracter);
                    this.setCPos(this.getText().length(),true);
                }
            }
        }
   }
    /// <summary>
    /// Contempla el caso de la coma
    /// </summary>
    private void CasoComa(){
        // Caso 0:
        // Sólo puede haber una coma
        if(this.getText().indexOf(',')!=-1){
            if (this.getTDerecha().indexOf(',')!=-1){//Si a la derecha tengo una coma
                if(this.getTDerecha().substring(0,1).equals(",")){
                    this.setCPos(this.getCPos()+1,true);
                    return;
                }
                if (this.getTDerecha().substring(0,2).equals("0,")){
                    this.setCPos(this.getCPos()+2,true);
                    return;
                }
            }
            return;
        }
    }
    /// <summary>
    /// Contempla el caso de cero
    /// </summary>
    private void Caso0(){
        /*
         * Se considerarán 7 casos
         * Caso 1: +
         * Caso 2: -
         * Caso 3: E
         * Caso 4: ,
         * Caso 5: 0
         * Caso 6: 1,2,3,4,5,6,7,8,9
         * Caso 7: al principio
         */
        // Caso 1: +
        // Se distinguen tres posibilidades que exista E o que no exista
        // Si no existe E, a la izquierda de + nunca puede haber un número
        // Si existe E, solo puede ponerse número a la izquierda de +,
        // pero nunca justo al lado
        // Si emplieza por + el primer numero será 0,
        if(this.getText().indexOf('+')!=-1){
            if((this.getTIzquierda().equals(""))&&(Izquierda(this.getTDerecha(),1).equals("+"))){
                return;
            }
            if((this.getText().indexOf('+')==0)&&(this.getCPos()==1)){
                if(this.getText().indexOf(',')==-1){
                    this.setText("+0,"+this.getText().substring(1,this.getText().length()));
                    this.setCPos(3,true);
                    return;
                }else{
                    return;
                }
            }
        }
        // Caso 2: -
        // Se distinguen tres posibilidades que exista E o que no exista
        // Si no existe E, a la izquierda de - nunca puede haber un número
        // Si existe E, solo puede ponerse número a la izquierda de -,
        // pero nunca justo al lado
        // Si emplieza por - el primer numero será 0,
        if(this.getText().indexOf('-')!=-1){
            if((this.getTIzquierda().equals(""))&&(Izquierda(this.getTDerecha(),1).equals("-"))){
                return;
            }
            if((this.getText().indexOf('-')==0)&&(this.getSelectionStart()==1)){
                if(this.getText().indexOf(',')==-1){
                    this.setText("-0,"+this.getText().substring(1,this.getText().length()));
                    this.setCPos(3,true);
                    return;
                }else{
                    return;
                }
            }
        }
        // Caso 5: 0
        // Si el primer número es un cero y se escribe a la derecha
        // lo borro
        // en el resto de casos nada
        if((this.getTIzquierda().equals("0"))||(this.getTIzquierda().equals("+0"))||(this.getTIzquierda().equals("-0"))){
            return;
        }
        // Caso 6: 1,2,3,4,5,6,7,8,9
        // No pasa nada con la coma
        //
        // Caso 7: al principio
        // Pongo 0,
        if(this.getSelectionStart()==0){
            OnKeyPress(KeyPress(','));			 
            return;
        }
        this.setText(this.getTIzquierda()+'0'+this.getTDerecha());
        this.setCPos(this.getCPos()+1,true);
    }
    //
    //**************************METODOS AUXILIARES******************************
    //
    private String formatea(String cadena){
        DecimalFormat df=new DecimalFormat(this.getMascara());
        try{
            cadena=df.format(Double.parseDouble(cadena.replace(",", ".")));        
        }catch(NumberFormatException nfe){
            cadena="0.0";
        }
        String izquierda="";
        String derecha="";
        int comaPos=cadena.indexOf(",");
        if(cadena.indexOf(",")>-1){
            try{
                izquierda=Izquierda(cadena,comaPos);
                int parteEntera=Integer.parseInt(izquierda);
            }catch(NumberFormatException nfe){
                izquierda="0";
            }
            try{
                derecha=String.valueOf(Integer.parseInt(this.quitaPorLaIzquierda(cadena,comaPos+1)));
            }catch(NumberFormatException nfe){
                derecha=this.rellena("0",this.getDecimales());
            }                
            if(derecha.length()>this.getDecimales()){
                derecha=String.valueOf(Integer.parseInt(this.quitaPorLaDerecha(derecha,derecha.length()-this.getDecimales())));
            }else{
                if(derecha.length()<this.getDecimales()){
                    derecha=derecha+this.rellena("0",this.getDecimales()-derecha.length());
                }
            }
        }else{
            izquierda=cadena;
            derecha=rellena("0",this.getDecimales());
        }
        this.setComaPos(izquierda.length());
        return izquierda+","+derecha;
        
    }
    private String getDefault(){
        String izquierda="0";
        String derecha=rellena("0",this.getDecimales());
        this.setComaPos(izquierda.length());        
        return izquierda+","+derecha;
    }
    private String getMascara(){
        String izquierda="0";
        String derecha=rellena("0",this.getDecimales());
        return izquierda+"."+derecha;
    }
    private int getSelectionlength(){
        if(this.getSelectedText()==null){
            return 0;
        }else{
            return this.getSelectedText().length();
        }
    }
    private String rellena(String texto,int n){
        StringBuffer sb=new StringBuffer();
        for(int contador=0;contador<n;contador++){
            sb.append(texto);
        }
        return sb.toString();
    }
    private String Izquierda(String texto,int n){
        if(texto.length()>=n){
            return texto.substring(0,n);
        }
        return "";
    }
    
    private String Derecha(String texto,int n){
        if(texto.length()>=n){
            return texto.substring(texto.length()-n);
        }
        return "";
    }
    private String quitaPorLaIzquierda(String texto,int n){
        if(texto.length()>n){
            return Derecha(texto,texto.length()-n);
        }
        return null;
    }
    private String quitaPorLaDerecha(String texto,int n){
        if(texto.length()>n){
            return Izquierda(texto,texto.length()-n);
        }
        return null;        
    }

    //
    //**************************METODOS DE ACCESO*******************************
    //
    @Override
    public String getText(){
        return formatea(super.getText());
    }
    public float getValor() {
        this._valor=Convert.toFloat(this.getText().replace(',','.'));
        return this._valor;
    }

    public void setValor(float valor) {
        this.firePropertyChange("valor",this._valor,valor);
        this._valor = valor;
        this.setText(Convert.toString(valor).replace('.',','));
    }

    private void setValor(String valorStr){
        float valor=0F;
        if ((valorStr!=null)&&(valorStr.length()>0)){
            if(valorStr.indexOf(",")!=-1){
                valorStr=valorStr.replace(',','.');
            }
            try{
                Float.parseFloat(valorStr);
                valor=Convert.toFloat(valorStr);
            }catch(NumberFormatException nfe){
                super.setText(this.getDefault());
            }
            this.firePropertyChange("valor",this._valor,valor);
        }
        this._valor=valor;
    }
    
    private int getCPos() {
        return _cPos;
    }
    private void setCPos(int cPos){
        this.setCPos(cPos,true);
    }
    private void setCPos(int cPos,boolean noSelec) {
        if(cPos>this.getText().length()){
            this._cPos=this.getText().length();
        }
        else{
            if(cPos<0){
                this._cPos=0;
            }
            else{
                this._cPos=cPos;
            }
        }
        this.setSelectionStart(this._cPos);
        if(noSelec==true){
            this.setSelectionEnd(this._cPos);
        }
        //Se Calcula this.getTIzquierda() y this.getTDerecha()
        this.setCaretPosition(this._cPos);
        this.setTIzquierda(Izquierda(this.getText(),this._cPos));
        this.setTDerecha(Derecha(this.getText(),this.getText().length()-_cPos));
    }

    private String getTDerecha() {
        return _tDerecha;
    }

    private void setTDerecha(String tDerecha) {
        this._tDerecha = tDerecha;
    }

    private String getTIzquierda() {
        return _tIzquierda;
    }

    private void setTIzquierda(String tIzquierda) {
        this._tIzquierda = tIzquierda;
    }

    @Override
    public void setText(String t) {
        if(t==null){
            t=this.getDefault();
        }else{
            t=t.replace(".",",");
            t=formatea(t);
        }
        super.setText(t);
        this.setValor(t);
    }

    public int getDecimales() {
        return _decimales;
    }

    public void setDecimales(int decimales) {
        this._decimales = decimales;
    }


    public int getComaPos() {
        this._comaPos=this.getText().indexOf(",");
        return this._comaPos;
    }

    public void setComaPos(int comaPos) {
        this._comaPos = comaPos;
    }

    public boolean isFocusWin() {
        return _focusWin;
    }

    public void setFocusWin(boolean focusWin) {
        this._focusWin = focusWin;
    }

}
