import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CellularView extends Group {
    private final Cellular cellular;
    private final Rectangle rect;
    private final Text label;

    public CellularView(Cellular cellular) {
        this.cellular = cellular;
        double width = 12;
        double height = 24;
        rect = new Rectangle(width, height);
        rect.setFill(Color.DODGERBLUE);
        rect.setArcWidth(4);   
        //. ¿....?
        label = new Text(cellular.getOwnerName());
        // Centrar el rectángulo en (x, y) del modelo
        rect.xProperty().bind(cellular.xProperty().subtract(width / 2));
        //¿....?

        // Ubicar la etiqueta a la derecha del rectángulo
        label.xProperty().bind(cellular.xProperty().add(width / 2 + 4));
        //¿...?

        // ¿....?
    }
}