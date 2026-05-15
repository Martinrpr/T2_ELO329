import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;
import java.util.List;

public class TerritoryView extends ScrollPane {
    private Pane pane;
    public static double WIDTH;
    public static double HEIGHT;

    public TerritoryView(Territory territory, String imageName) {
        Image image = new Image("file:" + imageName);
        ImageView mapView = new ImageView(image);
        WIDTH = image.getWidth();
        HEIGHT = image.getHeight();
        pane = new Pane();
        StackPane territoryPane = new StackPane();
        territoryPane.getChildren().addAll(mapView, pane);
        setContent(territoryPane);
    }

    public void add(Node equipo) { pane.getChildren().add(equipo); }
    public Pane getPaneForRadar() { return pane; }

    public List<EloTelTagView> getTagViews() {
        List<EloTelTagView> views = new ArrayList<>();
        for (Node n : pane.getChildren())
            if (n instanceof EloTelTagView) views.add((EloTelTagView) n);
        return views;
    }

    public List<TabletView> getTabletViews() {
        List<TabletView> views = new ArrayList<>();
        for (Node n : pane.getChildren())
            if (n instanceof TabletView) views.add((TabletView) n);
        return views;
    }
}
