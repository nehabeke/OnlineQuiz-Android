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
    EditText txtPassword, txtUserName;
    Button buttonLogin;
    TextView register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUserName = (EditText) findViewById(R.id.txtUserName);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        register = (TextView) findViewById(R.id.register);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (txtUserName.getText().equals(null) || txtUserName.getText().toString().equals(""))
                        Toast.makeText(getApplicationContext(), "Enter User Name", Toast.LENGTH_SHORT).show();
                    else if (txtPassword.getText().equals(null) || txtPassword.getText().toString().equals(""))
                        Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                    else {
                        if (txtUserName.getText().toString().equals("a") && txtPassword.getText().toString().equals("a")) {
                            Intent intent = new Intent(MainActivity.this, Adminchoice.class);
                            intent.putExtra("UserId", txtUserName.getText().toString().trim());
                            if (txtUserName.getText().toString().equals("a") && txtPassword.getText().toString().equals("a"))
                                intent.putExtra("IsAdmin","true");
                            else
                                intent.putExtra("IsAdmin", "false");

                            startActivity(intent);

                        } else {
                            boolean isValidUser = CheckIfValidUser();

                            if (isValidUser) {
                                Intent intent = new Intent(MainActivity.this, StudentDashboard.class);
                                intent.putExtra("UserId", txtUserName.getText().toString().trim());

                                if (txtUserName.getText().toString().equals("a") && txtPassword.getText().toString().equals("a"))
                                    intent.putExtra("IsAdmin","true");
                                else
                                    intent.putExtra("IsAdmin", "false");

                                startActivityForResult(intent, CH_REQUEST);
                                startActivity(intent);
                            } else
                                Toast.makeText(getApplicationContext(), "Invalid User!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(), "Some error occurred! Please try again later.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, addUserActivity.class);
                myIntent.putExtra("IsAdmin", "false");
                startActivity(myIntent);

            }
        });


    }

    public boolean CheckIfValidUser() {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        return dbHandler.CheckIfValidUser(txtUserName.getText().toString(), txtPassword.getText().toString());
    }

}