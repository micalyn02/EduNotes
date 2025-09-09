package com.example.edunotes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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
        EditText etName = findViewById(R.id.etName);
        EditText etSEmail = findViewById(R.id.etSEmail);
        EditText etUsername = findViewById(R.id.etUsername);
        Button btnContinue = findViewById(R.id.btnContinue);

        // handle continue button click
        btnContinue.setOnClickListener(v -> {
            // get the values from the edit texts
            String name = etName.getText().toString();
            String schoolEmail = etSEmail.getText().toString();
            String username = etUsername.getText().toString();

            // check if all fields are filled
            if (!name.isEmpty() || !schoolEmail.isEmpty() || !username.isEmpty()) {
                // save login details to shared preferences
                SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("isLoggedIn", true);
                editor.putString("name", name);
                editor.putString("schoolEmail", schoolEmail);
                editor.putString("username", username);
                editor.apply();

                // go to main activity
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish(); // close the login activity
            } else {
                // show error message if any field is empty
                etName.setError("Name is required");
                etSEmail.setError("School email is required");
                etUsername.setError("Username is required");
            }
        });
    }
}