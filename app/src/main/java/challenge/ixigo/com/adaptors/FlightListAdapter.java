package challenge.ixigo.com.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import challenge.ixigo.com.flightsapp.R;
import challenge.ixigo.com.modal.FlightListViewHolder;

/**
 * Created by ashwiask on 9/17/2015.
 */
public class FlightListAdapter extends BaseAdapter {

    private Context mContext = null;

    private ArrayList<FlightListViewHolder> mFlightList = null;

    private LayoutInflater mInflator = null;

    private int mListItemClickedPos = -1;

    public FlightListAdapter(Context context, ArrayList<FlightListViewHolder> flightList) {
        mContext = context;
        mFlightList = flightList;
        mInflator = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        return mFlightList.size();
    }

    @Override
    public FlightListViewHolder getItem(int position) {
        return mFlightList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        FlightViewItemHolder holder = null;
        if (convertView == null) {
            convertView = mInflator.inflate(R.layout.flight_list_view_item, parent, false);

            holder = new FlightViewItemHolder();
            holder.rlShortInfo = (RelativeLayout) convertView.findViewById(R.id.rlFlightShortInfo);
            holder.tvTakeOffTime = (TextView) convertView.findViewById(R.id.tvTakeOffTime);
            holder.tvLandingTime = (TextView) convertView.findViewById(R.id.tvLandingTime);
            holder.tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
            holder.tvAirlineCode = (TextView) convertView.findViewById(R.id.tvAirLineCode);
            holder.tvAirLineName1 = (TextView) convertView.findViewById(R.id.tvAirLineName);
            holder.tvSeatClass = (TextView) convertView.findViewById(R.id.tvSeatClass);

            holder.rlExtraInfo = (RelativeLayout) convertView.findViewById(R.id.rlFlightsExtraInfo);
            holder.tvOriginName = (TextView) convertView.findViewById(R.id.tvOriginName);
            holder.tvStartTime = (TextView) convertView.findViewById(R.id.tvStartTime);
            holder.tvAirLineName2 = (TextView) convertView.findViewById(R.id.tvAirLineName1);
            holder.tvTotalTime = (TextView) convertView.findViewById(R.id.tvTotalTime);
            holder.tvDestinationName = (TextView) convertView.findViewById(R.id.tvDestinationName);
            holder.tvEndTime = (TextView) convertView.findViewById(R.id.tvEndTime);

            fillFlightDetails(holder, position);

            convertView.setTag(holder);

        } else {
            holder = (FlightViewItemHolder) convertView.getTag();
            fillFlightDetails(holder, position);
        }

        return convertView;
    }

    private void fillFlightDetails(FlightViewItemHolder holder, int position) {
        if (mFlightList == null) {
            return;
        }

        FlightListViewHolder flightItem = getItem(position);

        if (mListItemClickedPos == position) {
            holder.rlExtraInfo.setVisibility(View.VISIBLE);
            holder.tvOriginName.setText(flightItem.getOriginName());
            holder.tvStartTime.setText(flightItem.getTakeOffTime());
            holder.tvAirLineName2.setText(flightItem.getAirLineName());
            holder.tvTotalTime.setText(flightItem.getTotalTime());
            holder.tvDestinationName.setText(flightItem.getDestinationName());
            holder.tvEndTime.setText(flightItem.getLandingTime());
        } else {
            holder.rlExtraInfo.setVisibility(View.GONE);
            holder.tvTakeOffTime.setText(flightItem.getTakeOffTime());
            holder.tvLandingTime.setText(flightItem.getLandingTime());
            holder.tvPrice.setText(flightItem.getPrice());
            holder.tvAirlineCode.setText(flightItem.getAirLineCode());
            holder.tvAirLineName1.setText(flightItem.getAirLineName());
            holder.tvSeatClass.setText(flightItem.getSeatClass());
        }


    }


    public void setListItemClickedPosition(int position) {
        mListItemClickedPos = position;
    }

    public void updateFlightList(ArrayList<FlightListViewHolder> list) {
        mFlightList = list;
        notifyDataSetChanged();
    }

    private class FlightViewItemHolder {
        // Short information
        RelativeLayout rlShortInfo;
        TextView tvTakeOffTime;
        TextView tvLandingTime;
        TextView tvPrice;
        TextView tvAirlineCode;
        TextView tvAirLineName1;
        TextView tvSeatClass;
        // Extra Information
        RelativeLayout rlExtraInfo;
        TextView tvOriginName;
        TextView tvStartTime;
        TextView tvTotalTime;
        TextView tvAirLineName2;
        TextView tvDestinationName;
        TextView tvEndTime;
    }
}
