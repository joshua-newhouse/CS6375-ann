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

    private static final NormalizedData[] NORMALIZED_DATA = new NormalizedData[14];

    static {
        NORMALIZED_DATA[AGE_IDX] = new NormalizedData();
        NORMALIZED_DATA[WKC_IDX] = new NormalizedData();
        NORMALIZED_DATA[FLW_IDX] = new NormalizedData();
        NORMALIZED_DATA[EDU_IDX] = new NormalizedData();
        NORMALIZED_DATA[MST_IDX] = new NormalizedData();
        NORMALIZED_DATA[OCC_IDX] = new NormalizedData();
        NORMALIZED_DATA[REL_IDX] = new NormalizedData();
        NORMALIZED_DATA[RAC_IDX] = new NormalizedData();
        NORMALIZED_DATA[SEX_IDX] = new NormalizedData();
        NORMALIZED_DATA[CPG_IDX] = new NormalizedData();
        NORMALIZED_DATA[CPL_IDX] = new NormalizedData();
        NORMALIZED_DATA[HPW_IDX] = new NormalizedData();
        NORMALIZED_DATA[CTY_IDX] = new NormalizedData();
    }

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

        this.updateNormalizedData();
    }

    private void updateNormalizedData() {
        NORMALIZED_DATA[AGE_IDX].add(this.getAge());
        NORMALIZED_DATA[WKC_IDX].add(this.getWorkClass().getWorkclassValue());
        NORMALIZED_DATA[FLW_IDX].add(this.getFnlwgt());
        NORMALIZED_DATA[EDU_IDX].add(this.getEducation().getEducationValue());
        NORMALIZED_DATA[MST_IDX].add(this.getMaritalStatus().getMaritalStatusValue());
        NORMALIZED_DATA[OCC_IDX].add(this.getOccupation().getOccupationValue());
        NORMALIZED_DATA[REL_IDX].add(this.getRelationship().getRelationshipValue());
        NORMALIZED_DATA[RAC_IDX].add(this.getRace().getRaceValue());
        NORMALIZED_DATA[SEX_IDX].add(this.getSex().getSexValue());
        NORMALIZED_DATA[CPG_IDX].add(this.getCapitalGain());
        NORMALIZED_DATA[CPL_IDX].add(this.getCapitalLoss());
        NORMALIZED_DATA[HPW_IDX].add(this.getHoursPerWeek());
        NORMALIZED_DATA[CTY_IDX].add(this.getCountry().getCountryValue());
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

        retVal.add(NORMALIZED_DATA[AGE_IDX].getNormalizedForValue(this.getAge()));
        retVal.add(NORMALIZED_DATA[WKC_IDX].getNormalizedForValue(this.getWorkClass().getWorkclassValue()));
        retVal.add(NORMALIZED_DATA[FLW_IDX].getNormalizedForValue(this.getFnlwgt()));
        retVal.add(NORMALIZED_DATA[EDU_IDX].getNormalizedForValue(this.getEducation().getEducationValue()));
        retVal.add(NORMALIZED_DATA[MST_IDX].getNormalizedForValue(this.getMaritalStatus().getMaritalStatusValue()));
        retVal.add(NORMALIZED_DATA[OCC_IDX].getNormalizedForValue(this.getOccupation().getOccupationValue()));
        retVal.add(NORMALIZED_DATA[REL_IDX].getNormalizedForValue(this.getRelationship().getRelationshipValue()));
        retVal.add(NORMALIZED_DATA[RAC_IDX].getNormalizedForValue(this.getRace().getRaceValue()));
        retVal.add(NORMALIZED_DATA[SEX_IDX].getNormalizedForValue(this.getSex().getSexValue()));
        retVal.add(NORMALIZED_DATA[CPG_IDX].getNormalizedForValue(this.getCapitalGain()));
        retVal.add(NORMALIZED_DATA[CPL_IDX].getNormalizedForValue(this.getCapitalLoss()));
        retVal.add(NORMALIZED_DATA[HPW_IDX].getNormalizedForValue(this.getHoursPerWeek()));
        retVal.add(NORMALIZED_DATA[CTY_IDX].getNormalizedForValue(this.getCountry().getCountryValue()));

        return retVal;
    }

    public double target() {
        return this.isSalaryGreaterThan50() ? 1.0 : 0.0;
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
