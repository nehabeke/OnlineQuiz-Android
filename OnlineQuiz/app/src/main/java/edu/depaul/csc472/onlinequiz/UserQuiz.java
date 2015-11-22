package edu.depaul.csc472.onlinequiz;

/**
 * Created by Neha on 11/19/2015.
 */
public class UserQuiz {
    private String _userId;
    private int _questionId;
    private String _isAnswerCorrect;

    public UserQuiz(){}

    public UserQuiz(String userId, int questionId, String isCorrect) {
        this._userId = userId;
        this._questionId = questionId;
        this._isAnswerCorrect = isCorrect;
    }

    public String getUserId() {
        return _userId;
    }

    public void setUserId(String userId) {
        this._userId = userId;
    }

    public int getQuestionId() {
        return _questionId;
    }

    public void setQuestionId(int questionId) {
        this._questionId = questionId;
    }

    public String getIsAnswerCorrect() {
        return _isAnswerCorrect;
    }

    public void setIsCorrect(String isCorrect) {
        this._isAnswerCorrect = isCorrect;
    }
}
