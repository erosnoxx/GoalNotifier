package com.erosnox.seeurun.domain.entities.common;

public abstract class BaseEntity<T> {
    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
