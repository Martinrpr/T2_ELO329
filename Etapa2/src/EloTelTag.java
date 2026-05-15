public class EloTelTag extends Equipo {
    private final String name;
    private static final double DETECTION_RANGE = 50.0;

    public EloTelTag(String owner, String n, double x, double y, double r, double theta, double dtheta) {
        super(owner, x, y, r, theta, dtheta);
        name = n;
    }

    public String getName() { return name; }

    public boolean isWithinRange(Cellular cell) {
        double dx = cell.xProperty().get() - x.get();
        double dy = cell.yProperty().get() - y.get();
        return Math.hypot(dx, dy) <= DETECTION_RANGE;
    }
}
