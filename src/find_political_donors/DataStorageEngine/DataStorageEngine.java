package find_political_donors.DataStorageEngine;

import find_political_donors.DataReader.Foir;
import java.util.Map;

/**
 * Defined the DataStorageEngine interface. A {@link DataStorageEngine} will use
 * a mapped data structure, i.e. REF1-REF2-{@link DataHeap} to store data.
 * {@link DataStorageEngine} be wrapped in a {@link DataStorageAndOutputWorker}
 * implementing classes to output into a file.
 *
 * @author Xiao Zhou
 */
public interface DataStorageEngine {

    /**
     * Add a {@link Foir} object to the data storage.
     *
     * @param foir the {@link Foir} object needs to be stored.
     * @return the {@link DataHeap} that the {@link Foir} object has been added
     * to, or null if {@link #isValid(find_political_donors.DataReader.Foir)}
     * returns false (in this case, the {@link Foir} object is not added to the
     * DataStorage}.
     */
    public DataHeap add(Foir foir);

    /**
     * Check if the {@link Foir} object fulfill the requirements of put into a
     * DataStorageEngine.
     *
     * @param foir the {@link Foir} object that need to be checked.
     * @return true if the {@link Foir} object pass the check, or false if it
     * does not.
     */
    public boolean isValid(Foir foir);
    
    /**
     * Get the Map of the DataStorageEngine.
     * @return the Map object of the DataStorageEngine.
     */
    public Map<String, Map<String, DataHeap>> getMap();
    
}
