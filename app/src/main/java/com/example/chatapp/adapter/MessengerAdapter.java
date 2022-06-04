package com.example.chatapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.R;
import com.example.chatapp.model.Chat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MessengerAdapter extends RecyclerView.Adapter<MessengerAdapter.messengerViewHolder> {
    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;
    private List<Chat> chatList;
    private FirebaseUser firebaseUser;

    public MessengerAdapter(List<Chat> chatList) {
        this.chatList = chatList;
    }

    @NonNull
    @Override
    public messengerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_right, parent, false);
            return new messengerViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_left, parent, false);
            return new messengerViewHolder(view);
        }

    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull messengerViewHolder holder, int position) {
        Chat chat = chatList.get(position);
        holder.text_message.setText(chat.getMessage());
        if (position == chatList.size() - 1) {
            holder.txt_seen.setText("Đã gửi");
        } else {
            holder.txt_seen.setText("");
        }


    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser.getEmail().equals(chatList.get(position).getSender())) {
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;

        }
    }

    class messengerViewHolder extends RecyclerView.ViewHolder {
        public TextView text_message, txt_seen;


        public messengerViewHolder(@NonNull View itemView) {
            super(itemView);
            text_message = itemView.findViewById(R.id.show_message);
            txt_seen = itemView.findViewById(R.id.txt_seen);

        }
    }

}
