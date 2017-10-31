package find_political_donors;

import find_political_donors.DataReader.FoirReader;
import find_political_donors.DataReader.Foir;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test Donation File Reader.
 *
 * @author Xiao Zhou
 */
public class DonationFileReaderTest {

    public DonationFileReaderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws FileNotFoundException {
        String text
                = "C00384529|N|Q3|P|201710130200292263|15|IND|BALDINO, MEGAN L|ANCHORAGE|AK|995167541|GCI COMMUNICATIONS|SENIOR DIRECTOR, EXTERNAL AFFAIRS|09222017|17||SA10171722631|1187824||IN-KIND:PRINTING|2101720171458474444\n"
                + "C00570994|N|Q3|P|201710130200292265|15|IND|GEHAN, SHAUN M|WASHINGTON|DC|200075100|LAW OFFICE OF SHAUN M. GEHAN|PRINCIPAL|09292017|250||SA10171722658|1187824||CONTRIBUTION|2101720171458474451\n"
                + "C00570994|N|Q3|P|201710130200292266|15|IND|GILMAN, BRADLEY D|VIENNA|VA|221805896|HOFFMAN SILVER GILMAN & BIASCO|ATTORNEY|09302017|125||SA101717226611|1187824||CONTRIBUTION|2101720171458474454\n"
                + "C00570994|N|Q3|P|201710130200292267|15|IND|GILMAN, LISA C|VIENNA|VA|221805896|HOMEMAKER|HOMEMAKER|09302017|125||SA101717226713|1187824||CONTRIBUTION|2101720171458474456\n"
                + "C00570994|N|Q3|P|201710130200292267|15|IND|GROSS, DIETRICH M|WILMETTE|IL|600911956|JUPITER OXYGEN|CEO|09292017|2500||SA101717226714|1187824||CONTRIBUTION|2101720171458474457\n"
                + "C00384529|N|Q3|P|201710130200292311|15|IND|BAKER, GEORGE|BETHESDA|MD|208162438|WILLIAMS & JENSEN,PLLC|ATTORNEY|09282017|500||SA10171723112|1187893||CONTRIBUTION|2101720171458476283\n"
                + "C00384529|N|Q3|P|201710130200292311|15|IND|BOCKORNY, DAVID|SIOUX FALLS|SD|571034669|BERGNER BOCKORNY|PARTNER|09262017|1000||SA10171723113|1187893||CONTRIBUTION|2101720171458476284\n"
                + "C00384529|N|Q3|P|201710130200292318|15|IND|KNIGHT, PATRICIA|ARLINGTON|VA|222012312|KNIGHT CAPITOL CONSULTANTS|CONSULTANT|09262017|500||SA101717231823|1187893||CONTRIBUTION|2101720171458476304";

        try (PrintWriter pw = new PrintWriter("test.txt")) {
            pw.print(text);
            pw.close();
        }

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getProgress method, of class DonationFileReader.
     *
     * @throws java.io.FileNotFoundException
     */
    @Test
    public void testGetProgress() throws FileNotFoundException, IOException {
        System.out.println("getProgress");
        FoirReader instance = new FoirReader("test.txt");
        assertEquals(0, instance.getProgress());
        instance.nextFoir();
        int result = instance.getProgress();
        assertEquals(true, result > 0 && result < 100);
        instance.close();

    }

    /**
     * Test of nextFoir method, of class DonationFileReader.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testNextFoir_String() throws Exception {
        System.out.println("nextFoir");
        FoirReader instance = new FoirReader("test.txt");
        Foir result = instance.nextFoir(",");
        assertEquals(null, result);
        instance = new FoirReader("test.txt");
        result = instance.nextFoir("\\|");
        assertEquals("C00384529", result.CMTE_ID);
        instance.close();

    }

    /**
     * Test of nextFoir method, of class DonationFileReader.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testNextFoir_0args() throws Exception {
        System.out.println("nextFoir");
        FoirReader instance = new FoirReader("test.txt");
        Foir result = instance.nextFoir();
        assertEquals("C00384529", result.CMTE_ID);
        result = instance.nextFoir();
        assertEquals("C00570994", result.CMTE_ID);
        instance.close();
    }

}
