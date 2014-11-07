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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.dap.hawkers.BaseActivity;
import com.dap.hawkers.R;
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

    private static final int REQUEST_CAMERA = 996;
    private static final int SELECT_FILE = 999;
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
    private String base64ImageString;


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
        openImageChooserDialog();
    }

    @OnClick(R.id.save_button)
    public void onSaveClick() {
        Utils.showToast(activity, "SAVE CALL PENDING");
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
