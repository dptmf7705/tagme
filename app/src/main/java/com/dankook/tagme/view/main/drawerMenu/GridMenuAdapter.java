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
import com.dankook.tagme.databinding.LayoutDrawerGridItemBinding;

import java.util.List;

public class GridMenuAdapter extends BaseRecyclerViewAdapter<MenuVO, GridMenuAdapter.GridMenuViewHolder> {

    public GridMenuAdapter(Context context, List<MenuVO> list){
        super(context, list);
    }

    @Override
    protected void onBindView(GridMenuViewHolder holder, int position) {
        final MenuVO item = itemList.get(position);

        holder.binding.setItem(item);
    }

    @NonNull
    @Override
    public GridMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_drawer_grid_item, null, false);
        return new GridMenuViewHolder(view);
    }

    public class GridMenuViewHolder extends RecyclerView.ViewHolder {

        protected LayoutDrawerGridItemBinding binding;

        public GridMenuViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
