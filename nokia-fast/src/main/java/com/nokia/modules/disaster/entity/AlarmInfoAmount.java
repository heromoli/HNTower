package com.nokia.modules.disaster.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AlarmInfoAmount implements Serializable {
    private String item;

    private int amount;

    private double amountRate;

}
