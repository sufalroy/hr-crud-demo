package io.bitscope.example.hr.controller;

import io.bitscope.example.hr.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private ReportService reportService;

    @GetMapping(value = "report/download")
    public ResponseEntity<byte[]> getReport() throws JRException, IOException {
        byte[] data = reportService.generateReportPdf();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "employees.pdf");

        return new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);
    }
}
