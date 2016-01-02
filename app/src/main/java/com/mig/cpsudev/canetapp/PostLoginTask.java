package com.mig.cpsudev.canetapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Mig on 25-Dec-15.
 */
public class PostLoginTask extends AsyncTask<String, Void, String> {
    private Context mContext;
    private User mUser;
    private String checksum;


    public PostLoginTask(Context mContext, User mUser) {
        this.mContext = mContext;
        this.mUser = mUser;
    }

    public String getChecksum() {
        return checksum;
    }

    @Override
    protected String doInBackground(String... urls) {
        return postData(urls[0], mUser);
    }

    private String postData(String url, User mUser) {
        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(url);

            String json = "";

            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("username", mUser.getUsername());
            jsonObject.accumulate("password", mUser.getPassword());

            json = jsonObject.toString();

            StringEntity se = new StringEntity(json);

            httpPost.setEntity(se);

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            HttpResponse httpResponse = httpclient.execute(httpPost);

            inputStream = httpResponse.getEntity().getContent();

            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;
    }

    @Override
    public void onPostExecute(String feedBackMessage) {
        TextView textView = (TextView) ((Activity)mContext).findViewById(R.id.StatusTextView);
        textView.setText(feedBackMessage);
        checksum = feedBackMessage.substring(13);
    }

    Intent i = new Intent(mContext,CourseActivity.class);
}
