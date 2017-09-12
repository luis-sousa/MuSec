/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
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
public class DBTest {

    public DBTest() {
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
     * Test of getConnection method, of class DB.
     */
    @Test
    public void testGetConnection() {
        System.out.println("getConnection");
        
        String host = "172.20.100.160"; //192.168.1.9
        String user = "root";
        String pass = "lCnSVUvXRP"; //6eYh3SDI9q

        Connection expResult0 = null;
        Connection result0 = DB.getConnection("", "", "");
        assertEquals(expResult0, result0);

        Connection expResult = null;
        Connection result = DB.getConnection(host, user, "");
        assertEquals(expResult, result);

        Connection expResult1 = null;
        Connection result1 = DB.getConnection(host, "", "");
        assertEquals(expResult1, result1);

        Connection expResult2 = null;
        Connection result2 = DB.getConnection(host, "usernotexist", pass);
        assertEquals(expResult2, result2);

        Connection expResult3 = null;
        Connection result3 = DB.getConnection(host, user, pass);
        assertNotEquals(expResult3, result3);

    }
}
