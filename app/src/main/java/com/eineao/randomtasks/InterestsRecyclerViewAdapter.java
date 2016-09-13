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
import android.widget.Toast;

import com.eineao.randomtasks.InterestsGetter.Interest;

import java.util.List;
/**
 * {@link RecyclerView.Adapter} that can display an {@link Interest}
 */
public class InterestsRecyclerViewAdapter extends
        RecyclerView.Adapter<InterestsRecyclerViewAdapter.ViewHolder> {

    private final List<Interest> mInterests;

    public InterestsRecyclerViewAdapter(List<Interest> interests) {
        mInterests = interests;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_interests, parent, false));
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Interest interest = mInterests.get(position);
        holder.mCircle.setColor(Color.parseColor(interest.mColor));
        holder.mFrameLayout.setBackground(holder.mCircle);
        holder.mImage.setImageResource(interest.mImageId);
        holder.mTitle.setText(interest.mTitle);
        holder.mSubTitle.setText(interest.mSubTitle);
        holder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                InterestsGetter.INTERESTS.get(position).mFavored = checkBox.isChecked();
                if (checkBox.isChecked())
                    Toast.makeText(v.getContext(), interest.mTitle +
                            " Tasks are back!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(v.getContext(), "No more " + interest.mTitle +
                            " Tasks", Toast.LENGTH_SHORT).show();
            }
        });
        holder.mCheckBox.setChecked(interest.mFavored);
    }

    @Override
    public int getItemCount() {
        return mInterests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final FrameLayout mFrameLayout;
        public final ImageView mImage;
        public final TextView mTitle, mSubTitle;
        public final CheckBox mCheckBox;
        public final GradientDrawable mCircle;

        public ViewHolder(View view) {
            super(view);
            mFrameLayout = (FrameLayout) view.findViewById(R.id.interest_circle);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mCircle = (GradientDrawable) view.getResources()
                        .getDrawable(R.drawable.interests_circle, view.getContext().getTheme());
            } else {
                mCircle = (GradientDrawable) view.getResources()
                        .getDrawable(R.drawable.interests_circle);
            }
            mImage = (ImageView) view.findViewById(R.id.interest_image);
            mTitle = (TextView) view.findViewById(R.id.interest_title);
            mSubTitle = (TextView) view.findViewById(R.id.interest_subtitle);
            mCheckBox = (CheckBox) view.findViewById(R.id.interest_checkBox);
        }
    }
}
