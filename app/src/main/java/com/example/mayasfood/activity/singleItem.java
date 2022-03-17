package com.example.mayasfood.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mayasfood.R;

public class singleItem extends AppCompatActivity {

    ImageButton back_img_s;
    ImageView foodImg;
    ImageView favoriteItem;
    int default_fav;
    TextView foodName, foodPrice, foodDes;
    Button add_to_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item);

        default_fav = R.drawable.ic_baseline_favorite_border_24;
        back_img_s = findViewById(R.id.back_img_s);
        foodImg = findViewById(R.id.single_foodimg);
        foodName = findViewById(R.id.single_foodname);
        foodPrice = findViewById(R.id.single_foodprice);
        foodDes = findViewById(R.id.food_des);
        add_to_cart = findViewById(R.id.add_to_cart);
        favoriteItem = findViewById(R.id.favorite_item);

        foodImg.setImageResource(getIntent().getIntExtra("imagefood", 0));
        foodName.setText(getIntent().getStringExtra("foodname"));
        foodPrice.setText(getIntent().getStringExtra("foodprice"));
        foodDes.setText(getIntent().getStringExtra("fooddes"));

        favoriteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                favoriteItem.setImageResource(R.drawable.ic_baseline_favorite_24);

            }
        });

        back_img_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(singleItem.this, "Item Added to Cart", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}