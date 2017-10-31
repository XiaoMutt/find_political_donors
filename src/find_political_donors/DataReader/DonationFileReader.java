package find_political_donors.DataReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Xiao Zhou
 */
public class DonationFileReader {

    private final BufferedReader reader;
    private final long fileLength;
    private long currentPosition;

    protected DonationFileReader(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        reader = new BufferedReader(new FileReader(file));
        fileLength = file.length();
        currentPosition = 0;
    }

    /**
     * Read a line from the file, and split into an array of Strings according
     * to the regex (i.e. the regex defines the separators).
     *
     * @param regex an regular expression that defines rules of the separators.
     * @return the array of Strings.
     * @throws IOException if there is an IO error while reading.
     */
    protected String[] readNextRecord(String regex) throws IOException {
        String strLine = reader.readLine();
        if (strLine != null) {
            currentPosition += strLine.length() + 1;
            return strLine.split(regex,-1);
        } else {
            return null;
        }

    }

    /**
     *
     * @return the integer value of the 100*percentage of the current progress.
     */
    public int getProgress() {
        return (int) (currentPosition * 100 / fileLength);
    }

    /**
     * Close the reading file.
     *
     * @throws IOException if there is an IO error while closing the file.
     */
    public void close() throws IOException {
        reader.close();
    }

}
