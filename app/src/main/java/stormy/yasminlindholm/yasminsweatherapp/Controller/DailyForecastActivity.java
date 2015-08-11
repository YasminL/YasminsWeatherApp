package stormy.yasminlindholm.yasminsweatherapp.Controller;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;

import stormy.yasminlindholm.yasminsweatherapp.Adapters.DayAdapter;
import stormy.yasminlindholm.yasminsweatherapp.Model.DailyWeather;
import stormy.yasminlindholm.yasminsweatherapp.R;

public class DailyForecastActivity extends ListActivity {

    public static final String TAG = DailyForecastActivity.class.getSimpleName();

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

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String dayOfTheWeek = mDailyWeather[position].getDaysOfWeek();
        String summary = mDailyWeather[position].getSummary();
        String message;

        if (position == 0) {
            dayOfTheWeek = "Today";
            message = String.format("%s the weather will be %s", dayOfTheWeek, summary);
        } else {
            message = String.format("On %s the weather will be %s", dayOfTheWeek, summary);
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}


