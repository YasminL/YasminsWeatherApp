package stormy.yasminlindholm.yasminsweatherapp.Controller;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import stormy.yasminlindholm.yasminsweatherapp.R;

public class StartActivity extends Activity {

    public final static String TAG = StartActivity.class.getSimpleName();

    AlertDialogFragment_emptyField alertDialog = new AlertDialogFragment_emptyField();

    private static final String PREF_NAME = "SharedPreferences_Location";
    private static final String PREF_LOCATION = "LocationName";
    private static final String PREF_ADDRESS = "LocationLongitude";

    private EditText mAddress;
    private EditText mLocation;
    private Button mButton;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mAddress = (EditText) findViewById(R.id.writeAddress);
        mLocation = (EditText) findViewById(R.id.writeLocationInStart);
        mButton = (Button) findViewById(R.id.continueToMainPage);

        SharedPreferences myPrefs = this.getSharedPreferences(PREF_NAME, 0);

        if (checkIfSharedPrefs(myPrefs)) {
            String location = myPrefs.getString(PREF_LOCATION, null);
            String address = myPrefs.getString(PREF_ADDRESS, null);
            insertSharedPrefIntoLayout(location, address);
            startMainActivity();
        }

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String location = mLocation.getText().toString();
                final String address = mAddress.getText().toString();

                boolean isAddressInputValid = seeIfStringIsValid(address);
                boolean isLocationInputValid = seeIfStringIsValid(location);

                if (isAddressInputValid && isLocationInputValid) {
                    saveCollection(location, address);
                    startMainActivity();
                } else {
                    alertUserAboutEmptyFields();
                }
            }
        });

    }

    private void insertSharedPrefIntoLayout(String location, String address) {
        mLocation.setText(location);
        mAddress.setText(address);
    }

    private boolean checkIfSharedPrefs(SharedPreferences myPrefs) {
        String location = myPrefs.getString(PREF_LOCATION, null);
        String address = myPrefs.getString(PREF_ADDRESS, null);

        if (location != null && address !=null) {
            return true;
        }
        else {
            return false;
        }
    }

    private void saveCollection(String location, String address ) {
        SharedPreferences settings = this.getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.putString(PREF_LOCATION, location);
        prefEditor.putString(PREF_ADDRESS, address);
        prefEditor.clear();
        prefEditor.commit();
    }

    private void alertUserAboutEmptyFields() {
        alertDialog.show(getFragmentManager(), "error_emptyFields");
    }

    private boolean seeIfStringIsValid(String str) {
        if (str == null || str.trim().equals("")) {
            return false;
        }
        else {
            return true;
        }
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
