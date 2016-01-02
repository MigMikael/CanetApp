package com.mig.cpsudev.canetapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class QuestionActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        progressDialog = ProgressDialog.show(QuestionActivity.this, "Please wait ...", "Downloading Data ...", true);
        progressDialog.setCancelable(true);
    }
}
