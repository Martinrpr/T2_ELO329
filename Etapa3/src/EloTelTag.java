public class EloTelTag extends Equipo {
    private final String name;
    public static final double DETECTION_RANGE = 50.0;

    public EloTelTag(String owner, String n, double x, double y, double r, double theta, double dtheta) {
        super(owner, x, y, r, theta, dtheta);
        name = n;
    }

    public String getName() { return name; }

    public boolean isWithinRange(Cellular cell) {
        return Math.hypot(cell.getX() - getX(), cell.getY() - getY()) <= DETECTION_RANGE;
    }
}
