import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Stage4 extends Application {
    private Territory territory;
    private TerritoryView territoryView;
    private ETNube nube;
    private Timeline animationLoop;
    private Timeline cellularReporter;
    private Timeline tagRadar;
    private Timeline tabletRadar;
    private double timeStep;

    @Override
    public void start(Stage primaryStage) {
        nube = new ETNube();
        Scanner configFile = openConfig(primaryStage);
        territory = new Territory();
        territoryView = new TerritoryView(territory, configFile.next());
        timeStep = configFile.nextDouble();
        BorderPane scenePane = new BorderPane();
        scenePane.setTop(createMenuBar());
        scenePane.setCenter(territoryView);
        setupSimulator(configFile);
        buildTimelines();
        Scene scene = new Scene(scenePane, 1000, 700);
        primaryStage.setTitle("EloTelTag Simulation: Stage 4");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Scanner openConfig(Stage stage) {
        Scanner configFile;
        do {
            try {
                File file = fileChooser(stage);
                configFile = new Scanner(file);
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
        MenuItem playMenuItem = new MenuItem("Play");
        MenuItem pauseMenuItem = new MenuItem("Pause");
        playMenuItem.setOnAction(e -> {
            if (animationLoop != null) { animationLoop.play(); cellularReporter.play(); tagRadar.play(); tabletRadar.play(); }
        });
        pauseMenuItem.setOnAction(e -> {
            if (animationLoop != null) { animationLoop.pause(); cellularReporter.pause(); tagRadar.pause(); tabletRadar.pause(); }
        });
        simulMenu.getItems().addAll(playMenuItem, pauseMenuItem);
        menuBar.getMenus().add(simulMenu);
        return menuBar;
    }

    private void buildTimelines() {
        animationLoop = new Timeline(new KeyFrame(
                Duration.millis(1000 * timeStep), e -> territory.moveAll(timeStep)));
        animationLoop.setCycleCount(Timeline.INDEFINITE);

        cellularReporter = new Timeline(new KeyFrame(Duration.seconds(4), e -> {
            for (Cellular c : territory.getCellulars()) c.reportLocation();
        }));
        cellularReporter.setCycleCount(Timeline.INDEFINITE);

        tagRadar = new Timeline(new KeyFrame(Duration.seconds(4), e -> {
            territory.reportTagLocations();
            for (EloTelTagView tv : territoryView.getTagViews()) tv.showRadar();
        }));
        tagRadar.setCycleCount(Timeline.INDEFINITE);

        tabletRadar = new Timeline(new KeyFrame(Duration.seconds(5), e -> {
            territory.reportTabletLocations();
            for (TabletView tv : territoryView.getTabletViews()) tv.showRadar();
        }));
        tabletRadar.setCycleCount(Timeline.INDEFINITE);
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

        Cellular cellular = new Cellular(personName, x, y, r, theta, dtheta, nube);
        territory.addEquipment(cellular);
        territoryView.add(new CellularView(cellular, nube));
        nube.updateLocation(personName, "celular", x, y);

        for (int j = 0; j < tagNumber; j++)
            setupEloTags(in, personName);

        if (isThereTablet) {
            x = in.nextDouble(); y = in.nextDouble();
            r = in.nextDouble();
            theta = Math.toRadians(in.nextDouble());
            dtheta = Math.toRadians(in.nextDouble());
            Tablet tablet = new Tablet(personName, x, y, r, theta, dtheta);
            territory.addEquipment(tablet);
            TabletView tv = new TabletView(tablet, nube);
            tv.setParentPane(territoryView.getPaneForRadar());
            territoryView.add(tv);
        }
    }

    private void setupEloTags(Scanner in, String personName) {
        String tagName = in.next();
        double x = in.nextDouble(), y = in.nextDouble();
        double r = in.nextDouble();
        double theta = Math.toRadians(in.nextDouble());
        double dtheta = Math.toRadians(in.nextDouble());
        EloTelTag tag = new EloTelTag(personName, tagName, x, y, r, theta, dtheta);
        territory.addEquipment(tag);
        EloTelTagView tv = new EloTelTagView(tag, nube);
        tv.setParentPane(territoryView.getPaneForRadar());
        territoryView.add(tv);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
