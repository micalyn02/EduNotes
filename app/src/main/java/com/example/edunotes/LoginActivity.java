package com.example.edunotes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // reference to name, school email, username and continue button
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnSignup = findViewById(R.id.btnSignup);

        DBHandler dbHandler;
        // initialize db handler
        dbHandler = new DBHandler(LoginActivity.this);

        // handle login button click
        btnLogin.setOnClickListener(v -> {
            // get the values from the edit texts
            String Email = etEmail.getText().toString().trim();
            String Password = etPassword.getText().toString().trim();

            // check if all fields are filled
            if (Email.isEmpty() || Password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please fill all fieldsl", Toast.LENGTH_SHORT).show();
                return;
            }
            // check if user is registered
            Boolean checkUser = dbHandler.checkUser(Email, Password);

            if (checkUser) {
                // save login details to shared preferences
                SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("isLoggedIn", true);
                editor.putString("email", Email);
                editor.putString("password", Password);
                editor.apply();

                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                // go to main activity
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish(); // close the login activity
            } else {
                Toast.makeText(LoginActivity.this, "Login failed. Incorrect email or password.", Toast.LENGTH_SHORT).show();
            }
        });

        // handle signup button click
        btnSignup.setOnClickListener(v1 -> {
            // go to signup activity
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        });
    }
}