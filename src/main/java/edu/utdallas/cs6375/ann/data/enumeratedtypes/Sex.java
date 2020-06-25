package edu.utdallas.cs6375.ann.data.enumeratedtypes;

import edu.utdallas.cs6375.ann.data.DataException;

public enum Sex {
    FEMALE("Female", 0),
    MALE("Male", 1);

    private final String sex;
    private final int sexValue;

    Sex(String sex, int sexValue) {
        this.sex = sex;
        this.sexValue = sexValue;
    }

    @Override
    public String toString() {
        return String.format("%s(%d)", getSex(), getSexValue());
    }

    public String getSex() {
        return this.sex;
    }

    public int getSexValue() {
        return this.sexValue;
    }

    public static Sex getSex(String sex) throws DataException {
        for(Sex s : Sex.values()) {
            if(s.getSex().equals(sex)) {
                return s;
            }
        }

        throw new DataException("No sex called: " + sex);
    }
}
