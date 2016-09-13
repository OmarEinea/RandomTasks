package com.eineao.randomtasks;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.eineao.randomtasks.TasksGetter.Task;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Task}
 */
public class ProgressRecyclerViewAdapter extends RecyclerView.Adapter<ProgressRecyclerViewAdapter.ViewHolder> {

    private final List<Task> mDoneTasks;

    public ProgressRecyclerViewAdapter(List<Task> tasks) {
        mDoneTasks = tasks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_progress, parent, false));
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Task doneTask = mDoneTasks.get(position);
        holder.mCircle.setColor(Color.parseColor(doneTask.mColor));
        holder.mFrameLayout.setBackground(holder.mCircle);
        holder.mImage.setImageResource(doneTask.mImageId);
        holder.mTitle.setText(doneTask.mTitle);
        holder.mCheckBox.setChecked(doneTask.mFavored);
    }

    @Override
    public int getItemCount() {
        return mDoneTasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final FrameLayout mFrameLayout;
        public final ImageView mImage;
        public final TextView mTitle;
        public final CheckBox mCheckBox;
        public final GradientDrawable mCircle;

        public ViewHolder(View view) {
            super(view);
            mFrameLayout = (FrameLayout) view.findViewById(R.id.progress_circle);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mCircle = (GradientDrawable) view.getResources()
                        .getDrawable(R.drawable.interests_circle, view.getContext().getTheme());
            } else {
                mCircle = (GradientDrawable) view.getResources()
                        .getDrawable(R.drawable.interests_circle);
            }
            mImage = (ImageView) view.findViewById(R.id.progress_image);
            mTitle = (TextView) view.findViewById(R.id.progress_title);
            mCheckBox = (CheckBox) view.findViewById(R.id.progress_checkBox);
        }
    }
}
