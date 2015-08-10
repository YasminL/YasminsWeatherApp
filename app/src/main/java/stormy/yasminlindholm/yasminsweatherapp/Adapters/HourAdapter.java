package stormy.yasminlindholm.yasminsweatherapp.Adapters;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import stormy.yasminlindholm.yasminsweatherapp.Model.HourlyWeather;
import stormy.yasminlindholm.yasminsweatherapp.R;

/**
 * Created by yasmin.lindholm on 2015-08-10.
 */
public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder> {

    private HourlyWeather[] mHourlyWeathers;

    public HourAdapter(HourlyWeather[] hourlyWeathers) {
        mHourlyWeathers = hourlyWeathers;
    }

    @Override
    public HourViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_list_item, parent, false);

        HourViewHolder viewHolder = new HourViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HourViewHolder hourViewHolder, int i) {
        hourViewHolder.bindHour(mHourlyWeathers[i]);

    }

    @Override
    public int getItemCount() {
        return mHourlyWeathers.length;
    }

    public class HourViewHolder extends RecyclerView.ViewHolder {

        public TextView mTimeLabel;
        public ImageView mIconLabel;
        public TextView mSummaryLabel;
        public TextView mTempLabel;

        public HourViewHolder(View itemView) {
            super(itemView);

            mTimeLabel = (TextView) itemView.findViewById(R.id.hourLabel);
            mIconLabel = (ImageView) itemView.findViewById(R.id.iconImageHour);
            mSummaryLabel = (TextView) itemView.findViewById(R.id.summaryLabelHour);
            mTempLabel = (TextView) itemView.findViewById(R.id.tempLabelHour);
        }

        public void bindHour(HourlyWeather hourWeather) {
            mTimeLabel.setText("@" + hourWeather.getHoursOfDay());
            mIconLabel.setImageResource(hourWeather.getIconId());
            mSummaryLabel.setText(hourWeather.getSummary());
            mTempLabel.setText(hourWeather.getTemp() + "");
        }

}
}


