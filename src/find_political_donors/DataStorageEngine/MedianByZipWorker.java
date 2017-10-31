package find_political_donors.DataStorageEngine;

import find_political_donors.DataReader.Foir;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Xiao Zhou
 */
public class MedianByZipWorker extends DataStorageAndOutputWorker {

    private final MedianByZipEngine engine;
    private final PrintWriter pw;
    public MedianByZipWorker(String outputFileName, BlockingQueue<Foir> foirQ) throws FileNotFoundException {
        super(foirQ);
        engine = new MedianByZipEngine();
        pw = new PrintWriter(outputFileName);
    }

    @Override
    protected void close() {
        pw.close();
    }

    @Override
    protected void addFoir(Foir foir) {
        DataHeap dh = engine.add(foir);
        if (dh != null) {
            pw.printf("%s|%s|%d|%d|%d%n",
                    foir.CMTE_ID, foir.ZIP_CODE, dh.getMedian(), dh.getTotalTransactions(), dh.getTotalAmount());
        }
    }

}
