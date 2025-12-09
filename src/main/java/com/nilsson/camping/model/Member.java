package com.nilsson.camping.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Member {
    private int id;
    private String firstName;
    private String lastName;
    private String membershipLevel;
    private List<String> history;

    public Member() {

    }

    public Member(int id, String firstName, String lastName, String membershipLevel, List history) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.membershipLevel = membershipLevel;
        this.history = history;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }

    // Checks if the member has a Student membership-level.
    @JsonIgnore
    public boolean isStudent() {
        return "Student".equalsIgnoreCase(this.membershipLevel);
    }

    // Checks if the member has a Premium membership-level.
    @JsonIgnore
    public boolean isPremium() {
        return "Premium".equalsIgnoreCase(this.membershipLevel);
    }
}
