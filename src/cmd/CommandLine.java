package cmd;

/*
 * ESTGF - Escola Superior de Tecnologia e Gestão de Felgueiras 
 * IPP - Instituto Politécnico do Porto 
 * MEI - Mestrado em Engenharia Informática
 * Tese 2015/16 
 */
import connection.DB;
import dao.EventDAO;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.application.Platform;
import javafx.concurrent.Task;
import model.Event;
import osc.OSCSender;
import osc.OSCThread;

/**
 * This class represents all work to execute MuSec in command line mode.
 *
 * @author Luís Sousa - 8090228@estgf.ipp.pt
 * @version 1.0 Jul 15, 201
 */
public class CommandLine {

    String[] args; //command line args

    public CommandLine(String[] args) {
        this.args = args;
    }

    /**
     * Try connection with CHUCK via OSC.
     *
     * @return True or false if gets connection.
     */
    private static OSCSender OSCSenderConnection() {
        OSCSender oscSender = null;
        try {
            int port = 57111;
            String address = "/chuck/events";
            oscSender = new OSCSender(InetAddress.getByName("localhost"), port, address);

        } catch (UnknownHostException eu) {
            System.out.println("Could not determine ip address of localhost: " + eu.getMessage());
        }
        return oscSender;
    }

    /**
     * Process all args and start MuSec in command line mode.
     *
     * @return true if exist errors
     */
    public boolean start() {
        String pass;
        String ip;
        String user;
        List<Event> eventList = Collections.synchronizedList(new ArrayList<Event>());
        OSCThread oscThread; //thread that start the events and send them to CHUCK.
        Thread EventsUpdateThread; //thread that get ossim server events (add them in eventList and show in User Interface)
        boolean error = true;
        try {
            if (args.length == 1) {
                if (args[0].equals("-help")) {
                    System.out.println("Sintax: \"start IP_OSSIM_SERVER USER PASSWORD\" to start.");
                    error = false;
                    Platform.exit();
                } else if (args[0].equals("-version")) {
                    System.out.println("Version: MuSec 1.0 compiled in 14/07/2016 by Luís Sousa 8090228@estg.ipp.pt.");
                    error = false;
                    Platform.exit();
                } else {
                    System.out.println("Invalid argument: " + args[0] + ".");
                    System.out.println("Try \"-help\"");
                    error = true;
                    Platform.exit();
                }
            } else if (args.length > 1 && args.length <= 4) {
                if (args[0].equals("start")) {
                    if (args.length == 4) {
                        System.out.println("starting ...");
                        ip = args[1];
                        user = args[2];
                        pass = args[3];
                        System.out.println("IP:" + ip + " User:" + user);
                        OSCSender oscSender = OSCSenderConnection();
                        if (oscSender != null && DB.getConnection(ip, user, pass) != null) {
                            oscThread = new OSCThread(eventList, oscSender);
                            oscThread.setDaemon(true);
                            oscThread.start(); //start a thread that start events obtained (eventList) and send them to Chuck via OSC messages.

                            Task task = new Task<Void>() {
                                @Override
                                public Void call() throws Exception {
                                    int eventCt = 0;
                                    String last_Timestamp = "";
                                    while (!Thread.interrupted()) {
                                        EventDAO dao = new EventDAO(ip, user, pass);
                                        if (eventCt == 0) { //first event
                                            last_Timestamp = dao.getLastEventTimestamp(); //get last timestamp event
                                            System.out.println("Started at: " + last_Timestamp);
                                        } else {
                                            Thread.sleep(1000);
                                            //if have new events in Ossim server database
                                            if (dao.haveNewEvents(last_Timestamp)) {
                                                EventDAO dao2 = new EventDAO(ip, user, pass);
                                                Event new_event = dao2.getNewEvent(last_Timestamp);
                                                eventList.add(new_event);//add event in list
                                                Platform.runLater(() -> {
                                                    System.out.println(new_event.getTimestamp() + " | " + new_event.getRisk());
                                                });
                                                last_Timestamp = new_event.getTimestamp(); //update last event timestamp obtained
                                            }
                                        }
                                        eventCt += 1;
                                    }
                                    return null;
                                }
                            };
                            EventsUpdateThread = new Thread(task);
                            EventsUpdateThread.setDaemon(true);
                            EventsUpdateThread.start(); //start a thread that add events in eventList and update text area

                            //waiting for user interrupt, such as typing ^C
                            Runtime.getRuntime().addShutdownHook(new Thread() {
                                @Override
                                public void run() {
                                    System.out.println("Finishing all work...");
                                    EventsUpdateThread.interrupt(); //interrupt thread that add events eand show them in text area
                                    oscThread.sendStoptoOSCSender();//notify the chuck that will stop
                                    oscThread.interrupt();//interrupt thread that start the events obtained and send them to Chuck via OSC messages
                                }
                            });
                            error = false;
                        } else {
                            System.out.println("Error in connection with Database Ossim Server or with CHUCK.");
                            error = true;
                            Platform.exit();
                        }
                        //error = false;
                    } else {
                        System.out.println("Invalid argument(s) to start.");
                        System.out.println("Try \"-help\"");
                        error = true;
                        Platform.exit();
                    }
                } else {
                    System.out.println("Invalid argument(s).");
                    System.out.println("Try \"-help\"");
                    error = true;
                    Platform.exit();
                }
            } else if (args.length > 4) {
                System.out.println("Invalid arguments number.");
                System.out.println("Try \"-help\"");
                error = true;
                Platform.exit();
            }
        } catch (Exception ex) {
            System.out.println("Message: " + ex.getMessage());
            error = true;
            Platform.exit();
        }
        return error;
    }
}
