package com.mig.cpsudev.canetapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

public class ChapterActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    private ListView chapterListView;
    private ArrayAdapter adapter;
    private String [] mData = new String[]{"test", "test2", "test3", "test4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        progressDialog = ProgressDialog.show(ChapterActivity.this, "Please wait ...", "Downloading Data ...", true);
        progressDialog.setCancelable(true);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mData);
        chapterListView = (ListView) findViewById(R.id.chapterListView);
        chapterListView.setAdapter(adapter);
        chapterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ChapterActivity.this, QuestionActivity.class);
                startActivity(i);
            }
        });

        progressDialog.dismiss();

    }
}
