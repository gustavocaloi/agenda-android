package com.example.gustavocaloi.agenda;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.gustavocaloi.agenda.converter.AlunoConverter;
import com.example.gustavocaloi.agenda.dao.AlunoDAO;
import com.example.gustavocaloi.agenda.modelo.Aluno;

import java.util.List;


public class EnviaAlunosTask extends AsyncTask<Void, Void, String> {

    private Context context;
    private ProgressDialog alertDialog;

    public EnviaAlunosTask( Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        alertDialog = ProgressDialog.show(context,"Aguarde" , "Enviando para o servidor ...", true, true);
        alertDialog.show();
    }

    @Override
    protected String doInBackground(Void... params) {

        WebClient webClient = new WebClient();
        AlunoConverter converter = new AlunoConverter();
        AlunoDAO dao = new AlunoDAO(context);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();
        String json = converter.converteParaJSON(alunos);
        String resposta = webClient.post(json);

        return resposta ;
    }

    @Override
    protected void onPostExecute(String resposta) {
        alertDialog.dismiss();
        Toast.makeText(context, resposta, Toast.LENGTH_LONG).show();    }
}
