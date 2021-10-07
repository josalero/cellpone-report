package com.wfc.cellphone.report.model;

import com.wfc.cellphone.report.util.CsvParser;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class CellPhoneTest extends BaseTest {

  private final String CSV_FILE = "CellPhone.csv";

  @Test
  public void testLoad() throws Exception{
    List<CellPhone> cellPhoneList = CsvParser.parse(getCsvFromFile(CSV_FILE), CellPhone.class);

    cellPhoneList.stream().forEach(value -> assertNotNull(value));

  }
}
