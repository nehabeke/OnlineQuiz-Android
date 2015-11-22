package edu.depaul.csc472.onlinequiz;

/**
 * Created by Neha on 11/22/2015.
 */
public class StudentReport {
    private User user;
    private int score;

    public StudentReport(){}

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
