package com.miw.databeestjes.crittr.model;

/**
 * <p>
 * This class describes the categories that educational info can belong to
 */

public enum EduInfoCategory {
    LATIN_NAME ("Latin name: "),
    CLASS("Class: "),
    LIFE_EXPECTANCY("Average Life Expectancy: "),
    HEIGHT("Average Height: "),
    DIET("Diet: ");

    private String displayName;

    EduInfoCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
