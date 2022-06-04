package com.example.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.adapter.MessengerAdapter;
import com.example.chatapp.model.Chat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MessengerActivity extends AppCompatActivity {

    String email, ten;
    RecyclerView recyclerView;
    ImageButton send;
    EditText editText_messega;
    List<Chat> list;
    MessengerAdapter messengerAdapter;
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    private TextView name, status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messenger_chat);
        Intent intent = getIntent();
        ten = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        name = findViewById(R.id.name_friends);
        name.setText(ten);

        //anh xa
        recyclerView = findViewById(R.id.recyclerview_list_message);
        send = findViewById(R.id.btn_send);
        editText_messega = findViewById(R.id.edit_text_gui_tn);
        //
        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL);
//        recyclerView.addItemDecoration(decoration);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        readMessage();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText_messega.getText().toString();
                if (msg.trim().length() == 0) {
                    Toast.makeText(MessengerActivity.this, "Hãy nhập nội dung tin nhắn", Toast.LENGTH_SHORT).show();
                } else {
                    sendMessage(email, firebaseUser.getEmail(), msg);
                }
                editText_messega.setText("");
            }
        });
    }

    private void sendMessage(String email, String email1, String msg) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Chat chat = new Chat(email1, email, msg);
        reference.child("Chat").push().setValue(chat).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                    Toast.makeText(MessengerActivity.this, "Đã gửi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void readMessage() {
        list = new ArrayList<>();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Chat");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Chat chat = dataSnapshot.getValue(Chat.class);
                    assert chat != null;
                    if ((chat.getReceiver().equals(email) && chat.getSender().equals(firebaseUser.getEmail())) || (chat.getSender().equals(email) && chat.getReceiver().equals(firebaseUser.getEmail()))) {
                        list.add(chat);
                    }
                }
                messengerAdapter = new MessengerAdapter(list);
                recyclerView.setAdapter(messengerAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
