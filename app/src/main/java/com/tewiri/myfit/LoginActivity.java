package com.tewiri.myfit;

import android.content.Intent;
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

//    declare UI elements used in the application(Login Activity UI)
    EditText usernameEt, passwordEt;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

//        initialize
        usernameEt = findViewById(R.id.usernameEtLogin);
        passwordEt=findViewById(R.id.passwordEtLogin);
        loginBtn=findViewById(R.id.loginBtn);

//        validations
//        username, password
//        login
        loginBtn.setOnClickListener(view -> {
            String username = usernameEt.getText().toString().trim();
            String password = passwordEt.getText().toString().trim();

            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Input all credentials", Toast.LENGTH_SHORT).show();

            }
            else{

//                intent-> new screen(move from current activity(LoginActivity.this, MainActivity.class
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

            }


        });











        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}