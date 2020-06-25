package edu.utdallas.cs6375.ann.data.enumeratedtypes;

import edu.utdallas.cs6375.ann.data.DataException;

public enum Country {
    US("United-States", 0),
    CAMBODIA("Cambodia", 1),
    ENGLAND("England", 2),
    PUERTO_RICO("Puerto-Rico", 3),
    CANADA("Canada", 4),
    GERMANY("Germany", 5),
    OUTLYING_US("Outlying-US(Guam-USVI-etc)", 6),
    INDIA("India", 7),
    JAPAN("Japan", 8),
    GREECE("Greece", 9),
    SOUTH("South", 10),
    CHINA("China", 11),
    IRAN("Iran", 12),
    HONDURAS("Honduras", 13),
    PHILIPPINES("Philippines", 14),
    ITALY("Italy", 15),
    POLAND("Poland", 16),
    JAMAICA("Jamaica", 17),
    VIETNAM("Vietnam", 18),
    MEXICO("Mexico", 19),
    PORTUGAL("Portugal", 20),
    IRELAND("Ireland", 21),
    FRANCE("France", 22),
    DOM_REP("Dominican-Republic", 23),
    LAOS("Laos", 24),
    ECUADOR("Ecuador", 25),
    TAIWAN("Taiwan", 26),
    HAITI("Haiti", 27),
    COLUMBIA("Columbia", 28),
    HUNGARY("Hungary", 29),
    GUATEMALA("Guatemala", 30),
    NICARAGUA("Nicaragua", 31),
    SCOTLAND("Scotland", 32),
    THAILAND("Thailand", 33),
    YUGOSLAVIA("Yugoslavia", 34),
    EL_SALVADOR("El-Salvador", 35),
    TRINIDAD_TOBAGO("Trinadad&Tobago", 36),
    PERU("Peru", 37),
    HONG("Hong", 38),
    HOLAND_NETHERLANDS("Holand-Netherlands", 39),
    CUBA("Cuba", 40);

    private final String country;
    private final int countryValue;

    Country(String country, int countryValue) {
        this.country = country;
        this.countryValue = countryValue;
    }

    @Override
    public String toString() {
        return String.format("%s(%d)", getCountry(), getCountryValue());
    }

    public String getCountry() {
        return this.country;
    }

    public int getCountryValue() {
        return this.countryValue;
    }

    public static Country getCountry(String country) throws DataException {
        for(Country c : Country.values()) {
            if(c.getCountry().equals(country)) {
                return c;
            }
        }

        throw new DataException("No country called: " + country);
    }


}
