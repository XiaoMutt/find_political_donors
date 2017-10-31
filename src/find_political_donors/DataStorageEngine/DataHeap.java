package find_political_donors.DataStorageEngine;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * This class keeps tracking of
 * <ul>
 * <li> the amount of each contribution
 * <li> the total number of contributions
 * <li> the total amount of contributions
 * <li> the median of contributions using the Heap method
 * </ul>
 * Although the Data Dictionary indicate the TRANSACTION_AMT is NUMBER(14,2),
 * after examining the data files, it is certain that the data files only
 * contain floored integer amount. Therefore Integer and Long are used in this
 * class.
 *
 * @author Xiao Zhou
 */
public class DataHeap {

    private final PriorityQueue<Integer> minHeap;
    private final PriorityQueue<Integer> maxHeap;
    private long totalTransactions;
    private long totalAmount;

    public DataHeap() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        totalTransactions = 0;
        totalAmount = 0;
    }

    /**
     * Add an integer to the {@link DataHeap} using the two-Heap (PriorityQueue)
     * method. Time complexity O(log(n)).
     *
     * @param num the integer needs to be added to the {@link DataHeap}.
     * @return true if the integer is added, or false if not added.
     */
    public boolean offer(int num) {
        boolean res;
        totalAmount += num;
        totalTransactions++;
        //put into maxHeap first.
        res = maxHeap.offer(num);
        res = res && minHeap.offer(maxHeap.poll());

        if (maxHeap.size() < minHeap.size()) {
            res = res && maxHeap.offer(minHeap.poll());
        }
        return res;

    }

    /**
     * Get the median of all the data sorted in the Heap. Time complexity O(1).
     *
     * @return the Median of the data stored in the {@link DataHeap}. If the
     * fractional portion of the argument is greater than 0.5, the argument is
     * rounded to the integer with the next higher absolute value. If it is less
     * than 0.5, the argument is rounded to the integer with the lower absolute
     * value. If the fractional portion is exactly 0.5, the argument is rounded
     * to the next integer in the direction of +∞. For example, 0.5 will be
     * rounded to 1, while -0.5 will be rounded to 0. However, 0.51 will be
     * rounded to -1.
     *
     */
    public int getMedian() {
        if (maxHeap.isEmpty()) {
            return 0;
        }
        if (maxHeap.size() == minHeap.size()) {
            return Math.round((maxHeap.peek() + minHeap.peek()) / 2.0f);
        } else {
            return maxHeap.peek();
        }
    }

    /**
     *
     * @return the total number of transactions, i.e. how many numbers are
     * stored in the {@link DataHeap}.
     */
    public long getTotalTransactions() {
        return totalTransactions;
    }

    /**
     *
     * @return the total amount of transactions, i.e. the sum of all the
     * integers stored in the {@link DataHeap}.
     *
     */
    public long getTotalAmount() {
        return totalAmount;
    }

}
