package omicron.mss;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.*;

import java.util.List;


public class ForgotPasswordActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forgot_password, menu);
        return true;
    }

    //called when user clicks Get Password
    public void getPassword(View view) throws ParseException{
        EditText email = (EditText) findViewById(R.id.passwordRetrieval);
        String name = email.getText().toString();

        ParseUser.requestPasswordResetInBackground(name, new RequestPasswordResetCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // An email was successfully sent with reset instructions.
                } else {
                    // Something went wrong. Look at the ParseException to see what's up.
                }
            }
        });
        //Toast.makeText(ForgotPasswordActivity.this, "N/A At this time", Toast.LENGTH_LONG).show();
    }

    //called when the user clicks Back
    public void gotoLogin(View view) {
        finish();
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
