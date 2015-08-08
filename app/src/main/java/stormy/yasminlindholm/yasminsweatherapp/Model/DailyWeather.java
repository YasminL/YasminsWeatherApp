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
public class DailyWeather implements Parcelable {

    private static final String TAG = DailyWeather.class.getSimpleName();
    private long mTime;
    private String mSummary;
    private double mTempMin;
    private String mIcon;
    private String mTimeZone;

    public DailyWeather() {}

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

    public int getTempMin() {
        double temp = ((mTempMin - 32)*5)/9;
        double tempOneDec = Math.round(temp*10.0)/10.0;
        int tempInt = (int) tempOneDec;
        return tempInt;
    }

    public void setTempMin(double temp) {
        mTempMin = temp;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public String getDaysOfWeek() {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        TimeZone timeZone = TimeZone.getTimeZone(mTimeZone);
        formatter.setTimeZone(timeZone);
        Date dateTime = new Date(mTime * 1000);
        //Log.i(TAG, "dateTime is: " + dateTime);
        //dateTime is: Fri Aug 14 00:00:00 CEST 2015

        String dayOfWeek = formatter.format(dateTime);
        Log.i(TAG, "dayOfWeek is: " + dayOfWeek);

        return dayOfWeek;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(mTime);
        out.writeString(mSummary);
        out.writeDouble(mTempMin);
        out.writeString(mIcon);
        out.writeString(mTimeZone);
    }

    private DailyWeather(Parcel in) {
        mTime = in.readLong();
        mSummary = in.readString();
        mTempMin = in.readDouble();
        mIcon = in.readString();
        mTimeZone = in.readString();
    }

    public static final Creator<DailyWeather> CREATOR
            = new Creator<DailyWeather>() {

        @Override
        public DailyWeather createFromParcel(Parcel source) {
            return new DailyWeather(source);
        }

        @Override
        public DailyWeather[] newArray(int size) {
            return new DailyWeather[size];
        }
    };
}
