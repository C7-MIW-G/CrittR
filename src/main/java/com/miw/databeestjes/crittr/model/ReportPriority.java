package com.miw.databeestjes.crittr.model;

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
