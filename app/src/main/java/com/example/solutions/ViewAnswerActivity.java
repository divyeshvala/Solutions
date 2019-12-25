package com.example.solutions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class ViewAnswerActivity extends AppCompatActivity
{
    private TextView answer;
    private TextView question, author, aboutAuthor, time;
    private ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_answer);

        answer = (TextView) findViewById(R.id.id_viewAnswer_answer);
        question = (TextView) findViewById(R.id.id_viewAnswer_question);
        author = (TextView) findViewById(R.id.id_viewAnswer_author);
        aboutAuthor = (TextView) findViewById(R.id.id_viewAnswer_about);
        time = (TextView) findViewById(R.id.id_viewAnswer_time);
        pic = (ImageView) findViewById(R.id.id_viewAnswer_profilePhoto);

        answer.setMovementMethod(new ScrollingMovementMethod());

        //String questionId = getIntent().getExtras().get("questionId").toString();

        question.setText( getIntent().getExtras().get("question").toString());
        answer.setText( getIntent().getExtras().get("answer").toString());
        author.setText( getIntent().getExtras().get("author").toString());
        aboutAuthor.setText( getIntent().getExtras().get("aboutAuthor").toString());
        time.setText( getIntent().getExtras().get("timeOfAnswer").toString());

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

    }
}
