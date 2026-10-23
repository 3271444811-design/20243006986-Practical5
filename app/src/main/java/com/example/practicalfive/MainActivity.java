package com.example.practicalfive;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MUNICIPALITY = "municipality_name";

    private EditText editTextMunicipality;
    private TextProcessor textProcessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMunicipality = findViewById(R.id.editTextMunicipality);
        textProcessor = new TextProcessor();

        editTextMunicipality.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            boolean enterPressed = keyEvent != null
                    && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER
                    && keyEvent.getAction() == KeyEvent.ACTION_DOWN;

            if (actionId == EditorInfo.IME_ACTION_GO
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || enterPressed) {
                processUserInput();
                return true;
            }

            return false;
        });
    }

    private void processUserInput() {
        String userInput = editTextMunicipality.getText().toString();
        String municipality = textProcessor.processMunicipality(userInput);

        if (municipality == null) {
            editTextMunicipality.setError("Enter Beijing, Shanghai, Tianjin, or Chongqing");
            Toast.makeText(
                    this,
                    "Invalid municipality. Please try again.",
                    Toast.LENGTH_SHORT
            ).show();
        } else {
            Intent intent = new Intent(MainActivity.this, Municipality.class);
            intent.putExtra(EXTRA_MUNICIPALITY, municipality);
            startActivity(intent);
        }
    }
}