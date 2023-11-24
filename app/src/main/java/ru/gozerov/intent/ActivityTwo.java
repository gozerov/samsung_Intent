package ru.gozerov.intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityTwo extends AppCompatActivity {

    public TextView textView;
    public Button goButton;
    public Button backButton;

    private final ActivityResultLauncher<String> activityResultContract = registerForActivityResult(
        new ActivityThreeContract(),
        result -> {
            Toast.makeText(
                    this,
                    "Navigate from " + result + " to " + ActivityTwo.class.getSimpleName(),
                    Toast.LENGTH_SHORT
            ).show();
        }
    );

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        textView = findViewById(R.id.txtMain);
        goButton = findViewById(R.id.nextButton);
        backButton = findViewById(R.id.backButton);
        textView.setText(getIntent().getStringExtra(MainActivity.KEY));

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityResultContract.launch(ActivityThree.class.getSimpleName());
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNavigationIntent();
            }
        });

        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                createNavigationIntent();
            }
        });

    }

    private void createNavigationIntent() {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.KEY, ActivityTwo.class.getSimpleName());
        setResult(RESULT_OK, intent);
        finish();
    }

}
