package com.kamiroo.todomanager;

public enum PriorityEnum {
    LOW("Low"),
    NORMAL("Normal"),
    HIGH("High");



    private String displayName;

    PriorityEnum(String displayName) {
        this.displayName = displayName;
    }
}
