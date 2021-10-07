package com.wfc.cellphone.report.service;

import com.wfc.cellphone.report.model.CellPhone;
import com.wfc.cellphone.report.model.CellPhoneUsagebyMonth;
import org.springframework.stereotype.Component;

import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ReportServiceHelper {

  public Integer getNumberOfPhones(List<CellPhone> cellPhones) {
    return cellPhones.stream().map(CellPhone::getModel).collect(Collectors.toSet()).size();
  }

  public Integer getTotalOfMinutes(List<CellPhoneUsagebyMonth> cellPhoneUsagebyMonths, int year) {
    return cellPhoneUsagebyMonths.stream()
            .filter(value -> value.getDate().getYear() == year)
            .map(CellPhoneUsagebyMonth::getTotalMinutes).reduce(0, (a, b) -> a + b );
  }

  public Double getTotalOfData(List<CellPhoneUsagebyMonth> cellPhoneUsagebyMonths, int year) {
    return cellPhoneUsagebyMonths.stream()
            .filter(value -> value.getDate().getYear() == year)
            .map(CellPhoneUsagebyMonth::getTotalData).reduce(0d, (a, b) -> a + b );
  }

  public Map<String, Integer> getMinutesUsagePerEmployeeMap(List<CellPhoneUsagebyMonth> cellPhoneUsagebyMonths,
                                                                         Long employeeId, int year) {
    Map<String, Integer> minutesUsagePerEmployeeMap  =
            cellPhoneUsagebyMonths.stream()
                    .filter(entry -> entry.getEmployeeId().equals(employeeId))
                    .filter(value -> value.getDate().getYear() == year)
                    .collect(Collectors.toMap(key -> key.getDate().getMonth().getDisplayName(TextStyle.FULL, Locale.US),
                        s1 -> s1.getTotalMinutes(),
                        (s1, s2) -> s1 + s2));

    return  minutesUsagePerEmployeeMap;
  }

  public Map<String, Double> getDataUsagePerEmployeeMap(List<CellPhoneUsagebyMonth> cellPhoneUsagebyMonths,
                                                            Long employeeId, int year) {
    Map<String, Double> dataUsagePerEmployeeMap  =
            cellPhoneUsagebyMonths.stream()
                    .filter(entry -> entry.getEmployeeId().equals(employeeId))
                    .filter(value -> value.getDate().getYear() == year)
                    .collect(Collectors.toMap(key -> key.getDate().getMonth().getDisplayName(TextStyle.FULL, Locale.US),
                            s1 -> s1.getTotalData(),
                            (s1, s2) -> s1 + s2));

    return  dataUsagePerEmployeeMap;
  }

}
