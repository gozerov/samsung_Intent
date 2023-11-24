package ru.gozerov.intent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ActivityTwoContract extends ActivityResultContract<String, String> {

    @NonNull
    @Override
    public Intent createIntent(@NonNull Context context, String s) {
        Intent intent = new Intent(context, ActivityTwo.class);
        intent.putExtra(MainActivity.KEY, s);
        return intent;
    }

    @Override
    public String parseResult(int resultCode, @Nullable Intent intent) {
        if (resultCode == Activity.RESULT_OK && intent != null)
            return intent.getStringExtra(MainActivity.KEY);
        else
            return null;
    }

}
