package com.example.solutions.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.solutions.Question;
import com.example.solutions.R;
import com.example.solutions.QuestionsAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment
{
    ArrayList<Question> questionsList;
    QuestionsAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        questionsList = new ArrayList<>();

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.id_home_recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager questionListLayoutManager = new LinearLayoutManager(getActivity());
        //for displaying bottom to top
        //((LinearLayoutManager) questionListLayoutManager).setReverseLayout(true);
        recyclerView.setLayoutManager(questionListLayoutManager);
        adapter = new QuestionsAdapter(getActivity(), questionsList);
        recyclerView.setAdapter(adapter);

        getQuestions();

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
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

                    // check if it has answer...
                    final DatabaseReference dataRefOfAnswer = FirebaseDatabase.getInstance().getReference().child("Questions").child(dataSnapshot.getKey()).child("Answers");

                    dataRefOfAnswer.addValueEventListener(new ValueEventListener()
                    {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                        {
                            if(dataSnapshot.exists())
                            {
                                // right now I am considering only first answer.
                                for(DataSnapshot childSnapshot : dataSnapshot.getChildren())
                                {
                                    String answer = childSnapshot.child("answer").getValue(String.class);
                                    String answerAuthorName = childSnapshot.child("answerAuthorName").getValue(String.class);
                                    String answerAuthorAbout = childSnapshot.child("answerAuthorAbout").getValue(String.class);
                                    String answerLikes = childSnapshot.child("answerLikes").getValue(String.class);
                                    String timeOfAnswer = childSnapshot.child("timeOfAnswer").getValue(String.class);

                                    Question object = new Question(question, author, emailOfAuthor, profilePhoto, about, timeOfQuestion, questionLikes, answer, answerAuthorName, answerAuthorAbout, answerLikes, timeOfAnswer);

                                    questionsList.add(object);
                                    //questionListLayoutManager.scrollToPosition(questionsList.size()-1);
                                    adapter.notifyDataSetChanged();
                                    break;
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

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