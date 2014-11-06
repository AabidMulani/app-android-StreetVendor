package com.dap.hawkers;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by AABID on 02-09-2014.
 */
public class BaseActivity extends ActionBarActivity {

    protected Activity activity;
    protected BaseApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        application = ((BaseApplication) getApplication());
    }

    public Activity getBaseActivity() {
        return activity;
    }

    public BaseApplication getBaseApplication() {
        return application;
    }

}
