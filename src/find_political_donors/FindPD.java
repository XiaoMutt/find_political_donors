/**
 *
 */
package find_political_donors;

import find_political_donors.DataReader.Foir;
import find_political_donors.DataReader.FoirReader;
import find_political_donors.DataStorageEngine.MedianByDateWorker;
import find_political_donors.DataStorageEngine.MedianByZipWorker;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Xiao Zhou
 */
public class FindPD {

    private final FoirReader reader;
    private final PrintWriter logPw;
    private final String mzhOutputFileName;
    private final String mdhOutputFileName;
    private final String pwd;//present working folder

    public FindPD(String inputFileName) throws IOException {
        File file = (new File(inputFileName)).getCanonicalFile();
        File parentFile = file.getParentFile().getCanonicalFile();
        String workingDir = null;
        if (parentFile != null) {
            workingDir = parentFile.getParent();
        }
        if (workingDir == null) {
            workingDir = file.getParent();
        }
        pwd = workingDir + File.separator + "output";

        file = new File(pwd);
        if (!file.exists()) {
            file.mkdir();
        }
        mzhOutputFileName = pwd + File.separator + "medianvals_by_zip.txt";
        mdhOutputFileName = pwd + File.separator + "medianvals_by_date.txt";
        logPw = new PrintWriter(pwd + File.separator + "log.txt");
        reader = new FoirReader(inputFileName);
        log("Processing: " + inputFileName);
    }

    public FindPD(String inputFileName, String outputFolder) throws IOException {
        pwd = outputFolder;

        File file = new File(pwd);
        if (!file.exists()) {
            file.mkdir();
        }
        mzhOutputFileName = pwd + File.separator + "medianvals_by_zip.txt";
        mdhOutputFileName = pwd + File.separator + "medianvals_by_date.txt";
        logPw = new PrintWriter(pwd + File.separator + "log.txt");
        reader = new FoirReader(inputFileName);
        log("Processing: " + inputFileName);
    }

    private void log(String msg) {
        System.out.println(msg);
        logPw.println(msg);
    }

    private void drawProgressBar(int n) {
        int width = 20;
        int progress = n * width / 100;
        StringBuilder sb = new StringBuilder();
        sb.append('|');
        for (int i = 0; i < progress; i++) {
            sb.append('=');
        }
        for (int i = progress; i < width; i++) {
            sb.append(' ');
        }
        sb.append('>');
        sb.append(n);
        sb.append('%');
        if (n == 100) {
            sb.append('\n');
        } else {
            sb.append('\r');
        }
        System.out.print(sb.toString());

    }

    public boolean process() {
        try {
            int pos = -1;
            Foir foir;
            BlockingQueue<Foir> mzhQ = new LinkedBlockingQueue<>();
            BlockingQueue<Foir> mdhQ = new LinkedBlockingQueue<>();
            Thread mzh = new Thread(new MedianByZipWorker(mzhOutputFileName, mzhQ));
            Thread mdh = new Thread(new MedianByDateWorker(mdhOutputFileName, mdhQ));
            mzh.start();
            mdh.start();
            while ((foir = reader.nextFoir()) != null) {
                mzhQ.put(foir);
                mdhQ.put(foir);
                if (pos != reader.getProgress()) {
                    pos = reader.getProgress();
                    drawProgressBar(pos);
                }
            }

            //close input file.
            reader.close();
            //send a invalid Foir object to BlockingQueue to signal the end of processing.
            foir = new Foir(null);
            mzhQ.put(foir);
            mdhQ.put(foir);

            //waiting for threads to finish.
            mzh.join();
            mdh.join();

        } catch (IOException | InterruptedException ex) {
            log("");
            log(ex.getMessage());
            return false;
        }

        log("Done. Files saved in " + pwd);
        logPw.close();
        return true;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println();
        if (args.length == 1 || args.length == 2) {
            long startTime = System.currentTimeMillis();
            try {
                FindPD fpd;
                if (args.length == 1) {
                    fpd = new FindPD(args[0]);
                } else {
                    fpd = new FindPD(args[0], args[1]);
                }
                if (!fpd.process()) {
                    System.out.println("Error occured while processing data.");
                    System.out.println("See log.txt file for details.");
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            System.out.printf("Total time spent: %d ms%n", duration);
        } else {
            System.out.println("Please use the following format:\n"
                    + "\njava  -jar findpd.jar input_file\n"
                    + "-The result files will be saved in the ..\\output\\ folder. Or\n"
                    + "\njava -jar findpd.jar input_file output_folder\n"
                    + "-The result files will be saved in the indicated folder.\n");
        }

    }

}
