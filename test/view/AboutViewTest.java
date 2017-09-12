/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
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
public class AboutViewTest {
    
    private final String title = "ABOUT_TESTE";
    
    public AboutViewTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws InterruptedException {
        Thread thread = new Thread(() -> {
            JFXPanel jfxPanel = new JFXPanel(); // Initializes the JavaFx Platform
            Platform.runLater(() -> {
                try {
                    new AboutView().start(new Stage());
                    AboutView.getStage().setTitle(title);
                    
                } catch (Exception ex) {
                    System.out.println("Error:" + ex.getMessage());
                }
            });
        });
        thread.start();// Initialize the thread
        Thread.sleep(2000);
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of start method, of class AboutView.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testStart() throws Exception {
        System.out.println("Start");
        assertEquals("ABOUT_TESTE", AboutView.getStage().getTitle());
        assertNotNull(AboutView.getStage());
    }

    /**
     * Test of getStage method, of class AboutView.
     */
    @Test
    public void testGetStage() {
        System.out.println("getStage");
        assertNotNull(AboutView.getStage());
    }
    
}
