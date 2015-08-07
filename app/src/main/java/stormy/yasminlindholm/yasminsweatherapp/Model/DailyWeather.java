package stormy.yasminlindholm.yasminsweatherapp.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import stormy.yasminlindholm.yasminsweatherapp.R;

/**
 * Created by yasmin.lindholm on 2015-08-05.
 */
public class DailyWeather {
    private long mTime;
    private String mSummary;
    private double mTempMin;
    private String mIcon;
    private String mTimeZone;

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
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
        formatter.setTimeZone(TimeZone.getTimeZone(mTimeZone));

        Date dateTime = new Date(mTime * 1000);
        return formatter.format(dateTime);

    }
}
