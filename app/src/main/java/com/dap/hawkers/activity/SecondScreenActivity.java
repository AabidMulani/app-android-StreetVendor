package com.dap.hawkers.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.dap.hawkers.BaseActivity;
import com.dap.hawkers.R;
import com.dap.hawkers.utils.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by AABID on 07-11-2014.
 */
public class SecondScreenActivity extends BaseActivity {

    @InjectView(R.id.editText_name)
    EditText nameEditText;
    @InjectView(R.id.editText_father)
    EditText fatherNameEditText;
    @InjectView(R.id.editText_permanent_addr)
    EditText permanentAddrEditText;
    @InjectView(R.id.editText_present_addr)
    EditText presentAddrEditText;
    @InjectView(R.id.editText_mobile)
    EditText phoneEditText;
    @InjectView(R.id.editText_email)
    EditText emailEditText;
    @InjectView(R.id.editText_age)
    EditText ageEditText;
    @InjectView(R.id.editText_enter_id_proof)
    EditText identityProofDetailsEditText;
    @InjectView(R.id.editText_enter_qualification)
    EditText qualificationDetailsEditText;
    @InjectView(R.id.editText_religion)
    EditText religionEditText;
    @InjectView(R.id.editText_business)
    EditText businessEditText;
    @InjectView(R.id.editText_duration)
    EditText durationEditText;
    @InjectView(R.id.editText_avg_daily_income)
    EditText avgDailyIncomeEditText;


    @InjectView(R.id.spinner_business_type)
    Spinner businessTypeSpinner;
    @InjectView(R.id.spinner_maritual_status)
    Spinner maritalStatusSpinner;
    @InjectView(R.id.spinner_caste)
    Spinner casteSpinner;
    @InjectView(R.id.spinner_qualification)
    Spinner qualificationSpinner;
    @InjectView(R.id.spinner_id_proof)
    Spinner identityProofSpinner;
    @InjectView(R.id.spinner_gender)
    Spinner genderSpinner;

    @InjectView(R.id.imageView_profile)
    ImageView profilePicImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.inject(this);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color
                .theme_main_color)));
        Utils.hideKeyboard(activity);

    }


    @OnClick(R.id.imageView_profile)
    public void onProfilePicClick() {
        Utils.showToast(activity, "Image Chooser Dialog");
    }

    @OnClick(R.id.save_button)
    public void onSaveClick() {
        Utils.showToast(activity, "SAVE CALL PENDING");
    }


}
