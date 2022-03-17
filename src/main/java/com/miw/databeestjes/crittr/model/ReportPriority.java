package com.miw.databeestjes.crittr.model;

/**
 * <p>
 * This class describes the priorities a report can have.
 */

public enum ReportPriority {
    LOW("Normal"),
    MEDIUM("Serious"),
    HIGH("Critical");

    private String displayName;

    ReportPriority(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
