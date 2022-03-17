package com.miw.databeestjes.crittr.model;

/**
 * <p>
 * This class describes the states a report can be in.
 */

public enum ReportStatus {
    NEW("New"),
    OPEN_ISSUE("Open Issue"),
    DISCARDED("Discarded"),
    UNDER_OBSERVATION("Under Observation"),
    CLOSED("Closed");

    private String displayName;

    ReportStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

}


