package com.kamiroo.todomanager;

public enum PriorityEnum {
    HIGH("High"),
    NORMAL("Normal"),
    LOW("Low");

    private String displayName;

    PriorityEnum(String displayName) {
        this.displayName = displayName;
    }
}
