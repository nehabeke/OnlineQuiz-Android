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

public class UserList extends ListActivity {

    ArrayList<User> list = new ArrayList<>();
    private static final String TAG = "UserList";
    private static final int CH_REQUEST = 100; // request code
    String userId;
    String isAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_user_list);

        list = GetAllUserList();

        setListAdapter(new UserDetail());
    }

    public ArrayList<User> GetAllUserList() {
        try {
            MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
            return dbHandler.GetAllUserList();
        }catch (Exception ex){
            throw ex;
        }
    }

    class UserDetail extends BaseAdapter {

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
                        inflater = (LayoutInflater) UserList.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    row = inflater.inflate(R.layout.activity_user_list, parent, false);
                }

                TextView txtUserListFName = (TextView) row.findViewById(R.id.txtUserListFName);
                TextView txtUserListLName = (TextView) row.findViewById(R.id.txtUserListLName);
                TextView txtUserListEmailId = (TextView) row.findViewById(R.id.txtUserListEmailId);

                User objUser = list.get(position);
                txtUserListFName.setText("First Name: " + objUser.getFname());
                txtUserListLName.setText("Last Name: " + objUser.getLname());
                txtUserListEmailId.setText("Email id: " + objUser.getEmailid());
                return row;
            }
            catch (Exception ex){
                throw ex;
            }
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Log.d(TAG, "onListItemClick position=" + position + " id=" + id + " " + list.get(position).getEmailid());
        Intent intent = new Intent(UserList.this, addUserActivity.class);
        intent.putExtra("UserId", list.get(position).getEmailid());
        intent.putExtra("IsAdmin", isAdmin);
        startActivityForResult(intent, CH_REQUEST);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_list, menu);
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
