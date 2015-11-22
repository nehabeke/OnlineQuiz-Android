package edu.depaul.csc472.onlinequiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final int CH_REQUEST = 100; // request code
    EditText PasswordEditText, userNameEditText;
    Button buttonLogin;
    TextView register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PasswordEditText = (EditText) findViewById(R.id.PasswordEditText);
        userNameEditText = (EditText) findViewById(R.id.userNameEditText);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        register = (TextView) findViewById(R.id.register);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userNameEditText.getText().toString().equals("a") &&
                        PasswordEditText.getText().toString().equals("a")) {
                    Intent myIntent = new Intent(MainActivity.this, Adminchoice.class);
                    startActivity(myIntent);

                } else {
                    boolean isValidUser = CheckIfValidUser();

                    if (isValidUser) {
                        Intent intent = new Intent(MainActivity.this, StudentDashboard.class);
                        intent.putExtra("UserId", userNameEditText.getText().toString().trim());
                        startActivityForResult(intent, CH_REQUEST);
                        startActivity(intent);
                    } else
                        Toast.makeText(getApplicationContext(), "Invalid User!", Toast.LENGTH_SHORT).show();


                }
//                else {
//
//                    Toast.makeText(getApplicationContext(), "Invalid User!",Toast.LENGTH_SHORT).show();
//
//                }

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, addUserActivity.class);
                startActivity(myIntent);

            }
        });


    }

    public boolean CheckIfValidUser() {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        return dbHandler.CheckIfValidUser(userNameEditText.getText().toString(), PasswordEditText.getText().toString());

//        if(isValidUser)
//            Toast.makeText(getApplicationContext(), "Hi User",Toast.LENGTH_SHORT).show();
//        else
//            Toast.makeText(getApplicationContext(), "Invalid User!",Toast.LENGTH_SHORT).show();
    }

}