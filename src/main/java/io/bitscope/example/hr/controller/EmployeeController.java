package io.bitscope.example.hr.controller;

import io.bitscope.example.hr.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    private ReportService reportService;

    @GetMapping(value = "report")
    public String getReport() throws JRException, IOException {
      String fileLink = reportService.generateReport();
      return "redirect:/"+fileLink;
    }
}
