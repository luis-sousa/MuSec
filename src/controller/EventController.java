/*
 * ESTGF - Escola Superior de Tecnologia e Gestão de Felgueiras 
 * IPP - Instituto Politécnico do Porto 
 * MEI - Mestrado em Engenharia Informática
 * Tese 2015/16 
 */
package controller;

import view.AboutView;
import view.EventView;
import osc.OSCSender;
import osc.OSCThread;
import connection.DB;
import dao.EventDAO;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Event;

/**
 * This class represents an Controller for EventView Window Application;
 * <p>
 * In this class its started 2 threads:</p>
 * <p>
 * - The first one (EventsUpdateThread), obtain events in Ossim Server Database,
 * add them in an List of Events and show the events in User interface;</p>
 * <p>
 * - The second (oscThread), use a List of Events and process all arriving
 * events, sending them to CHUCK via OSC;</p>
 *
 * @author Luís Sousa - 8090228@estgf.ipp.pt
 * @version 1.0 Jul 15, 2016
 */
public class EventController implements Initializable {

    @FXML
    private Button btStart;

    @FXML
    private Button btStop;

    @FXML
    private TextField txtIP;

    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextArea txtResults;

    private final List<Event> eventList = Collections.synchronizedList(new ArrayList<Event>());
    private int eventCt = 0;
    private String last_Timestamp = "";
    private OSCSender oscSender;
    private final int port = 57111;
    private final String address = "/chuck/events";
    private OSCThread oscThread; //thread that process the events and send them to CHUCK.
    private Thread EventsUpdateThread; //thread that get ossim server events (add them in eventList and show in User Interface)

