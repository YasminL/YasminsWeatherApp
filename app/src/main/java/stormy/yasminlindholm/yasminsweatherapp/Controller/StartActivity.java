package stormy.yasminlindholm.yasminsweatherapp.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.places.Places;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import stormy.yasminlindholm.yasminsweatherapp.R;

public class StartActivity extends Activity {
    //protected GoogleApiClient mGoogleApiClient;

    public final static String TAG = StartActivity.class.getSimpleName();
    AlertDialogFragment_emptyField alertDialog = new AlertDialogFragment_emptyField();

    private static final String PREF_NAME = "SharedPreferences_Location";
    private static final String PREF_LOCATION = "LocationName";
    private static final String PREF_ADDRESS = "LocationAddress";

    private EditText mAddress;
    private EditText mLocation;
    private Button mButton;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Places.GEO_DATA_API)
                .build(); */

        setContentView(R.layout.activity_start);
        SharedPreferences myPrefs = this.getSharedPreferences(PREF_NAME, 0);

        mAddress = (EditText) findViewById(R.id.writeAddress);
        mLocation = (EditText) findViewById(R.id.writeLocationInStart);
        mButton = (Button) findViewById(R.id.continueToMainPage);

        if (checkIfSharedPrefs(myPrefs)) {
            String location = myPrefs.getString(PREF_LOCATION, null);
            Log.i(TAG, "We are logging in checkifSharedPrefs() anf the location is: " + location);
            String address = myPrefs.getString(PREF_ADDRESS, null);
            Log.i(TAG, "We are logging in checkifSharedPrefs() anf the address is: " + address);
            insertSharedPrefIntoLayout(location, address);
            getLatitudeLongitudeFromAddressThroughGoogleAPI(address);
            startMainActivity();
        }

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String location = mLocation.getText().toString();
                final String address = mAddress.getText().toString();

                boolean isAddressInputValid = seeIfStringIsValid(address);
                boolean isLocationInputValid = seeIfStringIsValid(location);

                if (isAddressInputValid && isLocationInputValid) {
                    saveCollection(location, address);
                    startMainActivity();
                } else {
                    alertUserAboutEmptyFields();
                }
            }
        });


    }

    public void getLatitudeLongitudeFromAddressThroughGoogleAPI(String address) {
        String APIKey = getResources().getString(R.string.API_ServerKey);
        String GoogleAPIURL = "https://maps.googleapis.com/maps/api/place/autocomplete/json?input=" + address + "&radius=5000&key=" + APIKey + "&sensor=true";
        Log.i(TAG, "We are logging in getLatitudeLongitudeFromAddressThroughGoogleAPI " + GoogleAPIURL);
    }

    /* @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    } */

    private void insertSharedPrefIntoLayout(String location, String address) {
        mLocation.setText(location);
        mAddress.setText(address);
    }

    private boolean checkIfSharedPrefs(SharedPreferences myPrefs) {
        String location = myPrefs.getString(PREF_LOCATION, null);
        String address = myPrefs.getString(PREF_ADDRESS, null);

        if (location != null && address != null) {
            return true;
        } else {
            return false;
        }
    }

    private void saveCollection(String location, String address) {
        SharedPreferences settings = this.getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.putString(PREF_LOCATION, location);
        prefEditor.putString(PREF_ADDRESS, address);
        prefEditor.clear();
        prefEditor.commit();
    }

    private void alertUserAboutEmptyFields() {
        alertDialog.show(getFragmentManager(), "error_emptyFields");
    }

    private boolean seeIfStringIsValid(String str) {
        if (str == null || str.trim().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
