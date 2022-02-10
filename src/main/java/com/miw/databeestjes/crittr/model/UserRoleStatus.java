package com.miw.databeestjes.crittr.model;

public enum UserRoleStatus {
    ROLE_MEMBER("Member"),
    ROLE_CARETAKER("Caretaker");

    private String displayName;

    UserRoleStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
