package com.example.solutions.Utils;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.Map;

public class FirebaseUtils
{

    public FirebaseUtils()
    {
        //
    }

    public static void addData(final Context context, final DatabaseReference dataRef, final Map<String, Object> data)
    {
        dataRef.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                dataRef.updateChildren(data);
                Toast.makeText(context, "Added Successfully.", Toast.LENGTH_LONG).show();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                Toast.makeText(context, "Error occurred please try again later.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
