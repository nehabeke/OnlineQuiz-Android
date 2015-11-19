package edu.depaul.csc472.onlinequiz;

/**
 * Created by Akshu on 11/17/2015.
 */
public class Question {
    private int _id;
    private String _question;
    private String _op1;
    private String _op2;
    private String _op3;
    private String _op4;
    private String _answer;

    public Question() {

    }

    public Question(int id, String question, String op1, String op2, String op3, String op4, String answer) {
        this._id = id;
        this._question = question;
        this._op1 = op1;
        this._op2 = op2;
        this._op3 = op3;
        this._op4 = op4;
        this._answer = answer;
    }

    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public String getQuestion() {
        return this._question;
    }

    public void setQuestion(String question) {
        this._question = question;
    }

    public String getOp1() {
        return this._op1;
    }

    public void setOp1(String op1) {
        this._op1 = op1;
    }

    public String getOp2() {
        return this._op2;
    }

    public void setOp2(String op2) {
        this._op2 = op2;
    }

    public String getOp3() {
        return this._op3;
    }

    public void setOp3(String op3) {
        this._op3 = op3;
    }

    public String getOp4() {
        return this._op1;
    }

    public void setOp4(String op4) {
        this._op4 = op4;
    }
    public String getAnswer() {
        return this._answer;
    }

    public void setAnswer(String answer) {
        this._answer = answer;
    }
}
