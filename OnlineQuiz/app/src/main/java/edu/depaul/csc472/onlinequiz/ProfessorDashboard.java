package edu.depaul.csc472.onlinequiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ProfessorDashboard extends Activity {

    private static final int CH_REQUEST = 100; // request code
    Button btnStudentReports;
    Button btnQuestionList;
    Button btnProfessorQuestionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_dashboard);

        btnStudentReports = (Button) findViewById(R.id.btnStudentReports);
        btnQuestionList = (Button) findViewById(R.id.btnQuestionList);
        btnProfessorQuestionList = (Button) findViewById(R.id.btnProfessorQuestionList);

        btnStudentReports.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ProfessorDashboard.this, StudentReportList.class);
                    intent.putExtra("IsAdmin", "professor");
                    startActivity(intent);
                }
        });

        btnProfessorQuestionList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfessorDashboard.this, addQuestionActivity.class);
                intent.putExtra("IsAdmin", "professor");
                startActivity(intent);

            }
        });

        btnQuestionList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfessorDashboard.this, QuestionList.class);
                intent.putExtra("IsAdmin", "professor");
                startActivity(intent);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_professor_dashboard, menu);
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
            Intent intent = new Intent(ProfessorDashboard.this, MainActivity.class);
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
