package ru.gozerov.intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityThree extends AppCompatActivity {

    public Button goButton;
    public Button backButton;

    private final ActivityResultLauncher<String> activityResultContract = registerForActivityResult(
        new MainActivityContract(),
        result -> {
            Toast.makeText(
                    this,
                    "Navigate from " + result + " to " + ActivityThree.class.getSimpleName(),
                    Toast.LENGTH_SHORT
            ).show();
        }
    );

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        goButton = findViewById(R.id.nextButton);
        backButton = findViewById(R.id.backButton);

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
        intent.putExtra(MainActivity.KEY, ActivityThree.class.getSimpleName());
        setResult(RESULT_OK, intent);
        finish();
    }


}
