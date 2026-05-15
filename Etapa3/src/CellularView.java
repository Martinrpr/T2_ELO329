import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CellularView extends Group {
    public CellularView(Cellular cellular, ETNube nube) {
        double width = 12, height = 24;
        Rectangle rect = new Rectangle(width, height);
        rect.setFill(Color.DODGERBLUE);
        rect.setArcWidth(4);
        rect.setArcHeight(4);
        Text label = new Text(cellular.getOwnerName());

        rect.xProperty().bind(cellular.xProperty().subtract(width / 2));
        rect.yProperty().bind(cellular.yProperty().subtract(height / 2));
        label.xProperty().bind(cellular.xProperty().add(width / 2 + 4));
        label.yProperty().bind(cellular.yProperty());

        getChildren().addAll(rect, label);

        ContextMenu ctx = new ContextMenu();
        MenuItem findMy = new MenuItem("Find My");
        findMy.setOnAction(e -> new FindMyWindow(cellular.getOwnerName(), nube));
        ctx.getItems().add(findMy);
        rect.setOnMouseClicked(e -> { ctx.show(rect, e.getScreenX(), e.getScreenY()); e.consume(); });
    }
}
