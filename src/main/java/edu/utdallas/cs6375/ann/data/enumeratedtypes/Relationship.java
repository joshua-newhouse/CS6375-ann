package edu.utdallas.cs6375.ann.data.enumeratedtypes;

import edu.utdallas.cs6375.ann.data.DataException;

public enum Relationship {
    WIFE("Wife", 0),
    OWN_CHLD("Own-child", 1),
    HUSBAND("Husband", 2),
    NOT_IN_FAM("Not-in-family", 3),
    OTHER_REL("Other-relative", 4),
    UNMARRIED("Unmarried", 5);

    private final String relationship;
    private final int relationshipValue;

    Relationship(String relationship, int relationshipValue) {
        this.relationship = relationship;
        this.relationshipValue = relationshipValue;
    }

    @Override
    public String toString() {
        return String.format("%s(%d)", getRelationship(), getRelationshipValue());
    }

    public String getRelationship() {
        return this.relationship;
    }

    public int getRelationshipValue() {
        return this.relationshipValue;
    }

    public static Relationship getRelationship(String relationship) throws DataException {
        for(Relationship r : Relationship.values()) {
            if(r.getRelationship().equals(relationship)) {
                return r;
            }
        }

        throw new DataException("No relationship called: " + relationship);
    }
}
