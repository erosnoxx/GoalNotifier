package com.erosnox.seeurun.application.enums;

public enum GoalStatus {
    PENDING,  // Corrigi a ortografia de 'PEDING' para 'PENDING'
    SUCCESS,
    FAILED;

    public boolean isPending() {
        return this == PENDING;
    }
}
