package com.wfc.cellphone.report.service;

import com.wfc.cellphone.report.model.CellPhone;
import com.wfc.cellphone.report.model.CellPhoneUsagebyMonth;
import com.wfc.cellphone.report.util.CsvParser;
import com.wfc.cellphone.report.util.DetailEnum;
import com.wfc.cellphone.report.util.HeaderEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.util.*;

@Service
public class ReportService
{
  public static final String STR_SEPARATOR = ",";
  public static final String LINE_SEPARATOR = "\n";

  private final static String CELL_PHONE_CSV_FILE = "CellPhone.csv";
  private final static String CELL_PHONE_USAGE_BY_MONTH_CSV_FILE = "CellPhoneUsagebyMonth.csv";

  @Autowired
  private ReportServiceHelper reportServiceHelper;

  @Autowired
  private ResourceLoader resourceLoader;

  private List<CellPhone> cellPhones;
  private List<CellPhoneUsagebyMonth> cellPhoneUsagebyMonths;

  @PostConstruct
  public void init() throws Exception {
    cellPhones = getCellPhones();
    cellPhoneUsagebyMonths = getCellPhoneUsagebyMonths();
  }


  public String getHeaderSection(int year){

    StringBuilder headerSectionBuilder = new StringBuilder();
    int totalOfRows = cellPhoneUsagebyMonths.size();
    Integer totalOfMinutes = reportServiceHelper.getTotalOfMinutes(cellPhoneUsagebyMonths, year);
    Double totalOfData = reportServiceHelper.getTotalOfData(cellPhoneUsagebyMonths, year);

    headerSectionBuilder
            .append(HeaderEnum.REPORT_RUN_DATE)
            .append(STR_SEPARATOR)
            .append(HeaderEnum.NUMBER_OR_PHONES)
            .append(STR_SEPARATOR)
            .append(HeaderEnum.TOTAl_MINUTES)
            .append(STR_SEPARATOR)
            .append(HeaderEnum.TOTAL_DATA)
            .append(STR_SEPARATOR)
            .append(HeaderEnum.AVERAGE_MINUTES)
            .append(STR_SEPARATOR)
            .append(HeaderEnum.AVERAGE_DATA)
            .append(LINE_SEPARATOR);

    headerSectionBuilder
            .append(LocalDate.now())
            .append(STR_SEPARATOR)
            .append(reportServiceHelper.getNumberOfPhones(cellPhones))
            .append(STR_SEPARATOR)
            .append(totalOfMinutes)
            .append(STR_SEPARATOR)
            .append(totalOfData)
            .append(STR_SEPARATOR)
            .append((double) totalOfMinutes / (double) totalOfRows)
            .append(STR_SEPARATOR)
            .append(totalOfData / (double) totalOfRows);

    return headerSectionBuilder.toString();

  }

  public String getDetailSection(int year){
    String months[] =  new DateFormatSymbols(Locale.US).getMonths();

    StringBuilder detailSectionBuilder = new StringBuilder();
    detailSectionBuilder
            .append(DetailEnum.EMPLOYEE_ID)
            .append(STR_SEPARATOR)
            .append(DetailEnum.EMPLOYEE_NAME)
            .append(STR_SEPARATOR)
            .append(DetailEnum.MODEL)
            .append(STR_SEPARATOR)
            .append(DetailEnum.PURCHASE_DATE)
            .append(STR_SEPARATOR)
            .append(DetailEnum.MINUTES_USAGE)
            .append(STR_SEPARATOR)
            .append(DetailEnum.DATA_USAGE);
    for (String month: months) {
      if (month.isBlank())
        continue;
      detailSectionBuilder
              .append(STR_SEPARATOR)
              .append(month);
    }

    detailSectionBuilder
            .append(LINE_SEPARATOR);

    for (CellPhone cellPhone : cellPhones) {
      detailSectionBuilder
              .append(cellPhone.getEmployeeId()).append(STR_SEPARATOR)
              .append(cellPhone.getEmployeeName()).append(STR_SEPARATOR)
              .append(cellPhone.getModel()).append(STR_SEPARATOR)
              .append(cellPhone.getPurchaseDate());

      Map<String, Integer> minutesUsagePerEmployeeMap =
              reportServiceHelper.getMinutesUsagePerEmployeeMap(cellPhoneUsagebyMonths, cellPhone.getEmployeeId(), year);

      Map<String, Double> dataUsagePerEmployeeMap =
              reportServiceHelper.getDataUsagePerEmployeeMap(cellPhoneUsagebyMonths, cellPhone.getEmployeeId(), year);

      for (String month: months) {
        if (month.isBlank())
          continue;
        detailSectionBuilder
                .append(STR_SEPARATOR)
                .append(minutesUsagePerEmployeeMap.getOrDefault(month, 0))
                .append("|")
                .append(dataUsagePerEmployeeMap.getOrDefault(month, 0.00));
      }
      detailSectionBuilder.append(LINE_SEPARATOR);
    }
    return detailSectionBuilder.toString();

  }

  private List<CellPhone> getCellPhones ()  {
    List<CellPhone> cellPhones = new ArrayList<>();
    try {
      final File cellPhoneCsvFile = resourceLoader.getResource("classpath:/" + CELL_PHONE_CSV_FILE).getFile();
      cellPhones.addAll(CsvParser.parse(cellPhoneCsvFile, CellPhone.class));

    }
    catch (Exception exception) {
      System.out.println("Errpr found and returning empty List:  " + exception.getMessage());
    }

    return cellPhones;
  }

  private List<CellPhoneUsagebyMonth> getCellPhoneUsagebyMonths ()  {
    List<CellPhoneUsagebyMonth> cellPhoneUsagebyMonths = new ArrayList<>();
    try {
      final File cellPhoneUsagebyMonthsCsvFile =
              resourceLoader.getResource("classpath:/" + CELL_PHONE_USAGE_BY_MONTH_CSV_FILE).getFile();
      cellPhoneUsagebyMonths.addAll(CsvParser.parse(cellPhoneUsagebyMonthsCsvFile, CellPhoneUsagebyMonth.class));
    }
    catch (Exception exception) {
      System.out.println("Errpr found and returning empty List:  " + exception.getMessage());
    }

    return cellPhoneUsagebyMonths;
  }
}
