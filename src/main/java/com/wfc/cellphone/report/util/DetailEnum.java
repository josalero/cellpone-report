package com.wfc.cellphone.report.util;

import java.util.Arrays;

public enum DetailEnum {

  EMPLOYEE_ID("Employee Id"),
  EMPLOYEE_NAME("Employee Name"),
  MODEL("Model"),
  PURCHASE_DATE("Purchase Date"),
  MINUTES_USAGE("Minutes Usage"),
  DATA_USAGE("Data Usage");

  private String value;

  private DetailEnum(String value) {
    this.value = value;
  }

  public static DetailEnum findByFrequency(String header) {
    return Arrays.stream(DetailEnum.values())
            .filter(d -> d.toString().equalsIgnoreCase(header))
            .findAny()
            .orElse(null);
  }

  @Override
  public String toString() {
    return value;
  }
}
