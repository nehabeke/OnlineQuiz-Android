package edu.depaul.csc472.onlinequiz;

/**
 * Created by Neha on 11/19/2015.
 */
public class UserQuiz {
    private String _userId;
    private int _questionId;
    private int _isAnswerCorrect;

    public UserQuiz(){}

    public UserQuiz(String userId, int questionId, int isCorrect) {
        this._userId = userId;
        this._questionId = questionId;
        this._isAnswerCorrect = isCorrect;
    }

    public String get_userId() {
        return _userId;
    }

    public void set_userId(String userId) {
        this._userId = userId;
    }

    public int get_questionId() {
        return _questionId;
    }

    public void set_questionId(int questionId) {
        this._questionId = questionId;
    }

    public int getIsAnswerCorrect() {
        return _isAnswerCorrect;
    }

    public void setIsCorrect(int isCorrect) {
        this._isAnswerCorrect = isCorrect;
    }
}
