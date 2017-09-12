/*
 * ESTGF - Escola Superior de Tecnologia e Gestão de Felgueiras 
 * IPP - Instituto Politécnico do Porto 
 * MEI - Mestrado em Engenharia Informática
 * Tese 2015/16 
 */
package osc;

import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortOut;
import java.io.IOException;
import java.net.InetAddress;

/**
 * This class represents an Sender that sends messages via OSC (Open Sound
 * Control).
 *
 * @author Luís Sousa - 8090228@estgf.ipp.pt
 * @version 1.0 Jul 15, 2016
 */
public class OSCSender {

    private InetAddress remoteIP;
    private int remotePort;
    private String address;
    private OSCPortOut sender;

    /**
     * Constructor.
     */
    public OSCSender() {
    }

    /**
     * Constructor.
     *
     * @param remoteIP Connection IP.
     * @param remotePort Connection Port.
     * @param address Connection Address.
     */
    public OSCSender(InetAddress remoteIP, int remotePort, String address) {
        this.remoteIP = remoteIP;
        this.remotePort = remotePort;
        this.address = address;
        try {
            this.sender = new OSCPortOut(remoteIP, remotePort);
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }

    }

    /**
     * Send a message via OSC.
     *
     * @param values Message values.
     * @return True or false if send an message.
     */
    public boolean sendMessage(Object[] values) {
        try {
            OSCMessage message = new OSCMessage(this.address, values);
            this.sender.send(message);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Gets OSC IP address.
     *
     * @return OSC IP address to communicate.
     */
    public InetAddress getRemoteIP() {
        return remoteIP;
    }

    /**
     * Changes OSC IP address.
     *
     * @param remoteIP OSC IP address to communicate.
     */
    public void setRemoteIP(InetAddress remoteIP) {
        this.remoteIP = remoteIP;
    }

    /**
     * Gets a OSC connection Port.
     *
     * @return A OSC port number.
     */
    public int getRemotePort() {
        return remotePort;
    }

    /**
     * Changes a OSC connection Port.
     *
     * @param remotePort OSC Connection Port.
     */
    public void setRemotePort(int remotePort) {
        this.remotePort = remotePort;
    }

    /**
     * Gets a OSC connection address.
     *
     * @return An OSC address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Changes a OSC address.
     *
     * @param address OSC address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets a OSC Sender.
     *
     * @return OSCPortOut that sends OSC messages.
     */
    public OSCPortOut getSender() {
        return sender;
    }

    /**
     * Changes a OSC Sender.
     *
     * @param sender Sender that sends OSC messages.
     */
    public void setSender(OSCPortOut sender) {
        this.sender = sender;
    }

}
