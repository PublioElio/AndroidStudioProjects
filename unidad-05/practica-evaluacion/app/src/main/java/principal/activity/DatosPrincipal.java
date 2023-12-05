package principal.activity;

/**
 * Esta clase gestiona los datos del listado en la actividad principal, donde aparecen los listados de grupos de super héroes
 */
public class DatosPrincipal {
    private final int logoEquipo;

    public DatosPrincipal(int logoEquipo) {
        this.logoEquipo = logoEquipo;
    }

    public int getLogoEquipo() {
        return logoEquipo;
    }
}
