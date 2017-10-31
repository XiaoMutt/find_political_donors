package find_political_donors.DataStorageEngine;

import find_political_donors.DataReader.Foir;
import java.util.concurrent.BlockingQueue;

/**
 * This is an abstract class that defines a thread for storing data and output
 * to files. The data will be read from a {@link BlockingQueue}.
 *
 * @author Xiao Zhou
 */
public abstract class DataStorageAndOutputWorker implements Runnable {

    private final BlockingQueue<Foir> inputQ;

    /**
     * close the output file.
     */
    protected abstract void close();

    /**
     * Add data from a {@link Foir} object to data storage.
     *
     * @param foir a {@link Foir} object that need to be added to the data
     * engine.
     */
    protected abstract void addFoir(Foir foir);

    DataStorageAndOutputWorker(BlockingQueue<Foir> foirQ) {
        inputQ = foirQ;
    }

    @Override
    public void run() {
        try {
            Foir foir;
            while ((foir = inputQ.take()).isValid()) {
                addFoir(foir);
            }
            close();
        } catch (InterruptedException ex) {
            close();
        }
    }

}
