package com.mig.cpsudev.canetapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CourseActivity extends AppCompatActivity {

    private static final String TAG = "CourseActivity";

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CourseRecyclerViewAdapter mAdapter;

    protected String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        mRecyclerView = (RecyclerView) findViewById(R.id.courseRecyclerView);
        mLayoutManager = new GridLayoutManager(CourseActivity.this, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Load Data here.
        LoadCourseTask task = new LoadCourseTask(CourseActivity.this);
        task.execute("http://jsonplaceholder.typicode.com/posts/1");
    }

    public class LoadCourseTask extends AsyncTask<String, Void, String>{
        private Context mContext;
        private ProgressDialog progressDialog;

        public LoadCourseTask(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(mContext, "Please wait ...", "Downloading Data ...", true);
        }

        @Override
        protected String doInBackground(String... urls) {
            return loadData(urls[0]);
        }

        private String loadData(String urls) {
            String strResult = "";
            try {
                URL url = new URL(urls);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                strResult = readStream(con.getInputStream());
            }catch(Exception e){
                e.printStackTrace();
            }
            return strResult;
        }

        private String readStream(InputStream in) {
            BufferedReader reader = null;
            StringBuilder sb = new StringBuilder();
            try{
                reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while((line = reader.readLine()) != null){
                    sb.append(line);
                }
            }catch(IOException e){
                e.printStackTrace();
            }finally {
                if (reader != null){
                    try {
                        reader.close();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }
            return sb.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            
            Log.i(TAG, result);

            // Extract JSON here
            data = new String[]{"aaaaaaaaaa", "bbbbbbbbbb", "cccccccccc", "dddddddddd", "eeeeeeeeee", "ffffffffff", "gggggggggg"};

            // Set Data to Adaptor
            mAdapter = new CourseRecyclerViewAdapter(CourseActivity.this, data);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}