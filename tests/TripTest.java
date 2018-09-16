import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The test class for the Trip class
 *
 * @author Timothy Strawser
 * @version 9-5-18
 */
public class TripTest {

    public static final double DELTA = .001;

    /**
     * Test the constructor for a Trip object.
     */
    @Test
    public void testConstructor()
    {
        Trip journey = new Trip("08:00", "08:30", 25.0);

        //Test if Trip object was successfully created and initialize correctly
        assertNotNull(journey);
        assertEquals("08:00", journey.getBeginTrip());
        assertEquals("08:30", journey.getStopTrip());
        assertEquals(25.0, journey.getTripDistance(), DELTA);
    }

    /**
     * Tests the getTripSpeed method.
     */
    @Test
    public void testGetTripSpeed()
    {
        Trip journey = new Trip("08:00", "07:30", 25.0);

        // Test if starting time is after the end time.
        assertEquals(0, journey.getTripSpeed());

        // Test calculation of correct input.
        Trip journey2 = new Trip("08:00", "08:30", 25.0);
        assertEquals(50, journey2.getTripSpeed());

        // Test calculation of correct input.
        Trip journey3 = new Trip("11:00", "17:36", 427.5);
        assertEquals(65, journey3.getTripSpeed());
    }

    /**
     * Tests the getTripDistance method.
     */
    @Test
    public void testGetTripDistance()
    {
        Trip journey = new Trip("08:00", "08:30", 25.0);

        assertEquals(25.0, journey.getTripDistance(), DELTA);
    }
}