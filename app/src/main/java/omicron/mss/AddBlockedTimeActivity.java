package omicron.mss;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;


public class AddBlockedTimeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_blocked_time);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_blocked_time, menu);
        return true;
    }
    //called when user clicks Back
    public void backToCreate(View view) {
        finish();
    }

    //called when user clicks Done
    public void addTime(View view) {
        EditText startTime = (EditText) findViewById(R.id.usernameText);
        String start = startTime.getText().toString();
        EditText endTime = (EditText) findViewById(R.id.usernameText);
        String end = endTime.getText().toString();
        CheckBox monday = (CheckBox) findViewById(R.id.mondayCheckBox);
        CheckBox tuesday = (CheckBox) findViewById(R.id.tuesdayCheckBox);
        CheckBox wednesday = (CheckBox) findViewById(R.id.wednesdayCheckBox);
        CheckBox thursday = (CheckBox) findViewById(R.id.thursdayCheckedBox);
        CheckBox friday = (CheckBox) findViewById(R.id.fridayCheckBox);
        CheckBox saturday = (CheckBox) findViewById(R.id.saturdayCheckBox);
        //add status checking
        //add adding time algorithm
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
