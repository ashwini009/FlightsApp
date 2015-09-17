package challenge.ixigo.com.modal;

/**
 * Created by ashwiask on 9/17/2015.
 */
public class FlightListViewHolder {

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


}
