package edu.depaul.csc472.onlinequiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addUserActivity extends Activity {
    private static final int CH_REQUEST = 100; // request code
    String userId;
    String isAdmin;
    EditText txtFName;
    EditText txtLName;
    EditText txtEmailId;
    EditText txtUserPassword;
    EditText txtReenterPassword;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        txtFName= (EditText) findViewById(R.id.txtFName);
        txtLName= (EditText) findViewById(R.id.txtLName);
        txtEmailId= (EditText) findViewById(R.id.txtEmailId);
        txtUserPassword = (EditText) findViewById(R.id.txtUserPassword);
        txtReenterPassword = (EditText) findViewById(R.id.txtReenterPassword);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        btnDelete.setEnabled(false);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtEmailId.getText() != null || txtEmailId.getText().toString() != "") {
                    if (DeleteUser()) {
                        Toast.makeText(getApplicationContext(), "User deleted successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(addUserActivity.this, Adminchoice.class);
                        intent.putExtra("IsAdmin", isAdmin);

                        startActivity(intent);
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Error occurred while deleting user.", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "Email id can not be empty.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void newUser(View view) {
        if(txtFName.getText().equals(null) || txtFName.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "Enter first name.", Toast.LENGTH_SHORT).show();
        else if(txtLName.getText().equals(null) || txtLName.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "Enter last name.", Toast.LENGTH_SHORT).show();
        else if(txtEmailId.getText().equals(null) || txtEmailId.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "Enter email id.", Toast.LENGTH_SHORT).show();
        else if(txtUserPassword.getText().equals(null) || txtUserPassword.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "Enter password.", Toast.LENGTH_SHORT).show();
        else if(txtReenterPassword.getText().equals(null) || txtReenterPassword.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "Re-enter password.", Toast.LENGTH_SHORT).show();
        else {
            if(txtUserPassword.getText().toString().trim().equals(txtReenterPassword.getText().toString().trim())) {
                MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

                User user = new User(txtFName.getText().toString().trim(), txtLName.getText().toString().trim(), txtEmailId.getText().toString().trim(), txtUserPassword.getText().toString().trim());

                dbHandler.addUser(user);
                Toast.makeText(getApplicationContext(), "User Saved Successfully.", Toast.LENGTH_SHORT).show();

                if(isAdmin.equals("false")) {
                    Intent intent = new Intent(addUserActivity.this, StudentDashboard.class);
                    intent.putExtra("UserId", userId);
                    intent.putExtra("IsAdmin", isAdmin);

                    startActivityForResult(intent, CH_REQUEST);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(addUserActivity.this, UserList.class);
                    intent.putExtra("IsAdmin", isAdmin);
                    startActivityForResult(intent, CH_REQUEST);
                    startActivity(intent);
                }
            }
            else {
                Toast.makeText(getApplicationContext(), "Password is not matching with confirm password.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean DeleteUser() {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        return dbHandler.deleteUser(txtEmailId.getText().toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        if (intent != null) {
            if(intent.getCharSequenceExtra("UserId") != null){

                isAdmin = intent.getCharSequenceExtra("IsAdmin").toString();

                if(isAdmin.equals("true"))
                    btnDelete.setEnabled(true);
                else
                    btnDelete.setEnabled(false);

                if(isAdmin.equals("false")){
                    userId = intent.getCharSequenceExtra("UserId").toString();
                    User objUser = GetUser();
                    if(objUser != null) {
                        txtFName.setText(objUser.getFname());
                        txtLName.setText(objUser.getLname());
                        txtEmailId.setText(objUser.getEmailid());
                        txtUserPassword.setText(objUser.getPassword());
                        txtReenterPassword.setText(objUser.getPassword());
                    }
                }
                else if(intent.getCharSequenceExtra("UserId") != null){
                    userId = intent.getCharSequenceExtra("UserId").toString();
                    User objUser = GetUser();
                    if(objUser != null) {
                        txtFName.setText(objUser.getFname());
                        txtLName.setText(objUser.getLname());
                        txtEmailId.setText(objUser.getEmailid());
                        txtUserPassword.setText(objUser.getPassword());
                        txtReenterPassword.setText(objUser.getPassword());
                    }
                }
            }
        }
    }

    public User GetUser() {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        return dbHandler.GetUser(userId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_user, menu);
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
