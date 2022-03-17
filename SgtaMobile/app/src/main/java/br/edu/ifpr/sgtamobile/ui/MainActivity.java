package br.edu.ifpr.sgtamobile.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import br.edu.ifpr.sgtamobile.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void Aluno(View view) {
        Intent intent = new Intent(this, AlunoActivity.class);
        startActivity(intent);
    }

    public void Servidor(View view) {
        Intent intent = new Intent(this, ServidorActivity.class);
        startActivity(intent);
    }
    public void Tarefa(View view) {
        Intent intent = new Intent(this, TarefaActivity.class);
        startActivity(intent);
    }

    public void Turma(View view) {
        Intent intent = new Intent(this, CursoActivity.class);
        startActivity(intent);
    }
    public void Usuario(View view) {
        Intent intent = new Intent(this, UsuarioActivity.class);
        startActivity(intent);
    }

}