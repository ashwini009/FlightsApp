package challenge.ixigo.com.modal;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ashwiask on 9/17/2015.
 */
public class FlightListViewHolder implements Parcelable {

    private String originName;
    private String destinationName;
    private String takeOffTime;
    private String landingTime;
    private String price;
    private String seatClass;
    private String airLineName;
    private String airLineCode;
    private String totalTime;


    public FlightListViewHolder(String airLineCode, String airlineName, String originName, String destinationName, String takeOffTime, String landingTime, String price, String seatClass, String totalTime) {
        this.airLineCode = airLineCode;
        this.airLineName = airlineName;
        this.originName = originName;
        this.destinationName = destinationName;
        this.takeOffTime = takeOffTime;
        this.landingTime = landingTime;
        this.price = price;
        this.seatClass = seatClass;
        this.totalTime = totalTime;


    }

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
    }

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
    }
}
