package grupos.activity;

public class DatosGrupos {
    private final int fotoHeroe;
    private final String nombreHeroe;
    private boolean seleccionado;
    public DatosGrupos(int fotoHeroe, String nombreHeroe, boolean seleccionado) {
        this.fotoHeroe = fotoHeroe;
        this.nombreHeroe = nombreHeroe;
        this.seleccionado = seleccionado;
    }

    public int getFotoHeroe() {
        return fotoHeroe;
    }

    public String getNombreHeroe() {
        return nombreHeroe;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
}
