package stormy.yasminlindholm.yasminsweatherapp.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import stormy.yasminlindholm.yasminsweatherapp.R;

/**
 * Created by yasmin.lindholm on 2015-08-03.
 */
public class CurrentWeather {
    private String mIcon;
    private long mTime;
    private String mTimeZone;
    private double mTemperature;
    private double mPrecip;
    private double mCloudCover;
    private String mSummary;

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public int getIconId() {
        int iconId = R.mipmap.clear_day;

        if (mIcon.equals("clear-day")) {
            iconId = R.mipmap.clear_day;
        }
        else if (mIcon.equals("clear-night")) {
            iconId = R.mipmap.clear_night;
        }
        else if (mIcon.equals("rain")) {
            iconId = R.mipmap.rain;
        }
        else if (mIcon.equals("snow")) {
            iconId = R.mipmap.snow;
        }
        else if (mIcon.equals("sleet")) {
            iconId = R.mipmap.sleet;
        }
        else if (mIcon.equals("wind")) {
            iconId = R.mipmap.wind;
        }
        else if (mIcon.equals("fog")) {
            iconId = R.mipmap.fog;
        }
        else if (mIcon.equals("cloudy")) {
            iconId = R.mipmap.cloudy;
        }
        else if (mIcon.equals("partly-cloudy-day")) {
            iconId = R.mipmap.partly_cloudy;
        }
        else if (mIcon.equals("partly-cloudy-night")) {
            iconId = R.mipmap.cloudy_night;
        }
        return iconId;
    }

    public long getTime () {
        return mTime;
    }

    public String getFormattedTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
        formatter.setTimeZone(TimeZone.getTimeZone(getTimeZone()));
        Date dateTime = new Date(getTime()*1000);
        String timeString = formatter.format(dateTime);
        return timeString;
    }

    public void setTime(long mTime) {
        this.mTime = mTime;
    }

    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String mTimeZone) {
        this.mTimeZone = mTimeZone;
    }

    public double getTemperature() {
        double temp = ((mTemperature - 32)*5)/9;
        return Math.round(temp*10.0)/10.0;

    }

    public void setTemperature(double temperature) {
        mTemperature = temperature;
    }

    public int getPrecip() {
        double precip = mPrecip*100.0;
        int precipInt = (int) precip;
        return precipInt;
    }

    public void setPrecip (double precip) {
        mPrecip = precip;
    }

    public int getCloudCover() {
        double cloudCover = mCloudCover*100.0;
        int cloudCoverInt = (int) cloudCover;
        return cloudCoverInt;
    }

    public void setCloudCover(double cloudCover) {
        mCloudCover = cloudCover;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }
}
