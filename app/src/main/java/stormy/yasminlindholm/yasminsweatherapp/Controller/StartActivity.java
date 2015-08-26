package stormy.yasminlindholm.yasminsweatherapp.Controller;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import stormy.yasminlindholm.yasminsweatherapp.R;

public class StartActivity extends Activity {

    public final static String TAG = StartActivity.class.getSimpleName();

    AlertDialogFragment_emptyField alertDialog = new AlertDialogFragment_emptyField();

    private static final String PREF_NAME = "Location";
    private static final String PREF_LOCATION = "LocationName";
    private static final String PREF_LATITUDE = "LocationLatitude";
    private static final String PREF_LONGITUDE = "LocationLongitude";

    private TextView mTip;
    private EditText mLongitude;
    private EditText mLatitude;
    private EditText mLocation;
    private Button mButton;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mLongitude = (EditText) findViewById(R.id.writeLongitude);
        mLatitude = (EditText) findViewById(R.id.writeLatitude);
        mLocation = (EditText) findViewById(R.id.writeLocationInStart);
        mButton = (Button) findViewById(R.id.continueToMainPage);
        mTip = (TextView) findViewById(R.id.hrefLatLong);
        mTip.setMovementMethod(LinkMovementMethod.getInstance());

        SharedPreferences myPrefs = this.getSharedPreferences(PREF_NAME, 0);

        if (checkIfSharedPrefs(myPrefs)) {
            String location = myPrefs.getString(PREF_LOCATION, null);
            String latitude = myPrefs.getString(PREF_LATITUDE, null);
            String longitude = myPrefs.getString(PREF_LONGITUDE, null);
            insertSharedPrefIntoLayout(location, latitude, longitude);
            startMainActivity();
        }


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String longitude = mLongitude.getText().toString();
                final String latitude = mLatitude.getText().toString();
                final String location = mLocation.getText().toString();

                boolean isLongitudeInputValid = seeIfStringIsValid(longitude);
                boolean isLatitudeInputValid = seeIfStringIsValid(latitude);
                boolean isLocationInputValid = seeIfStringIsValid(location);
                Log.i(TAG, "We are logging in onClick() and the longitude is: " + isLongitudeInputValid);

                if (isLongitudeInputValid && isLatitudeInputValid && isLocationInputValid) {
                    saveCollection(latitude, longitude, location);
                    startMainActivity();
                } else {
                    alertUserAboutEmptyFields();
                }

            }
        });
        
    }

    private void insertSharedPrefIntoLayout(String location, String latitude, String longitude) {
        mLocation.setText(location);
        mLatitude.setText(latitude);
        mLongitude.setText(longitude);
    }

    private boolean checkIfSharedPrefs(SharedPreferences myPrefs) {
        String location = myPrefs.getString(PREF_LOCATION, null);
        String latitude = myPrefs.getString(PREF_LATITUDE, null);
        String longitude = myPrefs.getString(PREF_LONGITUDE, null);
        Log.i(TAG, "We are logging in checkIfSharedPrefs() and the location is: " + location);
        Log.i(TAG, "We are logging in checkIfSharedPrefs() and the latitude is: " + latitude);
        Log.i(TAG, "We are logging in checkIfSharedPrefs() and the longitude is: " + longitude);

        if (location != null && latitude !=null && longitude !=null) {
            return true;
        }
        else {
            return false;
        }
    }

    private void saveCollection(String latitude, String longitude, String location) {
        SharedPreferences settings = this.getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.putString(PREF_LOCATION, location);
        prefEditor.putString(PREF_LATITUDE, latitude);
        prefEditor.putString(PREF_LONGITUDE, longitude);
        prefEditor.clear();
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

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
