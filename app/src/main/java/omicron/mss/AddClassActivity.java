package omicron.mss;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class AddClassActivity extends ActionBarActivity {

    ClassSelector classSelector;
    List<ParseObject> listOfClasses;
    Intent goToSelectClass;
    Bundle forSelectClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        goToSelectClass = new Intent(this, SelectClassActivity.class);
        forSelectClass = new Bundle();
    }

    //called when user clicks Back
    public void backToCreate(View view) {
        finish();
    }

    //called when user clicks Add Class
    public void addClass(View view) {
        //add add class algorithm
        Intent createIntent = new Intent(this, CreateScheduleActivity.class);
        startActivity(createIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_class, menu);
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
    public void searchForClass(View view)throws ParseException{
        System.out.println("entered function");
        String dept;
        int classNum;
        dept = ((EditText)findViewById(R.id.departmentEditText)).getText().toString();
        classNum = Integer.parseInt(((EditText)findViewById(R.id.classNumberEditText)).getText().toString());
        ArrayList<String> listOfClasses=new ClassSelector(dept,classNum).getStrings();
        //store that value into the object of ParseObjecct
        //Put that object into the goToSelectClass bundle
        // this should put our classList into the bundle to be passed to the other intent
        forSelectClass.putStringArrayList("ListofClasses",listOfClasses);
        forSelectClass.putString("dept",dept);
        forSelectClass.putInt("classNum",classNum);
        goToSelectClass.putExtras(forSelectClass);  //This tells this intent to store the bundle into it as an extra
        startActivity(goToSelectClass);
        finish();
        //Start the activity for SelectClass whenever you're done gathering all the information
    }
}
