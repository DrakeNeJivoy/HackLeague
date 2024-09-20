package com.example.hackleague;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.RatingViewHolder> {

    private List<String> ratingList;

    public RatingAdapter(List<String> ratingList) {
        this.ratingList = ratingList;
    }

    @NonNull
    @Override
    public RatingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rating, parent, false);
        return new RatingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingViewHolder holder, int position) {
        String user = ratingList.get(position);
        holder.ratingTextView.setText(user);
    }

    @Override
    public int getItemCount() {
        return ratingList.size();
    }

    public static class RatingViewHolder extends RecyclerView.ViewHolder {

        TextView ratingTextView;

        public RatingViewHolder(@NonNull View itemView) {
            super(itemView);
            ratingTextView = itemView.findViewById(R.id.rating_text);
        }
    }
}
