package edu.depaul.csc472.onlinequiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class StudentDashboard extends Activity {

    private static final int CH_REQUEST = 100; // request code
    String userId;
    Button btnStartQuiz;
    Button btnReport;
    Button btnProfile;
    String isAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        btnStartQuiz = (Button) findViewById(R.id.btnStartQuiz);
        btnReport = (Button) findViewById(R.id.btnReport);
        btnProfile = (Button) findViewById(R.id.btnProfile);

        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentDashboard.this, Quiz.class);
                intent.putExtra("UserId", userId);
                intent.putExtra("IsAdmin", isAdmin);
                startActivityForResult(intent, CH_REQUEST);
                startActivity(intent);

            }
        });

        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentDashboard.this, User_Score.class);
                intent.putExtra("UserId", userId);
                intent.putExtra("IsAdmin", isAdmin);
                startActivityForResult(intent, CH_REQUEST);
                startActivity(intent);

            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentDashboard.this, addUserActivity.class);
                intent.putExtra("UserId", userId);
                intent.putExtra("IsAdmin", isAdmin);
                startActivityForResult(intent, CH_REQUEST);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_dashboard, menu);
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
        super.onStart();
        Intent intent = getIntent();
        if (intent != null) {
            userId = intent.getCharSequenceExtra("UserId").toString();
            isAdmin = intent.getCharSequenceExtra("IsAdmin").toString();
        }
    }
}
