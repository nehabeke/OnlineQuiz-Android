package edu.depaul.csc472.onlinequiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class addUserActivity extends Activity {
    EditText userId;
    EditText userFName;
    EditText userLName;
    EditText emailId;
    EditText passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        userId=(EditText)findViewById(R.id.userId);
        userFName= (EditText) findViewById(R.id.userFName);
        userLName= (EditText) findViewById(R.id.userLName);
        emailId= (EditText) findViewById(R.id.emailId);
        passWord= (EditText) findViewById(R.id.passWord);
    }
    public void newUser(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        int uid =  Integer.parseInt(userId.getText().toString());
        User user=  new User(uid,userFName.getText().toString(),userLName.getText().toString(),emailId.getText().toString(),passWord.getText().toString());

        dbHandler.addUser(user);
        userId.setText("");
        userFName.setText("");
        userLName.setText("");
        emailId.setText("");
        passWord.setText("");

    }

   /* public void lookupUser (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        User user = dbHandler.findUser(emailId.getText().toString());

        if (user != null) {
            userId.setText(String.valueOf(user.getID()));

            userFName.setText(String.valueOf(user.getFname()));
            userLName.setText(String.valueOf(user.getLname()));
            emailId.setText(String.valueOf(user.getEmailid()));
            passWord.setText("");

        } else {
            userId.setText("No Match Found");
        }
    }

    public void removeUser (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        boolean result = dbHandler.deleteUser(
                emailId.getText().toString());

        if (result)
        {
            userId.setText("Record Deleted");
            userFName.setText("");
            userLName.setText("");
            passWord.setText("");
        }
        else
            userId.setText("No Match Found");
    }*/
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
