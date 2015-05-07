package omicron.mss;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;


public class LoginActivity extends ActionBarActivity {
    ParseObject parse;
    //protected Button testButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*testButton = (Button) findViewById(R.id.button13);
        testButton.setOnClickListener(new View.OnClickListener(){


        });*/

        parse = new ParseObject("ParseObj");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    //called when user clicks Login button
    public void login(View view) throws ParseException {
        final Intent loginIntent = new Intent(this, MainActivity.class);
        EditText username = (EditText) findViewById(R.id.usernameText);
        String name = username.getText().toString();
        EditText password = (EditText) findViewById(R.id.passwordText);
        String pass = password.getText().toString();
        ParseUser.logInInBackground(name, pass, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    startActivity(loginIntent);
                } else {
                    Toast.makeText(LoginActivity.this,"Login Failed", Toast.LENGTH_LONG).show();
                    System.out.println("LoginError");
                    // Signup failed. Look at the ParseException to see what happened.
                }
            }
        });
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
    //called when user clicks Need to register
    public void gotoRegister(View view) {
        Intent registerIntent = new Intent(this, RegisterUserActivity.class);
        startActivity(registerIntent);
    }
    //called when user clicks Need to register
    public void gotoForget(View view) {
        Intent ForgetIntent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(ForgetIntent);
    }

    public void testFunction(View view) throws ParseException {
        QueryExample test = new QueryExample();
        test.findClass();
    }
}
