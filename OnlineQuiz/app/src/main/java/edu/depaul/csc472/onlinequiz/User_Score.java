package edu.depaul.csc472.onlinequiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class User_Score extends Activity {

    String userId;
    TextView txtScore;
    TextView txtFName;
    TextView txtLName;
    String isAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__score);

        txtScore = (TextView) findViewById(R.id.txtScore);
        txtFName = (TextView) findViewById(R.id.txtFName);
        txtLName = (TextView) findViewById(R.id.txtLName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user__score, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.title_activity_mainactivity) {
            Intent intent = new Intent(User_Score.this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.title_activity_home) {
            if(isAdmin.equals("false")) {
                Intent intent = new Intent(User_Score.this, StudentDashboard.class);
                intent.putExtra("UserId", userId);
                intent.putExtra("IsAdmin", isAdmin);
                startActivity(intent);
            }
            else if(isAdmin.equals("professor")) {
                Intent intent = new Intent(User_Score.this, ProfessorDashboard.class);
                intent.putExtra("UserId", userId);
                intent.putExtra("IsAdmin", isAdmin);
                startActivity(intent);
            }
            else{
                Intent intent = new Intent(User_Score.this, Adminchoice.class);
                intent.putExtra("UserId", userId);
                intent.putExtra("IsAdmin", isAdmin);
                startActivity(intent);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        try {
            super.onStart();
            Intent intent = getIntent();
            if (intent != null) {
                userId = intent.getCharSequenceExtra("UserId").toString();
                isAdmin = intent.getCharSequenceExtra("IsAdmin").toString();
                User user = GetUser();
                if(user != null) {
                    txtFName.setText(user.getFname());
                    txtLName.setText(user.getLname());
                }

                int score = GetUserScore();
                txtScore.setText(String.valueOf(score) + " / 5");
            }
        }
        catch (Exception ex){
            throw ex;
        }
    }

    public User GetUser(){
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        return dbHandler.GetUser(userId);
    }

    public int GetUserScore() {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        ArrayList<UserQuiz> list = dbHandler.GetUserQuizByUserId(userId);

        int score = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIsAnswerCorrect().equals("true")) {
                score++;
            }
        }

        return score;
    }
}
