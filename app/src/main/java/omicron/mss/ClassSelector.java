package omicron.mss;

import com.parse.*;

import java.util.List;

/**
 * Created by Ian on 5/6/2015.
 */
public class ClassSelector {

    String dept;
    int classNum;
    String sect;

    public ClassSelector(String dept, int classNum, String sect){
        this.dept = dept;
        this.classNum = classNum;
        this.sect = sect;
    }

    public void get()throws ParseException{
        ParseQuery<ParseObject> query = ParseQuery.getQuery("ClassDB");
        List<ParseObject> classList = null, tempClassList;
        query.whereEqualTo("department", dept);
        tempClassList = query.find();
        for(int i = 0; i < tempClassList.size(); i++){
            if(tempClassList.get(i).getNumber("classNum") == classNum){
                classList.add(tempClassList.get(i));
            }
        }
    }


}
