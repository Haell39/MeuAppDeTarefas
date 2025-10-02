package com.example.ciclodevida;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    // TAG é usada para filtrar as mensagens no Logcat e ver apenas as da nossa Activity.
    private static final String TAG = "LifecycleExample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "Método onCreate() foi chamado.");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Método onStart() foi chamado.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Método onResume() foi chamado.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Método onPause() foi chamado.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Método onStop() foi chamado.");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Método onRestart() foi chamado.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Método onDestroy() foi chamado.");
    }
}
