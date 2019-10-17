package com.nokia.modules.resourceManage.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class VennEntity implements Serializable {
    private String name;

    private String[] sets;

    private int value;

}
