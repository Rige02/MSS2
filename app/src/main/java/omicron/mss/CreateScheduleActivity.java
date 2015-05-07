package omicron.mss;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CreateScheduleActivity extends ActionBarActivity {
    TextView testText;
    String str;
    ArrayList<String> arrayList;
    DisplayAdapter displayAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_create_schedule);
            testText = (TextView) findViewById(R.id.test);
            ParseObject tempSchedule =new ParseScheduleControler().retrieveTempSchedule();
            arrayList = new ArrayList<>();

            for(int i = 1; i <=10; i++){  //Gets the information from the ParseObject and store it into the string that gets stored into the arraylist
                str = (String)((ParseObject)tempSchedule.get("Class"+i)).get("name");//this needs to be updated to show all information
                arrayList.add(str);
            }


            displayAdapter = new DisplayAdapter(CreateScheduleActivity.this, android.R.layout.simple_list_item_activated_1, arrayList);
            listView.setAdapter(displayAdapter);




        }catch(ParseException e){
            e.printStackTrace();
        }
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

    public void gotoSchedule(View view) {
        Intent schedIntent = new Intent(this, ScheduleScreenActivity.class);
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