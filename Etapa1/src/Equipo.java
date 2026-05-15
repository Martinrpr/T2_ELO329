import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Equipo {
    protected final String ownerName;
    protected DoubleProperty x = new SimpleDoubleProperty();
    protected DoubleProperty y = new SimpleDoubleProperty();
    private double r, theta, dtheta;

    public Equipo(String owner, double _x, double _y, double r, double theta, double dtheta) {
        ownerName = owner;
        x.set(_x);
        y.set(_y);
        this.r = r;
        this.theta = theta;
        this.dtheta = dtheta;
    }

    public DoubleProperty xProperty() { return x; }
    public DoubleProperty yProperty() { return y; }
    public String getOwnerName() { return ownerName; }
}
