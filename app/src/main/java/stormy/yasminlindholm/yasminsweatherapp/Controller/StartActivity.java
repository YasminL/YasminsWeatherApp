package stormy.yasminlindholm.yasminsweatherapp.Controller;

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

    private EditText mLongitude;
    private EditText mLatitude;
    private EditText mLocation;
    private Button mButton;
    private Context context;

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

                Boolean isStringEmpty= seeIfDouble(longitude, latitude, location);
                if (isStringEmpty) {
                    double latitudeDouble = Double.parseDouble(latitude);
                    double longitudeDouble = Double.parseDouble(longitude);
                    startMainActivity(longitudeDouble, latitudeDouble, location);
                } else {
                    Toast.makeText(context, "You have forgotten to fill in a field!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private Boolean seeIfDouble(String longitude, String latitude, String location) {
        if (longitude.trim().equals("") || latitude.trim().equals("") || location.trim().equals("")) {
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
        startActivity(intent);
    }
}
