package com.example.ejercicio_10;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestTask extends AsyncTask<String, Void, String> {
    private TextView textView;

    public HttpRequestTask(TextView textView) {
        this.textView = textView;
    }

    @Override
    protected String doInBackground(String... params) {
        String urlString = params[0];
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                return stringBuilder.toString();
            } finally {
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (result != null) {
            textView.setText(result);
        } else {
            textView.setText("Error al cargar el contenido.");
        }
    }
}
