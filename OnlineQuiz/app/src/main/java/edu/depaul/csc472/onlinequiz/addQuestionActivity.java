package edu.depaul.csc472.onlinequiz;

import android.app.Activity;
import android.content.Intent;
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
    int questionId;
    String isAdmin;
    Button btnDeleteQuestion;

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
        Qno.setText("");
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        btnDeleteQuestion = (Button) findViewById(R.id.btnDeleteQuestion);

        btnDeleteQuestion.setEnabled(false);

        btnDeleteQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DeleteQuestion()) {
                        Toast.makeText(getApplicationContext(), "User deleted successfully.", Toast.LENGTH_SHORT).show();

                        if(isAdmin.equals("true")) {
                            Intent intent = new Intent(addQuestionActivity.this, Adminchoice.class);
                            intent.putExtra("IsAdmin", isAdmin);
                            startActivity(intent);
                        }
                        else{
                            Intent intent = new Intent(addQuestionActivity.this, ProfessorDashboard.class);
                            intent.putExtra("IsAdmin", isAdmin);
                            startActivity(intent);
                        }
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Error occurred while deleting question.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean DeleteQuestion() {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        return dbHandler.DeleteQuestion(Integer.parseInt(Qno.getText().toString()));
    }

    public void newQuestion(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        int qid = 0;

        if(ETQuestion.getText().equals(null) || ETQuestion.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "Enter Question!", Toast.LENGTH_SHORT).show();
        else if(optionA.getText().equals(null) || optionA.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "Enter option 1!", Toast.LENGTH_SHORT).show();
        else if(optionB.getText().equals(null) || optionB.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "Enter option 2!", Toast.LENGTH_SHORT).show();
        else if(optionC.getText().equals(null) || optionC.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "Enter option 3!", Toast.LENGTH_SHORT).show();
        else if(optionD.getText().equals(null) || optionD.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "Enter option 4!", Toast.LENGTH_SHORT).show();
        else if(Answer.getText().equals(null) || Answer.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "Enter Answer!", Toast.LENGTH_SHORT).show();
        else {
            if(Qno.getText().equals(null) || Qno.getText().toString().equals("")) {
                //do nothing
            }
            else
                qid =  Integer.parseInt(Qno.getText().toString());

            Question question = new Question(qid, ETQuestion.getText().toString(), optionA.getText().toString(), optionB.getText().toString(),
                    optionC.getText().toString(), optionD.getText().toString(), Integer.parseInt(Answer.getText().toString()));

            dbHandler.addQuestion(question);

            Toast.makeText(getApplicationContext(), "Question Added Successfully.", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(addQuestionActivity.this, QuestionList.class);
            intent.putExtra("IsAdmin", isAdmin);
            startActivity(intent);
        }
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
        if (id == R.id.title_activity_mainactivity) {
            Intent intent = new Intent(addQuestionActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.title_activity_home) {
            if(isAdmin.equals("true")) {
                Intent intent = new Intent(addQuestionActivity.this, Adminchoice.class);
                intent.putExtra("IsAdmin", isAdmin);
                startActivity(intent);
            }
            else{
                Intent intent = new Intent(addQuestionActivity.this, ProfessorDashboard.class);
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
                isAdmin = intent.getCharSequenceExtra("IsAdmin").toString();
                questionId = intent.getIntExtra("QuestionId", 0);

                Question objQuestion = GetQuestion();
                if (objQuestion != null) {
                    Qno.setText(String.valueOf(objQuestion.getID()));
                    ETQuestion.setText(objQuestion.getQuestion());
                    optionA.setText(objQuestion.getOp1());
                    optionB.setText(objQuestion.getOp2());
                    optionC.setText(objQuestion.getOp3());
                    optionD.setText(objQuestion.getOp4());
                    Answer.setText(String.valueOf(objQuestion.getAnswer()));
                }

            }

            if(Qno.getText().equals(null) || Qno.getText().toString().equals(""))
                btnDeleteQuestion.setEnabled(false);
            else
                btnDeleteQuestion.setEnabled(true);

        }catch (Exception ex){
            throw ex;
        }
    }

    public Question GetQuestion() {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        return dbHandler.GetQuestion(questionId);
    }
}
