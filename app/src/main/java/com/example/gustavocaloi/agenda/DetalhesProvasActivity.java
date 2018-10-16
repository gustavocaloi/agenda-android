package com.example.gustavocaloi.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gustavocaloi.agenda.modelo.Prova;

public class DetalhesProvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_provas);

        Intent intent = getIntent();
        Prova prova = (Prova) intent.getSerializableExtra("prova");

        TextView materia = (TextView) findViewById(R.id.detalhes_prova_materia);
        TextView data = (TextView) findViewById(R.id.detalhes_prova_data);
        ListView listaTopicos = (ListView) findViewById(R.id.detalhes_prova_topicos);

        materia.setText(prova.getMateria());
        data.setText(prova.getData());

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, prova.getTopicos());

        listaTopicos.setAdapter(adapter);
    }
}
