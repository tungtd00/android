package com.example.chatapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.R;
import com.example.chatapp.model.Chat;
import com.example.chatapp.model.User;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.HashSet;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.chatViewHolder> {

    List<User> userList;
    List<Chat> chatList;
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    HashSet<String> userHashset;

    public ChatAdapter(List<User> userList, List<Chat> chatList) {
        this.userList = userList;
        this.chatList = chatList;
    }

    public ChatAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public chatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_messenger, parent, false);

        return new chatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull chatViewHolder holder, int position) {

        User user = userList.get(position);
        if (user == null) {
            return;
        }
        holder.txt_name_user.setText(user.getName());


    }


    @Override
    public int getItemCount() {
        return userList.size();
    }

    class chatViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_name_user, txt_message;

        public chatViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_message = itemView.findViewById(R.id.text_mess_end_user);
            txt_name_user = itemView.findViewById(R.id.text_name_user_chat);

        }
    }
}
