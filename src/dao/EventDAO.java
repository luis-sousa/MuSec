/*
 * ESTGF - Escola Superior de Tecnologia e Gestão de Felgueiras 
 * IPP - Instituto Politécnico do Porto 
 * MEI - Mestrado em Engenharia Informática
 * Tese 2015/16 
 */
package dao;

import connection.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Event;

/**
 * This class represents a Data Access Object that gets a database events and
 * creates an Object Event.
 *
 * @author Luís Sousa - 8090228@estgf.ipp.pt
 * @version 1.0 Jul 15, 2016
 */
public class EventDAO {

    private final Connection connection;
    private PreparedStatement stmt;

    /**
     * Create a Database connection to get events.
     *
     * @param host Database server IP.
     * @param userName Database username.
     * @param password Database password.
     */
    public EventDAO(String host, String userName, String password) {
        this.connection = DB.getConnection(host, userName, password);
    }

    /**
     * Stop the connection with database.
     *
     * @throws SQLException SQL error.
     */
    public void close() throws SQLException {
        this.connection.close();
    }

    /**
     * Gests all database events.
     *
     * @return List with all database events.
     */
    public ArrayList<Event> getAllEvents() {
        ArrayList<Event> list = new ArrayList<>();
        try {
            ResultSet rs;
            String sql = "SELECT id, ossim_priority, ossim_reliability, ossim_asset_src, ossim_asset_dst, ossim_risk_c, timestamp FROM acid_event ORDER BY timestamp DESC";
            stmt = this.connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                //create object event
                Event event = new Event(rs.getString("id"), rs.getInt("ossim_priority"), rs.getInt("ossim_reliability"), rs.getInt("ossim_asset_src"), rs.getInt("ossim_asset_dst"), rs.getInt("ossim_risk_c"), rs.getString("timestamp"));
                list.add(event);
            }
            //close
            rs.close();
            stmt.close();
            this.connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return list;
    }

    /**
     * Verify in database if exist new events.
     *
     * @param timestamp Timestamp received.
     * @return True or false if have new events starting from an certain
     * timestamp.
     */
    public boolean haveNewEvents(String timestamp) {
        boolean haveData = false;
        try {
            ResultSet rs;
            String sql = "SELECT id, ossim_priority, ossim_reliability, ossim_asset_src, ossim_asset_dst, ossim_risk_c, timestamp FROM acid_event where timestamp>? GROUP BY id ORDER BY ossim_risk_c DESC LIMIT 1";
            stmt = this.connection.prepareStatement(sql);
            this.stmt.setString(1, timestamp);
            rs = stmt.executeQuery();

            if (rs.isBeforeFirst()) {
                haveData = true;
            }
            //close
            rs.close();
            stmt.close();
            this.connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return haveData;
    }

    /**
     * Obtain in database, new events starting from a certain timestamp .
     *
     * @param timestamp Timestamp
     * @return List of new events
     */
    public ArrayList<Event> getAllNewEvents(String timestamp) {
        ArrayList<Event> list = new ArrayList<>();
        try {
            ResultSet rs;
            String sql = "SELECT id, ossim_priority, ossim_reliability, ossim_asset_src, ossim_asset_dst, ossim_risk_c, timestamp FROM acid_event where timestamp>? GROUP BY id ORDER BY ossim_risk_c DESC LIMIT 1";
            stmt = this.connection.prepareStatement(sql);
            this.stmt.setString(1, timestamp);
            rs = stmt.executeQuery();
            while (rs.next()) {
                //create a new event
                Event event = new Event(rs.getString("id"), rs.getInt("ossim_priority"), rs.getInt("ossim_reliability"), rs.getInt("ossim_asset_src"), rs.getInt("ossim_asset_dst"), rs.getInt("ossim_risk_c"), rs.getString("timestamp"));
                list.add(event);
            }
            // close
            rs.close();
            stmt.close();
            this.connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return list;
    }

    /**
     * Obtain in database, a new event starting from a certain timestamp.
     *
     * @param timestamp Timestamp.
     * @return New event.
     */
    public Event getNewEvent(String timestamp) {
        Event event = null;
        try {
            ResultSet rs;
            String sql = "SELECT id, ossim_priority, ossim_reliability, ossim_asset_src, ossim_asset_dst, ossim_risk_c, timestamp FROM acid_event where timestamp>? GROUP BY id ORDER BY ossim_risk_c DESC LIMIT 1";
            stmt = this.connection.prepareStatement(sql);
            this.stmt.setString(1, timestamp);
            rs = stmt.executeQuery();
            while (rs.next()) {
                //create a new event
                event = new Event(rs.getString("id"), rs.getInt("ossim_priority"), rs.getInt("ossim_reliability"), rs.getInt("ossim_asset_src"), rs.getInt("ossim_asset_dst"), rs.getInt("ossim_risk_c"), rs.getString("timestamp"));
            }
            //close
            rs.close();
            stmt.close();
            this.connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return event;
    }

    /**
     * Obtain in database, the last timestamp of an event inserted.
     *
     * @return The last date and time of an event.
     */
    public String getLastEventTimestamp() {
        String time = null;
        try {
            ResultSet rs;
            String sql = "SELECT timestamp FROM acid_event ORDER BY timestamp DESC LIMIT 1";
            stmt = this.connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                time = rs.getString("timestamp");
            }
            // Fechamos a ligação
            rs.close();
            stmt.close();
            //this.connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return time;
    }
}
