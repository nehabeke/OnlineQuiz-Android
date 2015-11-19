package edu.depaul.csc472.onlinequiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registerUser extends Activity {
    EditText userId;
    EditText userFName;
    EditText userLName;
    EditText emailId;
    EditText passWord;
    Button back,Resgister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        userId=(EditText)findViewById(R.id.userId);
        userFName= (EditText) findViewById(R.id.userFName);
        userLName= (EditText) findViewById(R.id.userLName);
        emailId= (EditText) findViewById(R.id.emailId);
        passWord= (EditText) findViewById(R.id.passWord);
        back=(Button)findViewById(R.id.back);
        Resgister=(Button)findViewById(R.id.Resgister);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent( registerUser.this,MainActivity.class);
                startActivity(myIntent);
            }
        });


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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register_user, menu);
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
