package com.wfc.cellphone.report.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class ReportServiceTest {

  private final int YEAR = 2018;

  @TestConfiguration
  static class EmployeeServiceImplTestContextConfiguration {
    @Bean
    public ReportService reportService() {
      return new ReportService();
    }

    @Bean
    public ReportServiceHelper reportServiceHelper() {
      return new ReportServiceHelper();
    }
  }

  @Autowired
  private ReportService reportService;

  @Test
  public void testHeaderSection() throws Exception{
    System.out.println(reportService.getHeaderSection(YEAR));
   assertNotNull(reportService.getHeaderSection(YEAR));
  }

  @Test
  public void testDetailSection() throws Exception{
    System.out.println(reportService.getDetailSection(YEAR));
    assertNotNull(reportService.getDetailSection(YEAR));
  }

}
