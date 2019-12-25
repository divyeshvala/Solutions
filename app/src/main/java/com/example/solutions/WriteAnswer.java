package com.example.solutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class WriteAnswer extends AppCompatActivity
{
    private EditText answer;
    private RelativeLayout submit;
    private TextView question, author, aboutAuthor, time;
    private ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_answer);

        answer = (EditText) findViewById(R.id.id_writeAnswer_answer);
        submit = (RelativeLayout) findViewById(R.id.id_writeAnswer_submitLayout);
        question = (TextView) findViewById(R.id.id_writeAnswer_question);
        author = (TextView) findViewById(R.id.id_writeAnswer_author);
        aboutAuthor = (TextView) findViewById(R.id.id_writeAnswer_about);
        time = (TextView) findViewById(R.id.id_writeAnswer_time);
        pic = (ImageView) findViewById(R.id.id_writeAnswer_profilePhoto);

        final String questionId = getIntent().getExtras().get("questionId").toString();

        question.setText( getIntent().getExtras().get("question").toString());
        author.setText( getIntent().getExtras().get("author").toString());
        aboutAuthor.setText( getIntent().getExtras().get("aboutAuthor").toString());
        time.setText( getIntent().getExtras().get("timeOfQuestion").toString());

        try
        {
            // displaying image from url to recycler view item
            Glide.with(this)
                    .load(getIntent().getExtras().get("profilePhoto").toString())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(pic);
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Couldn't load images", Toast.LENGTH_SHORT).show();
        }

        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                uploadAnswer(questionId);
            }
        });


    }

    private void uploadAnswer(String questionId)
    {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int min = Calendar.getInstance().get(Calendar.MINUTE);
        String current_time = (String.valueOf(hour))+":"+(String.valueOf(min));

        DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference().child("Questions").child(questionId).child("Answers").push();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Map<String, Object> data = new HashMap<>();
        data.put("answer", answer.getText().toString());
        data.put("answerAuthorName", user.getDisplayName());
        data.put("answerAuthorEmail", user.getEmail());
        data.put("answerAuthorAbout", "Nothing yet.");
        data.put("answerLikes", "0");
        data.put("timeOfAnswer", current_time);

        dataRef.updateChildren(data);
        
        Toast.makeText(this, "Answer added successfully.", Toast.LENGTH_SHORT).show();

    }
}
