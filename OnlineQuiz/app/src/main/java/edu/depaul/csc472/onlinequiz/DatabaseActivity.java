package edu.depaul.csc472.onlinequiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
/**
 * Created by Neha on 11/5/2015.
 */
public class DatabaseActivity extends Activity {
    EditText userId;
    EditText userFName;
    EditText userLName;
    EditText emailId;
    EditText passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);


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


}