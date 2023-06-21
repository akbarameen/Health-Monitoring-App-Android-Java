package com.example.healthyme.User;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthyme.Database.DbHelper;
import com.example.healthyme.Entities.UserModel;
import com.example.healthyme.R;
import com.example.healthyme.Sessions.LoginSessionManagement;
import com.example.healthyme.User.Login;
import com.example.healthyme.User.UpdateProfile;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;


public class ProfileFragment extends Fragment {
    Button profileEditBtn;
    LinearLayout logoutLL;
    TextView fullName, userWeight, userHeight;
    ImageView profileImage;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        //Hooks
        profileEditBtn = view.findViewById(R.id.profile_edit_btn);
        logoutLL = view.findViewById(R.id.logout_ll);
        fullName = view.findViewById(R.id.profile_full_name);
        profileImage = view.findViewById(R.id.profile_img);
        userWeight = view.findViewById(R.id.profile_user_weight);
        userHeight = view.findViewById(R.id.profile_user_height);

        int id = 0;


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("email_pref", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("userEmail", "").toString();

        DbHelper helper = new DbHelper(getContext());

        //getting user data
        Cursor cursorUser = helper.getUserData(email);
        while(cursorUser.moveToNext()){
            id = cursorUser.getInt(0);
            fullName.setText(cursorUser.getString(1));
            userHeight.setText(cursorUser.getString(4) + "cm");
            userWeight.setText(cursorUser.getString(5) + "kg");

        }
        int finalId = id;

        //fetching profile picture
        Cursor cursorImage = helper.getImage(finalId);
        while (cursorImage.moveToNext()) {
            Bitmap bitmap = convertByteArrayIntoBitmap(cursorImage.getBlob(1));
            profileImage.setImageBitmap(bitmap);
        }


        profileEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UpdateProfile.class);
                startActivity(intent);
            }
        });

        logoutLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertExitBox = new AlertDialog.Builder(getContext());
                alertExitBox.setTitle("Logout?");
                alertExitBox.setIcon(R.drawable.ic_logout);
                alertExitBox.setMessage("Are you sure you want to logout?");


                alertExitBox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LoginSessionManagement session = new LoginSessionManagement(getContext());
                        session.setLogin(false);
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("email_pref", Context.MODE_PRIVATE);
                        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.apply();
                        Intent intent = new Intent(getContext(), Login.class);
                        startActivity(intent);
                        requireActivity().finish();
                    }
                });
                alertExitBox.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(), "Welcome Back!", Toast.LENGTH_SHORT).show();
                    }
                });


                alertExitBox.show();




            }
        });

        return view;
    }

    // converting byteArray into Bitmap
    private Bitmap convertByteArrayIntoBitmap(byte[] bytes){
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}