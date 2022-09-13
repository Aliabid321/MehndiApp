package com.example.mehndidesignapp.Activitise;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.mehndidesignapp.R;

public class ViewImageActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView showImage;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        Intent intent = getIntent();
        init();
        String imgurl = intent.getStringExtra("img_url");
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
}