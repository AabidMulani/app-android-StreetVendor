package com.dap.hawkers.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.dap.hawkers.BaseActivity;
import com.dap.hawkers.R;
import com.dap.hawkers.utils.Utils;

import butterknife.ButterKnife;

/**
 * Created by AABID on 07-11-2014.
 */
public class ListAllActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);
        ButterKnife.inject(this);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color
                .theme_main_color)));
        Utils.showToast(activity, "List All");
    }
}
