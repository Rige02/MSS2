package omicron.mss;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class CreateScheduleActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_schedule);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_schedule, menu);
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

    //called when user clicks Create
    public void create(View view) {
        //Don't forget to add ScheduleCreator call
        //Intent createIntent = new Intent(this, SelectSchedule.class);
        //startActivity(createIntent);
    }

    //called when user clicks Add Class
    public void addClass(View view) {
        Intent addIntent = new Intent(this, AddClassActivity.class);
        startActivity(addIntent);
    }

    //called when user clicks Remove Selected Class
    public void removeClass(View view) {
        //Don't forget to add RemoveClass algorithm
        //no intent needed
    }

    //called when user clicks Add Blocked Time
    public void blockTime(View view) {
        //Intent blockIntent = new Intent(this, BlockTime.class);
        //somehow branch to block time
    }

    public void gotoMain(View view) {
        finish();
    }
}
