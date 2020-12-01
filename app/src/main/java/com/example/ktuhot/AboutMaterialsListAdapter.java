package com.example.ktuhot;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.icu.text.UFormat;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AboutMaterialsListAdapter extends RecyclerView.Adapter<AboutMaterialsListAdapter.ViewHolder> {

    List<Material> materials = new ArrayList<>();
    ArrayList<Bitmap> bitmapArray = new ArrayList<Bitmap>();
    ArrayList<Boolean> downloaded = new ArrayList<Boolean>();
    Bitmap.Config conf = Bitmap.Config.ARGB_8888;
    Bitmap empty = Bitmap.createBitmap(100, 100, conf);
    Context context;


    public AboutMaterialsListAdapter(List<Material> materials) {
        this.materials = materials;
        for (int i = 0; i < materials.size(); i++)
        {
            bitmapArray.add(empty);
            downloaded.add(false);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.aboutmaterials_layout_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.txt_name.setText(materials.get(position).getName());
        holder.txt_coefficient.setText(materials.get(position).getCoefficient());
        Picasso.get().load(materials.get(position).getImageUrl()).into(holder.materialImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent= new Intent(context, MaterialActivity.class);
                intent.putExtra("Name", materials.get(position).getName());
                intent.putExtra("ImageUrl", materials.get(position).getImageUrl());
                intent.putExtra("Description", materials.get(position).getDescription());
                intent.putExtra("Coefficient", materials.get(position).getCoefficient());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return materials.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_name;
        TextView txt_coefficient;
        ImageView materialImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_coefficient = itemView.findViewById(R.id.txt_coefficient);
            materialImage = itemView.findViewById(R.id.materialImage);
        }
    }
}
