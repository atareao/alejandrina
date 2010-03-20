/*
 * ModalFrameUtil.java
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
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.WindowAdapter; 
import java.awt.event.WindowEvent; 
import java.lang.reflect.InvocationHandler; 
import java.lang.reflect.Method; 
import java.lang.reflect.Proxy; 

/**
 *
 * @author Santhosh Kumar T - santhosh@in.fiorano.com
 */
public class ModalFrameUtil {
    //
    //********************************CONSTANTES********************************
    //
    
    //
    // *********************************CAMPOS*********************************
    //
    
    //
    //******************************CONSTRUCTORES*******************************
    //
    public ModalFrameUtil(){
        
    }
    
    //
    //********************************METODOS***********************************
    //
    class EventPump implements InvocationHandler{ 
        Frame frame; 
 
        public EventPump(Frame frame){ 
            this.frame = frame; 
        } 
 
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable { 
            return frame.isShowing() ? Boolean.TRUE : Boolean.FALSE; 
        } 
 
        // when the reflection calls in this method has to be 
        // replaced once Sun provides a public API to pump events. 
        public void start() throws Exception{ 
            Class clazz = Class.forName("java.awt.Conditional"); 
            Object conditional = Proxy.newProxyInstance( 
                    clazz.getClassLoader(), 
                    new Class[]{clazz}, 
                    this); 
            Method pumpMethod = Class.forName("java.awt.EventDispatchThread") 
                    .getDeclaredMethod("pumpEvents", new Class[]{clazz}); 
            pumpMethod.setAccessible(true); 
            pumpMethod.invoke(Thread.currentThread(), new Object[]{conditional}); 
        } 
    } 
 
    // show the given frame as modal to the specified owner. 
    // NOTE: this method returns only after the modal frame is closed. 
    public void showAsModal(final Frame frame, final Frame owner){ 
        frame.addWindowListener(new WindowAdapter(){ 
            public void windowOpened(WindowEvent e){ 
                owner.setEnabled(false); 
            } 
 
            public void windowClosed(WindowEvent e){ 
                owner.setEnabled(true); 
                frame.removeWindowListener(this); 
            } 
        }); 
 
        owner.addWindowListener(new WindowAdapter(){ 
            public void windowActivated(WindowEvent e){ 
                if(frame.isShowing()){ 
                    frame.setExtendedState(JFrame.NORMAL); 
                    frame.toFront(); 
                }else 
                    owner.removeWindowListener(this); 
            } 
        }); 
 
        frame.setVisible(true); 
        try{ 
            EventPump ep=new EventPump(frame);
            ep.start();
            //new EventPump(frame).start(); 
        } catch(Throwable throwable){ 
            throw new RuntimeException(throwable); 
        } 
    }     
    //
    //**************************METODOS AUXILIARES******************************
    //
    
    //
    //**************************METODOS DE ACCESO*******************************
    //
    
}
