import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class TerritoryView extends ScrollPane {
    private Territory territory;
    private Pane pane;  // to place each piece of equipment
    public static double WIDTH;
// ¿...?
    public TerritoryView(Territory territory, String imageName) {
        this.territory = territory;
        Image image = new Image("file:"+imageName);
        ImageView mapView = new ImageView(image);
        WIDTH = image.getWidth();
// ¿...?
        pane = new Pane();
        StackPane territoryPane = new StackPane();
        territoryPane.getChildren().addAll(/*¿...?*/);
        // ¿....?
    }
    public void add(Node equipo) {
      // ¿ .... ?
    }
}
