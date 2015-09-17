package challenge.ixigo.com.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ashwiask on 9/17/2015.
 */
public class Utils {


    public static String getTime(long timeInMillis) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat("kk:mm");

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        return formatter.format(calendar.getTime());
    }

    /**
     * Check for the internet connection availability
     *
     * @return - true if internet is available
     * else flase
     */
    public static boolean isInternetAvailable(Context context) {
        boolean isInternetPresent = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        isInternetPresent = true;
                    }
                }
            }
        }
        return isInternetPresent;
    }

    public static String getTotalTime(long startTime, long endTime) {
        long totalTripTime = endTime - startTime;

        long seconds = (int) (totalTripTime / 1000) % 60 ;
        long minutes = (int) ((totalTripTime / (1000*60)) % 60);
        long hours   = (int) ((totalTripTime / (1000*60*60)) % 24);
        long timeInDays = hours / 24;

        String totalTime = "";
        if (timeInDays > 0) {

            totalTime += timeInDays + "d";
        }
        if (hours > 0) {
            totalTime += hours + "h";
        }
        if (minutes > 0) {
            totalTime += minutes + "m";
        }
        return totalTime;


    }
    public static String getDate(long timeInMillis){
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        return formatter.format(calendar.getTime());
    }
}
