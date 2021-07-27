package com.hssn_mirza.digitify_code_challenge;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;

/**
 * Called when the application is starting, before any other application objects have been created.
 */
public class MainApp extends MultiDexApplication {

    private static MainApp mInstance;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        context = this;
        Stetho.initializeWithDefaults(this);
    }


    public static Context getContext() {
        if (context == null)
            context = mInstance.getApplicationContext();
        return context;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }

        return false;
    }


    public static synchronized MainApp getInstance() {
        return mInstance;
    }
}