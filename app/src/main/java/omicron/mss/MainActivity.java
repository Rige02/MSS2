package omicron.mss;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        switch (item.getItemId()) {
            case R.id.action_logout:
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser();
                Intent LoginIntent = new Intent(this, LoginActivity.class);
                startActivity(LoginIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //called when user clicks Create Schedule
    public void createSchedule(View view) {
        Intent createIntent = new Intent(this, CreateScheduleActivity.class);
        startActivity(createIntent);
    }

    //called when user clicks View Schedule
    public void viewSchedule(View view) {
        Intent viewIntent = new Intent(this, ViewScheduleActivity.class);
        startActivity(viewIntent);
    }

    //called when user clicks Edit User
    public void editUser(View view) {
        //Intent editIntent = new Intent(this, EditUserActivity.class);
        //startActivity(editIntent);
    }
}
