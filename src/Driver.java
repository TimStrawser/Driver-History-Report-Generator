import java.util.ArrayList;

/**
 * Represents a Driver Object
 *
 * @author Tim Strawser
 * @version  9-5-18
 */
public class Driver {

    private String name;
    private double driverTotalMiles;
    private ArrayList<Trip> trips;
    public double totalHighwayMiles;

    /**
     * Constructor for driver objects
     *
     * @param driverName - name of the new Driver.
     */
    public Driver (String driverName)
    {
        this.name = driverName;
        this.driverTotalMiles = 0.0;
        trips = new ArrayList<Trip>();
        this.totalHighwayMiles = 0.0;
    }

    /**
     * Returns the name of the driver object.
     *
     * @return {String} - the name of the driver object.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Returns the total amount of miles driven for a driver
     *                      across all trips.
     * @return {double} - the total miles driven by the driver.
     */
    public double getDriverMiles()
    {
        return this.driverTotalMiles;
    }

    /**
     * Return the number of recorded trips for the Driver.
     *
     * @return {int} - the number of total trips for the driver.
     */
    public int getNumTrips()
    {
        return this.trips.size();
    }

    /**
     * Creates a new trip object, calculates and returns avgSpeed of trip.
     *              If a new trip successfully added, method returns true.
     *
     * @param startTrip - the time stamp for the beginning of the trip in HH:mm.
     * @param stopTrip - the time stamp for the end of the trip in HH:mm.
     * @param distance - the distance of the trip as a double.
     *
     * @return {boolean} - if new trip is successfully added, returns true.
     *
     */
    public boolean recordNewTrip(String startTrip, String stopTrip, double
            distance)
    {
        if (distance > 0) {

            //Create new Trip object.
            Trip newTrip = new Trip(startTrip, stopTrip, distance);
            int tripSpeed = newTrip.getTripSpeed();

            if (tripSpeed >= 5 && tripSpeed <= 100)
            {
                trips.add(newTrip);
                driverTotalMiles += distance;

                if(newTrip.isHighwayMiles())
                {
                    this.totalHighwayMiles += newTrip.getTripDistance();
                }

                return true;
            }
            else
            {
                System.out.println("Trip speed too low or too high.");
                return false;
            }
        }
        else
        {
            System.out.println("No distance traveled");
            return false;
        }
    }

    /**
     * Calculates the avg speed of the driver
     *                              across all trips taken.
     *
     * @return {int} - the avg speed of driver across all trips in miles per
     *                  hour.
     */
    public int calculateDriverAvgSpeed()
    {
        int speeds = 0;

        if (trips.size() >= 1) {
            for (Trip driverTrip : trips) {
                speeds += driverTrip.getTripSpeed();
            }

            double calc = (speeds * 1.0) / trips.size();
            return (int)Math.round(calc);
        }
        else
        {
            return 0;
        }
    }

    /**
     * Returns a string representation of Driver stats
     *
     * @return {String} - The name of the Driver, total miles driver, driver
     *                          average speed.
     */
    public String driverToString()
    {
        int allMiles = (int)Math.round(this.driverTotalMiles);

        if (this.driverTotalMiles > 0)
        {
            return this.name + ": " + Integer.toString(allMiles) + " miles @ " +
                    Integer.toString(this.calculateDriverAvgSpeed()) + " mph, " +
                    "Percentage Highway Miles: " +
                    this.getPercentHighway();

        }
        else
        {
            return this.name + ": " + Integer.toString(allMiles) + " miles";
        }
    }

    public int getPercentHighway()
    {
        if (this.trips.size() >= 1)
        {
            return (int)Math.round(this.totalHighwayMiles / this.driverTotalMiles * 100);
        }
        return 0;

    }
}
