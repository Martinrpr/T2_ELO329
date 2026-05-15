import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class EloTelTagView extends Group {
    private final EloTelTag tag;
    private Pane parentPane;

    public EloTelTagView(EloTelTag tag, ETNube nube) {
        this.tag = tag;
        double radius = 8;
        Circle circle = new Circle(radius);
        circle.setFill(Color.ORANGE);
        circle.setStroke(Color.DARKORANGE);
        circle.setStrokeWidth(1.5);
        Text label = new Text(tag.getName());

        circle.centerXProperty().bind(tag.xProperty());
        circle.centerYProperty().bind(tag.yProperty());
        label.xProperty().bind(tag.xProperty().add(radius + 2));
        label.yProperty().bind(tag.yProperty());

        getChildren().addAll(circle, label);

        ContextMenu ctx = new ContextMenu();
        MenuItem findMy = new MenuItem("Find My");
        findMy.setOnAction(e -> new FindMyWindow(tag.getOwnerName(), nube));
        ctx.getItems().add(findMy);
        circle.setOnMouseClicked(e -> { ctx.show(circle, e.getScreenX(), e.getScreenY()); e.consume(); });
    }

    public void showRadar() {
        if (parentPane == null) return;
        Circle radar = new Circle(tag.getX(), tag.getY(), 0);
        radar.setFill(Color.TRANSPARENT);
        radar.setStroke(Color.YELLOW);
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
    public EloTelTag getTag() { return tag; }
}
