package com.dankook.tagme.view.store.storeDetail;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dankook.tagme.R;
import com.dankook.tagme.databinding.HolderStoreImageItemBinding;
import com.dankook.tagme.model.Image;
import com.dankook.tagme.view.adapter.BaseRecyclerViewAdapter;

public class StoreImageAdapter extends BaseRecyclerViewAdapter<Image, StoreImageAdapter.ViewHolder> {

    public StoreImageAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBindView(StoreImageAdapter.ViewHolder holder, int position) {
        holder.binding.setItem(itemList.get(position));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.holder_store_image_item, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        protected HolderStoreImageItemBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
