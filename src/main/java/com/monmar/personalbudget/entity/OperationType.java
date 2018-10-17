package com.monmar.personalbudget.entity;

public enum OperationType {
    INCOME(1), EXPENDITURE(-1);

    private final Integer operationMultiplier;

    private OperationType(Integer operationMultiplier){
        this.operationMultiplier = operationMultiplier;
    }

    public Integer getOperationMultiplier() {
        return operationMultiplier;
    }
}
