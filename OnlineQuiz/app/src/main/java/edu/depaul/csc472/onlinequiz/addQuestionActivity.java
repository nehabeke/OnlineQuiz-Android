package edu.depaul.csc472.onlinequiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class addQuestionActivity extends Activity {
    EditText ETQuestion,optionA,optionB,optionC,optionD,Answer;
    TextView Qno;
    Button buttonAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        ETQuestion = (EditText) findViewById(R.id.ETQuestion);
        optionA = (EditText) findViewById(R.id.optionA);
        optionB = (EditText) findViewById(R.id.optionB);
        optionC = (EditText) findViewById(R.id.optionC);
        optionD = (EditText) findViewById(R.id.optionD);
        Answer = (EditText) findViewById(R.id.Answer);
        Qno = (TextView)findViewById(R.id.Qno);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
    }

    public void newQuestion(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        int qid = 0;
        if(Qno.getText() != null)
            qid =  Integer.parseInt(Qno.getText().toString());

        Question question=  new Question(qid, ETQuestion.getText().toString(),optionA.getText().toString(),optionB.getText().toString(),
               optionC.getText().toString(),optionD.getText().toString(),Integer.parseInt(Answer.getText().toString()) );

        dbHandler.addQuestion(question);
//        Qno.setText("");
//       ETQuestion.setText("");
//       optionA.setText("");
//       optionB.setText("");
//        optionC.setText("");
//        optionD.setText("");
//        Answer.setText("");
        Toast.makeText(getApplicationContext(), "Question Added Successfully.", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_question, menu);
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
}
