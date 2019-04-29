package com.jpetalice.cba533.projetandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jpetalice.cba533.projetandroid.data.Recipe;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<Recipe> dataSet;

    public Adapter(List<Recipe> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View recipeView = inflater.inflate(R.layout.recycler_layout, parent,false);

        ViewHolder viewHolder = new ViewHolder(recipeView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe recipe = dataSet.get(position);

        TextView textViewName = holder.textView_Name;
        textViewName.setText(recipe.getName());
        TextView textViewDescr = holder.textView_Descr;
        textViewDescr.setText(recipe.getDescr());
    }

    @Override
    public int getItemCount() {

        return dataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView_Descr;
        public TextView textView_Name;

        public ViewHolder(View itemView)
        {
            super(itemView);

            textView_Descr = (TextView) itemView.findViewById(R.id.textView_description);
            textView_Name = (TextView) itemView.findViewById(R.id.textView_name);
        }
    }
}
