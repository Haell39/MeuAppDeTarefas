// Define o "pacote" ou a pasta onde esta classe Java está localizada.
// É como um endereço para organizar o projeto.
package com.example.meuappdetarefas;

// Importa classes essenciais do Android que nosso código vai precisar usar.
// Sem eles, o Java não saberia o que é um 'Bundle', 'Button', 'Log', etc.
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity é a nossa tela principal.
 * 'extends AppCompatActivity' significa que ela herda todo o comportamento básico de uma tela do Android.
 * 'implements AcaoTarefa' significa que ela promete (assina um contrato) que terá um método chamado 'salvar'.
 * Este é um exemplo de HERANÇA e INTERFACE da Programação Orientada a Objetos.
 */
public class MainActivity extends AppCompatActivity implements AcaoTarefa {

    // --- VARIÁVEIS DE CLASSE (FIELDS) ---

    // Constante para filtrar as mensagens no Logcat. Facilita a depuração.
    private static final String TAG = "CicloDeVida";
    
    // Constante para usar como chave ao salvar/restaurar o estado. Evita erros de digitação.
    private static final String TAREFA_KEY = "TAREFA_SALVA";

    // Declaração das variáveis para os componentes da UI (Views).
    // São declaradas aqui (como 'fields') para que possam ser acessadas por QUALQUER método da classe.
    private EditText editTextTarefa;
    private Button btnSalvar;
    private TextView textViewTarefaSalva;

    /**
     * MÉTODO ONCREATE
     * Este é o primeiro método do ciclo de vida a ser chamado. É como o "ponto de partida" da nossa tela.
     * É executado APENAS UMA VEZ quando a Activity é criada.
     * Perfeito para configurações iniciais.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Chama a implementação do método da classe pai (AppCompatActivity). É obrigatório.
        super.onCreate(savedInstanceState);
        
        // Conecta este arquivo Java com o arquivo de layout XML (activity_main.xml), desenhando a interface na tela.
        setContentView(R.layout.activity_main);
        
        // Registra uma mensagem no Logcat para podermos acompanhar o ciclo de vida.
        Log.d(TAG, "onCreate() chamado.");

        // --- VINCULANDO COMPONENTES DA UI ---
        // A função 'findViewById' localiza um componente no XML através do seu ID único (R.id.*)
        // e o atribui à nossa variável Java correspondente.
        editTextTarefa = findViewById(R.id.editTextTarefa);
        btnSalvar = findViewById(R.id.btnSalvar);
        textViewTarefaSalva = findViewById(R.id.textViewTarefaSalva);

        // --- CONFIGURANDO EVENTOS (LISTENERS) ---
        // 'setOnClickListener' anexa um "ouvinte" ao botão. O código dentro de 'onClick'
        // só será executado quando o usuário clicar no botão.
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // --- TRATAMENTO DE EXCEÇÃO ---
                // O bloco 'try' contém código que pode gerar um erro (uma exceção).
                try {
                    String tituloTarefa = editTextTarefa.getText().toString();
                    // Validação de entrada: verificamos se o usuário digitou algo.
                    if (tituloTarefa.isEmpty()) {
                        // Se não digitou, nós lançamos um erro intencionalmente.
                        throw new Exception("O título da tarefa não pode ser vazio.");
                    }
                    // Se a validação passou, chamamos nosso método para salvar.
                    salvar(tituloTarefa);
                } catch (Exception e) {
                    // O bloco 'catch' "captura" o erro lançado no 'try', evitando que o app quebre.
                    // Aqui, tratamos o erro de forma amigável.
                    Log.e(TAG, "Erro ao salvar: " + e.getMessage());
                    // Exibimos uma pequena mensagem pop-up (Toast) para o usuário.
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // --- RESTAURANDO O ESTADO DA INSTÂNCIA ---
        // 'savedInstanceState' só não é nulo se a Activity estiver sendo recriada
        // (ex: após girar a tela) e se tivermos salvo dados anteriormente.
        if (savedInstanceState != null) {
            Log.d(TAG, "Restaurando estado em onCreate().");
            // Pegamos a string que salvamos em 'onSaveInstanceState' usando a mesma chave.
            String tarefaSalva = savedInstanceState.getString(TAREFA_KEY);
            // Verificamos se a string não é nula ou vazia antes de usá-la.
            if (tarefaSalva != null && !tarefaSalva.isEmpty()) {
                textViewTarefaSalva.setText("Última tarefa salva: " + tarefaSalva);
            }
        }
    }

    /**
     * MÉTODO SALVAR
     * Esta é a implementação obrigatória do método definido na nossa interface 'AcaoTarefa'.
     * A anotação '@Override' confirma ao compilador que estamos sobrescrevendo um método.
     */
    @Override
    public void salvar(String titulo) {
        // Atualiza o texto na tela para mostrar a tarefa salva.
        textViewTarefaSalva.setText("Última tarefa salva: " + titulo);
        // Mostra uma mensagem de confirmação para o usuário.
        Toast.makeText(this, "Tarefa '" + titulo + "' salva com sucesso!", Toast.LENGTH_SHORT).show();
        // Limpa o campo de digitação para a próxima tarefa.
        editTextTarefa.setText("");
    }

    // --- DEMAIS MÉTODOS DO CICLO DE VIDA ---
    // Apenas registram no Logcat para fins de estudo.

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() chamado. (A tela está prestes a se tornar visível)");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() chamado. (O usuário pode interagir com a tela)");
    }



    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() chamado. (A tela está perdendo o foco)");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() chamado. (A tela não está mais visível)");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() chamado. (A tela está voltando a ser exibida)");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() chamado. (A tela está sendo destruída)");
    }

    /**
     * MÉTODO ONSAVEINSTANCESTATE
     * O sistema chama este método antes de destruir a Activity por uma mudança de configuração (como girar a tela).
     * É nossa chance de salvar dados temporários sobre o estado da UI.
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState() chamado.");

        String textoAtual = textViewTarefaSalva.getText().toString();
        // Verificamos se já existe uma tarefa salva para não salvar o texto padrão "Nenhuma tarefa salva".
        if (textoAtual.contains("Última tarefa salva: ")) {
            // Salvamos a tarefa no objeto 'outState' (um "pacote" de dados) usando nosso par chave-valor.
            outState.putString(TAREFA_KEY, textoAtual.replace("Última tarefa salva: ", ""));
        }
    }
}
