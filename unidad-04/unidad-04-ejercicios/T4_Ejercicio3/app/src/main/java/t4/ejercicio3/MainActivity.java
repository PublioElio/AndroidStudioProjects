package t4.ejercicio3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void mandar_mensaje(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "¿Te está gustando el cómic que te presté?");
        startActivity(intent);
    }
    public void abrirPagina(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://boardgamegeek.com/"));
        startActivity(intent);
    }
    public void llamarTelefono(View view) {
        // Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:000000000")); // Está comentado porque no puedo usar este intent, al no tener permisos
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:652111838"));
        startActivity(intent);
    }
    public void verMapa(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:51.53723727830385, -0.18344577301573692"));
        startActivity(intent);
    }
    public void tomarFoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }
    public void mandarCorreo(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Fotos del concierto");
        intent.putExtra(Intent.EXTRA_TEXT, "Te adjunto las fotos del concierto");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"musera24@gmail.com"});
        startActivity(intent);
    }
    public void streetView(View view) {
        Intent intent = new
        Intent(Intent.ACTION_VIEW,Uri.parse("google.streetview:cbll=38.996766, -0.1652696&cbp=0,250,0,0,0")); //cbll=latitud,longitud&cbp=0,azimut,0,zoom,altura
        startActivity(intent);

        // A continuación está mi otro intento de visualizar street view
        /*
        double latitud = 36.95038964026492;
        double longitud = -4.546720595064295;
        String uri = "google.streetview:cbll=" + latitud + "," + longitud;
        Uri gmmIntentUri = Uri.parse(uri);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");  // Esto asegura que se abra en la aplicación de Google Maps
        startActivity(mapIntent);
         */
    }
}