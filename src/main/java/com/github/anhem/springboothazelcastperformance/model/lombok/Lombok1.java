package com.github.anhem.springboothazelcastperformance.model.lombok;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Value
@Builder
public class Lombok1 implements Serializable {

 private static final long serialVersionUID = 1L;

 LombokId lombokId;
 String string;
 int anInt;
 List<Long> longList;
 Map<String, String> stringToStringMap;
 Map<LombokId, Lombok2> idToPojo2Map;


}
