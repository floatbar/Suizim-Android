package wafoot.becoming.wafoot;

import android.app.Application;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

public class Lifecycle extends Application implements LifecycleObserver {

    private static boolean isAppInBackground = false;

    @Override
    public void onCreate() {
        super.onCreate();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(androidx.lifecycle.Lifecycle.Event.ON_STOP)
    public void onAppBackgrounded() {
        isAppInBackground = true;
    }

    @OnLifecycleEvent(androidx.lifecycle.Lifecycle.Event.ON_START)
    public void onAppForegrounded() {
        isAppInBackground = false;
    }
}