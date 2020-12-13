package com.github.anhem.springboothazelcastperformance.model.lombok;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value
@Builder
public class Lombok2 implements Serializable {

    private static final long serialVersionUID = 1L;

    LombokId lombokId;
    String string;

}
