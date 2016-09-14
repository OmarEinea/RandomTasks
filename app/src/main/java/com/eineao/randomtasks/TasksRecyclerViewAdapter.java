package com.eineao.randomtasks;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eineao.randomtasks.TasksGetter.Task;

import java.util.List;
/**
 * {@link RecyclerView.Adapter} that can display a {@link Task}
 */
public class TasksRecyclerViewAdapter extends
        RecyclerView.Adapter<TasksRecyclerViewAdapter.ViewHolder> {

    private final List<Task> mTasks;

    public TasksRecyclerViewAdapter(List<Task> tasks) {
        mTasks = tasks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tasks, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Task task = mTasks.get(position);
        holder.mLinearLayout.setBackgroundColor(Color.parseColor(task.mColor));
        holder.mImage.setImageResource(task.mImageId);
        holder.mTitle.setText(task.mTitle);
        holder.mSubTitle.setText(task.mSubTitle);
        holder.mFavCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                task.mFavored = isChecked;
                ProgressFragment.refreshFavoring(task);
                if (isChecked)
                    Toast.makeText(buttonView.getContext(), task.mTitle +
                        " Will appear twice as often", Toast.LENGTH_SHORT).show();
            }
        });
        holder.mDoneCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(buttonView.getContext(), "Wohoo, done with the task #" +
                            (position + 1), Toast.LENGTH_SHORT).show();
                    ProgressFragment.setAsDone(task);
                } else {
                    ProgressFragment.setAsNotDone(task);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final LinearLayout mLinearLayout;
        public final ImageView mImage;
        public final TextView mTitle, mSubTitle;
        public final CheckBox mDoneCheckBox, mFavCheckBox;

        public ViewHolder(View view) {
            super(view);
            mLinearLayout = (LinearLayout) view.findViewById(R.id.task_card);
            mImage = (ImageView) view.findViewById(R.id.task_image);
            mTitle = (TextView) view.findViewById(R.id.task_title);
            mSubTitle = (TextView) view.findViewById(R.id.task_subtitle);
            mDoneCheckBox = (CheckBox) view.findViewById(R.id.task_done_checkBox);
            mFavCheckBox = (CheckBox) view.findViewById(R.id.task_fav_checkBox);
        }
    }
}
