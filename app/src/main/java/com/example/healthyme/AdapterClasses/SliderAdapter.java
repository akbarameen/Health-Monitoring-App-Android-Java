package com.example.healthyme.AdapterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.healthyme.R;


public class SliderAdapter extends PagerAdapter {

    Context context;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    int[] images = {
            R.drawable.blood_pressure2,
            R.drawable.heart_beats,
            R.drawable.sugar_level,
            R.drawable.calories_intake
    };

    int[] headings = {
            R.string.slide_1_title,
            R.string.slide_2_title,
            R.string.slide_3_title,
            R.string.slide_4_title
    };

    int[] descriptions = {
            R.string.slide_1_desc,
            R.string.slide_2_desc,
            R.string.slide_3_desc,
            R.string.slide_4_desc
    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.ob_slides_layout, container, false);

        // Hooks
        ImageView slideImage = view.findViewById(R.id.slide_image);
        TextView heading = view.findViewById(R.id.slide_title);
        TextView desc = view.findViewById(R.id.slide_des);

        // set them
         slideImage.setImageResource(images[position]);
         heading.setText(headings[position]);
         desc.setText(descriptions[position]);

         container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
