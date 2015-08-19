package stormy.yasminlindholm.yasminsweatherapp.Controller;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import stormy.yasminlindholm.yasminsweatherapp.R;

public class StartActivity extends Activity {

    public final static String TAG = StartActivity.class.getSimpleName();

    AlertDialogFragment_emptyField alertDialog = new AlertDialogFragment_emptyField();

    private static final String PREF_NAME = "Location";
    private static final String PREF_LOCATION = "LocationName";
    private static final String PREF_LATITUDE = "LocationLatitude";
    private static final String PREF_LONGITUDE = "LocationLongitude";

    private EditText mLongitude;
    private EditText mLatitude;
    private EditText mLocation;
    private Button mButton;
    public Context context;

    String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mLongitude = (EditText) findViewById(R.id.writeLongitude);
        mLatitude = (EditText) findViewById(R.id.writeLatitude);
        mLocation = (EditText) findViewById(R.id.writeLocationInStart);
        mButton = (Button) findViewById(R.id.continueToMainPage);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String longitude = mLongitude.getText().toString();
                final String latitude = mLatitude.getText().toString();
                final String location = mLocation.getText().toString();

                boolean isLongitudeInputValid = seeIfStringIsValid(longitude);
                boolean isLatitudeInputValid = seeIfStringIsValid(latitude);
                boolean isLocationInputValid = seeIfStringIsValid(location);

                if (isLongitudeInputValid && isLatitudeInputValid && isLocationInputValid) {
                    commitSharedPreferences(latitude, longitude, location);
                    double latitudeDouble = Double.parseDouble(latitude);
                    double longitudeDouble = Double.parseDouble(longitude);
                    startMainActivity(longitudeDouble, latitudeDouble, location);
                }
                else {
                    alertUserAboutEmptyFields();
                }

            }
        });
    }

    /* private String seeIfSharedPreferences() {
        SharedPreferences settings = getSharedPreferences(PREF_NAME, 0);
        String storedLocation = settings.getString(PREF_LOCATION, null);
        //String storedLatitude = settings.getString(PREF_LATITUDE, null);
        //String storedLongitude = settings.getString(PREF_LONGITUDE, null);

        if (storedLocation != null) {
            return storedLocation;
        }
        else {
            return null;
        }
        //Log.i(TAG, "We are logging in startSharedPreferences() and the storedLocation is: " + storedLatitude);
        //Log.i(TAG, "We are logging in startSharedPreferences() and the storedLocation is: " + storedLongitude);
    } */


    private void commitSharedPreferences(String latitude, String longitude, String location) {
        SharedPreferences settings = getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.putString(PREF_LOCATION, location);
        prefEditor.putString(PREF_LATITUDE, latitude);
        prefEditor.putString(PREF_LONGITUDE, longitude);
        prefEditor.commit();
    }

    private void alertUserAboutEmptyFields() {
        alertDialog.show(getFragmentManager(), "error_emptyFields");
    }

    private boolean seeIfStringIsValid(String str) {
        if (str == null || str.trim().equals("")) {
            return false;
        }
        else {
            return true;
        }
    }

    private void startMainActivity(double longitudeDouble, double latitudeDouble, String location) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("longitudeDouble", longitudeDouble);
        intent.putExtra("latitudeDouble", latitudeDouble);
        intent.putExtra("locationString", location);
        Log.i(TAG, "We are logging in startMainActivity() and the longitude is: " + longitudeDouble);
        Log.i(TAG, "We are logging in startMainActivity() and the latitude is: " + latitudeDouble);
        Log.i(TAG, "We are logging in startMainActivity() and the location is: " + location);
        startActivity(intent);
    }
}
