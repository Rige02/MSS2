package omicron.mss;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class ScheduleScreenActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {
    int file;
    ListView listView;
    ArrayList<String> arrayList;
    DisplayAdapter displayAdapter;
    String str;
    ParseObject newObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_screen);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        arrayList = new ArrayList<>();



        for(int i = 1; i <=5; i++){  //Gets the information from the ParseObject and store it into the string that gets stored into the arraylist
            str = "Save State "+ Integer.toString(i);//this needs to be updated to show all information
            arrayList.add(str);
        }


        displayAdapter = new DisplayAdapter(ScheduleScreenActivity.this, android.R.layout.simple_list_item_activated_1, arrayList);
        listView.setAdapter(displayAdapter);
    }


    public void saveSchedule(View view) throws Exception {
        ParseObject newObject;
        String className;
        List<ParseObject> classesTemp;
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Schedule");
        query.whereEqualTo("ScheduleUserNum",ParseUser.getCurrentUser().getUsername()+Integer.toString(0));
        ParseObject tempClasses = query.find().get(0);
        List<ParseObject> completeList =null;
        for(int i =1;i<=10;i++){

            className = tempClasses.getString("Class"+Integer.toString(i));//The class name
            query = ParseQuery.getQuery("ClassDB");//reset to classdb
            query.whereEqualTo("name",className);//query for the class name
            classesTemp =query.find();
            for(int x=0;x<classesTemp.size();x++){
                newObject.add("name",classesTemp.get(x).get("name"));
                newObject.add("timeStart",classesTemp.get(x).get("timeStart"));
                newObject.add("timeEnd",classesTemp.get(x).get("timeEnd"));
                newObject.add("days",classesTemp.get(x).get("days"));
                completeList.add(newObject);
            }
        }
        ScheduleCreator creator = new ScheduleCreator(completeList);
        List<ParseObject> Schedule =creator.run();
        names = null;
        if(Schedule==null){
            Toast.makeText(ScheduleScreenActivity.this,"Schedule could not be created",Toast.LENGTH_LONG).show();
            return;
        }
        else{
            query = ParseQuery.getQuery("Schedule");
            query.whereEqualTo("ScheduleUserNum",ParseUser.getCurrentUser().getUsername()+Integer.toString(1));
            names =query.find().get(0);
            for(int i=0;i<Schedule.size();i++){//save state hard coded to spot one
                names.add("Class"+Integer.toString(i+1),Schedule.get(i).get("name"));//Dunno about this...depends on how sechdule is mormated
            }
        }
        names.save();
        Toast.makeText(ScheduleScreenActivity.this,"Schedule save success",Toast.LENGTH_LONG).show();
    }
    protected void onResume(){
        super.onResume();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("Position: " + position + " id: " + id);   //Debugging stuff just in case
        System.out.println("☆*:.｡.o(≧▽≦)o.｡.:*☆");
        file = position;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_schedule_screen, menu);
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
