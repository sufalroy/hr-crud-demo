package io.bitscope.example.hr.service;

import net.sf.jasperreports.engine.JRException;

import java.io.IOException;

public interface ReportService {

    byte[] generateReportPdf() throws JRException, IOException;
}
