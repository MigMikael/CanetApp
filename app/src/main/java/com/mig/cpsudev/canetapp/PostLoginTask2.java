package com.mig.cpsudev.canetapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Mig on 12-Jan-16.
 */
public class PostLoginTask2 extends AsyncTask<String, Void, String>{

    private User mUser;
    private Context mContext;
    private TextView status;
    private ProgressDialog progressDialog;

    public PostLoginTask2(Context mContext, User mUser) {
        this.mUser = mUser;
        this.mContext = mContext;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(mContext, "Please wait ...", "Downloading Data ...", true);
    }

    @Override
    protected String doInBackground(String... urls) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.accumulate("username", mUser.getUsername());
            jsonObject.accumulate("password", mUser.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String json = jsonObject.toString();

        OkHttpClient okHttpClient = new OkHttpClient();

        Request.Builder builder = new Request.Builder();
        Request request = builder
                .url(urls[0])
                .post(RequestBody.create(JSON, json))
                .build();

        String responseText = "";
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                responseText = response.body().string();
            } else {
                responseText = "Not Success - Code : " + response.code();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseText;
    }

    @Override
    protected void onPostExecute(String feedBackStr) {
        super.onPostExecute(feedBackStr);
        status = (TextView) ((Activity) mContext).findViewById(R.id.StatusTextView);
        if (!feedBackStr.equals("{\"check_sum\":0}")) {
            Intent i = new Intent(mContext, CourseActivity.class);
            i.putExtra("courseList", feedBackStr);
            mContext.startActivity(i);
        } else {
            status.setText(R.string.loginFail);
        }
        progressDialog.dismiss();
    }
}
