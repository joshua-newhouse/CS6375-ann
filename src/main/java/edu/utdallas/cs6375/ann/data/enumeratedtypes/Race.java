package edu.utdallas.cs6375.ann.data.enumeratedtypes;

import edu.utdallas.cs6375.ann.data.DataException;

public enum Race {
    WHITE("White", 0),
    ASN_PAC_ISL("Asian-Pac-Islander", 1),
    AMR_IND_ESK("Amer-Indian-Eskimo", 2),
    OTHER("Other", 3),
    BLACK("Black", 4);

    private final String race;
    private final int raceValue;

    Race(String race, int raceValue) {
        this.race = race;
        this.raceValue = raceValue;
    }

    @Override
    public String toString() {
        return String.format("%s(%d)", getRace(), getRaceValue());
    }

    public String getRace() {
        return this.race;
    }

    public int getRaceValue() {
        return this.raceValue;
    }

    public static Race getRace(String race) throws DataException {
        for(Race r : Race.values()) {
            if(r.getRace().equals(race)) {
                return r;
            }
        }

        throw new DataException("No race called: " + race);
    }
}
