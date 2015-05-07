package omicron.mss;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ian on 5/6/2015.
 */
public class ScheduleCreator {

    String[][] classes;
    public ScheduleCreator(String[][] classes){
        for(int i = 0; i < classes.length; i++){
            for(int j = 0; j < 2; j++){
                this.classes[i][j] = classes[i][j];
            }
        }
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

    public void run() throws Exception {
        convert();
        if(hasConflicts()){

        }
    }

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < 10; i++){
            //ClassSelector
        }
        //ScheduleCreator create = new ScheduleCreator();
        //create.run();
    }
}
