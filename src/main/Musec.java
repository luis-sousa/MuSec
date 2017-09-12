package main;

/*
 * ESTGF - Escola Superior de Tecnologia e Gestão de Felgueiras 
 * IPP - Instituto Politécnico do Porto 
 * MEI - Mestrado em Engenharia Informática
 * Tese 2015/16 
 */
import cmd.CommandLine;
import view.EventView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class represents a EventView Class that start Musec.
 *
 * @author Luís Sousa - 8090228@estgf.ipp.pt
 * @version 1.0 Jul 15, 201
 */
public class Musec extends Application {

    /**
     * Start a EventView Window.
     *
     * @param primaryStage First stage.
     * @throws Exception Window open error.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        new EventView().start(new Stage());
        EventView.setStageTitle("MuSec - Ossim Plugin");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            //start command line mode
            CommandLine commandLineMode = new CommandLine(args);
            commandLineMode.start();
        } else {
            //start javafx interface mode
            launch(args);
        }
    }
}
