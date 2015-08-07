package stormy.yasminlindholm.yasminsweatherapp.Controller;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import stormy.yasminlindholm.yasminsweatherapp.Adapters.DayAdapter;
import stormy.yasminlindholm.yasminsweatherapp.Model.DailyWeather;
import stormy.yasminlindholm.yasminsweatherapp.R;

public class DailyForecastActivity extends ListActivity {

    private DailyWeather[] mDailyWeather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);

        DayAdapter adapter = new DayAdapter(this, mDailyWeather);
        }
}


