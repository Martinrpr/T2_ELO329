import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

/**
 * Ventana Find My que muestra y actualiza cada segundo la posición
 * de todos los bienes de una persona registrados en la nube.
 */
public class FindMyWindow extends Stage {

    private final String ownerName;
    private final ETNube nube;
    private final VBox content;
    private Timeline updateTimer;

    public FindMyWindow(String ownerName, ETNube nube) {
        this.ownerName = ownerName;
        this.nube = nube;

        setTitle("Find My – " + ownerName);
        content = new VBox(8);
        content.setPadding(new Insets(12));
        setScene(new Scene(content, 300, 350));

        refreshContent();
        startAutoUpdate();
        show();

        setOnCloseRequest(e -> stopAutoUpdate());
    }

    private void refreshContent() {
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

    private void startAutoUpdate() {
        updateTimer = new Timeline(new KeyFrame(Duration.seconds(1), e -> refreshContent()));
        updateTimer.setCycleCount(Animation.INDEFINITE);
        updateTimer.play();
    }

    private void stopAutoUpdate() {
        if (updateTimer != null) updateTimer.stop();
    }
}
