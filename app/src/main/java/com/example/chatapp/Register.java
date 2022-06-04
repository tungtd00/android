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

import com.example.chatapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private EditText email, pass, name;
    private Button btnRegister;
    private FirebaseAuth mAuth;
    private DatabaseReference ref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.user_text);
        pass = findViewById(R.id.pass_text);
        name = findViewById(R.id.user);
        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

    }

    private void register() {
        String userEd, passEd;
        userEd = email.getText().toString();
        passEd = pass.getText().toString();
        if (TextUtils.isEmpty(userEd)) {
            Toast.makeText(this, "Vui lòng nhập User!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(passEd)) {
            Toast.makeText(this, "Vui lòng nhập password!!", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(userEd, passEd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    // assert firebaseUser != null;
                    String uid = firebaseUser.getUid();
                    ref = FirebaseDatabase.getInstance().getReference();

                    String namedf = name.getText().toString();
                    String mail = email.getText().toString();
                    User user = new User(namedf, mail);


                    ref.child("users").push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Register.this, home_messenger.class);
                                startActivity(i);
                            }
                        }
                    });


                } else {
                    Toast.makeText(getApplicationContext(), "Đăng kí không thành công", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}
