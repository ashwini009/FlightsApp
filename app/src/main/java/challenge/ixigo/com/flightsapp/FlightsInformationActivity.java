package challenge.ixigo.com.flightsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import challenge.ixigo.com.adaptors.FlightListAdapter;
import challenge.ixigo.com.common.Utils;
import challenge.ixigo.com.listeners.FlightListListener;
import challenge.ixigo.com.modal.FlightListViewHolder;
import challenge.ixigo.com.utils.RequestDataTask;

public class FlightsInformationActivity extends AppCompatActivity implements FlightListListener {
    private static final String URL = "http://blog.ixigo.com/sampleflightdata.json";

    private Button mBtnFetchData = null;

    private ListView lvFlights = null;

    private TextView tvFlightHeader = null;

    private ArrayList<FlightListViewHolder> mFlightList = null;

    private String originName;

    private String destinationName;

    String date;

    private FlightListAdapter mFlightListAdapter = null;

    int mListItemClickedPos = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights_information);

        mBtnFetchData = (Button) findViewById(R.id.btn_fetch_data);

        lvFlights = (ListView) findViewById(R.id.lvFlights);

        tvFlightHeader = (TextView) findViewById(R.id.tvFlightHeader);



        mBtnFetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isInternetAvailable(FlightsInformationActivity.this)) {
                    new RequestDataTask(FlightsInformationActivity.this, FlightsInformationActivity.this).execute(URL);
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
                }
            }
        });



        lvFlights.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListItemClickedPos != position) {
                    mListItemClickedPos = position;
                } else {
                    mListItemClickedPos = -1;
                }
                mFlightListAdapter.setListItemClickedPosition(mListItemClickedPos);

                mFlightListAdapter.notifyDataSetInvalidated();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_flights_information, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setListView(){
        if (mFlightList != null && mFlightList.size() > 0) {

            mFlightListAdapter = new FlightListAdapter(this, mFlightList);

            lvFlights.setVisibility(View.VISIBLE);
            tvFlightHeader.setVisibility(View.VISIBLE);
            tvFlightHeader.setText(originName + " - " + destinationName + " on " + date);
            lvFlights.setAdapter(mFlightListAdapter);

        }
    }

    @Override
    public void onDataTaskCompleted(ArrayList<FlightListViewHolder> flightList, String originName, String destinationName, String date) {
        mFlightList = flightList;
        this.originName = originName;
        this.destinationName = destinationName;
        this.date = date;
        setListView();
    }
}
