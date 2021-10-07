package com.wfc.cellphone.report.converter;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class CellPhoneUsageDateConverter extends AbstractBeanField<String> {

  private DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();

  public CellPhoneUsageDateConverter() {
    dateTimeFormatterBuilder.appendOptional(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("MM/d/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("M/d/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("M/dd/yyyy"))
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0);

  }
  /**
   *
   * @param value
   * @return
   * @throws CsvDataTypeMismatchException
   * @throws CsvConstraintViolationException
   */
  @Override
  protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
    return LocalDate.parse(value, dateTimeFormatterBuilder.toFormatter());
  }
}
