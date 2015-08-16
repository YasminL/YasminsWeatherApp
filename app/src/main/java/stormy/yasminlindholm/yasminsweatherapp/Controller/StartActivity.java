package stormy.yasminlindholm.yasminsweatherapp.Controller;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
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

public class StartActivity extends ActionBarActivity {

    public final static String TAG = StartActivity.class.getSimpleName();

    AlertDialogFragment_emptyField alertDialog = new AlertDialogFragment_emptyField();

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

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String longitude =  mLongitude.getText().toString();
                String latitude = mLatitude.getText().toString();
                String location = mLocation.getText().toString();

                boolean isLongitudeInputValid = seeIfStringIsValid(longitude);
                boolean isLatitudeInputValid = seeIfStringIsValid(latitude);
                boolean isLocationInputValid = seeIfStringIsValid(location);


                if (isLongitudeInputValid && isLatitudeInputValid && isLocationInputValid) {
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
