package com.dap.hawkers;

import com.crashlytics.android.Crashlytics;
import com.dap.hawkers.utils.CrashlyticsTree;
import com.orm.SugarApp;

import timber.log.Timber;


/**
 * Created by AABID on 02-09-2014.
 */
public class BaseApplication extends SugarApp {

    @Override
    public void onCreate() {
        super.onCreate();
//        if (BuildConfig.DEBUG) {
//            Timber.plant(new Timber.DebugTree());
//        } else {
        Crashlytics.start(this);
        Timber.plant(new CrashlyticsTree());
//        }

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
