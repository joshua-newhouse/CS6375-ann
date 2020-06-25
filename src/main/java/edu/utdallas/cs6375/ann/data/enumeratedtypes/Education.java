package edu.utdallas.cs6375.ann.data.enumeratedtypes;

import edu.utdallas.cs6375.ann.data.DataException;

public enum Education {
    BACHELORS("Bachelors", 0),
    SOME_COLLEGE("Some-college", 1),
    GRD_11("11th", 2),
    HIGH_SCHOOL("HS-grad", 3),
    PROFESSIONAL_SCHOOL("Prof-school", 4),
    ASSOC_ACDM("Assoc-acdm", 5),
    ASSOC_VOC("Assoc-voc", 6),
    GRD_9("9th", 7),
    GRD_7_8("7th-8th", 8),
    GRD_12("12th", 9),
    MASTERS("Masters", 10),
    GRD_1_4("1st-4th", 11),
    GRD_10("10th", 12),
    DOCTORATE("Doctorate", 13),
    GRD_5_6("5th-6th", 14),
    PRESCHOOL("Preschool", 15);

    private final String education;
    private final int educationValue;

    Education(String education, int educationValue) {
        this.education = education;
        this.educationValue = educationValue;
    }

    @Override
    public String toString() {
        return String.format("%s(%d)", getEducation(), getEducationValue());
    }

    public String getEducation() {
        return this.education;
    }

    public int getEducationValue() {
        return this.educationValue;
    }

    public static Education getEducation(String education) throws DataException {
        for(Education ed : Education.values()) {
            if(ed.getEducation().equals(education)) {
                return ed;
            }
        }

        throw new DataException("No education called: " + education);
    }
}
