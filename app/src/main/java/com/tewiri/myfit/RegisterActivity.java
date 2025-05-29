package com.tewiri.myfit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

//declrations
    private EditText fullnameTv, usernameTv, emailTv, passwordTv, confirmTv;
    private TextView loginTv;
    private Button registerBtn;
    private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

//        initializing
        fullnameTv = findViewById(R.id.fullnameEtRegister);
        usernameTv = findViewById(R.id.usernameEtRegister);
        emailTv=findViewById(R.id.emailEtRegister);
        passwordTv=findViewById(R.id.passwordEtRegister);
        confirmTv = findViewById(R.id.confirmPassword);
        registerBtn=findViewById(R.id.registrationButton);
        loginTv=findViewById(R.id.loginTv);
       dbHelper = new DatabaseHelper(this);

        loginTv.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        registerBtn.setOnClickListener(view -> {
            String fullname = fullnameTv.getText().toString().trim();
            String username = usernameTv.getText().toString().trim();
            String email = emailTv.getText().toString().trim();
            String password = passwordTv.getText().toString().trim();
            String confirm = confirmTv.getText().toString().trim();


            if(fullname.isEmpty()){
                Toast.makeText(this, "Input Full Name", Toast.LENGTH_SHORT).show();

            }
            else if(username.isEmpty()){
                Toast.makeText(this, "Input Username", Toast.LENGTH_SHORT).show();

            }
            else if(email.isEmpty()){
                Toast.makeText(this, "Input Email", Toast.LENGTH_SHORT).show();

            }
            else if(password.isEmpty()){
                Toast.makeText(this, "Input Password", Toast.LENGTH_SHORT).show();

            }
            else if(confirm.isEmpty()){
                Toast.makeText(this, "Input Confirm Password", Toast.LENGTH_SHORT).show();

            }

            else if(!password.equals(confirm)){ //password.compare(confirm)
                Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();

            }
            else if(password.length()<8){ //password.compare(confirm)
                Toast.makeText(this, "Password must be more than 8 characters", Toast.LENGTH_SHORT).show();

            }
            else{
//                // store the record din SQLite database/Room
                if(dbHelper.registerUser(fullname, username, email, password)){
                    Toast.makeText(this, "User Registered Successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(this, "Registration failed. Username or Password Already exists. Please try again", Toast.LENGTH_SHORT).show();
                }

            }

        });
//        login
    }
}