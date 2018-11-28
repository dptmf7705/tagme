package com.dankook.tagme.view.store.storeReview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dankook.tagme.R;
import com.dankook.tagme.databinding.HolderReviewImageItemBinding;
import com.dankook.tagme.databinding.HolderStoreMenuItemBinding;
import com.dankook.tagme.databinding.HolderStoreReviewItemBinding;
import com.dankook.tagme.model.Image;
import com.dankook.tagme.model.Menu;
import com.dankook.tagme.model.Review;
import com.dankook.tagme.view.adapter.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class StoreReviewAdapter extends BaseRecyclerViewAdapter<Review, StoreReviewAdapter.ViewHolder> {

    private List<ReviewImageAdapter> adapterList = new ArrayList<>();

    public StoreReviewAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBindView(ViewHolder holder, int position) {

        Review item = itemList.get(position);

        holder.binding.setItem(item);

        holder.binding.recyclerReviewImage.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.binding.recyclerReviewImage.setAdapter(adapterList.get(position));
        holder.binding.recyclerReviewImage.setItemViewCacheSize(item.getImageList().size());
    }

    @Override
    public void addItems(List<Review> items) {
        super.addItems(items);
        for(Review rv : items){
            adapterList.add(new ReviewImageAdapter(context, rv.getImageList()));
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.holder_store_review_item, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        protected HolderStoreReviewItemBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    public class ReviewImageAdapter extends BaseRecyclerViewAdapter<Image, ReviewImageAdapter.ViewHolder>{

        public ReviewImageAdapter(Context context, List<Image> itemList) {
            super(context, itemList);
        }

        @Override
        protected void onBindView(ViewHolder holder, int position) {
            holder.binding.setItem(itemList.get(position));
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.holder_review_image_item, parent, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            protected HolderReviewImageItemBinding binding;

            public ViewHolder(View itemView) {
                super(itemView);
                binding = DataBindingUtil.bind(itemView);
            }
        }

    }
}
