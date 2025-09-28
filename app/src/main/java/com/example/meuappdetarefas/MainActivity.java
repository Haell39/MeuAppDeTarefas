package com.example.meuappdetarefas;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AcaoTarefa {

    private static final String TAG = "CicloDeVida";
    private static final String TAREFA_KEY = "TAREFA_SALVA";

    private EditText editTextTarefa;
    private Button btnSalvar;
    private TextView textViewTarefaSalva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate() chamado.");

        editTextTarefa = findViewById(R.id.editTextTarefa);
        btnSalvar = findViewById(R.id.btnSalvar);
        textViewTarefaSalva = findViewById(R.id.textViewTarefaSalva);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String tituloTarefa = editTextTarefa.getText().toString();
                    if (tituloTarefa.isEmpty()) {
                        throw new Exception("O título da tarefa não pode ser vazio.");
                    }
                    salvar(tituloTarefa);
                } catch (Exception e) {
                    Log.e(TAG, "Erro ao salvar: " + e.getMessage());
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (savedInstanceState != null) {
            Log.d(TAG, "Restaurando estado em onCreate().");
            String tarefaSalva = savedInstanceState.getString(TAREFA_KEY);
            if (tarefaSalva != null && !tarefaSalva.isEmpty()) {
                textViewTarefaSalva.setText("Última tarefa salva: " + tarefaSalva);
            }
        }
    }

    @Override
    public void salvar(String titulo) {
        textViewTarefaSalva.setText("Última tarefa salva: " + titulo);
        Toast.makeText(this, "Tarefa '" + titulo + "' salva com sucesso!", Toast.LENGTH_SHORT).show();
        editTextTarefa.setText("");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() chamado.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() chamado.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() chamado.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() chamado.");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() chamado.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() chamado.");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState() chamado.");

        String textoAtual = textViewTarefaSalva.getText().toString();
        if (textoAtual.contains("Última tarefa salva: ")) {
            outState.putString(TAREFA_KEY, textoAtual.replace("Última tarefa salva: ", ""));
        }
    }
}