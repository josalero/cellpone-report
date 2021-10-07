package com.wfc.cellphone.report.model;

import com.wfc.cellphone.report.util.CsvParser;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class CellPhoneIUsageByMonthTest extends BaseTest {

  private final String CSV_FILE = "CellPhoneUsagebyMonth.csv";


  @Test
  public void testLoad() throws Exception{
    List<CellPhoneUsagebyMonth> cellPhoneList = CsvParser.parse(getCsvFromFile(CSV_FILE), CellPhoneUsagebyMonth.class);

    cellPhoneList.stream().forEach(value -> assertNotNull(value));

  }

}
