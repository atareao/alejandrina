/*
 * NtpOffsetCalculator.java
 * 
 * Created on 11-nov-2007, 12:37:39
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
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
//********************************IMPORTACIONES*********************************
import java.net.InetAddress;
//
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
/**
 *
 * @author Propietario
 */
public class NtpOffsetCalculator {
    //
    //********************************CONSTANTES********************************
    //
    
    //
    // *********************************CAMPOS*********************************
    //
    private String _serverName;
    private double _localClockOffset;

    //
    //******************************CONSTRUCTORES*******************************
    //
    public NtpOffsetCalculator(String serverName){
        this.setServerName(serverName);
    }
    //
    //********************************METODOS***********************************
    //
    public void calculateOffset() throws SocketException, UnknownHostException, IOException{
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName(this.getServerName());
        byte[] buf = new NtpMessage().toByteArray();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 123);
        // Set the transmit timestamp *just* before sending the packet
        // ToDo: Does this actually improve performance or not?
        NtpMessage.encodeTimestamp(packet.getData(), 40,(System.currentTimeMillis()/1000.0) + 2208988800.0);
        socket.send(packet);
        // Get response
        System.out.println("NTP request sent, waiting for response...\n");
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        // Immediately record the incoming timestamp
        double destinationTimestamp =(System.currentTimeMillis()/1000.0) + 2208988800.0;
        // Process response
        NtpMessage msg = new NtpMessage(packet.getData());
        // Corrected, according to RFC2030 errata
        double roundTripDelay = (destinationTimestamp-msg.originateTimestamp) -	(msg.transmitTimestamp-msg.receiveTimestamp);
        this.setLocalClockOffset(((msg.receiveTimestamp - msg.originateTimestamp) +(msg.transmitTimestamp - destinationTimestamp)) / 2*1000);
        // Display response
        System.out.println("NTP server: " + this.getServerName());
        System.out.println(msg.toString());
        System.out.println("Dest. timestamp:     " +NtpMessage.timestampToString(destinationTimestamp));
        System.out.println("Round-trip delay: " + new DecimalFormat("0.00").format(roundTripDelay*1000) + " ms");
        System.out.println("Local clock offset: " + new DecimalFormat("0.00").format(this.getLocalClockOffset()) + " ms");
        socket.close();        
    }
    //
    //**************************METODOS AUXILIARES******************************
    //

    //
    //**************************METODOS DE ACCESO*******************************
    //
    public String getServerName() {
        return _serverName;
    }

    public void setServerName(String serverName) {
        this._serverName = serverName;
    }

    public double getLocalClockOffset() {
        return _localClockOffset;
    }

    public void setLocalClockOffset(double localClockOffset) {
        this._localClockOffset = localClockOffset;
    }
}
