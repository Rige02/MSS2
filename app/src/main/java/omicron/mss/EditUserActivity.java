package omicron.mss;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.parse.*;




public class EditUserActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_user, menu);
        return true;
    }

    //called when user clicks Back
    public void gotoMain(View view) {
        finish();
    }

    public void changePassword(View view) {
        //add change password algorithm
    }

    public void deleteAccount(View view) throws ParseException {
        //add delete account algorithm
        // get current user object ID
        // delete row of object ID
        ParseUser currentUser = ParseUser.getCurrentUser();
        Log.d("current user", " is" + currentUser.getUsername());
        // Authenticate user to delete id
        if(currentUser.isAuthenticated()){
            Log.d("This User", " is Authenticated");
            currentUser.delete();  // delete userID from Parse.com
            Toast.makeText(EditUserActivity.this, "Successfully Deleted", Toast.LENGTH_LONG).show();
            // Go back to login display
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);

        }
        else{
            Log.d("This User", " is Not Authenticated");
            Toast.makeText(EditUserActivity.this, "You Can't Delete", Toast.LENGTH_LONG).show();
        }

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
