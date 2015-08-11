package stormy.yasminlindholm.yasminsweatherapp.Adapters;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import stormy.yasminlindholm.yasminsweatherapp.Model.HourlyWeather;
import stormy.yasminlindholm.yasminsweatherapp.R;

/**
 * Created by yasmin.lindholm on 2015-08-10.
 */
public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder> {

    public static final String TAG = HourAdapter.class.getSimpleName();

    private HourlyWeather[] mHourlyWeathers;
    private Context mContext;

    public HourAdapter(Context context, HourlyWeather[] hourlyWeathers) {
        mContext = context;
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

    public class HourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTimeLabel;
        public ImageView mIconLabel;
        public TextView mTempLabel;
        public TextView mDayNameLabel;
        public TextView mSummary;

        public HourViewHolder(View itemView) {
            super(itemView);

            mTimeLabel = (TextView) itemView.findViewById(R.id.hourLabel);
            mIconLabel = (ImageView) itemView.findViewById(R.id.iconImageHour);
            mTempLabel = (TextView) itemView.findViewById(R.id.tempLabelHour);
            mDayNameLabel = (TextView) itemView.findViewById(R.id.dayNameLabelHour);
            mSummary = (TextView) itemView.findViewById(R.id.summaryHour);
            Log.i(TAG, "We are logging in HourViewHolder and the summary is" + mSummary);
            itemView.setOnClickListener(this);
        }

        public void bindHour(HourlyWeather hourWeather) {
            mTimeLabel.setText(hourWeather.getHoursOfDay());
            mIconLabel.setImageResource(hourWeather.getIconId());
            mTempLabel.setText(hourWeather.getTemp() + "");
            mDayNameLabel.setText(hourWeather.getDayName());
            mSummary.setText(hourWeather.getSummary());

        }

        @Override
        public void onClick(View v) {
            String dayName = mDayNameLabel.getText().toString();
            String time = mTimeLabel.getText().toString();
            String summary = mSummary.getText().toString();
            String message = String.format("The weather for %s at %s is %s", dayName, time, summary);
            Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
        }
    }
}


