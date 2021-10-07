package com.wfc.cellphone.report.util;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.util.List;

public class CsvParser {

  public static <T> List<T> parse(File csvFile, Class<T> clazz) {
    List<T> result = null;
    try{
      Reader reader = new FileReader(csvFile);
      CsvToBean<T> csvToBean = (new CsvToBeanBuilder(reader)).withType(clazz).withIgnoreLeadingWhiteSpace(true).build();
      result = csvToBean.parse();
    }
    catch (Exception exception){
      System.out.println(exception.getMessage());
    }
    return result;
  }

  public static File getCsvFromFile(String filename) throws Exception {

    return Path.of(CsvParser.class.getResource(filename).getFile()).toFile();
  }
}
