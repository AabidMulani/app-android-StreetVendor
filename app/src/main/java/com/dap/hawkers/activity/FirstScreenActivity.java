package com.dap.hawkers.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import com.dap.hawkers.BaseActivity;
import com.dap.hawkers.R;
import com.dap.hawkers.utils.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class FirstScreenActivity extends BaseActivity {

    @InjectView(R.id.spinner_option)
    Spinner optionSpinner;
    @InjectView(R.id.spinner_state)
    Spinner stateSpinner;
    @InjectView(R.id.spinner_district)
    Spinner districtSpinner;
    @InjectView(R.id.spinner_city)
    Spinner citySpinner;

    @InjectView(R.id.editText_area)
    EditText areaEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.inject(this);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color
                .theme_main_color)));
        Utils.hideKeyboard(activity);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_list) {
            startActivity(new Intent(activity, ListAllActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.submit_button)
    public void onSubmitClicked() {
        if (doValidation()) {
            Intent intent = new Intent(activity, SecondScreenActivity.class);
            intent.putExtra(SecondScreenActivity.EXTRA_AREA, areaEditText.getText().toString());
            intent.putExtra(SecondScreenActivity.EXTRA_OPTION, optionSpinner.getSelectedItem().toString());
            intent.putExtra(SecondScreenActivity.EXTRA_DISTRICT, districtSpinner.getSelectedItem().toString());
            intent.putExtra(SecondScreenActivity.EXTRA_STATE, stateSpinner.getSelectedItem().toString());
            intent.putExtra(SecondScreenActivity.EXTRA_CITY, citySpinner.getSelectedItem().toString());
            startActivity(intent);
        }
    }

    private boolean doValidation() {
        if (areaEditText.getText().toString().isEmpty()) {
            Utils.showToast(activity, "Enter AREA");
            return false;
        }
        return true;
    }

}
