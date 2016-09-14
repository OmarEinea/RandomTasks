package com.eineao.randomtasks;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A fragment representing a list of Tasks.
 */
public class TasksFragment extends Fragment {

    public RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.addItemDecoration(new PaddingItemDecoration());
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            setRecyclerViewAdapter();
        }
        return view;
    }

    public boolean setRecyclerViewAdapter() {
        if(!TasksGetter.fillTasks()) return false;
        recyclerView.setAdapter(new TasksRecyclerViewAdapter(TasksGetter.TASKS));
        return true;
    }

    public class PaddingItemDecoration extends RecyclerView.ItemDecoration {

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            // Add top margin only for the first item to avoid double space between items
            if(parent.getChildLayoutPosition(view) == 0)
                outRect.top = (int) parent.getResources().getDimension(R.dimen.cards_bottom);
        }
    }
}