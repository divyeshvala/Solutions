package com.example.solutions.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.solutions.Question;
import com.example.solutions.QuestionOnly;
import com.example.solutions.QuestionsAdapter;
import com.example.solutions.QuestionsOnlyAdapter;
import com.example.solutions.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class WriteFragment extends Fragment
{
    ArrayList<QuestionOnly> questionsList;
    QuestionsOnlyAdapter adapter;

    public WriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_write, container, false);

        questionsList = new ArrayList<>();

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.write_recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager questionListLayoutManager = new LinearLayoutManager(getActivity());
        //for displaying bottom to top
        //((LinearLayoutManager) questionListLayoutManager).setReverseLayout(true);
        recyclerView.setLayoutManager(questionListLayoutManager);
        adapter = new QuestionsOnlyAdapter(getActivity(), questionsList);
        recyclerView.setAdapter(adapter);

        getQuestions();

        return root;
    }

    private void getQuestions()
    {
        final DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference().child("Questions");

        dataRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {
                if(dataSnapshot.exists())
                {
                    final String question = dataSnapshot.child("question").getValue(String.class);
                    final String author = dataSnapshot.child("author").getValue(String.class);
                    final String about = dataSnapshot.child("aboutAuthor").getValue(String.class);
                    final String emailOfAuthor = dataSnapshot.child("emailOfAuthor").getValue(String.class);
                    final String profilePhoto = dataSnapshot.child("profilePhoto").getValue(String.class);
                    final String timeOfQuestion = dataSnapshot.child("timeOfQuestion").getValue(String.class);
                    final String questionLikes = dataSnapshot.child("questionLikes").getValue(String.class);

                    QuestionOnly object = new QuestionOnly(question, author, emailOfAuthor, about, profilePhoto, timeOfQuestion, questionLikes, dataSnapshot.getKey());

                    questionsList.add(object);
                    //questionListLayoutManager.scrollToPosition(questionsList.size()-1);
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }

}
