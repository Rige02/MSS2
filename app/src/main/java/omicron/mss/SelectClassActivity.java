package omicron.mss;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SelectClassActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    ArrayList<String> arrayList;
    String str;
    Bundle receiveFromAddClass;
    ArrayList<String> listOfClasses;
    DisplayAdapter displayAdapter;
    TextView testText;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_class);

        testText = (TextView) findViewById(R.id.test);


        receiveFromAddClass = getIntent().getExtras(); //Gets the bundle from the other class and stores it in here
        listOfClasses = (ArrayList<String>) receiveFromAddClass.getStringArrayList("ListofClasses"); //and store it into the list of classes thingy
        listView = (ListView) findViewById(R.id.classListView);
        listView.setOnItemClickListener(this);
        arrayList = new ArrayList<>();

        for(int i = 0; i < listOfClasses.size(); i++){  //Gets the information from the ParseObject and store it into the string that gets stored into the arraylist
            str = listOfClasses.get(i);//this needs to be updated to show all information
            arrayList.add(str);
        }

        displayAdapter = new DisplayAdapter(SelectClassActivity.this, android.R.layout.simple_list_item_activated_1, arrayList);
        listView.setAdapter(displayAdapter);

    }


    public void clickedText(View view){
        testText.setText("HELLO WORLD");
    }

    protected void onResume(){
        super.onResume();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_class, menu);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        try {
            ParseObject clickedClass;
            System.out.println("Position: " + position + " id: " + id);   //Debugging stuff just in case
            System.out.println("☆*:.｡.o(≧▽≦)o.｡.:*☆");
            String dept = receiveFromAddClass.getString("dept");
            int classNum = receiveFromAddClass.getInt("classNum");
            clickedClass = ((List<ParseObject>) (new ClassSelector(dept, classNum)).get()).get(0);
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Schedule");
            query.whereEqualTo("ScheduleUserNum", ParseUser.getCurrentUser().getUsername()+Integer.toString(0));
            List<ParseObject> testing = query.find();
            for(int i=1;i<=10;i++){
                if(testing.get(0).get("Class"+Integer.toString(i)).equals("Empty")){
                    testing.get(0).put("Class"+Integer.toString(i),clickedClass.get("name"));
                    testing.get(0).save();
                    Intent goToCreateSchedule = new Intent(this,CreateScheduleActivity.class);
                    startActivity(goToCreateSchedule);
                    finish();
                    break;
                }
                else{
                    if(i==10) {
                        Toast.makeText(SelectClassActivity.this, "No Room for class", Toast.LENGTH_LONG).show();
                        Intent goToCreateSchedule = new Intent(this, CreateScheduleActivity.class);
                        startActivity(goToCreateSchedule);
                        finish();
                        break;
                    }
                }
            }
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

class DisplayAdapter extends ArrayAdapter<String> {

    HashMap<String, Integer> idMap = new HashMap();

    public DisplayAdapter(Context context, int textViewResourceId, List<String> objects){
        super(context, textViewResourceId, objects);
        for(int i = 0; i < objects.size(); ++i) {
            idMap.put(objects.get(i), i);
        }

    }
}