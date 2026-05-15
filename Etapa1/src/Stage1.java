import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.Locale;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Stage1 extends Application {
    private Territory territory;
    private TerritoryView territoryView;

    @Override
    public void start(Stage primaryStage) {
        Scanner configFile = openConfig(primaryStage);
        territory = new Territory();
        territoryView = new TerritoryView(territory, configFile.next());
        configFile.nextDouble(); // skip timeStep
        BorderPane scenePane = new BorderPane();
        scenePane.setTop(createMenuBar());
        scenePane.setCenter(territoryView);
        setupSimulator(configFile);
        Scene scene = new Scene(scenePane, 1000, 700);
        primaryStage.setTitle("EloTelTag Simulation: Stage 1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Scanner openConfig(Stage stage) {
        Scanner configFile;
        do {
            try {
                File file = fileChooser(stage);
                configFile = new Scanner(file).useLocale(Locale.US);
            } catch (FileNotFoundException e) {
                configFile = null;
            }
        } while (configFile == null);
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
        MenuItem playItem = new MenuItem("Play");
        MenuItem pauseItem = new MenuItem("Pause");
        playItem.setDisable(true);
        pauseItem.setDisable(true);
        simulMenu.getItems().addAll(playItem, pauseItem);
        menuBar.getMenus().add(simulMenu);
        return menuBar;
    }

    private void setupSimulator(Scanner in) {
        int personNumber = in.nextInt();
        for (int i = 0; i < personNumber; i++)
            setupPersonEquipment(in);
    }

    private void setupPersonEquipment(Scanner in) {
        String personName = in.next();
        int tagNumber = in.nextInt();
        boolean isThereTablet = in.nextInt() == 1;

        double x = in.nextDouble(), y = in.nextDouble();
        double r = in.nextDouble();
        double theta = Math.toRadians(in.nextDouble());
        double dtheta = Math.toRadians(in.nextDouble());

        Cellular cellular = new Cellular(personName, x, y, r, theta, dtheta);
        territory.addEquipment(cellular);
        territoryView.add(new CellularView(cellular));

        for (int j = 0; j < tagNumber; j++)
            setupEloTags(in, personName);

        if (isThereTablet) {
            in.nextDouble(); in.nextDouble(); // x, y
            in.nextDouble(); in.nextDouble(); in.nextDouble(); // r, theta, dtheta
        }
    }

    private void setupEloTags(Scanner in, String personName) {
        in.next(); // tag name — skip
        in.nextDouble(); in.nextDouble(); // x, y
        in.nextDouble(); in.nextDouble(); in.nextDouble(); // r, theta, dtheta
    }

    public static void main(String[] args) {
        launch(args);
    }
}
