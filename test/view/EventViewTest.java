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
public class EventViewTest {

    private final String title = "TITLE";

    public EventViewTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        Thread thread = new Thread(() -> {
            JFXPanel jfxPanel = new JFXPanel(); // Initializes the JavaFx Platform
            Platform.runLater(() -> {
                try {
                    new EventView().start(new Stage());
                    EventView.setStageTitle(title);

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
     * Test of setStageTitle method, of class EventView.
     *
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testSetStageTitle() throws InterruptedException {
        Thread thread = new Thread(() -> {
            JFXPanel jfxPanel = new JFXPanel(); // Initializes the JavaFx Platform
            Platform.runLater(() -> {
                EventView.setStageTitle("New title");
                assertEquals("New title", EventView.getStage().getTitle());
            });
        });
        thread.start();// Initialize the thread
        Thread.sleep(1000);
    }

    /**
     * Test of start method, of class EventView.
     */
    @Test
    public void testStart() {
        assertEquals("TITLE", EventView.getStage().getTitle());
        assertNotNull(EventView.getStage());
    }

    /**
     * Test of getStage method, of class EventView.
     */
    @Test
    public void testGetStage() {
        assertNotNull(EventView.getStage());
    }

}
