package com.example.mehndidesignapp.Activitise;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.mehndidesignapp.Adapters.*;
import com.example.mehndidesignapp.Activitise.*;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mehndidesignapp.R;
import com.google.firebase.appcheck.interop.BuildConfig;
import com.google.firebase.installations.Utils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ViewImageActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView showImage;
    private Intent intent;
    private Button btn;
    private String imgurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        intent = getIntent();
        init();
        imgurl = intent.getStringExtra("imgurl");
        Glide.with(this).load(imgurl).into(showImage);
    }

    public void init() {
        showImage = findViewById(R.id.showimage);
        btn = findViewById(R.id.sharebtn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sharebtn) {
            shareImageWhatsApp();
        }
    }

    public void shareImageWhatsApp() {
        //so Hare is Getting Image from ImageView....
        Drawable mDrawable = showImage.getDrawable();
        Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();

        String path = MediaStore.Images.Media.insertImage(getContentResolver(), mBitmap, "Image Description", null);
        Uri uri = Uri.parse(path);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/jpeg");
        intent.setType("text/plain");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "Its A Test Device Image.");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(intent, "Share Image"));
    }
    private void shareText(String text) {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");// Plain format text

        // You can add subject also
        /*
         * sharingIntent.putExtra( android.content.Intent.EXTRA_SUBJECT,
         * "Subject Here");
         */
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(sharingIntent, "Share Text Using"));
    }
}