package com.example.solutions;

public class Question
{
    String question;
    String author;
    String emailOfAuthor;
    String profilePhoto;
    String about;
    String timeOfQuestion;
    String questionLikes;
    String answer;
    String answerAuthorName;
    String answerAuthorAbout;
    String answerLikes;
    String timeOfAnswer;

    public Question(String question, String author, String emailOfAuthor, String profilePhoto, String about, String timeOfQuestion, String questionLikes, String answer, String answerAuthorName, String answerAuthorAbout, String answerLikes, String timeOfAnswer) {
        this.question = question;
        this.author = author;
        this.emailOfAuthor = emailOfAuthor;
        this.profilePhoto = profilePhoto;
        this.about = about;
        this.timeOfQuestion = timeOfQuestion;
        this.questionLikes = questionLikes;
        this.answer = answer;
        this.answerAuthorName = answerAuthorName;
        this.answerAuthorAbout = answerAuthorAbout;
        this.answerLikes = answerLikes;
        this.timeOfAnswer = timeOfAnswer;
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

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getTimeOfQuestion() {
        return timeOfQuestion;
    }

    public void setTimeOfQuestion(String timeOfQuestion) {
        this.timeOfQuestion = timeOfQuestion;
    }

    public String getQuestionLikes() {
        return questionLikes;
    }

    public void setQuestionLikes(String questionLikes) {
        this.questionLikes = questionLikes;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerAuthorName() {
        return answerAuthorName;
    }

    public void setAnswerAuthorName(String answerAuthorName) {
        this.answerAuthorName = answerAuthorName;
    }

    public String getanswerAuthorAbout() {
        return answerAuthorAbout;
    }

    public void setanswerAuthorAbout(String answerAuthorAbout) {
        this.answerAuthorAbout = answerAuthorAbout;
    }

    public String getAnswerLikes() {
        return answerLikes;
    }

    public void setAnswerLikes(String answerLikes) {
        this.answerLikes = answerLikes;
    }

    public String getTimeOfAnswer() {
        return timeOfAnswer;
    }

    public void setTimeOfAnswer(String timeOfAnswer) {
        this.timeOfAnswer = timeOfAnswer;
    }
}
