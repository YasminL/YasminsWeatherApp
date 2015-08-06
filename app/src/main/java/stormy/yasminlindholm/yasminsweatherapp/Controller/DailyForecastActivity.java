package stormy.yasminlindholm.yasminsweatherapp.Controller;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import stormy.yasminlindholm.yasminsweatherapp.R;

public class DailyForecastActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);

        String[] daysOfTheWeek = {
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
                "Sunday"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1, daysOfTheWeek);

        setListAdapter(adapter);
    }

}
