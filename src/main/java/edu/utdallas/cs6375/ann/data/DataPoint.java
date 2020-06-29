package edu.utdallas.cs6375.ann.data;

import edu.utdallas.cs6375.ann.data.enumeratedtypes.*;

import java.util.ArrayList;
import java.util.List;

public class DataPoint {
    private static final int AGE_IDX = 0;
    private static final int WKC_IDX = 1;
    private static final int FLW_IDX = 2;
    private static final int EDU_IDX = 3;
    private static final int MST_IDX = 5;
    private static final int OCC_IDX = 6;
    private static final int REL_IDX = 7;
    private static final int RAC_IDX = 8;
    private static final int SEX_IDX = 9;
    private static final int CPG_IDX = 10;
    private static final int CPL_IDX = 11;
    private static final int HPW_IDX = 12;
    private static final int CTY_IDX = 13;
    private static final int SAL_IDX = 14;
    private static final String GT_THN_50 = ">50K";

    private final int age;
    private final WorkClass workClass;
    private final int fnlwgt;
    private final Education education;
    private final MaritalStatus maritalStatus;
    private final Occupation occupation;
    private final Relationship relationship;
    private final Race race;
    private final Sex sex;
    private final int capitalGain;
    private final int capitalLoss;
    private final int hoursPerWeek;
    private final Country country;
    private final boolean salaryGreaterThan50;

    public DataPoint(String[] rawData) throws DataException {
        try {
            this.age = Integer.parseInt(rawData[AGE_IDX].trim());
            this.workClass = WorkClass.getWorkClass(rawData[WKC_IDX].trim());
            this.fnlwgt = Integer.parseInt(rawData[FLW_IDX].trim());
            this.education = Education.getEducation(rawData[EDU_IDX].trim());
            this.maritalStatus = MaritalStatus.getMaritalStatus(rawData[MST_IDX].trim());
            this.occupation = Occupation.getOccupation(rawData[OCC_IDX].trim());
            this.relationship = Relationship.getRelationship(rawData[REL_IDX].trim());
            this.race = Race.getRace(rawData[RAC_IDX].trim());
            this.sex = Sex.getSex(rawData[SEX_IDX].trim());
            this.capitalGain = Integer.parseInt(rawData[CPG_IDX].trim());
            this.capitalLoss = Integer.parseInt(rawData[CPL_IDX].trim());
            this.hoursPerWeek = Integer.parseInt(rawData[HPW_IDX].trim());
            this.country = Country.getCountry(rawData[CTY_IDX].trim());
            this.salaryGreaterThan50 = rawData[SAL_IDX].trim().equals(GT_THN_50);

        } catch(NumberFormatException e) {
            throw new DataException(e);
        }
    }

    public int getAge() {
        return age;
    }

    public WorkClass getWorkClass() {
        return workClass;
    }

    public int getFnlwgt() {
        return fnlwgt;
    }

    public Education getEducation() {
        return education;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public Race getRace() {
        return race;
    }

    public Sex getSex() {
        return sex;
    }

    public int getCapitalGain() {
        return capitalGain;
    }

    public int getCapitalLoss() {
        return capitalLoss;
    }

    public int getHoursPerWeek() {
        return hoursPerWeek;
    }

    public Country getCountry() {
        return country;
    }

    public boolean isSalaryGreaterThan50() {
        return salaryGreaterThan50;
    }

    public List<Double> asInputsList() {
        ArrayList<Double> retVal = new ArrayList<>();

        retVal.add((double) this.getAge());
        retVal.add((double) this.getWorkClass().getWorkclassValue());
        retVal.add((double) this.getFnlwgt());
        retVal.add((double) this.getEducation().getEducationValue());
        retVal.add((double) this.getMaritalStatus().getMaritalStatusValue());
        retVal.add((double) this.getOccupation().getOccupationValue());
        retVal.add((double) this.getRelationship().getRelationshipValue());
        retVal.add((double) this.getRace().getRaceValue());
        retVal.add((double) this.getSex().getSexValue());
        retVal.add((double) this.getCapitalGain());
        retVal.add((double) this.getCapitalLoss());
        retVal.add((double) this.getHoursPerWeek());
        retVal.add((double) this.getCountry().getCountryValue());

        return retVal;
    }

    public double target() {
        return this.isSalaryGreaterThan50() ? 1.0 : -1.0;
    }

    @Override
    public String toString() {
        return "Age: " + this.getAge()
                + " :: Workclass: " + this.getWorkClass().toString()
                + " :: Final Weight: " + this.getFnlwgt()
                + " :: Education: " + this.getEducation().toString()
                + " :: Marital Status: " + this.getMaritalStatus().toString()
                + " :: Occupation: " + this.getOccupation().toString()
                + " :: Relationship: " + this.getRelationship().toString()
                + " :: Race: " + this.getRace().toString()
                + " :: Sex: " + this.getSex().toString()
                + " :: Capital Gain: " + this.getCapitalGain()
                + " :: Capital Loss: " + this.getCapitalLoss()
                + " :: Hours per week: " + this.getHoursPerWeek()
                + " :: Country: " + this.getCountry().toString()
                + " :: Salary > 50K? " + this.isSalaryGreaterThan50();
    }
}
