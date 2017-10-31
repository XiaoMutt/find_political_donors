package find_political_donors.DataStorageEngine;

import find_political_donors.DataReader.Foir;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Xiao Zhou
 */
public class MedianByDateWorker extends DataStorageAndOutputWorker{
    private final MedianByDateEngine engine;
    private final PrintWriter pw;
    
    public MedianByDateWorker(String outputFileName, BlockingQueue<Foir> foirQ) throws FileNotFoundException{
        super(foirQ);
        engine=new MedianByDateEngine();
        pw=new PrintWriter(outputFileName);
    }

    @Override
    protected void close() {
        Map<String, Map<String, DataHeap>> map=engine.getMap();
        for(String id: map.keySet()){
            Map<String, DataHeap> tmap=map.get(id);
            for(String date: tmap.keySet()){
                DataHeap dh=tmap.get(date);
                pw.printf("%s|%s|%d|%d|%d%n", id, date, dh.getMedian(), dh.getTotalTransactions(), dh.getTotalAmount());
            }
        }
        pw.close();
    }

    @Override
    protected void addFoir(Foir foir) {
        engine.add(foir);
    }
    
    


    
}
