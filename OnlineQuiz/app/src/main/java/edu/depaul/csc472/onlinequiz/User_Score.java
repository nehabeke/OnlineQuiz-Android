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
        if (id == R.id.action_settings) {
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

                User user = GetUser();
                if(user != null) {
                    txtFName.setText(user.getFname());
                    txtLName.setText(user.getLname());
                }

                int score = GetUserScore();
                txtScore.setText(String.valueOf(score));
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
        ArrayList<UserQuiz> list = dbHandler.GetUserScore(userId);

        int score = 0;
        for (int i = 0; i < list.size(); i++) {
            String iscorrect = list.get(i).getIsAnswerCorrect();
            if (iscorrect.equals("true")) {
                score++;
            }
        }

        return score;
    }
}
