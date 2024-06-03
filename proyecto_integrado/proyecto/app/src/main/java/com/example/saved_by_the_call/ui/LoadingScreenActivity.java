package com.example.saved_by_the_call.ui;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.saved_by_the_call.R;

public class LoadingScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loading_screen);

        ConstraintLayout layout = findViewById(R.id.loadingScreenLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) layout.getBackground();
        animationDrawable.start();

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(LoadingScreenActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(0, R.anim.fade_out);
            finish();
        }, 5000);

    }
}
