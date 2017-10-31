
package find_political_donors.DataStorageEngine;

import find_political_donors.DataReader.Foir;
import java.util.Map;
import java.util.TreeMap;


/**
 * This class add the data to the data structure of ID-Date-{@link DataHeap} using
 * TreeMap.
 * @author Xiao Zhou
 */
public class MedianByDateEngine implements DataStorageEngine{
    private final Map<String, Map<String, DataHeap>> map;
    
    public MedianByDateEngine(){
        map=new TreeMap<>();
    }

    @Override
    public Map<String, Map<String, DataHeap>> getMap(){
        return map;
    }

    @Override
    public boolean isValid(Foir foir) {
        return (foir!=null&&
                foir.TRANSACTION_DT.matches("^(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])(19|20)\\d\\d$"));
    }
    
    /**
     * Insert the data from a {@link Foir} record into a ID-DATE-{@link DataHeap} data
     * structure. The {@link Foir} record will first be examined for a valid date.
     * If the date is invalid, the data will not be inserted and the
     * method will return null.
     *
     * @param foir a {@link Foir} object.
     * @return the DataHeap that the {@link Foir} data is inserted into, or null if the
     * {@link Foir} record does not contain a valid date.
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
            tmap = new TreeMap<>();
            map.put(foir.CMTE_ID, tmap);

        }
        DataHeap dheap;
        if (tmap.containsKey(foir.TRANSACTION_DT)) {
            dheap = tmap.get(foir.TRANSACTION_DT);
        } else {
            dheap = new DataHeap();
            tmap.put(foir.TRANSACTION_DT, dheap);
        }
        dheap.offer(Integer.valueOf(foir.TRANSACTION_AMT));
        return dheap;
    }
}
