package com.example.meuappdetarefas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // A TAG é usada para filtrar as mensagens no Logcat e ver apenas as da nossa Activity.
    private static final String TAG = "LifecycleExample";
    private TextView statusTextView;

    /**
     * Chamado quando a activity é criada pela primeira vez.
     * É aqui que você deve fazer a inicialização da maioria dos recursos,
     * como chamar setContentView() para inflar o layout da UI.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Encontra o TextView no layout para exibir o status atual
        statusTextView = findViewById(R.id.status_textview);

        logAndUpdate("onCreate()");
    }

    /**
     * Chamado quando a activity está prestes a se tornar visível para o usuário.
     * É seguido por onResume() se a activity for para o primeiro plano, ou onStop() se ficar oculta.
     */
    @Override
    protected void onStart() {
        super.onStart();
        logAndUpdate("onStart()");
    }

    /**
     * Chamado quando a activity começa a interagir com o usuário.
     * Neste ponto, a activity está no topo da pilha de activities, com o foco do usuário.
     */
    @Override
    protected void onResume() {
        super.onResume();
        logAndUpdate("onResume()");
    }

    /**
     * Chamado quando o sistema está prestes a iniciar outra activity.
     * Este método é normalmente usado para confirmar alterações não salvas, parar animações
     * e outras coisas que podem consumir CPU.
     */
    @Override
    protected void onPause() {
        super.onPause();
        logAndUpdate("onPause()");
    }

    /**
     * Chamado quando a activity não está mais visível para o usuário.
     * Isso pode acontecer porque uma nova activity está a cobrindo, uma existente
     * está vindo para o primeiro plano, ou a activity está sendo destruída.
     */
    @Override
    protected void onStop() {
        super.onStop();
        logAndUpdate("onStop()");
    }

    /**
     * Chamado depois que sua activity foi interrompida (onStop()), antes de ser iniciada novamente (onStart()).
     * Uma activity é reiniciada quando o usuário navega de volta para ela.
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        logAndUpdate("onRestart()");
    }

    /**
     * Chamado antes da activity ser destruída.
     * Esta é a chamada final que a activity recebe. Pode ser chamado porque a activity
     * está terminando (alguém chamou finish()) ou porque o sistema está
     * destruindo temporariamente esta instância da activity para economizar espaço.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() - Finalizando a Activity.");
        // Não podemos atualizar o TextView aqui, pois a UI já pode ter sido destruída.
    }

    /**
     * Função auxiliar para registrar a mensagem no Logcat e atualizar o TextView na tela.
     * @param message A mensagem representando o método do ciclo de vida.
     */
    private void logAndUpdate(String message) {
        Log.d(TAG, "Método " + message + " foi chamado.");
        if (statusTextView != null) {
            String currentText = statusTextView.getText().toString();
            // Mantém um histórico dos últimos eventos na tela
            if (currentText.split("\n").length > 10) {
                currentText = currentText.substring(currentText.indexOf("\n") + 1);
            }
            statusTextView.setText(currentText + "\n" + message);
        }
    }
}
