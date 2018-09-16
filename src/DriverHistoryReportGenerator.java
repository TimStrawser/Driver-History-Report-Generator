import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * The DriverHistoryReportGenerator class represents a report generator object.
 *
 * @author Timothy Strawser
 * @version 9-5-18
 */
public class DriverHistoryReportGenerator {

    private ArrayList<Driver> driverList;
    public String inputPath = "drive_history_input/";
    public String outputPath = "drive_history_output/";

    /**
     * Constructor for DriverHistoryReportGenerator Object.
     */
    public DriverHistoryReportGenerator()
    {
        this.driverList = new ArrayList<Driver>();
    }

    /**
     * Returns the list of drivers registered.
     *
     * @return {int} - the number of drivers recorded.
     */
    public int getNumDrivers()
    {
        return this.driverList.size();
    }

    /**
     * Add a new driver to the report's driver Array List
     *
     * @param newDriver - a driver object to be added to the Array List
     *
     * @return {boolean} - return true if driver successfully added, false if
     *                      driver is already registered.
     */
    public boolean addDriver(Driver newDriver)
    {
        for (Driver customer: this.driverList)
        {
            if (newDriver.getName().equalsIgnoreCase(customer.getName()))
            {
                return false;
            }
        }
        this.driverList.add(newDriver);
        return true;
    }

    /**
     * Returns an ArrayList of Driver objects recorded in the report
     *
     * @return {ArrayList<Drivers>} - an array list of driver objects
     */
    public ArrayList<Driver> getDriverList()
    {
        return this.driverList;
    }

    /**
     * Prompts user for an input file, consumes file, creates Driver objects and
     * records trips.
     *
     * @param inputFile - the name of a valid input file to be consumed.
     *
     * @return {boolean} - returns true if either a driver was successfully added
     *                      or a trip was taken.
     */
    public boolean consumeInput(String inputFile) {

        boolean tripSuccess = false;
        boolean addDriverSuccess = false;

        // Read in selected input file.
        try {
            FileReader fr = new FileReader(inputPath + inputFile);
            BufferedReader br = new BufferedReader(fr);

            String inputLine;
            while ((inputLine = br.readLine()) != null) {

                // if the line contains "Driver", make a new driver
                // else if the line contains "Trip" record a new trip.
                if (inputLine.contains("Driver")) {
                    String[] line = inputLine.split(" ");
                    String driverName = line[1];

                    Driver newDriver = new Driver(driverName);
                    addDriverSuccess = this.addDriver(newDriver);
                } else if (inputLine.contains("Trip")) {
                    String[] line = inputLine.split(" ");
                    String driverName = line[1];
                    String beginTrip = line[2];
                    String endTrip = line[3];
                    double length = Double.parseDouble(line[4]);

                    //match trip to the right driver
                    for (Driver customer : driverList) {
                        if (customer.getName().equalsIgnoreCase(driverName)) {
                            tripSuccess = customer.recordNewTrip
                                    (beginTrip, endTrip, length);
                        }
                    }
                } else {
                    System.out.println("Incorrect input format.");
                }
            }
            br.close();
            fr.close();
        }
        catch (IOException e)
        {
            System.out.println("File not Found.");
            return false;
        }

        if (addDriverSuccess || tripSuccess)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * sortDriversByMilage() - Sorts the drivers by the total mileage using
     *                          the Insertion Sort algorithm.
     *
     */
    public void sortDriversByMileage()
    {
        if (driverList.size() > 1)
        {
            for (int i = 1; i < driverList.size(); i++)
            {
                Driver tmpDriver1 = this.driverList.get(i);
                int tmpDriver1_Mileage = (int)Math.round
                        (tmpDriver1.getDriverMiles());

                int j = i-1;
                Driver tmpDriver2 = this.driverList.get(j);
                int tmpDriver2_Mileage = (int)Math.round
                        (tmpDriver2.getDriverMiles());

                //go into loop comparing driver mileage
                while (j>=0 && tmpDriver1_Mileage > tmpDriver2_Mileage)
                {
                    this.driverList.set(j+1, this.driverList.get(j));
                    j--;

                    if (j >= 0)
                    {
                        tmpDriver2 = this.driverList.get(j);
                        tmpDriver2_Mileage = (int)Math.round
                                (tmpDriver2.getDriverMiles());
                    }
                }
                this.driverList.set(j+1, tmpDriver1);
            }
        }
    }

    /**
     * outputReportFile() - outputs report generated from input data to a
     *                          file in the output directory.
     *
     * @return {boolean} - returns true if reports for all drivers are outputted
     *                          to file.
     */
    public boolean outputReportFile()
    {
        // Get the amount of drivers that should be processed into output
        int driverCount = this.getNumDrivers();
        int counter = 0;

        //generate time stamp for unique output file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format
                (Calendar.getInstance().getTime());

        // output file
        try
        {
            FileWriter fw = new FileWriter(this.outputPath +
                    "Driver_History_Report_" + timeStamp + ".txt");

            PrintWriter pw = new PrintWriter(fw);

            this.sortDriversByMileage();

            for (Driver customer : driverList) {

                pw.println(customer.driverToString());
                System.out.println(customer.driverToString());
                counter++;
            }
            pw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error while generating report.");
            return false;
        }
        if(counter == driverCount)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
