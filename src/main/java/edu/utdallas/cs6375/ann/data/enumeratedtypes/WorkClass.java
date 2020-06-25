package edu.utdallas.cs6375.ann.data.enumeratedtypes;

import edu.utdallas.cs6375.ann.data.DataException;

public enum WorkClass {
    PRIVATE("Private", 0),
    SLF_EMP_NOT_INC("Self-emp-not-inc", 1),
    SLF_EMP_INC("Self-emp-inc", 2),
    FED_GOV("Federal-gov", 3),
    LOC_GOV("Local-gov", 4),
    STA_GOV("State-gov", 5),
    WO_PAY("Without-pay", 6),
    NEVER("Never-worked", 7);


    private final String workclass;
    private final int workclassValue;

    WorkClass(String workclass, int workclassValue) {
        this.workclass = workclass;
        this.workclassValue = workclassValue;
    }

    @Override
    public String toString() {
        return String.format("%s(%d)", getWorkClass(), getWorkclassValue());
    }

    public String getWorkClass() {
        return this.workclass;
    }

    public int getWorkclassValue() {
        return this.workclassValue;
    }

    public static WorkClass getWorkClass(String workclass) throws DataException {
        for(WorkClass wc : WorkClass.values()) {
            if(wc.getWorkClass().equals(workclass)) {
                return wc;
            }
        }

        throw new DataException("No workclass called: " + workclass);
    }
}
