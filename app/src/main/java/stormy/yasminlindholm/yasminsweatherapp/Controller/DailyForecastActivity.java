package stormy.yasminlindholm.yasminsweatherapp.Controller;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import stormy.yasminlindholm.yasminsweatherapp.Adapters.DayAdapter;
import stormy.yasminlindholm.yasminsweatherapp.Model.DailyWeather;
import stormy.yasminlindholm.yasminsweatherapp.R;

public class DailyForecastActivity extends ListActivity {

    public static final String TAG = DailyForecastActivity.class.getSimpleName();

    private static final String PREF_NAME = "SharedPreferences_Location";
    private static final String PREF_LOCATION = "LocationName";

    private DailyWeather[] mDailyWeather;
    private TextView mlocationLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);

        mlocationLabel = (TextView) findViewById(R.id.locationLabelWeek);
        setLocationText();

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.DAILY_FORECAST);

        mDailyWeather = Arrays.copyOf(parcelables, parcelables.length, DailyWeather[].class);

        DayAdapter adapter = new DayAdapter(DailyForecastActivity.this, mDailyWeather);
        setListAdapter(adapter);
        }

    private void setLocationText() {
        SharedPreferences settings = getSharedPreferences(PREF_NAME, 0);
        String location = settings.getString(PREF_LOCATION, "empty");
        mlocationLabel.setText(location);
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


