package stormy.yasminlindholm.yasminsweatherapp.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import stormy.yasminlindholm.yasminsweatherapp.R;

/**
 * Created by yasmin.lindholm on 2015-08-05.
 */
public class HourlyWeather implements Parcelable {

    private static final String TAG = HourlyWeather.class.getSimpleName();
    private long mTime;
    private String mSummary;
    private double mTemp;
    private String mIcon;
    private String mTimeZone;

    public HourlyWeather() {}

    public long getTime() {
        return mTime;
    }

    public String getFormattedTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("kk:mm");
        formatter.setTimeZone(TimeZone.getTimeZone(getTimeZone()));
        Date dateTime = new Date(getTime()*1000);
        String timeString = formatter.format(dateTime);
        return timeString;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String timeZone) {
        mTimeZone = timeZone;
    }

    public String getIcon() {
        return mIcon;
    }

    public int getIconId() {
        return Forecast.getIconId(mIcon);
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public int getTemp() {
        double temp = ((mTemp - 32)*5)/9;
        int tempInt = (int) temp;
        return tempInt;
    }

    public void setTemp(double temp) {
        mTemp = temp;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public String getHoursOfDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        TimeZone timeZone = TimeZone.getTimeZone(mTimeZone);
        formatter.setTimeZone(timeZone);
        Date dateTime = new Date(mTime * 1000);
        Log.i(TAG, "dateTime is: " + dateTime);
        String hourOfDay = formatter.format(dateTime);
        return hourOfDay;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(mTime);
        out.writeString(mSummary);
        out.writeDouble(mTemp);
        out.writeString(mIcon);
        out.writeString(mTimeZone);
    }

    private HourlyWeather(Parcel in) {
        mTime = in.readLong();
        mSummary = in.readString();
        mTemp = in.readDouble();
        mIcon = in.readString();
        mTimeZone = in.readString();
    }

    public static final Creator<HourlyWeather> CREATOR = new Creator<HourlyWeather>() {

        @Override
        public HourlyWeather createFromParcel(Parcel source) {
            return new HourlyWeather(source);
        }

        @Override
        public HourlyWeather[] newArray(int size) {
            return new HourlyWeather[size];
        }
    };

}
