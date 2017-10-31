package find_political_donors.DataReader;

/**
 * Defines methods that handling the fields of interest read out from a donation
 * record file.
 *
 * @author Xiao Zhou
 */
public interface FieldsOfInterestRecord {

    /**
     * Determine whether an implementing object of this interface contains
     * fields of data that valid for further analysis.
     *
     * @return true if it is of interest, or false if not.
     */
    public boolean isValid();
}
