package br.edu.ifpr.sgtamobile.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import br.edu.ifpr.sgtamobile.R;

public class UserCadastroActivity extends AppCompatActivity {

    private Toolbar tlbMainPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cadastro);

        tlbMainPage = findViewById(R.id.tlb_main_page);
        setSupportActionBar(tlbMainPage);
        getSupportActionBar().setTitle("Gerenciamento de Tarefas");
    }
}