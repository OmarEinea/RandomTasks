package com.eineao.randomtasks;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that gets data about Interests
 * Created by OmarEinea on 12/27/15.
 */
public class InterestsGetter {

    public final static List<Interest> INTERESTS = new ArrayList<>();

    static {
        String[] colors = new String[]{"#FF5252", "#FF4081", "#7C4DFF", "#448AFF", "#18FFFF",
                "#69F0AE", "#B2FF59", "#FFD740", "#FFAB40", "#FF6E40"};
        String[] titles = new String[]{"Exercise", "Learn", "Religion", "Think", "Online",
                "Library", "Eat & Drink", "Say", "Play", "Visit"};
        String[] subTitles = new String[]{"Keep your body healthy", "Become knowledgeable",
                "Remind yourself of Allah", "Be thoughtful and imagine",
                "Things to do online", "Hangout with books and papers",
                "Take care of your nutrition", "Get used to nice words",
                "Entertain yourself with games", "Go out, explore new places"};
        int[] imagesIds = new int[]{R.drawable.exercise, R.drawable.learn, R.drawable.religion,
                R.drawable.think, R.drawable.online,R.drawable.library, R.drawable.eatanddrink,
                R.drawable.say, R.drawable.play, R.drawable.visit};

        for (int i = 0; i < titles.length; i++) {
            INTERESTS.add(new Interest(colors[i], imagesIds[i],
                    titles[i], subTitles[i]));
        }
    }

    public static class Interest {

        String mColor, mTitle, mSubTitle;
        int mImageId;
        boolean mFavored;

        Interest (String color, int imageId, String title, String subTitle) {
            mColor = color;
            mImageId = imageId;
            mTitle = title;
            mSubTitle = subTitle;
            mFavored = true;
        }
    }
}