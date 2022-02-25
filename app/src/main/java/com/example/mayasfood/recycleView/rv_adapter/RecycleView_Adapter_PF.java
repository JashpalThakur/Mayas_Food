package com.example.mayasfood.recycleView.rv_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mayasfood.R;
import com.example.mayasfood.constants.Constants;
import com.example.mayasfood.recycleView.recycleViewModel.RecycleView_Model;

import java.util.ArrayList;
import java.util.List;

public class RecycleView_Adapter_PF extends RecyclerView.Adapter<RecycleView_Adapter_PF.MyViewHolder> {

    Context context;
    ArrayList<RecycleView_Model> foodModels2;

    public RecycleView_Adapter_PF(Context context, ArrayList<RecycleView_Model> foodModels2){
        this.context = context;
        this.foodModels2 = foodModels2;
    }

    @NonNull
    @Override
    public RecycleView_Adapter_PF.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the layout (Giving look)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.popular_food_rv, parent, false);
        return new RecycleView_Adapter_PF.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleView_Adapter_PF.MyViewHolder holder, int position) {
        //Assigning values to the views we created

        holder.name.setText(foodModels2.get(position).getFoodName());
        holder.imageView.setImageResource(foodModels2.get(position).getFoodImg());
        holder.option.setText(foodModels2.get(position).getFoodHeading());
        holder.price.setText(foodModels2.get(position).getFoodPrice());
    }

    @Override
    public int getItemCount() {
        //Number of Items you want to display
        return foodModels2.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //grabbing the views from rv_column.xml

        ImageView imageView;
        TextView name, option, price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgfood);
            name = itemView.findViewById(R.id.name_food);
            option = itemView.findViewById(R.id.food_op);
            price = itemView.findViewById(R.id.food_price);
        }
    }
}
