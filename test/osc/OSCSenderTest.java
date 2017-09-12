/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osc;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
public class OSCSenderTest {

    public OSCSenderTest() {
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
     * Test of sendMessage method, of class OSCSender.
     *
     * @throws java.net.UnknownHostException
     */
    @Test
    public void testSendMessage() throws UnknownHostException {
        System.out.println("Test sendMessage");
        int port = 57111;
        String address = "/chuck/events"; 
        OSCSender osc = new OSCSender(InetAddress.getByName("localhost"), port, address);
        Object[] values = {"event_id", 1, "event_timestamp"}; // event
        boolean send = true;
        boolean send_result = osc.sendMessage(values);
        assertEquals(send, send_result);
    }
}
