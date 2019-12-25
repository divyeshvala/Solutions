package com.example.solutions.Fragments;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.solutions.MainActivity;
import com.example.solutions.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ProfileFragment extends Fragment
{
    private TextView nameTV, emailTV;
    private Button signOutBTN;
    private ImageView pic;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        // initialise all the variables here and write code in onCreate.
        nameTV = (TextView) root.findViewById(R.id.id_profile_name);
        emailTV = (TextView) root.findViewById(R.id.id_profile_email);
        signOutBTN = (Button) root.findViewById(R.id.id_profile_signOut);
        pic = (ImageView) root.findViewById(R.id.id_profile_pic);

        FirebaseUser account = FirebaseAuth.getInstance().getCurrentUser();

        nameTV.setText(account.getDisplayName());
        emailTV.setText(account.getEmail());

        try
        {
            // displaying image from url to recycler view item
            Glide.with(getActivity())
                    .load(account.getPhotoUrl().toString())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(pic);
        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(), "Couldn't load images", Toast.LENGTH_SHORT).show();
        }

        signOutBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                signOut();
            }
        });

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    private void signOut()
    {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getActivity(), MainActivity.class));
    }
}