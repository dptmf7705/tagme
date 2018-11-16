package com.dankook.tagme.view.store.storeList;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dankook.tagme.R;
import com.dankook.tagme.databinding.HolderStoreListItemBinding;
import com.dankook.tagme.model.Store;
import com.dankook.tagme.view.adapter.BaseRecyclerViewAdapter;

public class StoreListAdapter extends BaseRecyclerViewAdapter<Store, StoreListAdapter.ViewHolder> {

    public StoreListAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBindView(ViewHolder holder, int position) {
        holder.binding.setItem(itemList.get(position));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.holder_store_list_item, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        protected HolderStoreListItemBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
