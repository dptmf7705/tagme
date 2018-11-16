package com.dankook.tagme.view.main.drawerMenu;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dankook.tagme.view.adapter.BaseRecyclerViewAdapter;
import com.dankook.tagme.R;
import com.dankook.tagme.databinding.LayoutDrawerListItemBinding;

import java.util.List;

public class ListMenuAdapter extends BaseRecyclerViewAdapter<MenuVO, ListMenuAdapter.ListMenuViewHolder> {
    public ListMenuAdapter(Context context, List<MenuVO> list){
        super(context, list);
    }

    @Override
    protected void onBindView(ListMenuViewHolder holder, int position) {
        final MenuVO item = itemList.get(position);

        holder.binding.setItem(item);
    }

    @NonNull
    @Override
    public ListMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_drawer_list_item, null, false);
        return new ListMenuViewHolder(view);
    }

    public class ListMenuViewHolder extends RecyclerView.ViewHolder {

        protected LayoutDrawerListItemBinding binding;

        public ListMenuViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
