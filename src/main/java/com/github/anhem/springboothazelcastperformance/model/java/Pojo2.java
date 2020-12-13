package com.github.anhem.springboothazelcastperformance.model.java;

import java.io.Serializable;

public class Pojo2 implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Id id;
    private final String string;

    public Pojo2(Id id, String string) {
        this.id = id;
        this.string = string;
    }

    public Id getId() {
        return id;
    }

    public String getString() {
        return string;
    }
}
