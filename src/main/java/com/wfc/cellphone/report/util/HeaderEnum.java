package com.wfc.cellphone.report.util;

import java.util.Arrays;

public enum HeaderEnum {
  REPORT_RUN_DATE("Report Run Date"),
  NUMBER_OR_PHONES("Number of Phones"),
  TOTAl_MINUTES("Total Minutes"),
  TOTAL_DATA("Total Data"),
  AVERAGE_MINUTES("Average Minutes"),
  AVERAGE_DATA("Average Data");

  private String value;

  private HeaderEnum(String value) {
    this.value = value;
  }

  public static HeaderEnum findByFrequency(String header) {
    return Arrays.stream(HeaderEnum.values())
            .filter(d -> d.toString().equalsIgnoreCase(header))
            .findAny()
            .orElse(null);
  }

  @Override
  public String toString() {
    return value;
  }
}
