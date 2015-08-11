package stormy.yasminlindholm.yasminsweatherapp.Controller;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Arrays;

import stormy.yasminlindholm.yasminsweatherapp.Adapters.DayAdapter;
import stormy.yasminlindholm.yasminsweatherapp.Adapters.HourAdapter;
import stormy.yasminlindholm.yasminsweatherapp.Model.HourlyWeather;
import stormy.yasminlindholm.yasminsweatherapp.R;

public class HourlyForecastActivity extends ActionBarActivity {

    public static final String TAG = HourlyForecastActivity.class.getSimpleName();

    private HourlyWeather[] mHourlyWeather;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_forecast);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.HOURLY_FORECAST);
        mHourlyWeather = Arrays.copyOf(parcelables, parcelables.length, HourlyWeather[].class);

        HourAdapter adapter = new HourAdapter(this, mHourlyWeather);
        mRecyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);
    }

    }




