package com.example.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    private EditText user, pass;
    private Button btnLogin, btnRegister;
    private FirebaseAuth mAuth;
    private DatabaseReference ref;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

//        myRef.setValue("Hello, World!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mAuth = FirebaseAuth.getInstance();
        user = findViewById(R.id.username_text);
        pass = findViewById(R.id.password_text);
        btnLogin = findViewById(R.id.button_login);
        btnRegister = findViewById(R.id.button_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        login();

    }

    private void register() {

        Intent i = new Intent(Login.this, Register.class);
        startActivity(i);
    }


    private void login() {
        String userEd, passEd;
        userEd = user.getText().toString();
        passEd = pass.getText().toString();
//        userEd="tung@gmail.com";
//        passEd="123456";
        if (TextUtils.isEmpty(userEd)) {
            Toast.makeText(this, "Vui lòng nhập User!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(passEd)) {
            Toast.makeText(this, "Vui lòng nhập password!!", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(userEd, passEd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, home_messenger.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}
