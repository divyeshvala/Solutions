package com.example.solutions.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.solutions.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AskFragment extends Fragment
{
    public AskFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_ask, container, false);

        Button submitBTN = (Button) root.findViewById(R.id.id_ask_submitBTN);
        final EditText question = (EditText) root.findViewById(R.id.id_ask_question);

        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askQuestion(question.getText().toString());
            }
        });
        return root;
    }

    private void askQuestion(String question)
    {
        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference dataRefToSelf = FirebaseDatabase.getInstance().getReference().child("Users").child(current_user.getUid()).child("QuestionsAsked").push();

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int min = Calendar.getInstance().get(Calendar.MINUTE);
        String current_time = (String.valueOf(hour))+":"+(String.valueOf(min));

        Map<String, Object> questionData = new HashMap<>();
        questionData.put("question", question);
        questionData.put("author", current_user.getDisplayName());
        questionData.put("emailOfAuthor", current_user.getEmail());
        questionData.put("aboutAuthor", "Nothing yet.");
        questionData.put("profilePhoto", current_user.getPhotoUrl().toString());
        questionData.put("timeOfQuestion", current_time);
        questionData.put("questionLikes", "0");
        dataRefToSelf.updateChildren(questionData);

        final DatabaseReference dataRefToMainList = FirebaseDatabase.getInstance().getReference().child("Questions").child(dataRefToSelf.getKey());
        dataRefToMainList.updateChildren(questionData);

        Toast.makeText(getActivity(), "Added successfully.", Toast.LENGTH_SHORT).show();
    }

}
