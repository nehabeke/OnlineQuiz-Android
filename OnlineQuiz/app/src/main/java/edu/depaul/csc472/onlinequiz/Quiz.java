package edu.depaul.csc472.onlinequiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Quiz extends Activity {

    private static final int CH_REQUEST = 100; // request code
    String userId;
    int questionId = 1;
    int prevQuestion = 0;
    int tempQuestionId = 0;
    int noOfQuestions = 0;
    int userAnswer;
    ArrayList<Integer> visitedQuestions = new ArrayList<>();
    TextView txtQuestion;
    RadioButton rdBtnA, rdBtnB, rdBtnC, rdBtnD;
    Button btnPrev, btnSave, btnNext, btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        txtQuestion = (TextView) findViewById(R.id.txtQuestion);

        rdBtnA = (RadioButton) findViewById(R.id.rdBtnA);
        rdBtnB = (RadioButton) findViewById(R.id.rdBtnB);
        rdBtnC = (RadioButton) findViewById(R.id.rdBtnC);
        rdBtnD = (RadioButton) findViewById(R.id.rdBtnD);

        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        if(noOfQuestions == 0)
            btnPrev.setEnabled(false);

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question = GetQuestion(prevQuestion);
                if(question != null) {
                    txtQuestion.setText(question.getQuestion().toString());
                    rdBtnA.setText(question.getOp1().toString());
                    rdBtnB.setText(question.getOp2().toString());
                    rdBtnC.setText(question.getOp3().toString());
                    rdBtnD.setText(question.getOp4().toString());

                    if(noOfQuestions == 0)
                        btnPrev.setEnabled(false);
                    else
                        btnPrev.setEnabled(true);

                    noOfQuestions --;

                    if(noOfQuestions == 1)
                        btnPrev.setEnabled(false);
                    else
                        btnPrev.setEnabled(true);

                    if(noOfQuestions >= 5)
                        btnNext.setEnabled(false);
                    else
                        btnNext.setEnabled(true);


                    if(noOfQuestions > 1)
                        prevQuestion = GetPrevQuestionId();

                }

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question = GetQuestion(questionId + 1);

                if(question != null) {
                    txtQuestion.setText(question.getQuestion().toString());
                    rdBtnA.setText(question.getOp1().toString());
                    rdBtnB.setText(question.getOp2().toString());
                    rdBtnC.setText(question.getOp3().toString());
                    rdBtnD.setText(question.getOp4().toString());

                    if(!visitedQuestions.contains(questionId))
                        visitedQuestions.add(questionId);

                    noOfQuestions ++;

                    if(noOfQuestions == 1)
                        btnPrev.setEnabled(false);
                    else
                        btnPrev.setEnabled(true);

                    if(noOfQuestions >= 5)
                        btnNext.setEnabled(false);
                    else
                        btnNext.setEnabled(true);

                    if(noOfQuestions > 1)
                        prevQuestion = GetPrevQuestionId();
                }

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveUserQuiz();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Quiz.this, User_Score.class);
            intent.putExtra("Score", GetUserScore());
            startActivityForResult(intent, CH_REQUEST);
            startActivity(intent);
        }
    });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
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
            Toast.makeText(getApplicationContext(), "UserId = " + userId, Toast.LENGTH_SHORT).show();

            Question question = GetQuestion(questionId);
            if(question != null) {
                visitedQuestions.add(questionId);
                noOfQuestions++;
                txtQuestion.setText(question.getQuestion().toString());
                rdBtnA.setText(question.getOp1().toString());
                rdBtnB.setText(question.getOp2().toString());
                rdBtnC.setText(question.getOp3().toString());
                rdBtnD.setText(question.getOp4().toString());
            }
        }
    }

    public Question GetQuestion(int qnId) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        Question question = dbHandler.GetQuestion(qnId);
        if(question != null){
            tempQuestionId = 0;
            questionId = qnId;

            return question;
        }
        else{
            if(tempQuestionId == 0)
                tempQuestionId = qnId;

            tempQuestionId = tempQuestionId + 1;
            GetQuestion(tempQuestionId);
        }
        return  null;
    }

    public int GetPrevQuestionId() {

        for(int i = 0; i < visitedQuestions.size(); i++){
            if(visitedQuestions.get(i) == questionId)
                return visitedQuestions.get(i - 1);
        }
        return 0;
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rdBtnA:
                if (checked)
                    userAnswer = 1;
                break;
            case R.id.rdBtnB:
                if (checked)
                    userAnswer = 2;
                break;
            case R.id.rdBtnC:
                if (checked)
                    userAnswer = 3;
                break;
            case R.id.rdBtnD:
                if (checked)
                    userAnswer = 4;
                break;
        }
    }

    public int checkIfAnwerTrue(){
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        Question question = dbHandler.GetQuestion(questionId);

        if(question.getAnswer() == userAnswer)
            return 1;
        else
            return 0;
    }

    public  void SaveUserQuiz(){
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        UserQuiz userQuiz = new UserQuiz(userId, questionId, checkIfAnwerTrue());
        dbHandler.addUserQuiz(userQuiz);
    }

    public int GetUserScore(){
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        ArrayList<UserQuiz> list = dbHandler.GetUserScore(userId);

        int score = 0;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getIsAnswerCorrect() == 1){
                score++;
            }
        }

        return score;
    }
}
