package omicron.mss;

import com.parse.ParseObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Ian on 5/6/2015.
 */
public class ScheduleCreator {
//.get(i).getString("timeEnd")
//.get(i).getString("timeStart")
    String[][] classes;
    List<ParseObject> schedule;
    List<ParseObject> failedSchedule = null;
    public ScheduleCreator(List<ParseObject> classList){
        for(int i = 0; i < classList.size(); i++){
            this.classes[i][0] = classList.get(i).getString("timeStart");
            this.classes[i][1] = classList.get(i).getString("timeEnd");
        }
        schedule = classList;
    }

    public void convert() throws Exception{//converts class times to 24 hour format
        SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mma");
        for(int i = 0; i < classes.length; i++){
            for(int j = 0; j < 2; j++){
                if(classes[i][j].compareTo("TBA") != 0) {
                    Date date = parseFormat.parse(classes[i][j]);
                    classes[i][j] = displayFormat.format(date);
                }
            }
        }
    }

    public boolean hasConflicts() {//returns false if the classes have no conflicts
        for(int i = 0; i < classes.length; i++){
            for(int j = 0; j < classes.length; j++) {
                if(i != j && classes[i][0].compareTo("TBA") != 0 || classes[j][0].compareTo("TBA") != 0) {
                    if (classes[i][1].compareTo(classes[j][0]) >= 0 && classes[j][1].compareTo(classes[i][0]) >= 0) {
                        return true;
                    /*if the end time of the first class is after or the same as the start of the second and the end time of the
                    *second class is after or the same as the start of the first, there's a conflict*/
                    }
                }
            }
        }
        return false;
    }

    public List<ParseObject> run() throws Exception {
        convert();
        if(hasConflicts()){
            return failedSchedule;
        }
        return schedule;
    }

}
