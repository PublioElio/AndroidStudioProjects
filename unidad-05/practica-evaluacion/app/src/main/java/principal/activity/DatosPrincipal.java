package principal.activity;

/**
 * Esta clase gestiona los datos del listado en la actividad principal, donde aparecen los listados
 * de equipos de super héroes. Simplemente contiene una imagen, que es el logo del equipo a mostrar
 */
public class DatosPrincipal {
    private final int logoEquipo;

    private final String nombreEquipo;

    public DatosPrincipal(int logoEquipo, String nombreEquipo) {
        this.logoEquipo = logoEquipo; this.nombreEquipo = nombreEquipo;
    }

    public int getLogoEquipo() {
        return logoEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }
}
