package com.lili.es.entity;

import lombok.Data;

@Data
public class ParamDto {
    private String indexName;
    private String searchValue;
    private String searchFiled;
    private String[] searchFileds;
}
