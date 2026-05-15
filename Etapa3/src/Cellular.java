public class Cellular extends Equipo {
    private final ETNube nube;

    public Cellular(String owner, double x, double y, double r, double theta, double dtheta, ETNube nube) {
        super(owner, x, y, r, theta, dtheta);
        this.nube = nube;
    }

    public void reportLocation() {
        nube.updateLocation(ownerName, "celular", x.get(), y.get());
    }

    public void reportTagLocation(EloTelTag tag) {
        nube.updateLocation(tag.getOwnerName(), tag.getName(), x.get(), y.get());
    }
}
