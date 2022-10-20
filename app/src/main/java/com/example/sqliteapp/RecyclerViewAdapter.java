package com.example.sqliteapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//-----------
//import java.util.List;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Integer> mImages = new ArrayList<>();
    private ArrayList<String> mCategoryName = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<Integer> mImages, ArrayList<String> mCategoryName, Context mContext) {
        this.mImages = mImages;
        this.mCategoryName = mCategoryName;
        this.mContext = mContext;
    }

    //---------------
    /*private List<ModelClass> userList;

    public RecyclerView.Adapter(List<ModelClass>userList){
        this.userList = userList;
    }*/
    CircleImageView image;
    TextView categoryName;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.icon_image.setImageResource(mImages.get(position));
        holder.category_name.setText(mCategoryName.get(position));
    }

    @Override
    public int getItemCount() {
        return mCategoryName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView icon_image;
        TextView category_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon_image =itemView.findViewById(R.id.icon_image);
            category_name = itemView.findViewById(R.id.category_name);

        }
    }
}
