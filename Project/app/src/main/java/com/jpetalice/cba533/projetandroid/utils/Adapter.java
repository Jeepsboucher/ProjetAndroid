package com.jpetalice.cba533.projetandroid.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jpetalice.cba533.projetandroid.R;
import com.jpetalice.cba533.projetandroid.data.Recipe;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Recipe recipe);
    }

    private List<Recipe> listRecipes;
    private final OnItemClickListener listener;

    public Adapter(List<Recipe> listRecipes, OnItemClickListener listener) {
        this.listRecipes = listRecipes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View recipeView = inflater.inflate(R.layout.recycler_layout, parent,false);

        return new ViewHolder(recipeView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(listRecipes.get(position), listener);

        Recipe recipe = listRecipes.get(position);

        ImageView imageView = holder.image_photo;
        imageView.setImageBitmap(recipe.getPhoto());
        TextView textViewName = holder.textView_Name;
        textViewName.setText(recipe.getName());
    }

    @Override
    public int getItemCount() {

        return listRecipes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image_photo;
        private TextView textView_Name;


        private ViewHolder(View itemView)
        {
            super(itemView);

            image_photo = itemView.findViewById(R.id.image_photo);
            textView_Name = itemView.findViewById(R.id.textView_name);
        }

        void bind(final Recipe recipe, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(recipe);
                }
            });
        }
    }
}
