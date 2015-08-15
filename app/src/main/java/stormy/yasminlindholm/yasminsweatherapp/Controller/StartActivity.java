package stormy.yasminlindholm.yasminsweatherapp.Controller;

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

import stormy.yasminlindholm.yasminsweatherapp.R;

public class StartActivity extends ActionBarActivity {

    public final static String TAG = StartActivity.class.getSimpleName();

    private EditText mLongitude;
    private EditText mLatitude;
    private EditText mLocation;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mLongitude = (EditText) findViewById(R.id.writeLongitude);
        mLatitude = (EditText) findViewById(R.id.writeLatitude);
        mLocation = (EditText) findViewById(R.id.writeLocation);
        mButton = (Button) findViewById(R.id.continueToMainPage);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String longitude =  mLongitude.getText().toString();
                double longitudeDouble = Double.parseDouble(longitude);
                String latitude = mLatitude.getText().toString();
                double latitudeDouble = Double.parseDouble(latitude);
                String location = mLocation.getText().toString();
                startMainActivity(longitudeDouble, latitudeDouble, location);
            }
        });
    }

    private void startMainActivity(double longitudeDouble, double latitudeDouble, String location) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("longitudeDouble", longitudeDouble);
        intent.putExtra("latitudeDouble", latitudeDouble);
        intent.putExtra("locationString", location + "");
        startActivity(intent);
    }
}
