import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class TabletView extends Group {
    private final Tablet tablet;

    public TabletView(Tablet tablet) {
        this.tablet = tablet;
        double width = 20, height = 28;
        Rectangle rect = new Rectangle(width, height);
        rect.setFill(Color.MEDIUMSEAGREEN);
        rect.setStroke(Color.DARKGREEN);
        rect.setStrokeWidth(1.5);
        rect.setArcWidth(4);
        rect.setArcHeight(4);
        Text label = new Text(tablet.getOwnerName());

        rect.xProperty().bind(tablet.xProperty().subtract(width / 2));
        rect.yProperty().bind(tablet.yProperty().subtract(height / 2));
        label.xProperty().bind(tablet.xProperty().add(width / 2 + 4));
        label.yProperty().bind(tablet.yProperty());

        getChildren().addAll(rect, label);
    }

    public Tablet getTablet() { return tablet; }
}
