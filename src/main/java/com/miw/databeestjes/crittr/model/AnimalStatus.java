package com.miw.databeestjes.crittr.model;

/**
 * <p>
 * This class describes the states an animal can be in
 */

public enum AnimalStatus {
    INCOMING("Incoming"),
    PRESENT("Present"),
    RELOCATED("Relocated"),
    DECEASED("Deceased");

    private String displayName;

    AnimalStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
