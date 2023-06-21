package com.example.healthyme.User;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthyme.AdapterClasses.InfoRVAdapter;
import com.example.healthyme.Entities.InfoModel;
import com.example.healthyme.R;

import java.util.ArrayList;


public class InfoFragment extends Fragment {
    RecyclerView infoRV;
    InfoRVAdapter adapter;
    ArrayList<InfoModel> arrInfo = new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        infoRV = view.findViewById(R.id.info_rv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);

        infoRV.setLayoutManager(gridLayoutManager);

        AddingData();

        adapter = new InfoRVAdapter(getContext(), arrInfo);
        infoRV.setAdapter(adapter);

        return view;
    }
    public void AddingData(){
        String title1 = requireContext().getString(R.string.title1);
        String t1Subtitle1 = requireContext().getString(R.string.t1_subtitle1);
        String t1Subtitle2 = requireContext().getString(R.string.t1_subtitle2);
        String t1Desc1 = requireContext().getString(R.string.t1_desc1);
        String t1Desc2 = requireContext().getString(R.string.t1_desc2);
        int image1 = R.drawable.info_pic1;
        int color1 = getContext().getResources().getColor(R.color.blue_dark);


        String title2 = requireContext().getString(R.string.title2);
        String t2Subtitle1 = requireContext().getString(R.string.t2_subtitle1);
        String t2Subtitle2 = requireContext().getString(R.string.t2_subtitle2);
        String t2Desc1 = requireContext().getString(R.string.t2_desc1);
        String t2Desc2 = requireContext().getString(R.string.t2_desc2);
        int image2 = R.drawable.info_pic2;
        int color2 =getContext().getResources().getColor(R.color.greenish);

        String title3 = requireContext().getString(R.string.title3);
        String t3Subtitle1 = requireContext().getString(R.string.t3_subtitle1);
        String t3Subtitle2 = requireContext().getString(R.string.t3_subtitle2);
        String t3Desc1 = requireContext().getString(R.string.t3_desc1);
        String t3Desc2 = requireContext().getString(R.string.t3_desc2);
        int image3 = R.drawable.info_pic3;
        int color3 =getContext().getResources().getColor(R.color.light_green);

        String title4 = requireContext().getString(R.string.title4);
        String t4Subtitle1 = requireContext().getString(R.string.t4_subtitle1);
        String t4Subtitle2 = requireContext().getString(R.string.t4_subtitle2);
        String t4Desc1 = requireContext().getString(R.string.t4_desc1);
        String t4Desc2 = requireContext().getString(R.string.t4_desc2);
        int image4 = R.drawable.info_pic4;
        int color4 =getContext().getResources().getColor(R.color.red);

        String title5 = requireContext().getString(R.string.title5);
        String t5Subtitle1 = requireContext().getString(R.string.t5_subtitle1);
        String t5Subtitle2 = requireContext().getString(R.string.t5_subtitle2);
        String t5Desc1 = requireContext().getString(R.string.t5_desc1);
        String t5Desc2 = requireContext().getString(R.string.t5_desc2);
        int image5 = R.drawable.info_pic5;
        int color5 =getContext().getResources().getColor(R.color.teal_700);

        String title6 = requireContext().getString(R.string.title6);
        String t6Subtitle1 = requireContext().getString(R.string.t6_subtitle1);
        String t6Subtitle2 = requireContext().getString(R.string.t6_subtitle2);
        String t6Desc1 = requireContext().getString(R.string.t6_desc1);
        String t6Desc2 = requireContext().getString(R.string.t6_desc2);
        int image6 = R.drawable.info_pic6;
        int color6 =getContext().getResources().getColor(R.color.purple_500);

        String title7 = requireContext().getString(R.string.title7);
        String t7Subtitle1 = requireContext().getString(R.string.t7_subtitle1);
        String t7Subtitle2 = requireContext().getString(R.string.t7_subtitle2);
        String t7Desc1 = requireContext().getString(R.string.t7_desc1);
        String t7Desc2 = requireContext().getString(R.string.t7_desc2);
        int image7 = R.drawable.info_pic7;
        int color7 = getContext().getResources().getColor(R.color.light_green);

        String title8 = requireContext().getString(R.string.title8);
        String t8Subtitle1 = requireContext().getString(R.string.t8_subtitle1);
        String t8Subtitle2 = requireContext().getString(R.string.t8_subtitle2);
        String t8Desc1 = requireContext().getString(R.string.t8_desc1);
        String t8Desc2 = requireContext().getString(R.string.t8_desc2);
        int image8 = R.drawable.info_pic8;
        int color8 =getContext().getResources().getColor(R.color.Yellowish);


        arrInfo.add(new InfoModel(image1, title1, color1, t1Subtitle1, t1Subtitle2, t1Desc1, t1Desc2));
        arrInfo.add(new InfoModel(image2, title2, color2, t2Subtitle1, t2Subtitle2, t2Desc1, t2Desc2));
        arrInfo.add(new InfoModel(image3, title3, color3, t3Subtitle1, t3Subtitle2, t3Desc1, t3Desc2));
        arrInfo.add(new InfoModel(image4, title4, color4, t4Subtitle1, t4Subtitle2, t4Desc1, t4Desc2));
        arrInfo.add(new InfoModel(image5, title5, color5, t5Subtitle1, t5Subtitle2, t5Desc1, t5Desc2));
        arrInfo.add(new InfoModel(image6, title6, color6, t6Subtitle1, t6Subtitle2, t6Desc1, t6Desc2));
        arrInfo.add(new InfoModel(image7, title7, color7, t7Subtitle1, t7Subtitle2, t7Desc1, t7Desc2));
        arrInfo.add(new InfoModel(image8, title8, color8, t8Subtitle1, t8Subtitle2, t8Desc1, t8Desc2));
    }
}