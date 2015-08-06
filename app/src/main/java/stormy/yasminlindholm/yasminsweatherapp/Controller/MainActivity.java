package stormy.yasminlindholm.yasminsweatherapp.Controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import stormy.yasminlindholm.yasminsweatherapp.Model.CurrentWeather;
import stormy.yasminlindholm.yasminsweatherapp.Model.DailyWeather;
import stormy.yasminlindholm.yasminsweatherapp.Model.Forecast;
import stormy.yasminlindholm.yasminsweatherapp.Model.HourlyWeather;
import stormy.yasminlindholm.yasminsweatherapp.R;

public class MainActivity extends ActionBarActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private AlertDialogFragment_error dialogError = new AlertDialogFragment_error();
    private AlertDialogFragment_noInternet dialogNoInteret = new AlertDialogFragment_noInternet();
    private Forecast mForecast;
    private TextView mTemperatureLabel;
    private TextView mTimeLabel;
    private TextView mTimeZone;
    private ImageView mWeatherIcon;
    private TextView mHumidityValueLabel;
    private TextView mcloudCover;
    private TextView mSummaryLabel;
    private ImageView mRefresh;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWeatherIcon = (ImageView) findViewById(R.id.weatherIconImage);
        mTimeLabel = (TextView) findViewById(R.id.timeLabel);
        mTimeZone = (TextView) findViewById(R.id.timeZoneLabel);
        mTemperatureLabel = (TextView) findViewById(R.id.temperatureLabel);
        mHumidityValueLabel = (TextView) findViewById(R.id.humidityValueLabel);
        mcloudCover = (TextView) findViewById(R.id.cloudCoverValueLabel);
        mSummaryLabel = (TextView) findViewById(R.id.summaryLabel);
        mRefresh = (ImageView) findViewById(R.id.refreshImage);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mProgressBar.setVisibility(View.INVISIBLE);

        final double latitude = 18.01480;
        final double longitude = 59.33259;

        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getForecast(latitude, longitude);
            }
        });

        getForecast(latitude, longitude);
        Log.d(TAG, "Main UI code is running");
    }

    private void getForecast(double latitude, double longitude) {
        String APIKey = "d14269a6407e6000a1eb9b9240a57826";
        String forecastURL = "https://api.forecast.io/forecast/"
                + APIKey + "/" + longitude + "," + latitude ;

        if (isNetworkAvaliable()) {
            toggleRefresh();

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(forecastURL)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {

                @Override
                public void onFailure(Request request, IOException e) {
                    Log.e(TAG, "Call failed");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toggleRefresh();
                        }
                    });
                    alertUserAboutError();
                }

                @Override
                public void onResponse(Response response) throws IOException {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toggleRefresh();
                        }
                    });


                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            mForecast = parseForecastDetails(jsonData);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updateDisplay();
                                }
                            });
                        } else {
                            alertUserAboutError();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    } catch (Exception e) {
                        Log.e(TAG, "JSON Exception caught", e);
                    }
                }
            });
        }
        else {
            alertUserAboutNoConnection();
        }
    }

    private void toggleRefresh() {
        if (mProgressBar.getVisibility() == View.INVISIBLE) {
            mProgressBar.setVisibility(View.VISIBLE);
            mRefresh.setVisibility(View.INVISIBLE);
        }
        else {
            mProgressBar.setVisibility(View.INVISIBLE);
            mRefresh.setVisibility(View.VISIBLE);
        }
    }

    private void updateDisplay() {
        mTemperatureLabel.setText(mForecast.getCurrentWeather().getTemperature() + "");
        mTimeLabel.setText("@" + mForecast.getCurrentWeather().getFormattedTime());
        mcloudCover.setText(mForecast.getCurrentWeather().getCloudCover() + "%");
        mHumidityValueLabel.setText(mForecast.getCurrentWeather().getHumidity() + "%");
        mSummaryLabel.setText(mForecast.getCurrentWeather().getSummary() + " with a chance of meatballs");
        mWeatherIcon.setImageResource(mForecast.getCurrentWeather().getIconId());
    }

    private Forecast parseForecastDetails(String jsonData) throws JSONException {
        Forecast forecast = new Forecast();
        forecast.setCurrentWeather(getCurrentDetails(jsonData));
        forecast.setHourlyWeather(getHourlyDetails(jsonData));
        //forecast.getDailyWeather(getDailyDetails(jsonData));
        return forecast;
    }

    private DailyWeather[] getDailyDetails(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        String timeZone = forecast.getString("timezone");
        JSONObject daily = forecast.getJSONObject("daily");
        JSONArray data = daily.getJSONArray("data");

        DailyWeather[] dailyWeathers = new DailyWeather[data.length()];

        for (int i = 0; i < data.length();i ++) {
            JSONObject jsonDaily = data.getJSONObject(i);
            DailyWeather dailyWeather = new DailyWeather();

            dailyWeather.setSummary(jsonDaily.getString("summary"));
            dailyWeather.setTempMin(jsonDaily.getLong("temperature"));
            dailyWeather.setTime(jsonDaily.getLong("time"));
            dailyWeather.setIcon(jsonDaily.getString("icon"));
            dailyWeather.setTimeZone(timeZone);

            dailyWeathers[i] = dailyWeather;
        }
            return dailyWeathers;

        }

    private HourlyWeather[] getHourlyDetails(String jsonData) throws JSONException{
        JSONObject forecast = new JSONObject(jsonData);
        String timezone = forecast.getString("timezone");
        JSONObject hourly = forecast.getJSONObject("hourly");
        JSONArray hourlyData = hourly.getJSONArray("data");

        HourlyWeather[] hourlyWeathers = new HourlyWeather[hourlyData.length()];

        for (int i = 0; i < hourlyData.length(); i++) {
            JSONObject jsonHour = hourlyData.getJSONObject(i);
            HourlyWeather hourlyWeather = new HourlyWeather();

            hourlyWeather.setSummary(jsonHour.getString("summary"));
            hourlyWeather.setTemp(jsonHour.getDouble("temperature"));
            hourlyWeather.setIcon(jsonHour.getString("icon"));
            hourlyWeather.setTime(jsonHour.getLong("time"));
            hourlyWeather.setTimeZone(timezone);

            hourlyWeathers[i] = hourlyWeather;
        }
        return hourlyWeathers;

    }

    private CurrentWeather getCurrentDetails(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        String timezone = forecast.getString("timezone");
        CurrentWeather currentWeather = new CurrentWeather();

        JSONObject currently = forecast.getJSONObject("currently");

        currentWeather.setTime(currently.getLong("time"));
        currentWeather.setSummary(currently.getString("summary"));
        currentWeather.setIcon(currently.getString("icon"));
        currentWeather.setTemperature(currently.getDouble("temperature"));
        currentWeather.setCloudCover(currently.getDouble("cloudCover"));
        currentWeather.setHumidity(currently.getDouble("humidity"));
        currentWeather.setTimeZone(timezone);

        Log.d(TAG, currentWeather.getFormattedTime());

        return currentWeather;
    }

    private boolean isNetworkAvaliable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvaliable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvaliable = true;
        }
        return isAvaliable;
    }

    private void alertUserAboutError() {
        dialogError.show(getFragmentManager(), "error_error");
    }

    private void alertUserAboutNoConnection() {
        dialogNoInteret.show(getFragmentManager(), "error_noInternet");
    }
}
