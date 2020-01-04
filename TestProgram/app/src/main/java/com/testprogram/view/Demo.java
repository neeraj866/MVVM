package com.testprogram.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.testprogram.R;

public class Demo extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView mTxtProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);
        progressBar = findViewById(R.id.simpleProgressBar);
        progressBar.setProgress(60);
        progressBar.setPadding(0,20,0,20);

        mTxtProgress = findViewById(R.id.progress);
        mTxtProgress.setText("60%");
    }
}
