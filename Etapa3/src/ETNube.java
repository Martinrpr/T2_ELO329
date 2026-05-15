import java.util.ArrayList;
import java.util.List;

/**
 * Nube que almacena la última posición conocida de cada dispositivo.
 */
public class ETNube {

    private final List<Data> cloudData = new ArrayList<>();

    public synchronized void updateLocation(String owner, String device, double x, double y) {
        for (Data d : cloudData) {
            if (d.ownerName.equals(owner) && d.deviceName.equals(device)) {
                d.x = x;
                d.y = y;
                return;
            }
        }
        cloudData.add(new Data(owner, device, x, y));
    }

    /** Retorna todos los registros de un dueño. */
    public synchronized List<DeviceRecord> getOwnerRecords(String owner) {
        List<DeviceRecord> result = new ArrayList<>();
        for (Data d : cloudData) {
            if (d.ownerName.equals(owner))
                result.add(new DeviceRecord(d.ownerName, d.deviceName, d.x, d.y));
        }
        return result;
    }

    public static class DeviceRecord {
        public final String owner, device;
        public final double x, y;
        DeviceRecord(String o, String d, double x, double y) {
            owner = o; device = d; this.x = x; this.y = y;
        }
    }

    private static class Data {
        String ownerName, deviceName;
        double x, y;
        Data(String o, String d, double x, double y) {
            ownerName = o; deviceName = d; this.x = x; this.y = y;
        }
    }
}
