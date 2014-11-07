package com.dap.hawkers.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;

import com.dap.hawkers.BaseActivity;
import com.dap.hawkers.R;
import com.dap.hawkers.database.InformationTable;
import com.dap.hawkers.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created by AABID on 07-11-2014.
 */
public class SecondScreenActivity extends BaseActivity {
    public static String EXTRA_OPTION = "option";
    public static String EXTRA_STATE = "state";
    public static String EXTRA_DISTRICT = "district";
    public static String EXTRA_CITY = "city";
    public static String EXTRA_AREA = "area";

    private static final int REQUEST_CAMERA = 996;
    private static final int SELECT_FILE = 999;

    @InjectView(R.id.scroll_view)
    ScrollView scroll_view;

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

    @InjectView(R.id.checkBox_bpl_card)
    CheckBox bplCardCheckBox;
    @InjectView(R.id.checkBox_food_security)
    CheckBox foodSecurityCheckBox;
    @InjectView(R.id.checkBox_health_card)
    CheckBox healthCardCheckBox;
    @InjectView(R.id.checkBox_indira_awas)
    CheckBox indiraAwasCheckBox;
    @InjectView(R.id.checkBox_laadali_yogna)
    CheckBox ladaliCheckBox;
    @InjectView(R.id.checkBox_manrega)
    CheckBox manregaCheckBox;
    @InjectView(R.id.checkBox_oldage_pension)
    CheckBox oldagePensionCheckBox;
    @InjectView(R.id.checkBox_rajeev_awas)
    CheckBox rajeevAwasCheckBox;
    @InjectView(R.id.checkBox_sawaswati_yojna)
    CheckBox saraswatiCheckBox;
    @InjectView(R.id.checkBox_sjsry)
    CheckBox sjsryCheckBox;
    @InjectView(R.id.checkBox_tpds)
    CheckBox tpdsCheckBox;
    @InjectView(R.id.checkBox_widow_pension)
    CheckBox widowPensionCheckBox;


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
    private String base64ImageString;

