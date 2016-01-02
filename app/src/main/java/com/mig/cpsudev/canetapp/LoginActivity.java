package com.mig.cpsudev.canetapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private TextView status;
    private Button signin;
    private Button signup;
    private String url = "http://172.27.225.192:3000/api/sign_in";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.usernameEditText);
        password = (EditText) findViewById(R.id.passwordEditText);
        status = (TextView) findViewById(R.id.StatusTextView);

        signin = (Button) findViewById(R.id.signinbutton);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User loginUser = new User(username.getText().toString(), password.getText().toString());
                Toast.makeText(LoginActivity.this, "Sending", Toast.LENGTH_SHORT).show();
                PostLoginTask task = new PostLoginTask(LoginActivity.this, loginUser);
                task.execute(url);

                if (task.getChecksum().equals("1")) {
                    Intent i = new Intent(LoginActivity.this, CourseActivity.class);
                    startActivity(i);
                } else {
                    status.setText(R.string.loginFail);
                }
            }
        });

        signup = (Button) findViewById(R.id.signupbutton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, CourseActivity.class);
                startActivity(i);
            }
        });
    }
}
