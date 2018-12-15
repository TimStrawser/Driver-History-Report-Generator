import java.time.LocalTime;


/**
 * Represents a Trip that a driver object will take.
 *
 * @author Timothy Strawser
 * @version 9-5-18
 */
public class Trip {

    private String beginTrip;
    private String stopTrip;
    private double tripDistance;

    /**
     * Constructor for a Trip object.
     *
     * @param startTime - the time at the beginning of the trip.
     * @param endTime - the time at the end of the trip.
     * @param distance - the length of the trip in miles.
     */
    public Trip(String startTime, String endTime, double distance)
    {
        this.beginTrip = startTime;
        this.stopTrip = endTime;
        this.tripDistance = distance;
    }

    /**
     * Returns the distance traveled for the Trip in miles.
     *
     * @return {double} - the distance in miles.
     */
    public double getTripDistance()
    {
        return this.tripDistance;
    }

    /**
     * Returns the trip's beginning time.
     *
     * @return {String} - The time the trip started.
     */
    public String getBeginTrip()
    {
        return this.beginTrip;
    }

    /**
     * Returns the trip's ending time
     *
     * @return {String} - The time the trip ended.
     */
    public String getStopTrip()
    {
        return stopTrip;
    }

    /**
     * Calculates the avg speed of the trip in miles per hour.
     *
     * @return {int} - the avg speed of the trip in miles/hr.
     */
    public int getTripSpeed()
    {
        LocalTime time1 = LocalTime.parse(this.beginTrip);
        LocalTime time2 = LocalTime.parse(this.stopTrip);

        double diffInHours = (time2.toSecondOfDay() - time1.toSecondOfDay()
        ) / 3600.0;

        int speed = (int)Math.round(this.tripDistance/diffInHours);

        if (speed <= 0)
        {
            return 0;
        }
        else
        {
            return speed;
        }
    }

    public boolean isHighwayMiles()
    {
        if(this.getTripSpeed() > 55)
        {
            return true;
        }
        return false;
    }
}
