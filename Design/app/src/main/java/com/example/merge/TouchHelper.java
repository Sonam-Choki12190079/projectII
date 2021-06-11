package com.example.merge;

import android.graphics.Canvas;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class TouchHelper extends ItemTouchHelper.SimpleCallback {
    private NotificationAdaptor adaptor;

    public TouchHelper(NotificationAdaptor adaptor) {
        super(0, ItemTouchHelper.LEFT);
        this.adaptor = adaptor;

    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAdapterPosition();
        if (direction == ItemTouchHelper.LEFT) {
            adaptor.deleteData(position);
        }

    }

}
