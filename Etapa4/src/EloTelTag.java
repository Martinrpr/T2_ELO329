/**
 * Dispositivo EloTelTag que extiende {@link Equipo}.
 * <p>
 * Modela un tag de rastreo (similar a un AirTag) que puede detectar
 * celulares cercanos dentro de un radio de {@value #DETECTION_RANGE} píxeles.
 * Cuando un celular se encuentra en rango, éste reporta a {@link ETNube}
 * el nombre del tag, su propietario y la posición del celular en ese instante.
 * </p>
 *
 * @author ELO329 Tarea 2
 * @version 4.0
 * @see Equipo
 * @see Cellular
 * @see ETNube
 */
public class EloTelTag extends Equipo {

    /**
     * Radio de detección de celulares en píxeles.
     * Coincide con el radio máximo de la animación de radar en la vista.
     */
    public static final double DETECTION_RANGE = 50.0;

    /** Identificador del tag (p.ej. "maleta", "llaves"). */
    private final String name;

    /**
     * Crea un EloTelTag con posición y parámetros de movimiento iniciales.
     *
     * @param owner  nombre del propietario
     * @param n      identificador del tag
     * @param x      posición inicial horizontal (px)
     * @param y      posición inicial vertical (px)
     * @param r      rapidez en px/s
     * @param theta  ángulo inicial en radianes
     * @param dtheta variación máxima del ángulo por paso (radianes)
     */
    public EloTelTag(String owner, String n, double x, double y, double r, double theta, double dtheta) {
        super(owner, x, y, r, theta, dtheta);
        name = n;
    }

    /**
     * Determina si el celular dado está dentro del rango de detección.
     *
     * @param cell el celular a evaluar
     * @return {@code true} si la distancia euclídea es ≤ {@value #DETECTION_RANGE} px
     */
    public boolean isWithinRange(Cellular cell) {
        return Math.hypot(cell.getX() - x.get(), cell.getY() - y.get()) <= DETECTION_RANGE;
    }

    /**
     * @return identificador del tag (p.ej. "maleta", "llaves")
     */
    public String getName() { return name; }
}
