package eu.laramartin.booklisting;

import android.app.Application;

/**
 * Created by Miquel Beltran on 11/18/16
 * More on http://beltran.work
 */
public class App extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
    }


    public AppComponent getAppComponent() {
        return appComponent;
    }
}
