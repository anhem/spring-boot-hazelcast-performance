package com.github.anhem.springboothazelcastperformance.model.java;

import java.io.Serializable;

public class Id implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Integer value;

    public Id(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
