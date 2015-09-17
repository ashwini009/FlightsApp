package challenge.ixigo.com.modal;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ashwiask on 9/17/2015.
 * Structure that holds all the flight related information
 */
public class FlightListViewHolder implements Parcelable {

    /**
     * Creating the structure from parcel
     */
    public static final Creator<FlightListViewHolder> CREATOR = new Creator<FlightListViewHolder>() {
        @Override
        public FlightListViewHolder createFromParcel(Parcel in) {
            return new FlightListViewHolder(in);
        }

        @Override
        public FlightListViewHolder[] newArray(int size) {
            return new FlightListViewHolder[size];
        }
    };
    /**
     * Origin Name
     */
    private String originName;
    /**
     * Destination Name
     */
    private String destinationName;
    /**
     * Flight take-off time
     */
    private String takeOffTime;
    /**
     * Flight landing time
     */
    private String landingTime;
    /**
     * Rate of the journey
     */
    private String price;
    /**
     * Seat class
     */
    private String seatClass;
    /**
     * Airline Name
     */
    private String airLineName;
    /**
     * Airline Code
     */
    private String airLineCode;
    /**
     * Total duration of the journey
     */
    private String totalTime;
    /**
     * Flight departure time in millis
     */
    private long departureTime;
    /**
     * Flight arrival time in millis
     */
    private long arrivalTime;
    /**
     * Flight duration in millis
     */
    private long duration;

    /**
     * Constructor to set up all the data
     */

    public FlightListViewHolder(String airLineCode, String airlineName, String originName, String destinationName, String takeOffTime, String landingTime, String price, String seatClass, String totalTime, long departureTime, long arrivalTime, long duration) {
        this.airLineCode = airLineCode;
        this.airLineName = airlineName;
        this.originName = originName;
        this.destinationName = destinationName;
        this.takeOffTime = takeOffTime;
        this.landingTime = landingTime;
        this.price = price;
        this.seatClass = seatClass;
        this.totalTime = totalTime;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.duration = duration;

    }

    /**
     * Reading from the parcel
     */

    protected FlightListViewHolder(Parcel in) {
        originName = in.readString();
        destinationName = in.readString();
        takeOffTime = in.readString();
        landingTime = in.readString();
        price = in.readString();
        seatClass = in.readString();
        airLineName = in.readString();
        airLineCode = in.readString();
        totalTime = in.readString();
        duration = in.readLong();
        departureTime = in.readLong();
        arrivalTime = in.readLong();
    }

    public String getAirLineName() {
        return airLineName;
    }


    public String getOriginName() {
        return originName;
    }


    public String getDestinationName() {
        return destinationName;
    }

    public String getTakeOffTime() {
        return takeOffTime;
    }

    public long getDepartureTime() {
        return departureTime;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public long getDuration() {
        return duration;
    }

    public String getLandingTime() {
        return landingTime;
    }


    public String getPrice() {
        return price;
    }

    public String getSeatClass() {
        return seatClass;
    }


    public String getAirLineCode() {
        return airLineCode;
    }

    public String getTotalTime() {
        return totalTime;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Writing to the parcel
     */

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(originName);
        out.writeString(destinationName);
        out.writeString(takeOffTime);
        out.writeString(landingTime);
        out.writeString(price);
        out.writeString(seatClass);
        out.writeString(airLineName);
        out.writeString(airLineCode);
        out.writeString(totalTime);
        out.writeLong(departureTime);
        out.writeLong(arrivalTime);
        out.writeLong(duration);

    }
}
