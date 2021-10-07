package com.wfc.cellphone.report;

import com.wfc.cellphone.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.print.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@SpringBootApplication
public class CellphoneReportApplication implements CommandLineRunner {

	@Autowired
	private ReportService reportService;

	public static void main(String[] args) {
		SpringApplication.run(CellphoneReportApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		int year = 2018;

		// Build Report
		String reportByYear = getReportByYear(year);
		System.out.println(reportByYear);

		// Print Report
		printReport(reportByYear);
	}

	private void printReport(String fullReport) throws PrintException {
		PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
		InputStream inputStream = new ByteArrayInputStream(fullReport.getBytes());
		DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
		Doc doc = new SimpleDoc(inputStream, flavor, null);
		defaultPrintService.createPrintJob().print(doc, null);
	}

	private String getReportByYear(int year) {
		return reportService.getHeaderSection(year) + "\n\n" + reportService.getDetailSection(year);
	}
}
