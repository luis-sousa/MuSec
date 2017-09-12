/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import view.EventView;

/**
 *
 * @author luis
 */
public class MusecTest {

    public MusecTest() {
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
                    new EventView().start(new Stage());
                    EventView.setStageTitle("New TITLE");

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
     * Test of start method, of class Musec and title of Stage.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testStart() throws Exception {
        assertEquals("New TITLE", EventView.getStage().getTitle());
        assertNotNull(EventView.getStage());
    }

}
