package com.example.edittextmutilineaeje3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText cosas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cosas=findViewById(R.id.cosas);
        String[] archivos = fileList();
        if (existe(archivos, "notas.txt"))
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("notas.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String todo = "";
                while (linea != null) {
                    todo =todo + linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                cosas.setText(todo);
            } catch (IOException e) {
            }
    }
    private boolean existe(String[] archivos, String archbusca) {
        for (int f = 0; f < archivos.length; f++)
            if (archbusca.equals(archivos[f]))
                return true;
        return false;
    }
    public void grabar(View v) {
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("notas.txt",
                    Activity.MODE_PRIVATE));
            archivo.write(cosas.getText().toString());
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
        }
        Toast.makeText(this, "Los datos fueron grabados",Toast.LENGTH_SHORT).show();
        finish();
    }
}