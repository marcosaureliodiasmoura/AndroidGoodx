package com.example.goodx;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditarActivity extends AppCompatActivity {

    private EditText editNome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        editNome = (EditText)findViewById(R.id.edit_nome);


        if(getIntent().getStringExtra("i")==null){
            Toast.makeText(this, "nulo" , Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "nao nulo", Toast.LENGTH_SHORT).show();
            String value = getIntent().getStringExtra("i");
            editNome.setText(value);
        }
    }

}
