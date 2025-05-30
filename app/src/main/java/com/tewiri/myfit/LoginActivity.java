package com.tewiri.myfit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    TextView registrationLinkTv;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

//        initialize
        usernameEt = findViewById(R.id.usernameEtLogin);
        passwordEt=findViewById(R.id.passwordEtLogin);
        loginBtn=findViewById(R.id.loginBtn);
        registrationLinkTv=findViewById(R.id.registerTv);
        // Initialize database helper
        databaseHelper = new DatabaseHelper(this);


//        use registrationLinkTv
        registrationLinkTv.setOnClickListener(view -> {
            Intent linktoregisteractivity = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(linktoregisteractivity);
            finish();

        });

//        validations
//        username, password
//        login button clicked handler
        loginBtn.setOnClickListener(view -> {
            String username = usernameEt.getText().toString().trim();
            String password = passwordEt.getText().toString().trim();

            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Input all credentials", Toast.LENGTH_SHORT).show();

            }
            if(username.equals("admin") && password.equals("admin")){
                Toast.makeText(this, "Welcome Admin", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
//            check credentials
            boolean loginSuccessful = databaseHelper.checkUser(username, password);
            if(loginSuccessful){
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                pass username to main activity to be able display profile username
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
            } else{
//                intent-> new screen(move from current activity(LoginActivity.this, MainActivity.class
               Toast.makeText(this, "Invalid Credentials, please register", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    protected void onDestroy() {
        databaseHelper.close();
        super.onDestroy();
    }
}