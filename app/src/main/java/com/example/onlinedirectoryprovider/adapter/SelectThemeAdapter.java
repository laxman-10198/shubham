package com.example.onlinedirectoryprovider.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinedirectoryprovider.R;
import com.example.onlinedirectoryprovider.activity.ViewYourThemeActivity;
import com.example.onlinedirectoryprovider.model.SelectThemeListModel;

import java.util.List;

public class SelectThemeAdapter  extends RecyclerView.Adapter<SelectThemeAdapter.ViewHolder>{
    private Context context;
    private List<SelectThemeListModel> listModels;



    public SelectThemeAdapter(Context context, List<SelectThemeListModel> listModels)
    {
        this.context = context;
        this.listModels = listModels;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.row_item_select_theme,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.img1.setImageResource(listModels.get(position).getImg1());
        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ViewYourThemeActivity.class));
            }
        });


    }
    @Override
    public int getItemCount() {
        return listModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img1=itemView.findViewById(R.id.img1);

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

