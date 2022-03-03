package com.miw.databeestjes.crittr.model;

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
