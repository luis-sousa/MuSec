/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class EventTest {

    private final Event event = new Event("1", 2, 3, 4, 5, 6, "2016-09-23 15:29:36.921");

    public EventTest() {
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
     * Test of getId method, of class Event.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        assertEquals("1", event.getId());
    }

    /**
     * Test of getPriority method, of class Event.
     */
    @Test
    public void testGetPriority() {
        System.out.println("getPriority");
        assertEquals(2, event.getPriority());
    }

    /**
     * Test of getReliability method, of class Event.
     */
    @Test
    public void testGetReliability() {
        System.out.println("getReliability");
        assertEquals(3, event.getReliability());
    }

    /**
     * Test of getAsset_src method, of class Event.
     */
    @Test
    public void testGetAsset_src() {
        System.out.println("getAsset_src");
        assertEquals(4, event.getAsset_src());
    }

    /**
     * Test of getAsset_dst method, of class Event.
     */
    @Test
    public void testGetAsset_dst() {
        System.out.println("getAsset_dst");
        assertEquals(5, event.getAsset_dst());
    }

    /**
     * Test of getTimestamp method, of class Event.
     */
    @Test
    public void testGetTimestamp() {
        System.out.println("getTimestamp");
        assertEquals("2016-09-23 15:29:36.921", event.getTimestamp());
    }

    /**
     * Test of getRisk method, of class Event.
     */
    @Test
    public void testGetRisk() {
        System.out.println("getRisk");
        assertEquals(6, event.getRisk());
    }

    /**
     * Test of setId method, of class Event.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        event.setId("2");
        assertEquals("2", event.getId());
    }

    /**
     * Test of setPriority method, of class Event.
     */
    @Test
    public void testSetPriority() {
        System.out.println("setPriority");
        event.setPriority(3);
        assertEquals(3, event.getPriority());
    }

    /**
     * Test of setReliability method, of class Event.
     */
    @Test
    public void testSetReliability() {
        System.out.println("setReliability");
        event.setReliability(4);
        assertEquals(4, event.getReliability());
    }

    /**
     * Test of setAsset_src method, of class Event.
     */
    @Test
    public void testSetAsset_src() {
        System.out.println("setAsset_src");
        event.setAsset_src(5);
        assertEquals(5, event.getAsset_src());
    }

    /**
     * Test of setAsset_dst method, of class Event.
     */
    @Test
    public void testSetAsset_dst() {
        System.out.println("setAsset_dst");
        event.setAsset_dst(6);
        assertEquals(6, event.getAsset_dst());
    }

    /**
     * Test of setTimestamp method, of class Event.
     */
    @Test
    public void testSetTimestamp() {
        System.out.println("setTimestamp");
        event.setTimestamp("2016-09-23 15:30:00.000");
        assertEquals("2016-09-23 15:30:00.000", event.getTimestamp());
    }

    /**
     * Test of setRisk method, of class Event.
     */
    @Test
    public void testSetRisk() {
        System.out.println("setRisk");
        event.setRisk(7);
        assertEquals(7, event.getRisk());
        //System.out.println(event.toString());
    }

    /**
     * Test of toString method, of class Event.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Event{id=1, priority=2, reliability=3, asset_src=4, asset_dst=5, risk=6, timestamp=2016-09-23 15:29:36.921}";
        assertEquals(expResult, event.toString());
    }

}
