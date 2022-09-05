package com.example.mapper;

import com.example.model.CsvInformation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CsvTransformMapper {
    boolean insertInformation(@Param("csvList") List<CsvInformation> csvInformations);
}
