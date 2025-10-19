package com.example.edunotes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignupActivity extends AppCompatActivity {

    // creating variables for textview, edittext, button, and dbhandler
    TextView tvSignupGreet;
    EditText etFullName;
    EditText etSEmail;
    EditText etSPassword;
    Button btnRegister;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        // initializing all variables
        tvSignupGreet = findViewById(R.id.tvSignupGreet);
        etFullName = findViewById(R.id.etFullName);
        etSEmail = findViewById(R.id.etSEmail);
        etSPassword = findViewById(R.id.etSPassword);
        btnRegister = findViewById(R.id.btnRegister);

        // creating new dbhandler class and passing context to it
        dbHandler = new DBHandler(SignupActivity. this);

        // setting on click listener for register button
        btnRegister.setOnClickListener(v -> {
            //@Override
            //public void onClick(View v) {

                // getting values from edittext
                String fullName = etFullName.getText().toString();
                String email = etSEmail.getText().toString();
                String password = etSPassword.getText().toString();

                // checking if all fields are filled
                if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling add user method to add user to database
                dbHandler.addUser(fullName, email, password);

                // after inputting data display a toast message
                Toast.makeText(SignupActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                etFullName.setText("");
                etSEmail.setText("");
                etSPassword.setText("");

                // navigate back to login activity after registering
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            //}
        });
    }
}