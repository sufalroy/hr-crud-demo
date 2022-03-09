package io.bitscope.example.hr.service;

import io.bitscope.example.hr.model.Employee;
import io.bitscope.example.hr.repository.EmployeeRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    public ReportServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public String generateReport() throws JRException, IOException {
        List<Employee> employeeCollection = employeeRepository.findAll();
        String resourceLocation = "classpath:Employees.jrxml";
        JasperPrint jasperPrint = getJasperPrint(employeeCollection, resourceLocation);
        String fileName = "/" + "employees.pdf";
        Path uploadPath = getUploadPath(jasperPrint, fileName);

        return getPdfFileLink(uploadPath.toString());
    }

    private String getPdfFileLink(String uploadPath) {
        return uploadPath+"/"+"employees.pdf";
    }

    private Path getUploadPath(JasperPrint jasperPrint, String fileName) throws IOException, JRException {
        String uploadDir = StringUtils.cleanPath("./generated-reports");
        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        JasperExportManager.exportReportToPdfFile(jasperPrint, uploadPath+fileName);

        return uploadPath;
    }

    private JasperPrint getJasperPrint(List<Employee> employeeCollection, String resourceLocation) throws FileNotFoundException, JRException {
        File file = ResourceUtils.getFile(resourceLocation);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employeeCollection);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("role", "Admin");
        parameters.put("employees", dataSource);

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,new JREmptyDataSource());

        return jasperPrint;
    }
}
