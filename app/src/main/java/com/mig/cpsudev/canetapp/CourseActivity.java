package com.mig.cpsudev.canetapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity {

    private static final String TAG = "CourseActivity";

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CourseRecyclerViewAdapter mAdapter;

    protected ArrayList<String> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        mRecyclerView = (RecyclerView) findViewById(R.id.courseRecyclerView);
        mLayoutManager = new GridLayoutManager(CourseActivity.this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Intent intent = getIntent();
        String feedBackStr = intent.getStringExtra("courseList");
        Log.i(TAG, feedBackStr);
        try {
            JSONObject jsonObject = new JSONObject(feedBackStr);
            JSONArray courseList = jsonObject.getJSONArray("list_courses");
            for (int i = 0 ; i < courseList.length() ; i++){
                JSONObject course = courseList.getJSONObject(i);
                String courseName = course.getString("Name");
                mData.add(courseName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mAdapter = new CourseRecyclerViewAdapter(CourseActivity.this, mData);
        mRecyclerView.setAdapter(mAdapter);
    }
}
