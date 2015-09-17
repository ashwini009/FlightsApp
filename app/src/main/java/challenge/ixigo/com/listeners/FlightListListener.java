package challenge.ixigo.com.listeners;

import java.util.ArrayList;

import challenge.ixigo.com.modal.FlightListViewHolder;

/**
 * Created by ashwiask on 9/17/2015.
 * Listener that fires the call back to FlightsInformationActivty when the data fetching task has finished.
 */
public interface FlightListListener {

    void onDataTaskCompleted(ArrayList<FlightListViewHolder> flightList, String originName, String destinationName, String date);
}
