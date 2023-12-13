package tiendas.activity;

/**
 * Esta clase tiene los datos de una tienda de cómics
 */
public class DatosTiendas {
    private final int imgTienda;
    private final String nombreTienda;
    private final String direccionTienda;
    private final String googleMapsTienda;
    private final String correoTienda;
    private final String tlfnTienda;
    private boolean checkBoxTienda;

    public DatosTiendas(String nombreTienda, int imgTienda, String direccionTienda,
                        String googleMapsTienda, String correoTienda, boolean checkBoxTienda, String tlfnTienda) {
        this.nombreTienda = nombreTienda;
        this.imgTienda = imgTienda;
        this.direccionTienda = direccionTienda;
        this.googleMapsTienda = googleMapsTienda;
        this.correoTienda = correoTienda;
        this.checkBoxTienda = checkBoxTienda;
        this.tlfnTienda = tlfnTienda;
    }

    public int getImgTienda() {
        return imgTienda;
    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public String getDireccionTienda() {
        return direccionTienda;
    }

    public String getGoogleMapsTienda() {
        return googleMapsTienda;
    }

    public String getTlfnTienda() {
        return tlfnTienda;
    }

    public boolean isCheckBoxTienda() {
        return checkBoxTienda;
    }

    public void setCheckBoxTienda(boolean checkBoxTienda) {
        this.checkBoxTienda = checkBoxTienda;
    }
    public String getCorreoTienda() {
        return correoTienda;
    }
}
