import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

/**
 * Ventana Find My que muestra los bienes de una persona.
 * En Etapa 3 la información es estática (no se actualiza).
 */
public class FindMyWindow extends Stage {

    protected final String ownerName;
    protected final ETNube nube;
    protected final VBox content;

    public FindMyWindow(String ownerName, ETNube nube) {
        this.ownerName = ownerName;
        this.nube = nube;

        setTitle("Find My – " + ownerName);
        content = new VBox(8);
        content.setPadding(new Insets(12));
        setScene(new Scene(content, 300, 350));
        refreshContent();
        show();
    }

    protected void refreshContent() {
        content.getChildren().clear();
        content.getChildren().add(new Label("Bienes de " + ownerName + ":"));
        List<ETNube.DeviceRecord> records = nube.getOwnerRecords(ownerName);
        if (records.isEmpty()) {
            content.getChildren().add(new Label("  (sin datos en la nube)"));
        } else {
            for (ETNube.DeviceRecord r : records) {
                String text = String.format("  %s.%s  →  (%.1f, %.1f)",
                        r.owner, r.device, r.x, r.y);
                content.getChildren().add(new Label(text));
            }
        }
    }
}
