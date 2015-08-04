package stormy.yasminlindholm.yasminsweatherapp.Controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import stormy.yasminlindholm.yasminsweatherapp.Model.CurrentWeather;
import stormy.yasminlindholm.yasminsweatherapp.R;

public class MainActivity extends ActionBarActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private AlertDialogFragment_error dialogError = new AlertDialogFragment_error();
    private AlertDialogFragment_noInternet dialogNoInteret = new AlertDialogFragment_noInternet();
    private CurrentWeather mCurrentWeather;
    private TextView mTemperatureLabel;
    private TextView mTimeLabel;
    private TextView mTimeZone;
    private ImageView mWeatherIcon;
    private TextView mHumidityLabel;
    private TextView mRainSnow;
    private TextView mSummaryLabel;
    private ImageView mRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWeatherIcon = (ImageView) findViewById(R.id.weatherIconImage);
        mTimeLabel = (TextView) findViewById(R.id.timeLabel);
        mTimeZone = (TextView) findViewById(R.id.timeZoneLabel);
        mTemperatureLabel = (TextView) findViewById(R.id.temperatureLabel);
        mHumidityLabel = (TextView) findViewById(R.id.humidityValueLabel);
        mRainSnow = (TextView) findViewById(R.id.precipValueLabel);
        mSummaryLabel = (TextView) findViewById(R.id.summaryLabel);
        mRefresh = (ImageView) findViewById(R.id.refreshImage);

        String APIKey = "d14269a6407e6000a1eb9b9240a57826";
        double latitude = 18.01480;
        double longitude = 59.33259;
        String forecastURL = "https://api.forecast.io/forecast/"
                + APIKey + "/" + longitude + "," + latitude ;

        if (isNetworkAvaliable()) {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(forecastURL)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {

                @Override
                public void onFailure(Request request, IOException e) {
                    Log.e(TAG, "Call failed");
                }

                @Override
                public void onResponse(Response response) throws IOException {

                    try {
                        String JsonData = response.body().string();
                        Log.v(TAG, JsonData);
                        if (response.isSuccessful()) {
                            mCurrentWeather = getCurrentDetails(JsonData);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updateDisplay();
                                }
                            });
                        }
                        else {
                            alertUserAboutError();
                        }
                    }
                    catch (IOException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    }
                    catch (Exception e) {
                        Log.e(TAG, "JSON Exception caught", e);
                    }
                }
            });
        }
        else {
            alertUserAboutNoConnection();
        }
        Log.d(TAG, "Main UI code is running");
    }

    private void updateDisplay() {
        mTemperatureLabel.setText(mCurrentWeather.getTemperature() + "");
        mTimeLabel.setText("@" + mCurrentWeather.getFormattedTime());
        mRainSnow.setText(mCurrentWeather.getPrecipitation() + "%");
        mHumidityLabel.setText(mCurrentWeather.getHumidity() + "%");
        mSummaryLabel.setText(mCurrentWeather.getSummary() + " with a chance of meatballs");
        mWeatherIcon.setImageResource(mCurrentWeather.getIconId());
    }


    private CurrentWeather getCurrentDetails(String JsonData) throws JSONException {
        JSONObject forecast = new JSONObject(JsonData);
        String timezone = forecast.getString("timezone");
        Log.i(TAG, "From JSON: " + timezone);

        JSONObject currently = forecast.getJSONObject("currently");
        long time = currently.getLong("time");
        String summary = currently.getString("summary");
        String icon = currently.getString("icon");
        double temp = currently.getDouble("temperature");
        double humidity = currently.getDouble("humidity");
        int precipitaiont = currently.getInt("precipProbability");

        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setTime(time);
        currentWeather.setSummary(summary);
        currentWeather.setIcon(icon);
        currentWeather.setTemperature(temp);
        currentWeather.setPrecipitation(precipitaiont);
        currentWeather.setHumidity(humidity);
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
