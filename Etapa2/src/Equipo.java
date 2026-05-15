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

    public void move(double dt) {
        theta += (Math.random() - 0.5) * dtheta;
        x.set(x.get() + r * Math.cos(theta) * dt);
        y.set(y.get() + r * Math.sin(theta) * dt);
        if (x.get() < 0 || x.get() > TerritoryView.WIDTH)
            theta = Math.PI - theta;
        if (y.get() < 0 || y.get() > TerritoryView.HEIGHT)
            theta = -theta;
    }

    public DoubleProperty xProperty() { return x; }
    public DoubleProperty yProperty() { return y; }
    public double getX() { return x.get(); }
    public double getY() { return y.get(); }
    public String getOwnerName() { return ownerName; }
}
