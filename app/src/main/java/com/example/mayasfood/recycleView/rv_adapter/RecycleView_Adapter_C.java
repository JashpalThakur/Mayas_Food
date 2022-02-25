package com.example.mayasfood.recycleView.rv_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mayasfood.R;
import com.example.mayasfood.constants.Constants;
import com.example.mayasfood.recycleView.recycleViewModel.RecycleView_Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RecycleView_Adapter_C extends RecyclerView.Adapter<RecycleView_Adapter_C.MyViewHolder> {

    Context context;
    ArrayList<RecycleView_Model> foodModels;

    public RecycleView_Adapter_C(Context context, ArrayList<RecycleView_Model> foodModels){
        this.context = context;
        this.foodModels = foodModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the layout (Giving look)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.food_categories_rv, parent, false);
        return new RecycleView_Adapter_C.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //Assigning values to the views we created

        holder.name.setText(foodModels.get(position).getFoodName());
        holder.imageView.setImageResource(foodModels.get(position).getFoodImg());
    }

    @Override
    public int getItemCount() {
        //Number of Items you want to display
        return foodModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //grabbing the views from rv_column.xml

        ImageView imageView;
        TextView name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.foodimage);
            name = itemView.findViewById(R.id.foodname);
        }
    }
}
