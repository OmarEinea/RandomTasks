package com.eineao.randomtasks;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eineao.randomtasks.TasksGetter.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Done {@link Task}s
 */
public class ProgressFragment extends Fragment {

    public final static List<Task> mDoneTasks = new ArrayList<>();
    public static RecyclerView mRecyclerView;
    public static TextView mTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress_list, container, false);
        View innerView = view.findViewById(R.id.progress_recycler_view);
        mTextView = (TextView) view.findViewById(R.id.progress_text_view);

        // Set the adapter
        if (innerView instanceof RecyclerView) {
            Context context = view.getContext();
            mRecyclerView = (RecyclerView) innerView;
            mRecyclerView.addItemDecoration(new DividerItemDecoration(context));
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            setRecyclerViewAdapter();
        }
        return view;
    }

    public static void setRecyclerViewAdapter() {
        mRecyclerView.setAdapter(new ProgressRecyclerViewAdapter(mDoneTasks));
        if(mDoneTasks.isEmpty())
            mTextView.setText("Nothing yet ! get started ^_^");
        else
            mTextView.setText("Tasks you did Today :");
    }

    public static void setAsDone(Task task) {
        mDoneTasks.add(task);
        setRecyclerViewAdapter();
    }

    public static void setAsNotDone(Task task) {
        mDoneTasks.remove(task);
        setRecyclerViewAdapter();
    }

    public static void refreshFavoring(Task task) {
        int index = mDoneTasks.indexOf(task);
        if(index >= 0) {
            mDoneTasks.remove(index);
            mDoneTasks.add(index, task);
            setRecyclerViewAdapter();
        }
    }
}