    private String optionType, state, city, district, area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.inject(this);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color
                .theme_main_color)));
        Utils.hideKeyboard(activity);
        optionType = getIntent().getStringExtra(EXTRA_OPTION);
        state = getIntent().getStringExtra(EXTRA_STATE);
        city = getIntent().getStringExtra(EXTRA_CITY);
        district = getIntent().getStringExtra(EXTRA_DISTRICT);
        area = getIntent().getStringExtra(EXTRA_AREA);
    }


    @OnClick(R.id.imageView_profile)
    public void onProfilePicClick() {
        openImageChooserDialog();
    }

    @OnClick(R.id.save_button)
    public void onSaveClick() {
        if (doValidation()) {
            InformationTable informationTable = new InformationTable(optionType, state, district, city, area);
            informationTable.setImage_string(base64ImageString);
            informationTable.setName(nameEditText.getText().toString());
            informationTable.setFather_name(fatherNameEditText.getText().toString());
            informationTable.setPermanent_address(permanentAddrEditText.getText().toString());
            informationTable.setPresent_address(presentAddrEditText.getText().toString());
            informationTable.setMobile_number(phoneEditText.getText().toString());
            informationTable.setEmail(emailEditText.getText().toString());
            informationTable.setAge(ageEditText.getText().toString());
            informationTable.setGender(genderSpinner.getSelectedItem().toString());
            informationTable.setIdentity_proof(identityProofSpinner.getSelectedItem().toString());
            informationTable.setIdentity_proof_details(identityProofDetailsEditText.getText().toString());
            informationTable.setQualification(qualificationSpinner.getSelectedItem().toString());
            informationTable.setQualification_details(qualificationDetailsEditText.getText().toString());
            informationTable.setReligion(religionEditText.getText().toString());
            informationTable.setCast(casteSpinner.getSelectedItem().toString());
            informationTable.setMarital_status(maritalStatusSpinner.getSelectedItem().toString());
            informationTable.setBpl_card(bplCardCheckBox.isChecked());
            informationTable.setHealth_card(healthCardCheckBox.isChecked());
            informationTable.setIndira_aawas(indiraAwasCheckBox.isChecked());
            informationTable.setRajeev_aawas(rajeevAwasCheckBox.isChecked());
            informationTable.setTpds(tpdsCheckBox.isChecked());
            informationTable.setSjsry(sjsryCheckBox.isChecked());
            informationTable.setFood_security(foodSecurityCheckBox.isChecked());
            informationTable.setWidow_pension(widowPensionCheckBox.isChecked());
            informationTable.setOldage_pension(oldagePensionCheckBox.isChecked());
            informationTable.setManrega(manregaCheckBox.isChecked());
            informationTable.setLadali_yogna(ladaliCheckBox.isChecked());
            informationTable.setSaraswati_yogna(saraswatiCheckBox.isChecked());
            informationTable.setBusiness(businessEditText.getText().toString());
            informationTable.setBusiness_type(businessTypeSpinner.getSelectedItem().toString());
            informationTable.setAvg_daily_income(avgDailyIncomeEditText.getText().toString());
            informationTable.save();
            Timber.e(informationTable.toString());
            Utils.showThisMsg(activity, "Success:", "Your entry has been saved successfully!",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            clearAllFields();
                        }
                    }, null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.second_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void clearAllFields() {
        nameEditText.setText("");
        fatherNameEditText.setText("");
        permanentAddrEditText.setText("");
        presentAddrEditText.setText("");
        phoneEditText.setText("");
        emailEditText.setText("");
        ageEditText.setText("");
        identityProofDetailsEditText.setText("");
        qualificationDetailsEditText.setText("");
        religionEditText.setText("");
        businessEditText.setText("");
        durationEditText.setText("");
        avgDailyIncomeEditText.setText("");
        bplCardCheckBox.setChecked(false);
        foodSecurityCheckBox.setChecked(false);
        healthCardCheckBox.setChecked(false);
        indiraAwasCheckBox.setChecked(false);
        ladaliCheckBox.setChecked(false);
        manregaCheckBox.setChecked(false);
        oldagePensionCheckBox.setChecked(false);
        rajeevAwasCheckBox.setChecked(false);
        saraswatiCheckBox.setChecked(false);
        sjsryCheckBox.setChecked(false);
        tpdsCheckBox.setChecked(false);
        widowPensionCheckBox.setChecked(false);
        businessTypeSpinner.setSelection(0);
        maritalStatusSpinner.setSelection(0);
        casteSpinner.setSelection(0);
        qualificationSpinner.setSelection(0);
        identityProofSpinner.setSelection(0);
        genderSpinner.setSelection(0);
        profilePicImageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_add));
        base64ImageString = null;
        scroll_view.fullScroll(ScrollView.FOCUS_UP);
    }

    private boolean doValidation() {

        if (base64ImageString == null || base64ImageString.isEmpty()) {
            Utils.showToast(activity, "SELECT IMAGE");
            return false;
        }

        if (nameEditText.getText().toString().isEmpty()) {
            Utils.showToast(activity, "Enter Name");
            return false;
        }

        if (fatherNameEditText.getText().toString().isEmpty()) {
            Utils.showToast(activity, "Enter Father/Husbands Name");
            return false;
        }

        if (permanentAddrEditText.getText().toString().isEmpty()) {
            Utils.showToast(activity, "Enter Permanent Address");
            return false;
        }

        if (phoneEditText.getText().toString().isEmpty()) {
            Utils.showToast(activity, "Enter Phone Number");
            return false;
        }

        if (ageEditText.getText().toString().isEmpty()) {
            Utils.showToast(activity, "Enter Age/DOB");
            return false;
        }
        return true;
    }


    private void openImageChooserDialog() {
        final CharSequence[] items = {"Camera", "Gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(null);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Camera")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media
                            .EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            new ProcessImageAsync(requestCode, data).execute();
        }
    }


    class ProcessImageAsync extends AsyncTask<Void, Void, Boolean> {
        int requestCode;
        Intent data;
        private Bitmap compressBitmap;

        ProcessImageAsync(int requestCode, Intent data) {
            this.requestCode = requestCode;
            this.data = data;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Utils.showProgressBar(activity, "Processing Image", false, null);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            Bitmap bitmap;
            if (requestCode == REQUEST_CAMERA) {
                File fileObj = new File(Environment.getExternalStorageDirectory()
                        .toString());
                for (File temp : fileObj.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        fileObj = temp;
                        break;
                    }
                }
                try {
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmap = BitmapFactory.decodeFile(fileObj.getAbsolutePath(), bitmapOptions);
                    String path = android.os.Environment.getExternalStorageDirectory() + "";
                    fileObj.delete();
                    OutputStream fOut = null;
                    File file = new File(path, String.valueOf(System
                            .currentTimeMillis()) + ".png");
                    try {
                        fOut = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                        compressBitmap = getResizeBitmap(bitmap, 500);
                        fOut.flush();
                        fOut.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        return false;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            } else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();
                String tempPath = getPath(selectedImageUri, activity);
                BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
                bitmap = BitmapFactory.decodeFile(tempPath, btmapOptions);
                compressBitmap = getResizeBitmap(bitmap, 500);
            }
            if (compressBitmap != null) {
                base64ImageString = Utils.convertImageToBase64(compressBitmap);
                Timber.e(base64ImageString);
                return true;
            } else {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            Utils.hideProgressDialog();
            if (result) {
                if (compressBitmap != null) {
                    profilePicImageView.setImageBitmap(compressBitmap);
                }
            } else {
                Utils.showToast(activity, "Error while processing Image");
            }
        }
    }

    public String getPath(Uri uri, Activity activity) {
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = activity
                .managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public Bitmap getResizeBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();
        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 0) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}
