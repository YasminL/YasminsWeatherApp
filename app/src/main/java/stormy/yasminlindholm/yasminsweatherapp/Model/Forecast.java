package stormy.yasminlindholm.yasminsweatherapp.Model;

import stormy.yasminlindholm.yasminsweatherapp.R;

/**
 * Created by yasmin.lindholm on 2015-08-05.
 */
public class Forecast {
    private CurrentWeather mCurrentWeather;
    private HourlyWeather[] mHourlyWeather;
    private DailyWeather[] mDailyWeather;

    public CurrentWeather getCurrentWeather() {
        return mCurrentWeather;
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        mCurrentWeather = currentWeather;
    }

    public HourlyWeather[] getHourlyWeather() {
        return mHourlyWeather;
    }

    public void setHourlyWeather(HourlyWeather[] hourlyWeather) {
        mHourlyWeather = hourlyWeather;
    }

    public DailyWeather[] getDailyWeather() {
        return mDailyWeather;
    }

    public void setDailyWeather(DailyWeather[] dailyWeather) {
        mDailyWeather = dailyWeather;
    }

    public static int getIconId(String iconString) {
        int iconId = R.mipmap.clear_day;

        if (iconString.equals("clear-day")) {
            iconId = R.mipmap.clear_day;
        }
        else if (iconString.equals("clear-night")) {
            iconId = R.mipmap.clear_night;
        }
        else if (iconString.equals("rain")) {
            iconId = R.mipmap.rain;
        }
        else if (iconString.equals("snow")) {
            iconId = R.mipmap.snow;
        }
        else if (iconString.equals("sleet")) {
            iconId = R.mipmap.sleet;
        }
        else if (iconString.equals("wind")) {
            iconId = R.mipmap.wind;
        }
        else if (iconString.equals("fog")) {
            iconId = R.mipmap.fog;
        }
        else if (iconString.equals("cloudy")) {
            iconId = R.mipmap.cloudy;
        }
        else if (iconString.equals("partly-cloudy-day")) {
            iconId = R.mipmap.partly_cloudy;
        }
        else if (iconString.equals("partly-cloudy-night")) {
            iconId = R.mipmap.cloudy_night;
        }
        return iconId;
    }
}
