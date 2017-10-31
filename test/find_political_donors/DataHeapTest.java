package find_political_donors;

import find_political_donors.DataStorageEngine.DataHeap;
import java.util.Arrays;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test DataHeap data storage and getting median using large amount of random
 * numbers
 *
 * @author Xiao Zhou
 */
public class DataHeapTest {

    public DataHeapTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class DataHeap.
     */
    @Test
    public void testMedian() {
        System.out.println("Test DataHeap to manage large amount of data");
        int size = 1000000;
        int[] t = new int[size];
        Random rand = new Random();

        DataHeap instance = new DataHeap();
        for (int i = 0; i < t.length; i++) {
            t[i] = rand.nextInt(2000001) - 1000000;
            assertEquals(true, instance.offer(t[i]));
        }
        Arrays.sort(t);
        int median = (int) Math.round((t[size / 2 - 1] + t[size / 2]) / 2.0);
        assertEquals(median, instance.getMedian());

        size = 1000001;
        t = new int[size];
        instance = new DataHeap();
        for (int i = 0; i < t.length; i++) {
            t[i] = rand.nextInt(2000001) - 1000000;
            assertEquals(true, instance.offer(t[i]));
        }
        Arrays.sort(t);
        median = t[size / 2];
        assertEquals(median, instance.getMedian());

    }

}
