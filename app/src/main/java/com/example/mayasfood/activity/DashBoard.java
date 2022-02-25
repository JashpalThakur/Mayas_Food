package com.example.mayasfood.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mayasfood.R;
import com.example.mayasfood.constants.Constants;
import com.example.mayasfood.fragments.Dashboard_frag;
import com.example.mayasfood.fragments.Favorite_frag;
import com.example.mayasfood.fragments.Profile_frag;
import com.example.mayasfood.fragments.Search_frag;
import com.example.mayasfood.recycleView.recycleViewModel.RecycleView_Model;
import com.example.mayasfood.recycleView.rv_adapter.RecycleView_Adapter_C;
import com.example.mayasfood.recycleView.rv_adapter.RecycleView_Adapter_PF;
import com.example.mayasfood.recycleView.rv_adapter.RecycleView_Adapter_RC;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem;
import np.com.susanthapa.curved_bottom_navigation.CurvedBottomNavigationView;

public class DashBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private boolean isBackPressed = false;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ImageButton toolbar, close;
    Fragment fragment;


    ArrayList<RecycleView_Model> recycleView_models = new ArrayList<>();
    ArrayList<RecycleView_Model> recycleView_models1 = new ArrayList<>();
    ArrayList<RecycleView_Model> recycleView_models2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        CurvedBottomNavigationView cbn = findViewById(R.id.chip_nav);
        CbnMenuItem dashboard = new CbnMenuItem(R.drawable.mdi___view_grid_outline, R.drawable.dashboard_anim, R.id.dashboard_frag);
        CbnMenuItem search = new CbnMenuItem(R.drawable.icon_feather_search_r, R.drawable.search_anim, R.id.search_frag);
        CbnMenuItem favorite = new CbnMenuItem(R.drawable.icon_feather_heart_red, R.drawable.avd_anim, R.id.favorite_frag);
        CbnMenuItem profile = new CbnMenuItem(R.drawable.icon_feather_user_red, R.drawable.profile_anim,R.id.profile_frag);
        CbnMenuItem[] navigation_items = {dashboard,search,favorite,profile};
        cbn.setMenuItems(navigation_items, 0);


        //cbn.setupWithNavController(Navigation.findNavController(this, R.id.frag_cont));

            getSupportFragmentManager().beginTransaction().replace(R.id.frag_cont, new Search_frag()).commit();



        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        Menu menu = navigationView.getMenu();
        toolbar = findViewById(R.id.toolbar);

        RecyclerView recyclerView = findViewById(R.id.rv1);
        RecyclerView recyclerView2 = findViewById(R.id.rv2);
        RecyclerView recyclerView3 = findViewById(R.id.rv3);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView3.setLayoutManager(layoutManager3);

        setUpFoodModel();


        RecycleView_Adapter_C recycleView_adapter = new RecycleView_Adapter_C(this ,recycleView_models);
        RecycleView_Adapter_PF recycleView_adapter_pf = new RecycleView_Adapter_PF(this, recycleView_models1);
        RecycleView_Adapter_RC recycleView_adapter_rc = new RecycleView_Adapter_RC(this, recycleView_models2);
        recyclerView.setAdapter(recycleView_adapter);
        recyclerView2.setAdapter(recycleView_adapter_pf);
        recyclerView3.setAdapter(recycleView_adapter_rc);
        recycleView_adapter.notifyDataSetChanged();

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
                close = findViewById(R.id.close);

                close.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }
                    }
                });
            }
        });



        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.homeNav);
    }

    private void setUpFoodModel(){
        String[] foodName = getResources().getStringArray(R.array.Food_txt);
        String[] nameFood = getResources().getStringArray(R.array.Food_name);
        String[] foodop = getResources().getStringArray(R.array.Food_option);
        String[] foodprice = getResources().getStringArray(R.array.Food_price);
        String[] NameFood = getResources().getStringArray(R.array.Name_Food);
        String[] Food_op = getResources().getStringArray(R.array.Food_op);
        String[] Food_rate = getResources().getStringArray(R.array.Food_rate);

        for(int i = 0; i<foodName.length; i++) {
            recycleView_models.add(new RecycleView_Model(foodName[i], Constants.foodImage[i]));
        }
        for(int i = 0; i<nameFood.length; i++){
            recycleView_models1.add(new RecycleView_Model(nameFood[i], foodop[i], foodprice[i], Constants.imgFood[i]));
          }
         for (int i =0; i<NameFood.length; i++){
            recycleView_models2.add(new RecycleView_Model(NameFood[i], Food_op[i], Food_rate[i], Constants.foodimg[i]));
        }
    }

    @Override
    public void onBackPressed() {

        if(isBackPressed){
            super.onBackPressed();
            return;
        }

        Toast.makeText(DashBoard.this, "Press again to exit", Toast.LENGTH_SHORT).show();
        isBackPressed = true;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isBackPressed = false;
            }
        },2000);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            //go to home
            case R.id.homeNav:
                break;
            //go to Registration
            case R.id.orderNav:

                break;
            //go to Login
            case R.id.profileNav:

                break;
            //Logout User
            case R.id.categoriesNav:
                break;

            case R.id.notificationNav:
                break;

            case R.id.logoutNav:
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}