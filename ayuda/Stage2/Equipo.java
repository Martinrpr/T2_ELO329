import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Equipo {
    protected final String ownerName;
    protected DoubleProperty x = new SimpleDoubleProperty();
    protected DoubleProperty y = new SimpleDoubleProperty();
    private double r,theta, dtheta;

    public Equipo(String owner, double _x, double _y, double r, double theta, double dtheta) {
        ownerName = owner;
        x.set(_x);
        y.set(_y);
  /* ¿...?  */
    }

    public void move(double dt) {
        theta += (Math.random()-0.5)*dtheta;
/*.  ¿...?*/
        if (x.get()<0 || x.get()>TerritoryView.WIDTH )
// ¿...?
        if (y.get()<0 || y.get()>TerritoryView.HEIGHT )
// ¿...?
    }

    public DoubleProperty xProperty() { return x; }
    public DoubleProperty yProperty() { return y; }

    public String getOwnerName() { return ownerName; }
}
