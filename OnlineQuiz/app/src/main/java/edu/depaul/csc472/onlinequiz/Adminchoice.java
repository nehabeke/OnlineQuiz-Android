package edu.depaul.csc472.onlinequiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Adminchoice extends Activity {
    String userId;
    String isAdmin;
    Button addUserButton,addQuestionButton;
    Button btnUserList;
    Button btnStudentReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminchoice);
        addUserButton = (Button)findViewById(R.id.addUserButton);

        addQuestionButton = (Button) findViewById(R.id.addQuestionButton);
        btnUserList = (Button) findViewById(R.id.btnUserList);
        btnStudentReports = (Button) findViewById(R.id.btnStudentReports);


        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Adminchoice.this, addUserActivity.class);
                intent.putExtra("IsAdmin", isAdmin);
                startActivity(intent);

            }
        });

        addQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Adminchoice.this, addQuestionActivity.class);
                intent.putExtra("IsAdmin", isAdmin);
                startActivity(intent);

            }
        });

        btnUserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Adminchoice.this, UserList.class);
                intent.putExtra("IsAdmin", isAdmin);
                startActivity(intent);

            }
        });

        btnStudentReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Adminchoice.this, StudentReportList.class);
                intent.putExtra("IsAdmin", "true");
                startActivity(intent);
            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        Intent intent = getIntent();
//        if (intent != null) {
//            userId = intent.getCharSequenceExtra("UserId").toString();
//            isAdmin = intent.getCharSequenceExtra("IsAdmin").toString();
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_adminchoice, menu);
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
            isAdmin = intent.getCharSequenceExtra("IsAdmin").toString();
        }
    }
}
