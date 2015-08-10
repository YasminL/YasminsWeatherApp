package stormy.yasminlindholm.yasminsweatherapp.Controller;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Arrays;

import stormy.yasminlindholm.yasminsweatherapp.Model.HourlyWeather;
import stormy.yasminlindholm.yasminsweatherapp.R;

public class HourlyForecastActivity extends ActionBarActivity {

    public static final String TAG = HourlyForecastActivity.class.getSimpleName();

    private HourlyWeather[] mHourlyWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_forecast);

        /* Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.HOURLY_FORECAST);
        mHourlyWeather = Arrays.copyOf(parcelables, parcelables.length, HourlyWeather[].class);
        Log.i(TAG, "Logging Hourly Parc which is: " + mHourlyWeather); */

    }



}
