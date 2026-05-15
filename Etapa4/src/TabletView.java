import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class TabletView extends Group {
    private final Tablet tablet;
    private Pane parentPane;

    public TabletView(Tablet tablet, ETNube nube) {
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

        ContextMenu ctx = new ContextMenu();
        MenuItem findMy = new MenuItem("Find My");
        findMy.setOnAction(e -> new FindMyWindow(tablet.getOwnerName(), nube));
        ctx.getItems().add(findMy);
        rect.setOnMouseClicked(e -> { ctx.show(rect, e.getScreenX(), e.getScreenY()); e.consume(); });
    }

    public void showRadar() {
        if (parentPane == null) return;
        SoundPlayer.playBeep();
        Circle radar = new Circle(tablet.getX(), tablet.getY(), 0);
        radar.setFill(Color.TRANSPARENT);
        radar.setStroke(Color.CYAN);
        radar.setStrokeWidth(2);
        radar.setOpacity(0.7);
        parentPane.getChildren().add(radar);
        Timeline anim = new Timeline(
                new KeyFrame(Duration.ZERO,       new KeyValue(radar.radiusProperty(), 0)),
                new KeyFrame(Duration.seconds(1), new KeyValue(radar.radiusProperty(), 50))
        );
        anim.setOnFinished(ev -> parentPane.getChildren().remove(radar));
        anim.play();
    }

    public void setParentPane(Pane pane) { this.parentPane = pane; }
    public Tablet getTablet() { return tablet; }
}
