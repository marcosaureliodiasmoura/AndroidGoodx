package com.example.goodx;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private Button btnAdicionar;
    private ListView lista;
    private SQLiteDatabase bda;

    private ArrayAdapter<String> itensAdapter;
    private ArrayList<String> itens;
    private ArrayList<Integer> ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdicionar = (Button) findViewById(R.id.btn_Adicionar);
        lista = (ListView) findViewById(R.id.listadeitens);

        try {

            bda = openOrCreateDatabase("meubanco", MODE_PRIVATE, null);
            bda.execSQL("CREATE TABLE IF NOT EXISTS projetos(id INTEGER PRIMARY KEY AUTOINCREMENT, projeto VARCHAR)");


            btnAdicionar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getBaseContext(), AdicionarActivity.class));
                }
            });

            lista.setLongClickable(true);
            lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                    Log.i("Item", position + "/ " + ids.get(position));
                    removerProjeto(ids.get(position));
                    return true;
                }
            });


            //Clique dos itens da lista
            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent it = new Intent(MainActivity.this, EditarActivity.class);

//                        it.putExtra("i", "0");
                        it.putExtra("i", itens.get(position));
                        startActivity(it);
                    Log.i("Item", position + "/ " + ids.get(position) + "/" + itens.get(position));
                    Toast.makeText(MainActivity.this, position + "/ " + ids.get(position) + "/" + itens.get(position), Toast.LENGTH_SHORT).show();
                }
            });

            listarProjetos();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void listarProjetos() {
        Cursor cursor = bda.rawQuery("SELECT * FROM projetos", null);

        int indiceColunaId = cursor.getColumnIndex("id");
        int indiceColunaProjeto = cursor.getColumnIndex("projeto");



        //Criamos itens que é um arraylist de Strings
        itens = new ArrayList<String>();
        ids = new ArrayList<Integer>();

        //Criamos o nosso adapter
        itensAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
        android.R.id.text1,
        itens);

        //Adicionamos os itens no adapter que criamos
        lista.setAdapter(itensAdapter);

        cursor.moveToFirst();
        while (cursor != null) {
//            Log.i("Resultado", "Projeto" + cursor.getString(indiceColunaProjeto));

            //Adicionamos os elementos dentro do arraylist.
            itens.add(cursor.getString(indiceColunaProjeto));
            ids.add(Integer.parseInt(cursor.getString(indiceColunaId)));
            cursor.moveToNext();
        }

    }

    private void removerProjeto(Integer id){
        try{
                bda.execSQL("DELETE FROM projetos WHERE id="+id);
                listarProjetos();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
