package com.monmar.personalbudget.entity;

public enum OperationType {
    INCOME(1), EXPENDITURE(-1);

    private final int operationMultiplier;

    private OperationType(int operationMultiplier){
        this.operationMultiplier = operationMultiplier;
    }
}
