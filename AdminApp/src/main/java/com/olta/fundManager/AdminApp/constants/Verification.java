package com.olta.fundManager.AdminApp.constants;

public enum Verification {
    COMPLETE("Complete"),
    PENDING("Pending"),
    NOTSTARTED("Not Started"),
    INPROGRESS("In Progress");

    private final String value;

    private Verification(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
