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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class represents an creation and apresentation of a new Window (AboutView
 Application Window).
 *
 * @author Luís Sousa - 8090228@estgf.ipp.pt
 * @version 1.0 Jul 15, 2016
 */
public class AboutView extends Application {

    private static Stage stage;

    @Override
    /**
     * Create and show a new Window Application.
     *
     * @param Stage to create a new Window.
     * @return void
     */
    public void start(final Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/About.fxml"));
        Scene scene = new Scene(root);
        //stage.setTitle("MUSEC");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.centerOnScreen();
        stage.show();
        //define a stage
        AboutView.stage = stage;
    }

    /**
     * Returns an stage.
     *
     * @return Returns an specific Stage.
     */
    public static Stage getStage() {
        return stage;
    }
}
