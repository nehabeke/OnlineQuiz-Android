package edu.depaul.csc472.onlinequiz;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionList extends ListActivity {

    ArrayList<Question> list = new ArrayList<>();
    private static final String TAG = "QuestionList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list = GetAllQuestionList();

        setListAdapter(new QuestionDetail());
    }

    public ArrayList<Question> GetAllQuestionList() {
        try {
            MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
            return dbHandler.GetAllQuestionList();
        }catch (Exception ex){
            throw ex;
        }
    }

    class QuestionDetail extends BaseAdapter {

        private LayoutInflater inflater;

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView (int position, View convertView, ViewGroup parent) {
            try {
                View row = convertView;
                if (convertView == null) {
                    if (inflater == null)
                        inflater = (LayoutInflater) QuestionList.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    row = inflater.inflate(R.layout.activity_question_list, parent, false);
                }

                TextView txtQuestionListId = (TextView) row.findViewById(R.id.txtQuestionListId);
                TextView txtQuestionListQuestion = (TextView) row.findViewById(R.id.txtQuestionListQuestion);
                TextView txtQuestionListOp1 = (TextView) row.findViewById(R.id.txtQuestionListOp1);
                TextView txtQuestionListOp2 = (TextView) row.findViewById(R.id.txtQuestionListOp2);
                TextView txtQuestionListOp3 = (TextView) row.findViewById(R.id.txtQuestionListOp3);
                TextView txtQuestionListOp4 = (TextView) row.findViewById(R.id.txtQuestionListOp4);
                TextView txtQuestionListAnswer = (TextView) row.findViewById(R.id.txtQuestionListAnswer);

                Question objQuestion = list.get(position);
                txtQuestionListId.setText("Qustion Id: " + objQuestion.getID());
                txtQuestionListQuestion.setText("Qustion: " + objQuestion.getQuestion());
                txtQuestionListOp1.setText("Option 1: " + objQuestion.getOp1());
                txtQuestionListOp2.setText("Option 2: " + objQuestion.getOp2());
                txtQuestionListOp3.setText("Option 3: " + objQuestion.getOp3());
                txtQuestionListOp4.setText("Option 4: " + objQuestion.getOp4());
                txtQuestionListAnswer.setText("Answer: " + objQuestion.getAnswer());
                return row;
            }
            catch (Exception ex){
                throw ex;
            }
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Log.d(TAG, "onListItemClick position=" + position + " id=" + id + " " + list.get(position).getID());
        Intent intent = new Intent(QuestionList.this, addQuestionActivity.class);
        intent.putExtra("QuestionId", list.get(position).getID());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question_list, menu);
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
