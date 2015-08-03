package stormy.yasminlindholm.yasminsweatherapp.Model;

/**
 * Created by yasmin.lindholm on 2015-08-03.
 */
public class CurrentWeather {
    private String mIcon;
    private long mTime;
    private String mTimeZone;
    private double mTemperature;
    private double mHumidity;
    private double mPrecipitation;
    private String mSummary;

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public long getTime () {
        return mTime;
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
        return mTemperature;
    }

    public void setTemperature(double temperature) {
        mTemperature = temperature;
    }

    public double getHumidity() {
        return mHumidity;
    }

    public void setHumidity(double humidity) {
        mHumidity = humidity;
    }

    public double getPrecipitation() {
        return mPrecipitation;
    }

    public void setPrecipitation(double precipitation) {
        mPrecipitation = precipitation;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }
}
