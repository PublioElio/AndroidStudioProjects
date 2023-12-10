package grupos.activity;

/**
 * Esta clase integra los elementos que habrá en la lista que muestra los grupos de súper héroes.
 * Se compone de una imagen y el nombre del personaje.
 */
public class DatosGrupos {
    private final int fotoHeroe;
    private final String nombreHeroe;

    public DatosGrupos(int fotoHeroe, String nombreHeroe) {
        this.fotoHeroe = fotoHeroe;
        this.nombreHeroe = nombreHeroe;
    }

    public int getFotoHeroe() {
        return fotoHeroe;
    }

    public String getNombreHeroe() {
        return nombreHeroe;
    }
}
