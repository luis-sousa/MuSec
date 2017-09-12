/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osc;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Event;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author luis
 */
public class OSCThreadTest {

    public OSCThreadTest() {
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
     * Test of run method, of class OSCThread.
     */
    @Test
    public void testRun() {
    }

    /**
     * Test of sendStoptoOSCSender method, of class OSCThread.
     *
     * @throws java.net.UnknownHostException
     */
    @Test
    public void testSendStoptoOSCSender() throws UnknownHostException {
        System.out.println("Test sendStoptoOSCSender");
        int port = 57111;
        String address = "/chuck/events";
        OSCSender osc = new OSCSender(InetAddress.getByName("localhost"), port, address);
        List<Event> events = Collections.synchronizedList(new ArrayList<Event>());
        events.add(new Event("ID", 1, 3, 2, 1, 5, "timestamp"));
        OSCThread thread = new OSCThread(events, osc);
        boolean expResult = true;
        boolean result = thread.sendStoptoOSCSender(); //result send stop message
        assertEquals(expResult, result);
    }

}
