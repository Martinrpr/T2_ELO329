import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class EloTelTagView extends Group {
    private final EloTelTag tag;

    public EloTelTagView(EloTelTag tag) {
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
    }

    public EloTelTag getTag() { return tag; }
}
