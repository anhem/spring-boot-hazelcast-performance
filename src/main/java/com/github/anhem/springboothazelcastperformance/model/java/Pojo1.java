package com.github.anhem.springboothazelcastperformance.model.java;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Pojo1 implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Id id;
    private final String string;
    private final int anInt;
    private final List<Long> longList;
    private final Map<String, String> stringToStringMap;
    private final Map<Id, Pojo2> idToPojo2Map;

    public Pojo1(Id id, String string, int anInt, List<Long> longList, Map<String, String> stringToStringMap, Map<Id, Pojo2> idToPojo2Map) {
        this.id = id;
        this.string = string;
        this.anInt = anInt;
        this.longList = longList;
        this.stringToStringMap = stringToStringMap;
        this.idToPojo2Map = idToPojo2Map;
    }

    public Id getId() {
        return id;
    }

    public String getString() {
        return string;
    }

    public int getAnInt() {
        return anInt;
    }

    public List<Long> getLongList() {
        return longList;
    }

    public Map<String, String> getStringToStringMap() {
        return stringToStringMap;
    }

    public Map<Id, Pojo2> getIdToPojo2Map() {
        return idToPojo2Map;
    }
}
