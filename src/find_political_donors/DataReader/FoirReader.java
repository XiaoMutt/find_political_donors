package find_political_donors.DataReader;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class read out the DonationFile line by line.
 *
 * @author Xiao Zhou
 */
public class FoirReader extends DonationFileReader {

    private final int DonationRecordSchemaLength;

    public FoirReader(String fileName) throws FileNotFoundException {
        super(fileName);
        DonationRecordSchemaLength = DonationRecordSchema.values().length;
    }

    /**
     *
     * @param regex the regex used to split a line of text to extract each
     * field.
     * @return the {@link Foir} object containing the data, or null if the EOF
     * is reached. The {@link Foir} object will always be valid according to the
     * implementation of {@link FieldsOfInterestRecord#isValid()}
     * @throws IOException if there is any IO error.
     */
    public Foir nextFoir(String regex) throws IOException {
        if (regex == null || regex.isEmpty()) {
            return null;
        }
        String[] data;
        while ((data = readNextRecord(regex)) != null) {
            if (data.length == DonationRecordSchemaLength) {
                Foir res = new Foir(data);
                if (res.isValid()) {
                    return res;
                }
            }
        }
        return null;
    }

    /**
     *
     * @return get the next {@link Foir} object from the file using the default
     * split character '|'.
     * @throws IOException if there is any IO error.
     */
    public Foir nextFoir() throws IOException {
        return nextFoir("\\|");
    }

}
