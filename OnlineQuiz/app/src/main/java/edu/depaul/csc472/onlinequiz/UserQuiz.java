package edu.depaul.csc472.onlinequiz;

/**
 * Created by Neha on 11/19/2015.
 */
public class UserQuiz {
    private String userId;
    private int questionId;
    private String userAnswer;
    private boolean isCorrect;


    public UserQuiz(String userId, int questionId, String userAnswer, boolean isCorrect) {
        this.userId = userId;
        this.questionId = questionId;
        this.userAnswer = userAnswer;
        this.isCorrect = isCorrect;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
}
