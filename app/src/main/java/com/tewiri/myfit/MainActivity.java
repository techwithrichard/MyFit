package com.tewiri.myfit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

//    declarations
    Button loutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        initialize
        loutBtn=findViewById(R.id.logoutBtn);

        loutBtn.setOnClickListener(view -> {
            Toast.makeText(this, "Logout Successfully!", Toast.LENGTH_SHORT).show();
            Intent logout_intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(logout_intent);
        });

    }
}