package edu.utdallas.cs6375.ann.data.enumeratedtypes;

import edu.utdallas.cs6375.ann.data.DataException;

public enum Occupation {
    TECH_SPT("Tech-support", 0),
    CFT_REPAIR("Craft-repair", 1),
    OTHER_SVC("Other-service", 2),
    SALES("Sales", 3),
    EXEC_MGR("Exec-managerial", 4),
    PROF_SPC("Prof-specialty", 5),
    HDL_CLR("Handlers-cleaners", 6),
    MCH_OP_INS("Machine-op-inspct", 7),
    ADM_CLK("Adm-clerical", 8),
    FRM_FSH("Farming-fishing", 9),
    TRANS_MVG("Transport-moving", 10),
    PRV_HS_SVC("Priv-house-serv", 11),
    PRO_SVC("Protective-serv", 12),
    AF("Armed-Forces", 13);

    private final String occupation;
    private final int occupationValue;

    Occupation(String occupation, int occupationValue) {
        this.occupation = occupation;
        this.occupationValue = occupationValue;
    }

    @Override
    public String toString() {
        return String.format("%s(%d)", getOccupation(), getOccupationValue());
    }

    public String getOccupation() {
        return this.occupation;
    }

    public int getOccupationValue() {
        return this.occupationValue;
    }

    public static Occupation getOccupation(String occupation) throws DataException {
        for(Occupation o : Occupation.values()) {
            if(o.getOccupation().equals(occupation)) {
                return o;
            }
        }

        throw new DataException("No occupation called: " + occupation);
    }
}
