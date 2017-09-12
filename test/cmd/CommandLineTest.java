/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd;

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
public class CommandLineTest {

    private final String host = "localhost"; //192.168.1.9
    private final String user = "root";
    private final String pass = "tubarao16H!"; //6eYh3SDI9q

    public CommandLineTest() {
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
     * Test of start method, of class CommandLine.
     */
    @Test
    public void testStart() {

        //nenhum argumento
        System.out.println("Nenhum argumento");
        boolean error2 = true;
        CommandLine commandLineMode2 = new CommandLine(new String[]{});
        boolean result2 = commandLineMode2.start();
        assertEquals(error2, result2);

        //5 argumentos 
        System.out.println("Cinco argumentos");
        boolean error3 = true;
        CommandLine commandLineMode3 = new CommandLine(new String[]{"start", host, user, pass, "another_arg"});
        boolean result3 = commandLineMode3.start();
        assertEquals(error3, result3);

        //1 argumento não existente
        System.out.println("Um argumento não existente");
        boolean error4 = true;
        CommandLine commandLineMode4 = new CommandLine(new String[]{"start"});
        boolean result4 = commandLineMode4.start();
        assertEquals(error4, result4);

        //1 argumento existente
        boolean error5 = false;
        System.out.println("Um argumento existente - Help");
        CommandLine commandLineMode5 = new CommandLine(new String[]{"-help"});
        boolean result5 = commandLineMode5.start();
        assertEquals(error5, result5);

        //1 argumento existente
        System.out.println("Um argumento existente - Version");
        boolean error6 = false;
        CommandLine commandLineMode6 = new CommandLine(new String[]{"-version"});
        boolean result6 = commandLineMode6.start();
        assertEquals(error6, result6);

    }

    /**
     * Test of start method, of class CommandLine with correct login and args
     * struct.
     */
    @Test(timeout = 5000)
    public void testStart2() {
        System.out.println("Start Command Line Mode");
        boolean error = false;
        CommandLine commandLineMode = new CommandLine(new String[]{"start", host, user, pass});
        boolean result = commandLineMode.start();
        assertEquals(error, result);
    }

    /**
     * Test of start method, of class CommandLine with incorrect login.
     */
    @Test
    public void testStart3() {
        System.out.println("Start Command Line Mode");
        boolean error = true;
        CommandLine commandLineMode = new CommandLine(new String[]{"start", "172.20.100.161", "root1", "lCnSVUvXRP"});
        boolean result = commandLineMode.start();
        System.out.println(result);
        assertEquals(error, result);
    }

}
