import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Stage1 extends Application {
    private Territory territory;
    private TerritoryView territoryView;
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        Scanner configFile= openConfig(primaryStage);
        territory = new Territory();
        territoryView = new TerritoryView(territory, configFile.next());
        configFile.nextDouble();  // skip timeStep
        BorderPane scenePane = new BorderPane();
        scenePane.setTop(createMenuBar());
        scenePane.setCenter(territoryView);
        setupSimulator(configFile);
        Scene scene = new Scene(scenePane, 1000, 700);
        primaryStage.setTitle("EloTelTag Simulation: Stage 1"); // Set the window title
        primaryStage.setScene(scene); // Place the scene in the window
        primaryStage.show();
    }
    private Scanner openConfig(Stage stage) {
        Scanner configFile;
        do {
            try {
                File file = fileChooser(stage);
                configFile = new Scanner(file);
            } catch (FileNotFoundException e) {
                configFile=null;
            }
        } while (configFile ==null);
        return configFile;
    }
    private File fileChooser(Stage stage) {
        FileChooser fChooser = new FileChooser();
        fChooser.setTitle("Select configuration file");
        fChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text file", "*.txt"));
        return fChooser.showOpenDialog(stage);
    }
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu simulMenu = new Menu("Simulation");
        //. ¿....?
        return menuBar;
    }
    private void setupSimulator(Scanner in) {  // create objects from file
        int personNumber = in.nextInt();
        for (int i = 0; i < personNumber; i++)
            setupPersonEquipment(in);
    }
    private void setupPersonEquipment(Scanner in){
        Cellular cellular;
        double x, y, r, theta, dt;

        String personName = in.next();
        int tagNumber = in.nextInt();
        boolean isThereTablet= in.nextInt()==1;
        x = in.nextFloat(); // cellular's location
        y = in.nextFloat();
        r = in.nextFloat();
        theta = Math.toRadians(in.nextFloat());
        dt = Math.toRadians(in.nextFloat());
        cellular = new Cellular(personName, x, y, r, theta, dt);
        CellularView cView = new CellularView(cellular);
        territory.addEquipment(cellular);
        territoryView.add(cView);
        for (int j = 0; j < tagNumber; j++)
            setupEloTags(in, personName);
        if (isThereTablet) {
            in.nextFloat(); in.nextFloat();   // skip tablet's location
            in.nextFloat(); in.nextFloat(); in.nextFloat(); // skip r, theta , dTheta
        }
    }
    private void setupEloTags(Scanner in, String personName) {
// Skip all
        float x, y, r, theta, dt;
        String tagName = in.next();
// ¿......?
    }
    /**
     * The main method is only needed for the IDE with limited JavaFX support.
     * Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}