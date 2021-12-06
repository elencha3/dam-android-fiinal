package com.curso.examenandroid;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    ArrayList<Enemy> enemies;
    LayoutInflater layoutInflater;
    CustomItemClickListener customItemClickListener;

    public Adapter (Context context, ArrayList<Enemy> enemies){
        this.enemies = enemies;
        this.layoutInflater = LayoutInflater.from(context);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.item_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Enemy enemy = enemies.get(position);
        holder.textView.setText(enemy.name);

    }



    @Override
    public int getItemCount() {
        return enemies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.itemName);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (customItemClickListener!=null){
                        customItemClickListener.onItemClick(v,getAdapterPosition());
                    }
                }
            });
        }
    }

    public void setOnClickListener(CustomItemClickListener customItemClickListener){
        this.customItemClickListener = customItemClickListener;
    }

    public interface CustomItemClickListener{
        void onItemClick (View view, int position);
    }
}
