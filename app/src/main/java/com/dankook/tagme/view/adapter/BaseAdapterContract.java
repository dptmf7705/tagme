package com.dankook.tagme.view.adapter;

import com.dankook.tagme.view.listener.OnItemClickListener;
import com.dankook.tagme.view.listener.OnItemLongClickListener;

import java.util.List;

public interface BaseAdapterContract {

    interface View {

        void setOnItemClickListener(OnItemClickListener itemClickListener);

        void setOnItemLongClickListener(OnItemLongClickListener itemLongClickListener);
    }

    interface Model<T> {

        T getItem(int position);

        List<T> getItemList();

        void updateItems(List<T> items);

        void addItems(List<T> items);

        void addItem(T item);

        void clearItems();
    }
}
