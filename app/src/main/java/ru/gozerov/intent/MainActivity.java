package ru.gozerov.intent;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button goButton;
    public Button backButton;

    public EditText editText;

    private String text;

    public static String KEY = "ARG_KEY";

    private final ActivityResultLauncher<String> activityResultContract = registerForActivityResult(
        new ActivityTwoContract(),
        result -> {
            Toast.makeText(
                    this,
                    "Navigate from " + result + " to " + MainActivity.class.getSimpleName(),
                    Toast.LENGTH_SHORT
            ).show();
        }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.nextButton);
        backButton = findViewById(R.id.backButton);
        editText = findViewById(R.id.editText);


        text = editText.getText().toString();

        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                setResult();
            }
        });


        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = editText.getText().toString();
                activityResultContract.launch(text);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult();
            }
        });

    }

    private void setResult() {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.KEY, "ActivityMain");
        setResult(RESULT_OK, intent);
        finish();
    }

}