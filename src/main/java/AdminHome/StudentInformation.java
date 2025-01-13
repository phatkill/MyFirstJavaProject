package AdminHome;

import java.util.Date;

public class StudentInformation {
    private String id, surName, firstName, gender,placeOfResidence, address, phone, email;
    private double mathScore, literatureScore,physicsOrHistoryScore,chemistryOrGeographyScore,biologyOrCivicScore;
    private Date dob;
    public StudentInformation(String id, String surName, String firstName, Date dob,
                              String gender, String placeOfResidence, String address,
                              String phone, String email, double mathScore,
                              double literatureScore, double physicsOrHistoryScore,
                              double chemistryOrGeographyScore, double biologyOrCivicScore) {
        this.id = id;
        this.surName = surName;
        this.firstName = firstName;
        this.dob = dob;
        this.gender = gender;
        this.placeOfResidence = placeOfResidence;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.mathScore = mathScore;
        this.literatureScore = literatureScore;
        this.physicsOrHistoryScore = physicsOrHistoryScore;
        this.chemistryOrGeographyScore = chemistryOrGeographyScore;
        this.biologyOrCivicScore = biologyOrCivicScore;
    }

    // Getter and Setter methods (optional)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPlaceOfResidence() {
        return placeOfResidence;
    }

    public void setPlaceOfResidence(String placeOfResidence) {
        this.placeOfResidence = placeOfResidence;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getMathScore() {
        return mathScore;
    }

    public void setMathScore(double mathScore) {
        this.mathScore = mathScore;
    }

    public double getLiteratureScore() {
        return literatureScore;
    }

    public void setLiteratureScore(double literatureScore) {
        this.literatureScore = literatureScore;
    }

    public double getPhysicsOrHistoryScore() {
        return physicsOrHistoryScore;
    }

    public void setPhysicsOrHistoryScore(double physicsOrHistoryScore) {
        this.physicsOrHistoryScore = physicsOrHistoryScore;
    }

    public double getChemistryOrGeographyScore() {
        return chemistryOrGeographyScore;
    }

    public void setChemistryOrGeographyScore(double chemistryOrGeographyScore) {
        this.chemistryOrGeographyScore = chemistryOrGeographyScore;
    }

    public double getBiologyOrCivicScore() {
        return biologyOrCivicScore;
    }

    public void setBiologyOrCivicScore(double biologyOrCivicScore) {
        this.biologyOrCivicScore = biologyOrCivicScore;
    }
}