    /**
     * All actions that will happen after button being clicked: In this method
     * its started 2 threads:</p>
     * <p>
     * - The (EventsUpdateThread) - obtain events in Ossim Server Database, add
     * them in an List of Events and show the events in User interface;</p>
     * <p>
     * - The (oscThread) - use a List of Events and process all arriving events,
     * sending them to CHUCK via OSC;</p>
     *
     * @param event Action event.
     */
    @FXML
    private void btStartFired(ActionEvent event) {
        //txtResults.appendText("Make connection with Ossim Server Database and with CHUCK..." + "\n");
        if (OSCSenderConnection() && DB.getConnection(txtIP.getText(), txtUser.getText(), txtPassword.getText()) != null) {
            //txtResults.appendText("Sucessfull connection with Database and CHUCK !!!" + "\n");
            showSucessDialog("Successful connection !!!");
            btStop.setDisable(false);
            btStart.setDisable(true);
            txtIP.setDisable(true);
            txtPassword.setDisable(true);
            txtUser.setDisable(true);

            oscThread = new OSCThread(eventList, oscSender);
            oscThread.setDaemon(true);
            oscThread.start(); //start a thread that process events obtained (eventList) and send them to Chuck via OSC messages.

            Task task = new Task<Void>() {
                @Override
                public Void call() throws Exception {
                    while (!Thread.interrupted()) {
                        EventDAO dao = new EventDAO(txtIP.getText(), txtUser.getText(), txtPassword.getText());
                        if (eventCt == 0) { //first event
                            last_Timestamp = dao.getLastEventTimestamp(); //get last timestamp event
                            System.out.println("Started at: " + last_Timestamp);
                        } else {
                            Thread.sleep(1000);
                            //if have new events in Ossim server database
                            if (dao.haveNewEvents(last_Timestamp)) {
                                EventDAO dao2 = new EventDAO(txtIP.getText(), txtUser.getText(), txtPassword.getText());
                                Event new_event = dao2.getNewEvent(last_Timestamp);
                                eventList.add(new_event);
                                Platform.runLater(() -> {
                                    txtResults.appendText(new_event.getTimestamp() + " | " + "risk:" + new_event.getRisk() + "\n");
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
        } else {
            //txtResults.appendText("Error in connection." + "\n");
            showErrorDialog("Error in connection with Database Ossim Server \n" + "or with CHUCK.");
        }
    }

    /**
     * All actions that will happen after button being clicked:
     * <p>
     * Enable and disable some buttons.</p>
     * <p>
     * Stop EventsUpdateThread and oscThread.</p>
     *
     * @param event Action event.
     */
    @FXML
    private void btStopFired(ActionEvent event) {
        btStop.setDisable(true);
        btStart.setDisable(false);
        txtIP.setDisable(false);
        txtPassword.setDisable(false);
        txtUser.setDisable(false);

        EventsUpdateThread.interrupt(); //interrupt thread that add events eand show them in text area
        oscThread.sendStoptoOSCSender();//notify the chuck that will stop
        oscThread.interrupt();//interrupt thread that process the events obtained and send them to Chuck via OSC messages
        eventCt = 0;//update events counter
    }

    /**
     * All actions that will happen after button being clicked: In this method
     * its started 2 threads:
     * <p>
     * - The (EventsUpdateThread) - random Events values (simulating OSSIM), add
     * them in an List of Events and show the events in User interface;</p>
     * <p>
     * - The (oscThread) - use a List of Events and process all arriving events,
     * sending them to CHUCK via OSC;</p>
     *
     * @param event Action event.
     */
    @FXML
    private void btStartSiemSimulateFired(ActionEvent event) {
        btStart.setDisable(true);
        btStop.setDisable(true);
        btStart.setDisable(true);
        txtIP.setDisable(true);
        txtPassword.setDisable(true);
        txtUser.setDisable(true);

        if (OSCSenderConnection()) {
            System.out.println("Successful connection with CHUCK via OSC !!!");

            oscThread = new OSCThread(eventList, oscSender);
            oscThread.setDaemon(true);
            oscThread.start(); //start a thread that send a eventList to chuck via OSC messages

            Task task = new Task<Void>() {
                @Override
                public Void call() throws Exception {
                    while (!Thread.interrupted()) {
                        Event event = new Event("ID: " + eventCt, randomNumber(0, 5), randomNumber(0, 10), randomNumber(0, 5), randomNumber(0, 5), randomNumber(0, 10), newTimestamp());
                        eventList.add(event);
                        Platform.runLater(() -> {
                            txtResults.appendText(event.toString() + "\n");
                        });
                        Thread.sleep(1000);
                        eventCt += 1;
                    }
                    return null;
                }
            };
            EventsUpdateThread = new Thread(task);
            EventsUpdateThread.setDaemon(true);
            EventsUpdateThread.start(); //start a thread that add events in eventList and update text area
        } else {
            System.out.println("Error in connection via OSC.");
        }
    }

    /**
     * All actions that will happen after button being clicked:
     * <p>
     * Enable and disable some buttons.</p>
     * <p>
     * Stop EventsUpdateThread and oscThread.</p>
     *
     * @param event Action event.
     */
    @FXML
    private void btStopSiemSimulateFired(ActionEvent event) {
        btStop.setDisable(true);
        btStart.setDisable(false);
        txtIP.setDisable(false);
        txtPassword.setDisable(false);
        txtUser.setDisable(false);

        EventsUpdateThread.interrupt(); //interrupt thread that add events eand show them in text area
        oscThread.sendStoptoOSCSender();//notify the chuck that will stop
        oscThread.interrupt();//interrupt thread that process the events obtained and send them to Chuck via OSC messages
        eventCt = 0;//update events counter
    }

    /**
     * Try connection with CHUCK via OSC.
     *
     * @return True or false if gets connection.
     */
    private boolean OSCSenderConnection() {
        boolean connection = false;
        try {
            oscSender = new OSCSender(InetAddress.getByName("localhost"), port, address);
            connection = true;
        } catch (UnknownHostException eu) {
            System.out.println("Could not determine ip address of localhost: " + eu.getMessage());
        }
        return connection;
    }

    /**
     * Initializes the controller class.
     *
     * @param url url
     * @param rb resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btStop.setDisable(true);
        txtIP.setText("172.20.100.160");
        txtUser.setText("root");
        txtPassword.setText("lCnSVUvXRP");
        // extern access http://www.thegeekstuff.com/2010/08/allow-mysql-client-connection/
        // GRANT ALL ON *.* to root@'192.168.1.%' IDENTIFIED BY 'your-root-password (6eYh3SDI9q)';
        // vi /etc/iptables/rules001-common.iptables
        // A INPUT -p tcp -m tcp --dport 3306 -j ACCEPT
        // A OUTPUT -p tcp -m tcp --sport 3306 -j ACCEPT
        // reboot
        // 172.20.100.160 lCnSVUvXRP 6eYh3SDI9q
    }

    /**
     * Returns a random number between 2 values.
     *
     * @param min Value min.
     * @param max Value max.
     * @return A number.
     */
    private int randomNumber(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * Obtain date and hour.
     *
     * @return A new timestamp.
     */
    private String newTimestamp() {
        java.util.Date date = new java.util.Date();
        return new Timestamp(date.getTime()).toString();
    }

    /**
     * Open a new Window (AboutView Window).
     *
     * @param event Event.
     */
    @FXML
    private void AboutFired(ActionEvent event) {
        try {
            new AboutView().start(new Stage());
        } catch (Exception ex) {
            System.out.println("Error opening About Window: " + ex.getMessage());
        }
    }

    /**
     * Close this Window (EventView Window).
     *
     * @param event Event.
     */
    @FXML
    private void CloseFired(ActionEvent event) {
        try {
            if (oscThread != null && oscThread.isAlive()) {
                oscThread.sendStoptoOSCSender();//notify Chuck to stop
            }
            EventView.getStage().close();
        } catch (Exception ex) {
            System.out.println("Error closing this Window: " + ex.getMessage());
        }
    }

    /**
     * Show a sucessfull Dialog.
     *
     * @param msg Message to show in Dialog.
     */
    private void showSucessDialog(String msg) {
        Alert dlg = createAlert(Alert.AlertType.INFORMATION);
        dlg.setTitle("SUCESS");
        String optionalMasthead = "Message:";
        dlg.getDialogPane().setContentText(msg);
        dlg.getDialogPane().setHeaderText(optionalMasthead);

        dlg.show();
    }

    /**
     * Show a error Dialog.
     *
     * @param msg Message to show in Dialog.
     */
    private void showErrorDialog(String msg) {
        Alert dlg = createAlert(Alert.AlertType.ERROR);
        dlg.setTitle("FAILURE");
        String optionalMasthead = "Error:";
        dlg.getDialogPane().setContentText(msg);
        dlg.getDialogPane().setHeaderText(optionalMasthead);
        dlg.show();
    }

    /**
     * Create an customized alert to show in an Dialog.
     *
     * @param type Alert type.
     * @return An alert.
     */
    private Alert createAlert(Alert.AlertType type) {
        Window owner = EventView.getStage();
        Alert dlg = new Alert(type, "");
        dlg.getDialogPane().setStyle("-fx-background-color: rgb(215, 232, 250);");
        dlg.initModality(Modality.APPLICATION_MODAL);
        dlg.initOwner(owner);
        return dlg;
    }
}
