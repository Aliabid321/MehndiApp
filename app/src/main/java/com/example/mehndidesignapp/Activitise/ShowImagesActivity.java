package com.example.mehndidesignapp.Activitise;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mehndidesignapp.Adapters.ShowImagesAdapter;
import com.example.mehndidesignapp.Interface.ItemClickListener;
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

public class ShowImagesActivity extends AppCompatActivity implements ItemClickListener {
    protected RecyclerView images_recycler;
    private List<ImageModel>imageModelList;
    private ImageModel imageModel;
    private ShowImagesAdapter imageAdapter;
    private String cateName;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_images);
        intent = getIntent();
        cateName = intent.getStringExtra(ConstantModel.CATEGORY_KEY);
        init();
    }
    public void init(){
        images_recycler =findViewById(R.id.images_recler);
        getMostViews(cateName);
    }
    public void getMostViews(String cateName) {
        imageModelList=new ArrayList<>();
        imageModelList.clear();
        imageModel = new ImageModel();
        FirebaseDatabase firebaseDatabase;
        DatabaseReference myRef;
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();
        myRef.child(cateName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    imageModel = postSnapshot.getValue(ImageModel.class);
                    imageModelList.add(imageModel);
                }
                imageAdapter = new ShowImagesAdapter(imageModelList, ShowImagesActivity.this);
                imageAdapter.setClickCallBack(ShowImagesActivity.this);
                images_recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                images_recycler.setAdapter(imageAdapter);
                myRef.child(cateName).removeEventListener(this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }

        });

    }

    @Override
    public void onClick(View view, int position, String name) {
        ImageModel obj=imageModelList.get(position);
        Intent intent=new Intent(ShowImagesActivity.this,ViewImageActivity.class);
        intent.putExtra("img_url",obj.getImgUrl());
        startActivity(intent);
    }
}