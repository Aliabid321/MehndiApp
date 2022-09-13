package com.example.mehndidesignapp.Activitise;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mehndidesignapp.Adapters.ImageAdapter;
import com.example.mehndidesignapp.Interface.ItemClickListener;
import com.example.mehndidesignapp.Models.ConstantModel;
import com.example.mehndidesignapp.Models.ImageModel;
import com.example.mehndidesignapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener, View.OnClickListener {
    protected RecyclerView recyclerFeatured, recyclerMostView, recyclerChild;
    private List<ImageModel> fearedModelList;
    private List<ImageModel> movieModelList;
    private List<ImageModel> childModelList;
    private ImageAdapter imageAdapter;
    private ImageModel imageModel;
    ArrayList<String> images_array_api;
    CarouselView carouselView;
    private Button btnMorefrontHand;
    private Button btnMorebackHand;
    private Button btnMoreImagesHand;
    private String strBackHand="BackHand";
    private String strFrontHand="FrontHand";
    private String strImagesHand="Images";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carouselView = findViewById(R.id.carsoulviewid);
        init_Views();
        setCarousalView();
        setFeaturedCat();
        setMostViewsCat();
        setChildCat();
    }

    public void setFeaturedCat() {
        fearedModelList = new ArrayList<>();
        recyclerFeatured = findViewById(R.id.reclerFeatured);
        getFeaturedImages();

    }

    public void setMostViewsCat() {
        movieModelList = new ArrayList<>();
        recyclerMostView = findViewById(R.id.reclerMostView);
        getMostViews();
    }

    public void setChildCat() {
        childModelList = new ArrayList<>();
        recyclerChild = findViewById(R.id.reclerChild);
        getIChild();
    }
    public void init_Views(){
        btnMorefrontHand=findViewById(R.id.btn_more_front_hand);
        btnMorefrontHand.setOnClickListener(this);
        btnMorebackHand=findViewById(R.id.btn_more_back_hand);
        btnMorebackHand.setOnClickListener(this);
        btnMoreImagesHand=findViewById(R.id.btn_featured);
        btnMoreImagesHand.setOnClickListener(this);
    }
    public void getFeaturedImages() {
        fearedModelList.clear();
        imageModel = new ImageModel();
        FirebaseDatabase firebaseDatabase;
        DatabaseReference myRef;
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();
        myRef.child("Images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    imageModel = postSnapshot.getValue(ImageModel.class);
                    fearedModelList.add(imageModel);
                    images_array_api.add(imageModel.getImgUrl());
                }
                imageAdapter = new ImageAdapter(fearedModelList, MainActivity.this);
                imageAdapter.setClickCallBack(MainActivity.this);
                recyclerFeatured.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                recyclerFeatured.setAdapter(imageAdapter);
                myRef.child("Images").removeEventListener(this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }

        });

    }

    public void getMostViews() {
        movieModelList.clear();
        imageModel = new ImageModel();
        FirebaseDatabase firebaseDatabase;
        DatabaseReference myRef;
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();
        myRef.child("Images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    imageModel = postSnapshot.getValue(ImageModel.class);
                    movieModelList.add(imageModel);
                }
                imageAdapter = new ImageAdapter(movieModelList, MainActivity.this);
                imageAdapter.setClickCallBack(MainActivity.this);
                recyclerMostView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                recyclerMostView.setAdapter(imageAdapter);
                myRef.child("Images").removeEventListener(this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }

        });

    }

    public void getIChild() {
        childModelList.clear();
        imageModel = new ImageModel();
        FirebaseDatabase firebaseDatabase;
        DatabaseReference myRef;
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();
        myRef.child("Images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    imageModel = postSnapshot.getValue(ImageModel.class);
                    childModelList.add(imageModel);
                }
                imageAdapter = new ImageAdapter(childModelList, MainActivity.this);
                imageAdapter.setClickCallBack(MainActivity.this);
                recyclerChild.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                recyclerChild.setAdapter(imageAdapter);
                myRef.child("Images").removeEventListener(this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }

        });

    }

    @Override
    public void onClick(View view, int position, String name) {
        Intent intent = new Intent(MainActivity.this, ShowImagesActivity.class);
        intent.putExtra(ConstantModel.CATEGORY_KEY, name);
        startActivity(intent);
    }

    public void setCarousalView() {
        images_array_api = new ArrayList<>();
        images_array_api.add("https://upload.wikimedia.org/wikipedia/en/5/5a/Deewangi.png");
        images_array_api.add("https://upload.wikimedia.org/wikipedia/en/5/5a/Deewangi.png");
        images_array_api.add("https://upload.wikimedia.org/wikipedia/en/5/5a/Deewangi.png");
        images_array_api.add("https://upload.wikimedia.org/wikipedia/en/5/5a/Deewangi.png");
        ViewListener viewListener = new ViewListener() {
            @Override
            public View setViewForPosition(int position) {
                View customView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.recycler_container_crousel,null);

                ImageView image_view = customView.findViewById(R.id.captre);

                Glide.with(getApplicationContext()).load(images_array_api.get(position)).into(image_view);

                return customView;
            }
        };
        carouselView.setViewListener(viewListener);
        carouselView.setPageCount(images_array_api.size());
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btn_more_front_hand){
            Intent intent = new Intent(MainActivity.this, ShowImagesActivity.class);
            intent.putExtra(ConstantModel.CATEGORY_KEY, strFrontHand);
            startActivity(intent);
        }
        if (view.getId()==R.id.btn_more_back_hand){
            Intent intent = new Intent(MainActivity.this, ShowImagesActivity.class);
            intent.putExtra(ConstantModel.CATEGORY_KEY, strBackHand);
            startActivity(intent);
        }    if (view.getId()==R.id.btn_featured){
            Intent intent = new Intent(MainActivity.this, ShowImagesActivity.class);
            intent.putExtra(ConstantModel.CATEGORY_KEY, strImagesHand);
            startActivity(intent);
        }


    }
}