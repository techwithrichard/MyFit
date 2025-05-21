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

public class RegisterActivity extends AppCompatActivity {

//declrations
    EditText fullnameTv, usernameTv, emailTv, passwordTv, confirmTv;
    Button regsiterBtn;
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
        regsiterBtn=findViewById(R.id.registrationButton);

        regsiterBtn.setOnClickListener(view -> {
            String fullname = fullnameTv.getText().toString().trim();
            String username = usernameTv.getText().toString().trim();
            String email = emailTv.getText().toString().trim();
            String password = passwordTv.getText().toString().trim();
            String confirm = confirmTv.getText().toString().trim();

//

            if(fullname.isEmpty() ||  username.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()){
                Toast.makeText(this, "Input all credentials", Toast.LENGTH_SHORT).show();

            }
            else if(!password.equals(confirm)){ //password.compare(confirm)
                Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();

            }
            else if(password.length()<8){ //password.compare(confirm)
                Toast.makeText(this, "Password must be more than 8 char", Toast.LENGTH_SHORT).show();

            }
            else{
//                store the record din SQLite database/Room

//                intent-> new screen(move from current activity(LoginActivity.this, MainActivity.class
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }

        });
    }
}