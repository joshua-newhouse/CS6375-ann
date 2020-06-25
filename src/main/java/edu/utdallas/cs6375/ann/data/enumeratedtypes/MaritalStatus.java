package edu.utdallas.cs6375.ann.data.enumeratedtypes;

import edu.utdallas.cs6375.ann.data.DataException;

public enum MaritalStatus {
    MARRIED_CIV_SP("Married-civ-spouse", 0),
    DIVORCED("Divorced", 1),
    NEV_MRD("Never-married", 2),
    SEPARATED("Separated", 3),
    WIDOWED("Widowed", 4),
    MARRIED_SP_ABS("Married-spouse-absent", 5),
    MARRIED_AF_SP("Married-AF-spouse", 6);

    private final String maritalStatus;
    private final int maritalStatusValue;

    MaritalStatus(String education, int maritalStatusValue) {
        this.maritalStatus = education;
        this.maritalStatusValue = maritalStatusValue;
    }

    @Override
    public String toString() {
        return String.format("%s(%d)", getMaritalStatus(), getMaritalStatusValue());
    }

    public String getMaritalStatus() {
        return this.maritalStatus;
    }

    public int getMaritalStatusValue() {
        return this.maritalStatusValue;
    }

    public static MaritalStatus getMaritalStatus(String maritalStatus) throws DataException {
        for(MaritalStatus ms : MaritalStatus.values()) {
            if(ms.getMaritalStatus().equals(maritalStatus)) {
                return ms;
            }
        }

        throw new DataException("No marital status called: " + maritalStatus);
    }
}
