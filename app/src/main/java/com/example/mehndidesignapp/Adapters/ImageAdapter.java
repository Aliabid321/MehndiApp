package com.example.mehndidesignapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mehndidesignapp.Interface.ItemClickListener;
import com.example.mehndidesignapp.Models.ImageModel;
import com.example.mehndidesignapp.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyImageViewHolder> {
      private final List<ImageModel>imageModelList;
      private final Context context;
      private ItemClickListener itemClickListener;

    public ImageAdapter(List<ImageModel> imageModelList, Context context) {
        this.imageModelList = imageModelList;
        this.context = context;
    }
    public void setClickCallBack(ItemClickListener clickCallBack){
        itemClickListener =clickCallBack;
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
                itemClickListener.onClick(view, holder.getBindingAdapterPosition(),imageModel.getImgName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageModelList.size();
    }

    public static class MyImageViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final RelativeLayout clickItem;
        public MyImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            clickItem=itemView.findViewById(R.id.itemcclick_id);
        }
    }
}
