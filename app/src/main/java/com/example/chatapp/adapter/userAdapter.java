package com.example.chatapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.MessengerActivity;
import com.example.chatapp.R;
import com.example.chatapp.model.User;

import java.util.List;

public class userAdapter extends RecyclerView.Adapter<userAdapter.userViewHolder> {
    EditText editText;
    private List<User> userList;
    private Context context;

    public userAdapter(List<User> userList, EditText editText, Context context) {
        this.userList = userList;
        this.editText = editText;
        this.context = context;
    }

    public userAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_users, parent, false);
        return new userViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull userViewHolder holder, int position) {
        User user = userList.get(position);
        if (user == null) {
            return;
        }
        holder.txtName.setText(user.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), MessengerActivity.class);
                intent.putExtra("email", user.getemail());
                intent.putExtra("name", user.getName());
                v.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        if (userList != null) {
            return userList.size();

        }
        return 0;
    }

    class userViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;

        public userViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.text_name_item_users);

        }
    }
}
