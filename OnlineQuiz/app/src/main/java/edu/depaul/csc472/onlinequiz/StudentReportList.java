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

public class StudentReportList extends ListActivity {

    ArrayList<StudentReport> list = new ArrayList<>();
    private static final String TAG = "StudentReportList";
    String isAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_student_report_list);
        list = GetAllUserQuizDetails();

        setListAdapter(new StudentReportDetail());
    }

    public ArrayList<StudentReport> GetAllUserQuizDetails() {
        try {
            MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
            return dbHandler.GetAllUserQuizDetails();
        }catch (Exception ex){
            throw ex;
        }
    }

    class StudentReportDetail extends BaseAdapter {

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
                        inflater = (LayoutInflater) StudentReportList.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    row = inflater.inflate(R.layout.activity_student_report_list, parent, false);
                }

                TextView txtFName = (TextView) row.findViewById(R.id.txtListFName);
                TextView txtEmailId = (TextView) row.findViewById(R.id.txtListEmailId);
                TextView txtScore = (TextView) row.findViewById(R.id.txtListScore);

                StudentReport objStudentReport = list.get(position);
                txtFName.setText("Name: " + objStudentReport.getUser().getFname() + " " + objStudentReport.getUser().getLname());
                txtEmailId.setText("Email id: " + objStudentReport.getUser().getEmailid());
                txtScore.setText("Score: " + String.valueOf(objStudentReport.getScore()));
                return row;
            }
            catch (Exception ex){
                throw ex;
            }
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Log.d(TAG, "onListItemClick position=" + position + " id=" + id + " " + list.get(position).getUser().getEmailid());
        Intent intent = new Intent(StudentReportList.this, User_Score.class);
        intent.putExtra("UserId", list.get(position).getUser().getEmailid());
        intent.putExtra("IsAdmin", isAdmin);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_report_list, menu);
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
            Intent intent = new Intent(StudentReportList.this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.title_activity_home) {
            if(isAdmin.equals("true")) {
                Intent intent = new Intent(StudentReportList.this, Adminchoice.class);
                intent.putExtra("IsAdmin", isAdmin);
                startActivity(intent);
            }
            else{
                Intent intent = new Intent(StudentReportList.this, ProfessorDashboard.class);
                intent.putExtra("IsAdmin", isAdmin);
                startActivity(intent);
            }
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
