package com.wfc.cellphone.report.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.wfc.cellphone.report.converter.CellPhoneDateConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CellPhone {
  @CsvBindByName(
   column = "employeeId"
  )
  private Long employeeId;
  @CsvBindByName(
   column = "employeeName"
  )
  private String employeeName;
  @CsvCustomBindByName(
   converter = CellPhoneDateConverter.class
  )
  private LocalDate purchaseDate;
  @CsvBindByName(
   column = "model"
  )
  private String model;
}
