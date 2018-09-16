import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test the Driver Class.
 *
 * @author Tim Strawser
 * @version 9-5-18
 */
public class DriverTest {

    public static final double DELTA = .001;

    /**
     * Tests the Driver Constructor.
     */
    @Test
    public void testConstructor()
    {
        Driver customer = new Driver("Bob");

        // Test to see if Driver object successfully created and values
        // initialized.
        assertNotNull(customer);
        assertEquals("Bob", customer.getName());
        assertEquals(0, customer.getDriverMiles(), DELTA);
        assertEquals(0, customer.getNumTrips());
    }

    /**
     * Tests the recordNewTrip method.
     */
    @Test
    public void testRecordNewTrip()
    {
        Driver customer = new Driver("Bob");

        //Test if distance is less than 1.0, trip not recorded
        assertFalse(customer.recordNewTrip("13:00", "15:00", 0.0));
        assertEquals(0, customer.getDriverMiles(), DELTA);
        assertEquals(0, customer.getNumTrips());

        //Test for valid distance, speed less than 5 mph, trip not recorded
        assertFalse(customer.recordNewTrip("13:00", "14:00", 3.9));
        assertEquals(0, customer.getDriverMiles(), DELTA);
        assertEquals(0, customer.getNumTrips());

        //Test for valid distance, speed greater than 100,mph, trip not recorded
        assertFalse(customer.recordNewTrip("13:00", "14:00", 101.5));
        assertEquals(0, customer.getDriverMiles(), DELTA);
        assertEquals(0, customer.getNumTrips());

        //Test for valid distance, valid speed, Trip recorded.
        assertTrue(customer.recordNewTrip("13:00", "14:00", 32.0));
        assertEquals(32.0, customer.getDriverMiles(), DELTA);
        assertEquals(1, customer.getNumTrips());

        //Add more valid values
        assertTrue(customer.recordNewTrip("08:00", "18:35", 725.7));
        assertEquals(757.7, customer.getDriverMiles(), DELTA);
        assertEquals(2, customer.getNumTrips());
    }

    /**
     * Tests the calculateDriverAvgSpeed method.
     */
    @Test
    public void testCalculateDriverAvgSpeed()
    {
        Driver customer = new Driver("Bob");

        //Test if no trips taken.
        assertEquals(0, customer.calculateDriverAvgSpeed());

        //Record valid trip
        assertTrue(customer.recordNewTrip("13:00", "14:00", 32.0));
        assertEquals(32, customer.calculateDriverAvgSpeed());

        //Record valid trip
        assertTrue(customer.recordNewTrip("08:00", "18:35", 725.7));
        assertEquals(51, customer.calculateDriverAvgSpeed());

        //Record valid trip
        assertTrue(customer.recordNewTrip("03:35", "09:43", 375.3));
        assertEquals(54, customer.calculateDriverAvgSpeed());
    }

    /**
     * Tests the driverToString method.
     */
    @Test
    public void testDriverToString()
    {
        Driver customer1 = new Driver("Bob");

        //Test that no speed is shown if driver did not take trip
        String a = customer1.driverToString();
        assertTrue(a.contains("Bob: 0 miles"));

        //Test valid data
        customer1.recordNewTrip("08:00", "18:35", 725.7);
        String b = customer1.driverToString();
        assertTrue(b.contains("Bob: 726 miles @ 69 mph"));

        //Test valid data
        customer1.recordNewTrip("03:35", "09:43", 375.3);
        String c = customer1.driverToString();
        assertTrue(c.contains("Bob: 1101 miles @ 65 mph"));
    }
}