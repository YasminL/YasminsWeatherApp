package stormy.yasminlindholm.yasminsweatherapp.Controller;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.Arrays;

import stormy.yasminlindholm.yasminsweatherapp.Adapters.DayAdapter;
import stormy.yasminlindholm.yasminsweatherapp.Model.DailyWeather;
import stormy.yasminlindholm.yasminsweatherapp.R;

public class DailyForecastActivity extends ListActivity {

    private DailyWeather[] mDailyWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.DAILY_FORECAST);

        mDailyWeather = Arrays.copyOf(parcelables, parcelables.length, DailyWeather[].class);

        DayAdapter adapter = new DayAdapter(DailyForecastActivity.this, mDailyWeather);
        setListAdapter(adapter);
        }
}


