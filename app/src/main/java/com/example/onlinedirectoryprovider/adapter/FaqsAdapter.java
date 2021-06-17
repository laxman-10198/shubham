package com.example.onlinedirectoryprovider.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinedirectoryprovider.R;
import com.example.onlinedirectoryprovider.model.faqs.Result;

import java.util.List;

public class FaqsAdapter extends RecyclerView.Adapter<FaqsAdapter.ViewHolder> {
    private Context context;
    private List<Result> listModels;
    private int currentSelectedPosition = 0;

    public FaqsAdapter(Context context, List<Result> listModels) {
        this.context = context;
        this.listModels = listModels;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.row_item_faqs, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (listModels != null) {
            holder.question.setText(listModels.get(position).getQuestion());
            holder.answer.setText(listModels.get(position).getAnswer());
            if (currentSelectedPosition == position) {
                holder.question.setSelected(true);
                holder.answer.setVisibility(View.VISIBLE);
            } else {
                holder.question.setSelected(false);
                holder.answer.setVisibility(View.GONE);
            }
            holder.question.setOnClickListener(v -> {
                currentSelectedPosition = holder.getLayoutPosition();
                notifyDataSetChanged();
            });
        }

    }

    @Override
    public int getItemCount() {
        if (listModels != null) {
            return listModels.size();
        } else return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView question, answer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.question);
            answer = itemView.findViewById(R.id.answer);

        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
