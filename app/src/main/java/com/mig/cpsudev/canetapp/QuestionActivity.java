package com.mig.cpsudev.canetapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private QuestionRecyclerViewAdapter mAdapter;

    protected ArrayList<String> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        mData.add("What is your name ?");
        mData.add("Where are you ?");
        mData.add("Where is the love ?");
        mData.add("What is your name ?");
        mData.add("Who is the one ?");

        mRecyclerView = (RecyclerView) findViewById(R.id.questionRecyclerView);
        mLayoutManager = new GridLayoutManager(QuestionActivity.this, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new QuestionRecyclerViewAdapter(QuestionActivity.this, mData);
        mRecyclerView.setAdapter(mAdapter);

    }
}
