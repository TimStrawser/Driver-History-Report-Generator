import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the DriverHistoryReportGenerator class
 *
 * @author Tim Strawser
 * @version 9-5-18
 */
public class DriverHistoryReportGeneratorTest {

    /**
     * Tests the constructor
     */
    @Test
    public void testConstructor()
    {
        DriverHistoryReportGenerator report = new
                DriverHistoryReportGenerator();

        // Test if object successfully created and initialized correctly
        assertNotNull(report);
        assertEquals(0, report.getNumDrivers());
    }

    /**
     * Test the addDriver method
     */
    @Test
    public void testAddDriver()
    {
        DriverHistoryReportGenerator report = new
                DriverHistoryReportGenerator();

        //Test with no drivers
        assertEquals(0, report.getNumDrivers());

        //Add driver
        Driver customer = new Driver("Bob");
        assertTrue(report.addDriver(customer));

        //Check if successfully added
        assertEquals(1, report.getNumDrivers());
        String first = report.getDriverList().get(0).driverToString();
        assertTrue(first.contains("Bob: 0 miles"));
    }

    /**
     * Tests the consumeInput method
     */
    @Test
    public void testConsumeInput()
    {
        DriverHistoryReportGenerator report = new DriverHistoryReportGenerator();

        //test by giving invalid input file to consume
        assertFalse(report.consumeInput("Invalid_File.txt"));

        //Give valid input file
        assertTrue(report.consumeInput("driverInput.txt"));
    }

    /**
     * Tests the sortDriverByMileage method, sorts the driver by mileage
     * traveled.
     */
    @Test
    public void testSortDriversByMileage()
    {
        DriverHistoryReportGenerator report = new
                DriverHistoryReportGenerator();

        //Add drivers to the report
        Driver customer1 = new Driver("Bob");
        assertTrue(report.addDriver(customer1));
        assertTrue(customer1.recordNewTrip("15:30", "18:00", 155.7));

        Driver customer2 = new Driver("Kate");
        assertTrue(report.addDriver(customer2));

        Driver customer3 = new Driver("Tom");
        assertTrue(report.addDriver(customer3));
        assertTrue(customer3.recordNewTrip("05:00", "12:00", 325.9));

        Driver customer4 = new Driver("Sarah");
        assertTrue(report.addDriver(customer4));
        assertTrue(customer4.recordNewTrip("02:42", "07:53", 403.4));

        //Check that order of drivers in Array List is the order they were added
        String first = report.getDriverList().get(0).driverToString();
        assertTrue(first.contains("Bob: 156 miles @ 62 mph"));

        String second = report.getDriverList().get(1).driverToString();
        assertTrue(second.contains("Kate: 0 miles"));

        String third = report.getDriverList().get(2).driverToString();
        assertTrue(third.contains("Tom: 326 miles @ 47 mph"));

        String fourth = report.getDriverList().get(3).driverToString();
        assertTrue(fourth.contains("Sarah: 403 miles @ 78 mph"));

        //Run sort Algorithm, Drivers should now be in descending order.
        report.sortDriversByMileage();

        first = report.getDriverList().get(0).driverToString();
        assertTrue(first.contains("Sarah: 403 miles @ 78 mph"));

        second = report.getDriverList().get(1).driverToString();
        assertTrue(second.contains("Tom: 326 miles @ 47 mph"));

        third = report.getDriverList().get(2).driverToString();
        assertTrue(third.contains("Bob: 156 miles @ 62 mph"));

        fourth = report.getDriverList().get(3).driverToString();
        assertTrue(fourth.contains("Kate: 0 miles"));
    }

    /**
     * Tests the outputReportFile method
     */
    @Test
    public void testOutputReportFile()
    {
        DriverHistoryReportGenerator report = new
                DriverHistoryReportGenerator();

        assertTrue(report.consumeInput("driverInput.txt"));
        assertTrue(report.outputReportFile());

    }
}