package com.example.solutions;

public class QuestionOnly
{
    String question;
    String author;
    String emailOfAuthor;
    String aboutAuthor;
    String profilePhoto;
    String timeOfQuestion;
    String questionLikes;
    String questionId;

    public QuestionOnly(String question, String author, String emailOfAuthor, String aboutAuthor, String profilePhoto, String timeOfQuestion, String questionLikes, String questionId) {
        this.question = question;
        this.author = author;
        this.emailOfAuthor = emailOfAuthor;
        this.aboutAuthor = aboutAuthor;
        this.profilePhoto = profilePhoto;
        this.timeOfQuestion = timeOfQuestion;
        this.questionLikes = questionLikes;
        this.questionId = questionId;
    }

    public String getQuestionLikes() {
        return questionLikes;
    }

    public void setQuestionLikes(String questionLikes) {
        this.questionLikes = questionLikes;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmailOfAuthor() {
        return emailOfAuthor;
    }

    public void setEmailOfAuthor(String emailOfAuthor) {
        this.emailOfAuthor = emailOfAuthor;
    }

    public String getAboutAuthor() {
        return aboutAuthor;
    }

    public void setAboutAuthor(String aboutAuthor) {
        this.aboutAuthor = aboutAuthor;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getTimeOfQuestion() {
        return timeOfQuestion;
    }

    public void setTimeOfQuestion(String timeOfQuestion) {
        this.timeOfQuestion = timeOfQuestion;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
