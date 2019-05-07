package com.example.goodx;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdicionarActivity extends AppCompatActivity {

    private Button btnRegistrar;
    private EditText nome;
    private SQLiteDatabase bda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);

        btnRegistrar = (Button)findViewById(R.id.btn_Registrar);
        nome = (EditText)findViewById(R.id.edt_nome);

        bda = openOrCreateDatabase("meubanco", MODE_PRIVATE, null);
        bda.execSQL("CREATE TABLE IF NOT EXISTS projetos(id INTEGER PRIMARY KEY AUTOINCREMENT, projeto VARCHAR)");

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String nomeproj  = nome.getText().toString();

              registrarProjeto(nomeproj);
                startActivity(new Intent(getBaseContext(), MainActivity.class));

            }

            private void registrarProjeto(String nomeproj) {

                try {
                    if (nomeproj.equals("") ) {
                        Toast.makeText(AdicionarActivity.this, "Complete os campos nome e descrição", Toast.LENGTH_SHORT).show();
                    } else {
                        bda.execSQL("INSERT INTO projetos (projeto) VALUES(' " + nomeproj + "')");
                        @SuppressLint("WrongConstant") SQLiteDatabase db =   openOrCreateDatabase("meubanco", SQLiteDatabase.CREATE_IF_NECESSARY, null);


                        Toast.makeText(AdicionarActivity.this, "Projeto registrado com sucesso!", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } });
        }
    }