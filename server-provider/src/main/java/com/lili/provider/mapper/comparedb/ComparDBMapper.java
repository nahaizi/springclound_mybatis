package com.lili.provider.mapper.comparedb;

import java.util.List;

public interface ComparDBMapper {
    public int insertOracle(List<String> paramlist);

    public int insertClickhouse(List<String> paramlist);
}
