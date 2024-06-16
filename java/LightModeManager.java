package wafoot.becoming.wafoot;

import androidx.appcompat.app.AppCompatDelegate;

public class LightModeManager {
    protected static void setLightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}