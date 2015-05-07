package omicron.mss;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import java.util.List;

/**
 * Created by HeyImRige on 5/7/2015.
 */
public class ParseScheduleControler {


    public void deleteParseSchedule(int scheduleNum){//Should remove a schedule. Might need to be changed. Needs to be tested
        ParseObject schedule = new ParseObject("Schedule");
        schedule.put("ScheduleUserNum", ParseUser.getCurrentUser().getUsername()+scheduleNum);
        try {
            schedule.save();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void createParseSchedule(int scheduleNum,List<ParseObject> parseList){
        int i;
        ParseObject schedule = new ParseObject("Schedule");
        schedule.put("ScheduleUserNum",ParseUser.getCurrentUser().getUsername()+scheduleNum);
        for(i=1;(i<parseList.size())&&(i<=10);i++){
            schedule.put("Class"+1,parseList.get(i-1));
        }
        try {
            schedule.save();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public List<ParseObject> retrieveSchedule(int scheduleNum) throws ParseException {
        int i;
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Schedule");
        List<ParseObject> schedule;
        List<ParseObject> scheduleList = null;
        query.whereEqualTo("ScheduleUserNum",ParseUser.getCurrentUser().getUsername()+scheduleNum);
        schedule = query.find();
        for(i=1;i<=10;i++){
            scheduleList.add(schedule.get(0).getParseObject("Class"+i));
        }
        return scheduleList;
    }

    public ParseObject retrieveTempSchedule() throws ParseException {
        int i;
        ParseQuery<ParseObject> query = ParseQuery.getQuery("TempSchedule");
        ParseObject schedule;
        List<ParseObject> scheduleList = null;
        query.whereEqualTo("UserID",ParseUser.getCurrentUser().getUsername());
        return query.getFirst();
    }
}
