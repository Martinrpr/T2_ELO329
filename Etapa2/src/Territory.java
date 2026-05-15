import java.util.ArrayList;
import java.util.List;

public class Territory {
    private ArrayList<Equipo> equipment = new ArrayList<>();

    public void addEquipment(Equipo eq) {
        equipment.add(eq);
    }

    public void moveAll(double timeStep) {
        for (Equipo eq : equipment)
            eq.move(timeStep);
    }

    public List<Cellular> getCellulars() {
        List<Cellular> list = new ArrayList<>();
        for (Equipo eq : equipment)
            if (eq instanceof Cellular) list.add((Cellular) eq);
        return list;
    }

    public List<EloTelTag> getTags() {
        List<EloTelTag> list = new ArrayList<>();
        for (Equipo eq : equipment)
            if (eq instanceof EloTelTag && !(eq instanceof Tablet)) list.add((EloTelTag) eq);
        return list;
    }

    public List<Tablet> getTablets() {
        List<Tablet> list = new ArrayList<>();
        for (Equipo eq : equipment)
            if (eq instanceof Tablet) list.add((Tablet) eq);
        return list;
    }
}
