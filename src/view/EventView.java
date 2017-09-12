/*
 * ESTGF - Escola Superior de Tecnologia e Gestão de Felgueiras 
 * IPP - Instituto Politécnico do Porto 
 * MEI - Mestrado em Engenharia Informática
 * Tese 2015/16 
 */
package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class represents an creation and apresentation of a new Window (EventView
 Application Window).
 *
 * @author Luís Sousa - 8090228@estgf.ipp.pt
 * @version 1.0 Jul 15, 2016
 */
public class EventView extends Application {

    private static Stage stage;

    @Override
    /**
     * Create and show a new Window Application.
     *
     * @param Stage to create a new Window.
     * @return void
     */
    public void start(final Stage stage) throws Exception {
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/house.png")));
        Parent root = FXMLLoader.load(getClass().getResource("Event.fxml"));
        Scene scene = new Scene(root);
        //stage.setResizable(false);
        stage.setTitle("MuSec - Ossim Plugin");
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        //define stage
        EventView.stage = stage;

    }

    /**
     * Returns an stage.
     *
     * @return Returns an specific Stage.
     */
    public static Stage getStage() {
        return stage;
    }

    /**
     * Defines a Window title.
     *
     * @param title Window title.
     */
    public static void setStageTitle(String title) {
        EventView.getStage().setTitle(title);
    }

}
