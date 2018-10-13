package com.dankook.tagme.view;

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

        void addItems(List<T> items);

        void addItems(int position, List<T> items);

        void clearItems();
    }
}
