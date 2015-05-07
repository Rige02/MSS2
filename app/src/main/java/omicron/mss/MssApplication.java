package omicron.mss;

import com.parse.Parse;
import android.app.Application;

public class MssApplication extends android.app.Application {

    @Override
    public void onCreate() {
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "FHwTc8y140hzykJ9bRRf5rcQAx9fIWENsF5Og9Xh", "98T1AS8768nb2UAMiaaGL1SuecHaYooPZo4w8IH5");
    }

}