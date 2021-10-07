package com.wfc.cellphone.report.model;


import java.io.File;
import java.nio.file.Path;

public abstract class BaseTest {

  protected File getCsvFromFile(String filename) throws Exception {
    return Path.of(this.getClass().getClassLoader().getResource(filename).toURI()).toFile();
  }
}
