package com.wfc.cellphone.report.converter;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CellPhoneDateConverter extends AbstractBeanField<String> {

  //source: 20150101
  private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
  /**
   *
   * @param value
   * @return
   * @throws CsvDataTypeMismatchException
   * @throws CsvConstraintViolationException
   */
  @Override
  protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {

    return LocalDate.parse(value, formatter);
  }
}
