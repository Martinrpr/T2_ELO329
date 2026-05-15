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

/**
 * Vista JavaFX de un {@link EloTelTag}.
 * <p>
 * Renderiza el tag como un círculo naranja de radio 8 px con su nombre,
 * cuya posición se mantiene actualizada automáticamente mediante enlace
 * de propiedades ({@code bind}) con el modelo.
 * </p>
 * <p>
 * Al realizar la búsqueda periódica de celulares ({@link #showRadar()}),
 * emite un pitido corto ({@link SoundPlayer#playBeep()}) y muestra una
 * animación de radar: círculo que crece de 0 a 50 px de radio en 1 segundo.
 * </p>
 * <p>
 * Al hacer clic derecho en el tag aparece un menú emergente con la opción
 * "Find My" que abre una {@link FindMyWindow} con los bienes del propietario.
 * </p>
 *
 * @author ELO329 Tarea 2
 * @version 4.0
 * @see EloTelTag
 * @see Equipo
 * @see SoundPlayer
 * @see FindMyWindow
 */
public class EloTelTagView extends Group {

    /** Modelo asociado a esta vista. */
    private final EloTelTag tag;

    /** Panel padre donde se dibujan los círculos de radar. */
    private Pane parentPane;

    /**
     * Construye la vista del tag dado.
     * <p>
     * Las propiedades de posición del círculo y la etiqueta quedan enlazadas
     * al modelo, por lo que se actualizan automáticamente sin llamadas manuales.
     * </p>
     *
     * @param tag  modelo del {@link EloTelTag} a representar
     * @param nube referencia a la nube para la ventana Find My
     */
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

    /**
     * Muestra la animación de radar y emite un pitido sonoro.
     * <p>
     * Crea un círculo transparente centrado en la posición actual del tag
     * que crece de radio 0 a 50 px en 1 segundo y luego se elimina del panel.
     * Requiere que {@link #setParentPane(Pane)} haya sido llamado previamente.
     * </p>
     */
    public void showRadar() {
        if (parentPane == null) return;
        SoundPlayer.playBeep();
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

    /**
     * Establece el panel padre donde se dibujarán los círculos de radar.
     *
     * @param pane panel raíz del territorio
     */
    public void setParentPane(Pane pane) { this.parentPane = pane; }

    /**
     * @return el modelo {@link EloTelTag} asociado a esta vista
     */
    public EloTelTag getTag() { return tag; }
}
