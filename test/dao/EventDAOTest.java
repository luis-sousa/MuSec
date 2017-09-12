/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Event;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This classe test used a dump of a OSSIM server (alienvault_siem_db.7z).
 *
 * @author luis
 */
public class EventDAOTest {
    
    private final String host = "localhost"; 
    private final String user = "root";
    private final String pass = ""; 

    public EventDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of close method, of class EventDAO.
     *
     * @throws java.sql.SQLException
     */
    @Test()
    public void testClose() throws SQLException {
        System.out.println("close");
        EventDAO eventdao = new EventDAO(host, user, pass);
        eventdao.close();
        ArrayList<Event> eventos = eventdao.getAllEvents();
        assertEquals(0, eventos.size());
        assertNotEquals(12381, eventos.size());
    }

    /**
     * Test of getAllEvents method, of class EventDAO.
     */
    @Test
    public void testGetAllEvents() {
        System.out.println("getAllEvents");
        EventDAO eventdao = new EventDAO(host, user, pass);
        ArrayList<Event> eventos = eventdao.getAllEvents();
        assertEquals(12381, eventos.size());
    }

    /**
     * Test of haveNewEvents method, of class EventDAO.
     */
    @Test
    public void testHaveNewEvents() {
        System.out.println("haveNewEvents");
        EventDAO eventdao = new EventDAO(host, user, pass);
        boolean result = eventdao.haveNewEvents("2015-11-09 12:14:58");
        assertEquals(false, result);

        EventDAO eventdao2 = new EventDAO(host, user, pass);
        boolean result2 = eventdao2.haveNewEvents("2015-11-09 12:14:56");
        assertEquals(true, result2);
    }

    /**
     * Test of getAllNewEvents method, of class EventDAO.
     */
    @Test
    public void testGetAllNewEvents() {
        System.out.println("getAllNewEvents");
        EventDAO eventdao = new EventDAO(host, user, pass);
        ArrayList<Event> eventos = eventdao.getAllNewEvents("2015-11-09 12:14:58");
        assertEquals(0, eventos.size());

        EventDAO eventdao2 = new EventDAO(host, user, pass);
        ArrayList<Event> eventos2 = eventdao2.getAllNewEvents("2015-11-09 12:14:56");
        assertEquals(1, eventos2.size());

    }

    /**
     * Test of getNewEvent method, of class EventDAO.
     */
    @Test
    public void testGetNewEvent() {
        System.out.println("getNewEvent");
        EventDAO eventdao = new EventDAO(host, user, pass);
        Event evento = eventdao.getNewEvent("2015-11-09 12:14:58");
        assertEquals(null, evento);

        EventDAO eventdao2 = new EventDAO(host, user, pass);
        Event evento2 = eventdao2.getNewEvent("2015-11-09 12:14:56");
        ArrayList<Event> lista = new ArrayList<>();
        lista.add(evento2);
        assertNotEquals(null, evento2);
        assertEquals(1, lista.size());
    }

    /**
     * Test of getLastEventTimestamp method, of class EventDAO.
     */
    @Test
    public void testGetLastEventTimestamp() {
        System.out.println("getLastEventTimestamp");
        EventDAO eventdao = new EventDAO(host, user, pass);
        String result = eventdao.getLastEventTimestamp();
        assertEquals("2015-11-09 12:14:58.0", result);
    }

}
