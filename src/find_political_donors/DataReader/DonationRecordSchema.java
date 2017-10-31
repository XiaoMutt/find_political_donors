package find_political_donors.DataReader;

/**
 * This enum define the Schema of a DonationRecord It was copied from:
 * http://classic.fec.gov/finance/disclosure/metadata/DataDictionaryContributionsbyIndividuals.shtml
 *
 * Although the TRANSACTION_AMT is defined as NUMBER (14,2), after examining the
 * online text files, it is certainly an integer.
 *
 * @author Xiao Zhou
 */
public enum DonationRecordSchema {
    CMTE_ID("Filer Identification Number", "String"),
    AMNDT_IND("Amendment Indicator", "String"),
    RPT_TP("Report Type", "String"),
    TRANSACTION_PGI("Primary-General Indicator", "String"),
    IMAGE_NUM("Image Number", "String"),
    TRANSACTION_TP("Transaction Type", "String"),
    ENTITY_TP("Entity Type", "String"),
    NAME("Contributor/Lender/Transfer Name", "String"),
    CITY("City/Town", "String"),
    STATE("State", "String"),
    ZIP_CODE("Zip Code", "String"),
    EMPLOYER("Employer", "String"),
    OCCUPATION("Occupation", "String"),
    TRANSACTION_DT("Transaction Date(MMDDYYYY)", "String"),
    TRANSACTION_AMT("Transaction Amount", "int"),
    OTHER_ID("Other Identification Number", "String"),
    TRAN_ID("Transaction ID", "String"),
    FILE_NUM("File Number / Report ID", "String"),
    MEMO_CD("Memo Code", "String"),
    MEMO_TEXT("Memo Text", "String"),
    SUB_ID("FEC Record Number", "String");

    private final String description;
    private final String dataType;

    DonationRecordSchema(String description, String dataType) {
        this.description = description;
        this.dataType = dataType;
    }

    /**
     * 
     * @return the description of this property.
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * 
     * @return the data type of this property.
     */

    public String getDataType() {
        return this.dataType;
    }
}
