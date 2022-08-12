package com.example.mehndidesignapp.Activitise;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mehndidesignapp.Adapters.ImageAdapter;
import com.example.mehndidesignapp.Interface.ItemClickListner;
import com.example.mehndidesignapp.Models.ConstantModel;
import com.example.mehndidesignapp.Models.ImageModel;
import com.example.mehndidesignapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListner{
    protected RecyclerView reclerFeatured, reclerMostView, reclerChild;
    private List<ImageModel> imageModelList;
    private List<ImageModel> feauredModelList;
    private List<ImageModel> mostviewModelList;
    private List<ImageModel> childModelList;
    private ImageAdapter imageAdapter;
    private ImageModel imageModel;
    ItemClickListner clickListner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFeaturedCat();
        setMostViewsCat();
        setChildCat();

    }

    public void setFeaturedCat() {
        feauredModelList = new ArrayList<>();
        reclerFeatured = findViewById(R.id.reclerFeatured);
        getFeaturedImages();

    }

    public void setMostViewsCat() {
        mostviewModelList = new ArrayList<>();
        reclerMostView = findViewById(R.id.reclerMostView);
        getMostViews();
    }

    public void setChildCat() {
        childModelList = new ArrayList<>();
        reclerChild = findViewById(R.id.reclerChild);
        getIChild();
    }

    private void getDatafun() {
        ImageModel obj = new ImageModel();
        for (int i = 0; i < 5; i++) {
            obj.setImgUrl("https://upload.wikimedia.org/wikipedia/en/5/5a/Deewangi.png");
            obj.setImgName(i + "");
            imageModelList.add(obj);
        }
    }

    public void getFeaturedImages(){
        feauredModelList.clear();
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
                    feauredModelList.add(imageModel);
                }
                imageAdapter = new ImageAdapter(feauredModelList, MainActivity.this);
                imageAdapter.setClickCallBack(MainActivity.this);
                reclerFeatured.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                reclerFeatured.setAdapter(imageAdapter);
                myRef.child("Images").removeEventListener(this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }

        });

    }
    public void getMostViews() {
        mostviewModelList.clear();
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
                    mostviewModelList.add(imageModel);
                }
                imageAdapter = new ImageAdapter(mostviewModelList, MainActivity.this);
                imageAdapter.setClickCallBack(MainActivity.this);
                reclerMostView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                reclerMostView.setAdapter(imageAdapter);
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
                reclerChild.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                reclerChild.setAdapter(imageAdapter);
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
        Intent intent=new Intent(MainActivity.this,ShowImagesActivity.class);
        intent.putExtra(ConstantModel.CATEGORY_KEY,name);
        startActivity(intent);
    }
}