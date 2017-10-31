package find_political_donors.DataStorageEngine;

import find_political_donors.DataReader.Foir;
import java.util.HashMap;
import java.util.Map;

/**
 * This class add the data to the data structure of ID-ZIP-DataHeap using
 * HashMap.
 *
 * @author Xiao Zhou
 */
public class MedianByZipEngine implements DataStorageEngine{
    private final Map<String, Map<String, DataHeap>> map;

    public MedianByZipEngine(){
        map=new HashMap<>();
    }

    @Override
    public boolean isValid(Foir foir) {
        return (foir != null
                && foir.ZIP_CODE.matches("^\\d{5}$"));
    }
    /**
     * Insert the data from a foir record into a ID-ZIP-DataHeap data
     * structure. The foir record will first be examined for a valid ZIP code.
     * If the ZIP code is invalid, the data will not be inserted and the
     * method will return null.
     *
     * @param foir a Foir object.
     * @return the DataHeap that the foir data is inserted into, or null if the
     * foir record does not contain a valid ZIP code.
     */
    @Override
    public DataHeap add(Foir foir) {
        if (!isValid(foir)) {
            return null;
        }

        Map<String, DataHeap> tmap;
        if (map.containsKey(foir.CMTE_ID)) {
            tmap = map.get(foir.CMTE_ID);

        } else {
            tmap = new HashMap<>();
            map.put(foir.CMTE_ID, tmap);

        }
        DataHeap dheap;
        if (tmap.containsKey(foir.ZIP_CODE)) {
            dheap = tmap.get(foir.ZIP_CODE);
        } else {
            dheap = new DataHeap();
            tmap.put(foir.ZIP_CODE, dheap);
        }
        dheap.offer(Integer.valueOf(foir.TRANSACTION_AMT));
        return dheap;
    }

    @Override
    public Map<String, Map<String, DataHeap>> getMap() {
        return map;
    }
}
