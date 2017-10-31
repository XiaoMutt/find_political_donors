package find_political_donors.DataReader;

/**
 * This class define the datatype of a Fields-Of-Interest Record (FOIR)
 *
 * @author Xiao Zhou
 */
public class Foir implements FieldsOfInterestRecord{

    public final String CMTE_ID;
    public final String ZIP_CODE;
    public final String TRANSACTION_DT;
    public final String TRANSACTION_AMT;
    public final String OTHER_ID;

    public Foir(String[] fields) {
        if (fields != null && fields.length == DonationRecordSchema.values().length) {
            CMTE_ID = fields[DonationRecordSchema.CMTE_ID.ordinal()];

            //trimming zipcode;
            String zip = fields[DonationRecordSchema.ZIP_CODE.ordinal()];
            if (zip.matches("^(\\d{5}|\\d{9})$")) {//a zip code is consider valid only when it contains exactly 5 digits or 9 digits.
                ZIP_CODE = zip.substring(0, 5);
            } else {
                ZIP_CODE = "";
            }

            TRANSACTION_DT = fields[DonationRecordSchema.TRANSACTION_DT.ordinal()];
            TRANSACTION_AMT = fields[DonationRecordSchema.TRANSACTION_AMT.ordinal()];
            OTHER_ID = fields[DonationRecordSchema.OTHER_ID.ordinal()];
        } else {
            CMTE_ID = "";
            ZIP_CODE = "";
            TRANSACTION_DT = "";
            TRANSACTION_AMT = "";
            OTHER_ID = "";
        }
    }

    /**
     * Check if the Foir record is valid.
     *
     * @return if the Foir is valid for futher analysis.
     */
    @Override
    public boolean isValid() {
        return OTHER_ID.isEmpty()
                && !CMTE_ID.isEmpty()
                && !TRANSACTION_AMT.isEmpty();
    }
}
