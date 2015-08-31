package stormy.yasminlindholm.yasminsweatherapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import stormy.yasminlindholm.yasminsweatherapp.Model.DailyWeather;
import stormy.yasminlindholm.yasminsweatherapp.R;

/**
 * Created by yasmin.lindholm on 2015-08-07.
 */
public class DayAdapter extends BaseAdapter {
    private Context mContext;
    private DailyWeather[] mDailyWeathers;

    public DayAdapter(Context context,DailyWeather[] days) {
        mContext = context;
        mDailyWeathers = days;
    }

    @Override
    public int getCount() {
        return mDailyWeathers.length;
    }

    @Override
    public Object getItem(int position) {
        return mDailyWeathers[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertViewNeedsToBeCreated, ViewGroup parent) {
        ViewHolder holder;

        if (convertViewNeedsToBeCreated == null) {
            convertViewNeedsToBeCreated = LayoutInflater.from(mContext).inflate(R.layout.daily_list_item, null);
            holder = new ViewHolder();
            holder.iconImageView = (ImageView) convertViewNeedsToBeCreated.findViewById(R.id.iconImageView);
            holder.dayNameLabel = (TextView) convertViewNeedsToBeCreated.findViewById(R.id.dayNameLabel);
            holder.tempMinLabel = (TextView) convertViewNeedsToBeCreated.findViewById(R.id.tempMinLabel);
            holder.tempMaxLabel = (TextView) convertViewNeedsToBeCreated.findViewById(R.id.tempMaxLabel);

            convertViewNeedsToBeCreated.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertViewNeedsToBeCreated.getTag();
        }

        DailyWeather day = mDailyWeathers[position];
        holder.iconImageView.setImageResource(day.getIconId());
        holder.tempMinLabel.setText(day.getTempMin() + "");
        holder.tempMaxLabel.setText(day.getTempMax() + "");
        String name = day.getDaysOfWeek();

        if (position == 0) {
            holder.dayNameLabel.setText("Today");
        }
        else {
            holder.dayNameLabel.setText(name);
        }


        return convertViewNeedsToBeCreated;
    }

    private static class ViewHolder {
        public ImageView iconImageView;
        public TextView dayNameLabel;
        public TextView tempMinLabel;
        public TextView tempMaxLabel;

    }
}
