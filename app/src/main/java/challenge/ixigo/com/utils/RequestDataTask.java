package challenge.ixigo.com.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

import challenge.ixigo.com.common.Utils;
import challenge.ixigo.com.flightsapp.R;
import challenge.ixigo.com.listeners.FlightListListener;
import challenge.ixigo.com.modal.FlightListViewHolder;

/**
 * Created by ashwiask on 9/17/2015.
 * AsyncTask for fetching data from URL. It parses the JSON data and populates into the structure (FlightListViewHolder).
 * Once the structure is set. it populates the flight list and after finishing up the task, gives a call back to the activity so that list view can be populated
 */
public class RequestDataTask extends AsyncTask<String, Void, String> {

    private static final String TAG = RequestDataTask.class.getSimpleName();

    /**
     * Activity context
     */
    private Context mContext = null;

    /**
     * Map [key - Airline Code, Value - Airline Name]
     */
    private HashMap<String, String> airlineMap = new HashMap<>();

    /**
     * Map [key - Airport Code , Value - Airport Name]
     */

    private HashMap<String, String> airportMap = new HashMap<>();

    /**
     * Progress dialog is shown when the task starts
     */
    private ProgressDialog mProgressDialog = null;


    /**
     * Arralist of flight information
     */
    private ArrayList<FlightListViewHolder> mFlightList;

    /**
     * Listener for activity call back
     */
    private FlightListListener mFlightListListener = null;

    //Constructor
    public RequestDataTask(Context context, FlightListListener listener) {
        mContext = context;
        mProgressDialog = new ProgressDialog(context);
        mFlightListListener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog.setMessage(mContext.getString(R.string.fetching_data));
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {

        StringBuilder builder = new StringBuilder("");
        java.net.URL url;
        BufferedReader reader = null;

        try {
            url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            String response;

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
            while ((response = reader.readLine()) != null) {
                builder.append(response + "\n");
            }

        } catch (MalformedURLException ex) {
            Log.d(TAG, "URL is not proper " + ex.getMessage());
        } catch (SecurityException e) {
            Log.d(TAG, "Permission denied. Please check the API key");
        } catch (IOException e) {
            Log.d(TAG, "URL not found " + e.getMessage());
        } finally {
            try {

                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return builder.toString();


    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        //Parsing JSON data

        try {
            JSONObject jsonObj = new JSONObject(result);

            JSONObject airlineObj = jsonObj.getJSONObject("airlineMap");

            // Populate Airline map
            airlineMap.put("SJ", airlineObj.getString("SJ"));
            airlineMap.put("AI", airlineObj.getString("AI"));
            airlineMap.put("G8", airlineObj.getString("G8"));
            airlineMap.put("JA", airlineObj.getString("JA"));
            airlineMap.put("IN", airlineObj.getString("IN"));

            JSONObject airportObj = jsonObj.getJSONObject("airportMap");

            //Populate Airport Map
            airportMap.put("DEL", airportObj.getString("DEL"));
            airportMap.put("MUM", airportObj.getString("MUM"));

            JSONArray flightsArr = jsonObj.getJSONArray("flightsData");

            mFlightList = new ArrayList<>(flightsArr.length());

            String originName = "";
            String destinationName = "";
            long takeoffTime = 0;
            for (int i = 0; i < flightsArr.length(); i++) {
                JSONObject flight = flightsArr.getJSONObject(i);

                String originCode = flight.getString("originCode");
                String destinationCode = flight.getString("destinationCode");
                takeoffTime = Long.parseLong(flight.getString("takeoffTime"));
                long landingTime = Long.parseLong(flight.getString("landingTime"));
                String price = flight.getString("price");
                String airlineCode = flight.getString("airlineCode");
                String seatClass = flight.getString("class");

                originName = airportMap.get(originCode);
                destinationName = airportMap.get(destinationCode);
                FlightListViewHolder flightItem = new FlightListViewHolder(airlineCode, airlineMap.get(airlineCode), originName, destinationName, Utils.getTime(takeoffTime), Utils.getTime(landingTime), price, seatClass, Utils.getTotalTime(takeoffTime, landingTime), takeoffTime, landingTime, (landingTime - takeoffTime));

                //Populate the list
                mFlightList.add(flightItem);

            }

            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
            // Call back to the activity
            mFlightListListener.onDataTaskCompleted(mFlightList, originName, destinationName, Utils.getDate(takeoffTime));


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
