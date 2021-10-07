package com.wfc.cellphone.report.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.wfc.cellphone.report.converter.CellPhoneUsageDateConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CellPhoneUsagebyMonth {
  @CsvBindByName(column = "emplyeeId")
  private Long employeeId;

  @CsvCustomBindByName(
   converter = CellPhoneUsageDateConverter.class
  )
  private LocalDate date;

  @CsvBindByName(
   column = "totalMinutes"
  )
  private Integer totalMinutes;

  @CsvBindByName(
   column = "totalData"
  )
  private Double totalData;
}
