package com.miw.databeestjes.crittr.model;

/**
 * <p>
 * This class describes the roles a user can have.
 */

public enum UserRoleStatus {
    ROLE_MEMBER("Member"),
    ROLE_CARETAKER("Caretaker"),
    ROLE_ADMIN("Admin");

    private String displayName;

    UserRoleStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
