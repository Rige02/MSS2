package omicron.mss;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SignUpCallback;
import com.parse.ParseUser;
import com.parse.ParseException;

import static java.lang.Object.*;


public class RegisterUserActivity extends ActionBarActivity {

    protected EditText rUsername;
    protected EditText rPassword;
    protected EditText rConfirmPassword;
    protected Button rRegisterButton;
    protected EditText rEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        //initializing user and pass
        rUsername = (EditText) findViewById(R.id.usernameRegisterEditText);
        rPassword = (EditText) findViewById(R.id.passwordRegisterEditText);
        rConfirmPassword = (EditText) findViewById(R.id.confirmPasswordRegisterEditText);
        rRegisterButton = (Button) findViewById(R.id.registerButton);
        rEmail = (EditText) findViewById(R.id.emailRegisterEditText);

        //Setting Button to listen to click
        rRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = rUsername.getText().toString().trim();
                String password = rPassword.getText().toString().trim();
                String email = rEmail.getText().toString().trim();
                String confirmPassword = rConfirmPassword.getText().toString().trim();

                if(password.equals(confirmPassword)) {
                    // Pulled This Parse code from Parse.com, then modified
                    // https://www.parse.com/docs/android/guide#users-signing-up
                    ////////////////////////////////////////////////////////////
                    ParseUser user = new ParseUser();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setEmail(email);


                    user.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {

                                // User signed up Scuccessfully
                                Toast.makeText(RegisterUserActivity.this, "Successfully Registered!", Toast.LENGTH_LONG).show();
                                ParseObject schedule;
                                ParseObject tempSchedule = new ParseObject("TempSchedule");
                                tempSchedule.put("UserID",""+username);
                                for(int i = 1; i <= 10; i++){
                                    tempSchedule.put("Class"+Integer.toString(i),"Empty");
                                }
                                try {
                                    tempSchedule.save();
                                } catch (ParseException e1) {
                                    e1.printStackTrace();
                                }
                                for(int createClassListCounter = 0;createClassListCounter<5;createClassListCounter++){
                                    schedule = new ParseObject("Schedule");
                                    schedule.put("ScheduleUserNum",""+username+createClassListCounter);
                                    try {
                                        schedule.save();
                                    } catch (ParseException e1) {
                                        e1.printStackTrace();
                                    }
                                    finish();
                                }
                            } else {
                                // Sign up didn't succeed. Look at the ParseException
                                Toast.makeText(RegisterUserActivity.this, "Registration Failed!", Toast.LENGTH_LONG).show();
                                // to figure out what went wrong
                            }
                        }
                    });
                } else {
                    Toast.makeText(RegisterUserActivity.this, "Password fields dont match..", Toast.LENGTH_LONG).show();
                }
            }
        });
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
    //called when the user clicks Back
    public void gotoLogin(View view) {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }
}
