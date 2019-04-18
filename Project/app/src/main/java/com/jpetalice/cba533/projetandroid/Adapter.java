package com.jpetalice.cba533.projetandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jpetalice.cba533.projetandroid.data.Recipe;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<Recipe> dataSet;

    public Adapter(List<Recipe> dataSet) {
        this.dataSet = dataSet;
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
        Recipe recipe = dataSet.get(position);

        ImageView imageView = holder.image_photo;
        imageView.setImageBitmap(recipe.getPhoto());
        TextView textViewName = holder.textView_Name;
        textViewName.setText(recipe.getName());
    }

    @Override
    public int getItemCount() {

        return dataSet.size();
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
    }
}
