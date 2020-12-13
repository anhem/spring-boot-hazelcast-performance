package com.github.anhem.springboothazelcastperformance.model.lombok;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Value
@AllArgsConstructor
public class LombokId implements Serializable {

    private static final long serialVersionUID = 1L;

    Integer value;

}
