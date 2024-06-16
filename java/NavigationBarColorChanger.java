package wafoot.becoming.wafoot;

import android.app.Activity;
import android.graphics.Color;

public class NavigationBarColorChanger {
    protected static void changeNavigationBarColor(Activity activity) {
        activity.getWindow().setNavigationBarColor(Color.parseColor("#FFFFFF"));
    }
}