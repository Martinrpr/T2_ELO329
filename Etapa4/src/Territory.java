import java.util.ArrayList;
import java.util.List;

public class Territory {
    private ArrayList<Equipo> equipment = new ArrayList<>();

    public void addEquipment(Equipo e) { equipment.add(e); }

    public List<Cellular> getCellulars() {
        List<Cellular> result = new ArrayList<>();
        for (Equipo e : equipment)
            if (e instanceof Cellular) result.add((Cellular) e);
        return result;
    }

    public void moveAll(double dt) {
        for (Equipo e : equipment) e.move(dt);
    }

    private Cellular findNearbyCellular(EloTelTag tag) {
        for (Equipo e : equipment)
            if (e instanceof Cellular && tag.isWithinRange((Cellular) e))
                return (Cellular) e;
        return null;
    }

    public void reportTagLocations() {
        for (Equipo e : equipment) {
            if (e instanceof EloTelTag && !(e instanceof Tablet)) {
                Cellular near = findNearbyCellular((EloTelTag) e);
                if (near != null) near.reportTagLocation((EloTelTag) e);
            }
        }
    }

    public void reportTabletLocations() {
        for (Equipo e : equipment) {
            if (e instanceof Tablet) {
                Cellular near = findNearbyCellular((Tablet) e);
                if (near != null) near.reportTagLocation((Tablet) e);
            }
        }
    }
}
