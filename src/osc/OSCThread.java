/*
 * ESTGF - Escola Superior de Tecnologia e Gestão de Felgueiras 
 * IPP - Instituto Politécnico do Porto 
 * MEI - Mestrado em Engenharia Informática
 * Tese 2015/16 
 */
package osc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Event;

/**
 * This class represents a Thread for sending messages via OSC.
 *
 * @author Luís Sousa - 8090228@estgf.ipp.pt
 * @version 1.0 Jul 15, 2016
 */
public class OSCThread extends Thread {

    private List<Event> events = Collections.synchronizedList(new ArrayList<Event>());
    private final OSCSender osc_Sender;

    public OSCThread(List<Event> eventList, OSCSender osc) {
        this.events = eventList;
        this.osc_Sender = osc;
    }

    /**
     * Start a thread and wait for events in ArrayList (events), for sends them
     * to CHUCK via OSC, immediately.
     */
    @Override
    public void run() {
        System.out.println("Sending OSSIM Server events to Chuck...");
        Object[] start_Message = {"", 0, 0, 0, 0, 0, "start"};
        this.osc_Sender.sendMessage(start_Message); //send message start
        while (!OSCThread.interrupted()) {
            if (events.size() > 0) { // if have events in list
                //System.out.println(events.get(0).getTimestamp());
                Object[] play_Message = {events.get(0).getTimestamp(), events.get(0).getPriority(), events.get(0).getReliability(), events.get(0).getAsset_src(), events.get(0).getAsset_dst(), events.get(0).getRisk(), "play"};
                this.osc_Sender.sendMessage(play_Message);//send event
                events.remove(0); //remove the event sent.
            }
        }
    }

    /**
     * Send message to stop CHUCK. 
     * @return True or false if sent a stop message to CHUCK.
     */
    public boolean sendStoptoOSCSender() {
        Object[] stop_Message = {"", 0, 0, 0, 0, 0, "stop"};
        return this.osc_Sender.sendMessage(stop_Message);//send stop message
    }

}
