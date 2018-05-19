package com.suneetsrivastava.pascolan;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.suneetsrivastava.pascolan.Model.SampleUser;
import com.suneetsrivastava.pascolan.Model.Users;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private SampleUser sampleUser;
    RecyclerViewAdapter(Context context, SampleUser sampleUser){
        this.context = context;
        this.sampleUser = sampleUser;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.users_list, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(sampleUser.getSampleUsers().get(position).getImage()).into(holder.profilePic);
        holder.priority.setText(sampleUser.getSampleUsers().get(position).getPriority());
        holder.name.setText(sampleUser.getSampleUsers().get(position).getName());
        if(( sampleUser.getSampleUsers().get(position).getVerified()).equalsIgnoreCase("1")) {
            holder.verified.setChecked(true);
        }else {
            holder.verified.setChecked(false);
        }



    }


    @Override
    public int getItemCount() {
        return sampleUser.getSampleUsers().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        private ImageView profilePic;
        private TextView name;
        private CheckBox verified;
        private TextView priority;
        private LinearLayout itemListLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            profilePic = itemView.findViewById(R.id.profileImage);
            name = itemView.findViewById(R.id.name);
            verified = itemView.findViewById(R.id.verified);
            priority = itemView.findViewById(R.id.priority);
            itemListLayout = itemView.findViewById(R.id.list_item_layout);
            itemListLayout.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            sampleUser.getSampleUsers().remove(getAdapterPosition());
            notifyDataSetChanged();
        }
    }
}
