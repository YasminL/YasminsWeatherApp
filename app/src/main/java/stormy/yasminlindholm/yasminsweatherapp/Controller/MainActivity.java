package stormy.yasminlindholm.yasminsweatherapp.Controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;

import stormy.yasminlindholm.yasminsweatherapp.R;

public class MainActivity extends ActionBarActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private AlertDialogFragment_error dialogError = new AlertDialogFragment_error();
    private AlertDialogFragment_noInternet dialogNoInteret = new AlertDialogFragment_noInternet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String APIKey = "d14269a6407e6000a1eb9b9240a57826";
        double latitude = 59.33259;
        double longitude = 18.01480;
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
                        Log.v(TAG, response.body().string());
                        if (response.isSuccessful()) {
                        } else {
                            alertUserAboutError();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    }
                }
            });
        } else {
            alertUserAboutNoConnection();
        }

        Log.d(TAG, "Main UI code is running");
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
