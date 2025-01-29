package com.example.quiz;

public class Question {
    private final String question;
    private final String[] answers;
    private final int correctAnswer;
    private final boolean isQuestion;
    private boolean isAnswered;
    private boolean isAnsweredCorrectly;
    private int chosenAnswer;

    public Question(String question, String[] answers, int correctAnswer, boolean isQuestion) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.isQuestion = isQuestion;
        this.isAnswered = false;
        this.isAnsweredCorrectly = false;
        this.chosenAnswer = -1;
    }

    public String GetQuestion() {
        return question;
    }

    public String[] GetAnswers() {
        return answers;
    }

    public int GetCorrectAnswer() {
        return correctAnswer;
    }

    public boolean GetIsQuestion(){
        return isQuestion;
    }

    public boolean GetIsAnswered() {
        return isAnswered;
    }

    public void SetIsAnswered(boolean isAnswered) {
        this.isAnswered = isAnswered;
    }

    public boolean GetIsAnsweredCorrectly(){
        return isAnsweredCorrectly;
    }
    public void SetIsAnsweredCorrectly(boolean isCorrect){
        this.isAnsweredCorrectly = isCorrect;
    }

    public int GetChosenAnswer(){
        return this.chosenAnswer;
    }
    public void SetChosenAnswer(int index){
        this.chosenAnswer = index;
    }
}
