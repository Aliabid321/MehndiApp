package com.example.mehndidesignapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mehndidesignapp.Models.*;
import com.example.mehndidesignapp.R;
import com.example.mehndidesignapp.Interface.*;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyImageViewHolder> {
      private List<ImageModel>imageModelList;
      private Context context;
      private ItemClickListner itemClickListner;

    public ImageAdapter(List<ImageModel> imageModelList, Context context) {
        this.imageModelList = imageModelList;
        this.context = context;
    }
    public void setClickCallBack(ItemClickListner clickCallBack){
        itemClickListner=clickCallBack;
    }
    public ImageAdapter.MyImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.imagesresourcefile, parent, false);
        return new MyImageViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.MyImageViewHolder holder, int position) {
        ImageModel imageModel=imageModelList.get(position);
        Glide.with(context).load(imageModel.getImgUrl()).into(holder.imageView);
        holder.clickItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListner.onClick(view, holder.getAdapterPosition(),imageModel.getImgName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageModelList.size();
    }

    public class MyImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private RelativeLayout clickItem;
        public MyImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            clickItem=itemView.findViewById(R.id.itemcclick_id);
        }
    }
}
