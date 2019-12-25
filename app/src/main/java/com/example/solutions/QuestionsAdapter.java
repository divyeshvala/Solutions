package com.example.solutions;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class QuestionsAdapter extends RecyclerView.Adapter< QuestionsAdapter.QuestionListViewHolder >
{
    ArrayList<Question> questionList;
    Context context;

    public QuestionsAdapter(Context context, ArrayList<Question> questionList)
    {
        this.questionList = questionList;
        this.context = context;
    }

    public class QuestionListViewHolder extends RecyclerView.ViewHolder
    {
        private TextView author, question, time, about, answer;
        private RelativeLayout layout;
        private ImageView pic;

        public  QuestionListViewHolder(View view)
        {
            super(view);

            question = (TextView) view.findViewById(R.id.id_item_question_question);
            author = (TextView) view.findViewById(R.id.id_item_question_author);
            time = (TextView) view.findViewById(R.id.id_item_question_time);
            about = (TextView) view.findViewById(R.id.id_item_question_about);
            answer = (TextView) view.findViewById(R.id.id_item_question_answer);
            layout = (RelativeLayout) view.findViewById(R.id.item_question_layout);
            pic = (ImageView) view.findViewById(R.id.id_item_question_profilePhoto);
        }

        public ImageView getImage()
        {
            return this.pic;
        }
    }

    @NonNull
    @Override
    public QuestionListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        return new QuestionListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_question, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionListViewHolder questionListViewHolder, final int i)
    {
        try
        {
            // displaying image from url to recycler view item
            Glide.with(this.context)
                    .load(questionList.get(i).getProfilePhoto())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(questionListViewHolder.getImage());
        }
        catch (Exception e)
        {
            Toast.makeText(this.context, "Couldn't load images", Toast.LENGTH_SHORT).show();
        }

        questionListViewHolder.question.setText(questionList.get(i).getQuestion());
        questionListViewHolder.time.setText(questionList.get(i).getTimeOfAnswer());
        questionListViewHolder.about.setText(questionList.get(i).getanswerAuthorAbout());
        questionListViewHolder.author.setText(questionList.get(i).getAnswerAuthorName());
        questionListViewHolder.answer.setText(questionList.get(i).getAnswer());
        questionListViewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, ViewAnswerActivity.class);
                //intent.putExtra("questionId", questionList.get(i).getQuestionId());
                intent.putExtra("question", questionList.get(i).getQuestion());
                intent.putExtra("author", questionList.get(i).getAnswerAuthorName());
                intent.putExtra("aboutAuthor", questionList.get(i).getanswerAuthorAbout());
                intent.putExtra("profilePhoto", questionList.get(i).getProfilePhoto());
                intent.putExtra("timeOfAnswer", questionList.get(i).getTimeOfAnswer());
                intent.putExtra("answer", questionList.get(i).getAnswer());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return questionList.size();
    }
}